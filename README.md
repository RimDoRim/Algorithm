# Algorithm
This is an auto push repository for Baekjoon Online Judge created with [BaekjoonHub](https://github.com/BaekjoonHub/BaekjoonHub).

============================= 노션에 자동으로 올리는 법 ================================

# 🚀 GitHub to Notion Auto Sync (알고리즘 문제 풀이 자동 기록기)

이 프로젝트는 GitHub에 알고리즘 문제 풀이 코드와 설명을 커밋하면, 지정된 Notion 데이터베이스로 자동으로 업로드(동기화) 해주는 Python 스크립트입니다. 

매번 노션에 들어가서 코드를 복사하고 붙여넣는 번거로움을 줄이고, 오직 문제 풀이에만 집중하세요!

## ✨ 주요 기능
- 커밋 시 추가/수정된 코드 파일(`.py`, `.java`)을 감지합니다.
- 같은 폴더에 있는 `README.md` 파일의 내용(문제 설명)을 깔끔하게 파싱하여 노션에 기록합니다.
- 노션 페이지 내에 문제 설명(토글), 내 코드(코드 블록), GitHub 링크를 자동으로 생성합니다.

---

## 🛠 1. 환경 설정 (Prerequisites)

이 스크립트를 사용하려면 먼저 Notion API 토큰과 데이터베이스 ID가 필요합니다.

### A. 노션(Notion) 설정
1. [Notion API 페이지](https://www.notion.so/my-integrations)에 접속하여 새 API 통합(Integration) 을 생성하고, `프라이빗 API 토큰`을 복사합니다.
2. 알고리즘 풀이를 기록할 새 데이터베이스(표 형태) 를 만듭니다.
3. 데이터베이스 우측 상단 `...` 메뉴 ➡️ `연결` ➡️ 방금 만든 API 통합을 찾아 추가합니다.
4. 데이터베이스 링크를 복사하여 `https://www.notion.so/.../본인워크스페이스/` 와 `?v=` 사이에 있는 영문/숫자 조합인 데이터베이스 ID를 메모합니다.
5. [중요] 데이터베이스의 속성(Property)을 아래와 정확히 똑같이 맞춰주세요.
   - `이름` (속성 유형: 제목 / Title)
   - `URL` (속성 유형: URL)
   - `날짜` (속성 유형: 날짜 / Date)

### B. 깃허브(GitHub) 설정
1. 이 코드를 적용할 GitHub 레포지토리로 이동합니다.
2. `Settings` ➡️ `Secrets and variables` ➡️ `Actions` ➡️ `New repository secret`을 클릭합니다.
3. 아래 두 개의 시크릿을 등록합니다.
   - Name: `NOTION_TOKEN` / Value: 위에서 복사한 노션 API 토큰
   - Name: `DATABASE_ID` / Value: 위에서 복사한 노션 데이터베이스 ID

---

## 📂 2. 폴더 구조 규칙

이 스크립트는 문제마다 독립된 폴더가 있고, 그 안에 코드와 `README.md`가 함께 있다고 가정합니다. 다음과 같은 구조로 파일을 업로드해 주세요.

```text
📦 내 레포지토리
 ┣ 📂 백준_1000번_A+B
 ┃ ┣ 📜 solution.py (또는 .java)
 ┃ ┗ 📜 README.md (문제 설명 및 풀이 과정)
 ┣ 📂 프로그래머스_두수수의합
 ┃ ┣ 📜 solution.py
 ┃ ┗ 📜 README.md
