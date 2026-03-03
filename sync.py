import os
import subprocess
import requests
from datetime import datetime

def sync_to_notion():
    token = os.environ.get("NOTION_TOKEN")
    db_id = os.environ.get("DATABASE_ID")
    repo_name = os.environ.get("GITHUB_REPOSITORY")

    if not all([token, db_id, repo_name]):
        print("필수 환경 변수가 없습니다.")
        return

    # 방금 푸시된 파이썬/자바 파일 찾기 (기존은 .py만 했지만 .java도 포함되도록 수정)
    result = subprocess.run(['git', 'diff', '--name-only', 'HEAD^', 'HEAD'], capture_output=True, text=True)
    changed_files = [f for f in result.stdout.strip().split('\n') if f.endswith('.py') or f.endswith('.java')]

    headers = {
        "Authorization": f"Bearer {token}",
        "Content-Type": "application/json",
        "Notion-Version": "2022-06-28"
    }

    for file_path in changed_files:
        problem_name = os.path.basename(file_path).split('.')[0]
        github_url = f"https://github.com/{repo_name}/blob/main/{file_path}"
        
        # 확장자에 따라 언어 설정
        code_lang = "python" if file_path.endswith('.py') else "java"
        
        with open(file_path, 'r', encoding='utf-8') as f:
            code_content = f.read()

        # 분석한 템플릿 구조대로 payload 작성
        payload = {
            "parent": {"database_id": db_id},
            "properties": {
                "이름": {"title": [{"text": {"content": problem_name}}]},
                "URL": {"url": github_url},
                "날짜": {"date": {"start": datetime.now().strftime("%Y-%m-%d")}}
            },
            "children": [
                # 1. 문제 이름 콜아웃
                {
                    "object": "block",
                    "type": "callout",
                    "callout": {
                        "rich_text": [{"text": {"content": problem_name}}],
                        "icon": {"type": "emoji", "emoji": "📝"}
                    }
                },
                # 2. 문제 링크 콜아웃 (임시로 Github 링크 연결, 실제 백준 링크가 있다면 교체 가능)
                {
                    "object": "block",
                    "type": "callout",
                    "callout": {
                        "rich_text": [{"text": {"content": "문제 링크 (GitHub 코드 보기)", "link": {"url": github_url}}}],
                        "icon": {"type": "emoji", "emoji": "🔗"}
                    }
                },
                # 3. 성능 정보 콜아웃 (백준허브에서는 성능 정보를 커밋 메시지나 주석에 담으므로 임시 텍스트 추가)
                {
                    "object": "block",
                    "type": "callout",
                    "callout": {
                        "rich_text": [{"text": {"content": "⏱️ 성능: (백준허브 자동 기록)"}}],
                        "icon": {"type": "emoji", "emoji": "⏱️"}
                    }
                },
                # 4. 구분선
                {
                    "object": "block",
                    "type": "divider",
                    "divider": {}
                },
                # 5. 토글 안에 코드 넣기
                {
                    "object": "block",
                    "type": "toggle",
                    "toggle": {
                        "rich_text": [{"text": {"content": "답안"}}],
                        "children": [
                            # 토글 내부의 실제 코드 블록
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
        
        response = requests.post("https://api.notion.com/v1/pages", headers=headers, json=payload, timeout=10)
        if response.status_code == 200:
            print(f"성공: {problem_name}")
        else:
            print(f"실패: {response.text}")

if __name__ == "__main__":
    sync_to_notion()
