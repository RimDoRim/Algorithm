import os
import subprocess
import requests
from datetime import datetime

def sync_to_notion():
    token = os.environ.get("NOTION_TOKEN")
    db_id = os.environ.get("DATABASE_ID")
    repo_name = os.environ.get("GITHUB_REPOSITORY")

    if not all([token, db_id, repo_name]):
        print("에러: 환경 변수(토큰, DB ID, Repo)가 없습니다.")
        return

    print("변경된 파일을 찾습니다...")
    # git diff 대신 가장 확실한 git show 명령어로 방금 커밋된 파일 목록만 가져옵니다!
    result = subprocess.run(['git', 'show', '--name-only', '--format=', 'HEAD'], capture_output=True, text=True)
    
    # 디버깅을 위해 콘솔에 출력
    print(f"Git 명령 결과:\n{result.stdout}")
    
    changed_files = [f.strip() for f in result.stdout.strip().split('\n') if f.endswith('.py') or f.endswith('.java')]

    if not changed_files:
        print("이번 커밋에 추가되거나 수정된 .py / .java 파일이 없습니다.")
        return

    headers = {
        "Authorization": f"Bearer {token}",
        "Content-Type": "application/json",
        "Notion-Version": "2022-06-28"
    }

    for file_path in changed_files:
        problem_name = os.path.basename(file_path).split('.')[0]
        github_url = f"https://github.com/{repo_name}/blob/main/{file_path}"
        code_lang = "python" if file_path.endswith('.py') else "java"
        
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                code_content = f.read()
        except FileNotFoundError:
            print(f"에러: {file_path} 파일을 찾을 수 없습니다. (삭제된 파일일 수 있음)")
            continue

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
                        "rich_text": [{"text": {"content": "문제 링크 (GitHub 코드 보기)", "link": {"url": github_url}}}],
                        "icon": {"type": "emoji", "emoji": "🔗"}
                    }
                },
                {
                    "object": "block",
                    "type": "callout",
                    "callout": {
                        "rich_text": [{"text": {"content": "⏱️ 성능: (백준허브 기록 확인)"}}],
                        "icon": {"type": "emoji", "emoji": "⏱️"}
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
                        "rich_text": [{"text": {"content": "답안"}}],
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
