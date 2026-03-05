import os
import subprocess
import requests
import urllib.parse
from datetime import datetime

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
        
        # 1. 소스 코드 파일 읽기
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                code_content = f.read()
        except FileNotFoundError:
            continue

        # 2. README.md (문제 설명) 파일 읽기
        readme_path = os.path.join(folder_path, "README.md")
        readme_content = ""
        try:
            with open(readme_path, 'r', encoding='utf-8') as f:
                # 노션 텍스트 한도(2000자)를 넘지 않도록 자르기
                readme_content = f.read()[:2000] 
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
                # ▼ 추가된 부분: 문제 설명(README)을 접은 상태(토글)로 깔끔하게 넣기 ▼
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
