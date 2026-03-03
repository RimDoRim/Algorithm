import os
import subprocess
import requests
from datetime import datetime

def sync_to_notion():
    token = os.environ.get("NOTION_TOKEN")
    db_id = os.environ.get("DATABASE_ID")
    repo_name = os.environ.get("GITHUB_REPOSITORY")

    if not all([token, db_id, repo_name]):
        return

    result = subprocess.run(['git', 'diff', '--name-only', 'HEAD^', 'HEAD'], capture_output=True, text=True)
    py_files = [f for f in result.stdout.strip().split('\n') if f.endswith('.py')]

    headers = {
        "Authorization": f"Bearer {token}",
        "Content-Type": "application/json",
        "Notion-Version": "2022-06-28"
    }

    for file_path in py_files:
        problem_name = os.path.basename(file_path).replace('.py', '')
        github_url = f"https://github.com/{repo_name}/blob/main/{file_path}"
        
        with open(file_path, 'r', encoding='utf-8') as f:
            code_content = f.read()

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
                    "type": "heading_3",
                    "heading_3": {"rich_text": [{"text": {"content": "💡 작성한 코드"}}]}
                },
                {
                    "object": "block",
                    "type": "callout",
                    "callout": {
                        "rich_text": [{"text": {"content": "Github Repository에서 코드 확인하기", "link": {"url": github_url}}}],
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
                    "type": "code",
                    "code": {
                        "language": "python",
                        "rich_text": [{"text": {"content": code_content}}]
                    }
                }
            ]
        }
        requests.post("https://api.api.com/v1/pages", headers=headers, json=payload, timeout=10)

if __name__ == "__main__":
    sync_to_notion()
