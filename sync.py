import os
import subprocess
import requests
import urllib.parse
import re  
from datetime import datetime

# HTML 태그를 깔끔하게 제거하는 함수 추가
def clean_html(raw_text):
    # 1. <li> (리스트 항목) 태그를 마크다운 글머리 기호(•)로 변경
    text = re.sub(r'<li>', '• ', raw_text)
    text = re.sub(r'</li>', '\n', text)
    
    # 2. <tr> (표의 행) 태그가 끝날 때 줄바꿈
    text = re.sub(r'</tr>', '\n', text)
    
    # 3. <td>, <th> (표의 열) 태그를 탭(간격) 기호와 세로선(|)으로 분리
    text = re.sub(r'<td[^>]*>', ' | ', text)
    text = re.sub(r'<th[^>]*>', ' | ', text)
    text = re.sub(r'</td>', ' ', text)
    text = re.sub(r'</th>', ' ', text)
    
    # 4. 헤딩 태그 (<h5>, <h3> 등)를 깔끔한 노션 텍스트 제목으로 변경
    text = re.sub(r'<h[1-6][^>]*>', '\n\n📌 ', text)
    text = re.sub(r'</h[1-6]>', '\n', text)
    
    # 5. <br> 태그를 줄바꿈으로 변경
    text = re.sub(r'<br\s*/?>', '\n', text)
    
    # 6. 나머지 모든 HTML 껍데기 태그 삭제 (p, div, ul, code 등)
    text = re.sub(r'<.*?>', '', text)
    
    # 7. HTML 특수문자 원상복구
    text = text.replace('&lt;', '<').replace('&gt;', '>').replace('&amp;', '&').replace('&quot;', '"')
    
    # 8. 쓸데없이 연속된 빈 줄 제거
    text = re.sub(r'\n{3,}', '\n\n', text)
    
    return text.strip()

def sync_to_notion():
    token = os.environ.get("NOTION_TOKEN")
    db_id = os.environ.get("DATABASE_ID")
    repo_name = os.environ.get("GITHUB_REPOSITORY")

    if not all([token, db_id, repo_name]):
        print("에러: 환경 변수(토큰, DB ID, Repo)가 없습니다.")
        return

    result = subprocess.run(['git', 'show', '--name-only', '--format=', 'HEAD'], capture_output=True, text=True)
    changed_files = [f.strip() for f in result.stdout.strip().split('\n') if f.endswith('.py') or f.endswith('.java')]

    if not changed_files:
        print("이번 커밋에 추가되거나 수정된 소스 코드 파일이 없습니다.")
        return

    headers = {
        "Authorization": f"Bearer {token}",
        "Content-Type": "application/json",
        "Notion-Version": "2022-06-28"
    }

    for file_path in changed_files:
        problem_name = os.path.basename(file_path).split('.')[0]
        folder_path = os.path.dirname(file_path)
        encoded_folder_path = urllib.parse.quote(folder_path)
        github_url = f"https://github.com/{repo_name}/tree/main/{encoded_folder_path}"
        code_lang = "python" if file_path.endswith('.py') else "java"
        
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                code_content = f.read()
        except FileNotFoundError:
            continue

        readme_path = os.path.join(folder_path, "README.md")
        readme_content = ""
        try:
            with open(readme_path, 'r', encoding='utf-8') as f:
                raw_readme = f.read()
                # ★ 여기서 HTML 태그를 모두 제거합니다!
                readme_content = clean_html(raw_readme)
                
                # 노션 텍스트 블록 한도(2000자)에 맞춰 자르기
                if len(readme_content) > 2000:
                    readme_content = readme_content[:1997] + "..."
        except FileNotFoundError:
            readme_content = "문제 설명(README.md) 파일을 찾을 수 없습니다."

        payload = {
            "parent": {"database_id": db_id},
            "properties": {
                "이름": {"title": [{"text": {"content": problem_name}}]},
                "URL": {"url": github_url},
                "날짜": {"date": {"start": datetime.now().strftime("%Y-%m-%d")}}
            },
            "children": [
                {
                    "object": "block",
                    "type": "callout",
                    "callout": {
                        "rich_text": [{"text": {"content": problem_name}}],
                        "icon": {"type": "emoji", "emoji": "📝"}
                    }
                },
                {
                    "object": "block",
                    "type": "callout",
                    "callout": {
                        "rich_text": [{"text": {"content": "문제 링크 (GitHub 폴더 보기)", "link": {"url": github_url}}}],
                        "icon": {"type": "emoji", "emoji": "🔗"}
                    }
                },
                {
                    "object": "block",
                    "type": "divider",
                    "divider": {}
                },
                {
                    "object": "block",
                    "type": "toggle",
                    "toggle": {
                        "rich_text": [{"text": {"content": "📄 문제 설명 보기"}}],
                        "children": [
                            {
                                "object": "block",
                                "type": "paragraph",
                                "paragraph": {
                                    "rich_text": [{"text": {"content": readme_content}}]
                                }
                            }
                        ]
                    }
                },
                {
                    "object": "block",
                    "type": "toggle",
                    "toggle": {
                        "rich_text": [{"text": {"content": "💻 내 답안 코드 보기"}}],
                        "children": [
                            {
                                "object": "block",
                                "type": "code",
                                "code": {
                                    "language": code_lang,
                                    "rich_text": [{"text": {"content": code_content}}]
                                }
                            }
                        ]
                    }
                }
            ]
        }
        
        print(f"노션 API 전송 중: {problem_name}...")
        response = requests.post("https://api.notion.com/v1/pages", headers=headers, json=payload, timeout=10)
        
        if response.status_code == 200:
            print(f"✅ 성공: {problem_name} 노션 업로드 완료!")
        else:
            print(f"❌ 실패 ({problem_name}): {response.status_code} - {response.text}")

if __name__ == "__main__":
    sync_to_notion()
