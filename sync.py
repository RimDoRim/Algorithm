import os
import subprocess
from datetime import datetime
import requests
from requests.exceptions import RequestException

NOTION_API_URL = "https://api.notion.com/v1/pages"
NOTION_VERSION = "2022-06-28"

def get_modified_python_files() -> list[str]:
    """최근 커밋에서 수정된 파이썬 파일 목록을 반환합니다."""
    try:
        result = subprocess.run(
            ['git', 'diff', '--name-only', 'HEAD^', 'HEAD'],
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            text=True,
            check=True
        )
        return [f for f in result.stdout.strip().split('\n') if f.endswith('.py')]
    except subprocess.CalledProcessError as e:
        print(f"Git diff execution failed: {e.stderr.strip()}")
        return []

def read_file_content(file_path: str) -> str:
    """파일의 내용을 읽어 반환합니다."""
    with open(file_path, 'r', encoding='utf-8') as f:
        return f.read()

def create_notion_payload(db_id: str, problem_name: str, github_url: str, code: str) -> dict:
    """노션 API 요청에 사용할 Payload를 생성합니다."""
    return {
        "parent": {"database_id": db_id},
        "properties": {
            "이름": {"title": [{"text": {"content": problem_name}}]},
            "URL": {"url": github_url},
            "날짜": {"date": {"start": datetime.now().strftime("%Y-%m-%d")}}
        },
        "children": [{
            "object": "block",
            "type": "code",
            "code": {
                "language": "python",
                "rich_text": [{"text": {"content": code}}]
            }
        }]
    }

def sync_to_notion():
    """변경된 파이썬 파일들을 노션 데이터베이스와 동기화합니다."""
    token = os.environ.get("NOTION_TOKEN")
    db_id = os.environ.get("DATABASE_ID")
    repo_name = os.environ.get("GITHUB_REPOSITORY")

    if not all([token, db_id, repo_name]):
        raise ValueError("Required environment variables are missing.")

    py_files = get_modified_python_files()
    if not py_files:
        print("No python files modified in the latest commit.")
        return

    headers = {
        "Authorization": f"Bearer {token}",
        "Content-Type": "application/json",
        "Notion-Version": NOTION_VERSION
    }

    with requests.Session() as session:
        for file_path in py_files:
            problem_name = os.path.basename(file_path).removesuffix('.py')
            github_url = f"https://github.com/{repo_name}/blob/main/{file_path}"
            
            try:
                code_content = read_file_content(file_path)
                payload = create_notion_payload(db_id, problem_name, github_url, code_content)
                
                response = session.post(NOTION_API_URL, headers=headers, json=payload, timeout=10)
                response.raise_for_status()
                
                print(f"Successfully synced: {problem_name}")
                
            except RequestException as e:
                print(f"API request failed for {problem_name}: {e}")
            except Exception as e:
                print(f"Unexpected error processing {problem_name}: {e}")

if __name__ == "__main__":
    sync_to_notion()
