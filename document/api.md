## 나만의 블로그: Mlog API 명세

### 개발 포스트

- `(GET) /api/post`
  - 역할: 포스트 전체 목록 불러오기
  - 권한: 모든 사용자
  - 요청 시 필요한 데이터: -
  - 반환: 포스트 전체 목록
  - 반환 예시
  ```json
    [
      {
        "id": 2,
        "title": "포스트 제목 2",
        "previewContent": "포스트 본문 요약 내용 2",
        "thumbnail": "썸네일 주소 2",
        "writingTime": "2023-08-08T17:41:08"
      },
      {
        "id": 1,
        "title": "포스트 제목 1",
        "previewContent": "포스트 본문 요약 내용 1",
        "thumbnail": "썸네일 주소 1",
        "writingTime": "2023-08-08T17:41:08"
      }
    ]
  ```
- `(GET) /api/post/{id}`
  - 역할: id에 해당하는 포스트의 상세정보 불러오기
  - 권한: 모든 사용자
  - 요청 시 필요한 데이터: 포스트의 id
  - 반환: id에 해당하는 포스트의 상세정보
  - 반환 예시
  ```json
  {
    "id": 1,
    "title": "포스트 제목 1",
    "content": "포스트 본문 내용 1",
    "writingTime": "2023-08-08T17:23:49"
   }
   ```
- `(POST) /api/post`
  - 역할: 포스트 추가
  - 권한: 관리자
  - 요청 시 필요한 데이터 예시:
  ```json
  {
    "title": "작성 테스트 제목",
    "content": "작성 테스트입니다.",
    "thumbnail": "썸네일 경로",
    "visible": true
  }
    ```
  - 반환
    - 성공 시: true
    - 오류 발생 시: 500 에러
  
- `(DELETE) /api/post`
  - 역할: 포스트의 공개 여부 변경
  - 권한: 관리자
  - 요청 시 필요한 데이터 예시:
    ```json
    {
      "id": 1
    }
    ```
- `(PUT) /api/post`
  - 역할: 포스트 수정
  - 권한: 관리자
  - 요청 시 필요한 데이터 예시:
  ```json
  {
    "id": 1,
    "title": "수정할 포스트 제목",
    "content": "수정할 포스트 본문 내용",
    "previewContent": "수정할 포스트 본문 미리보기 예시",
    "thumbnail": "썸네일 경로",
    "fileList": 파일 리스트,
    "visible": 공개여부(공개: true, 비공개 false)
    }
  ```
- `(GET) /api/post/preview`
  - 역할: 메인페이지에서 공개할 최신 포스트 3개의 데이터 제공
  - 권한: 모든 사용자
  - 요청 시 필요한 데이터: -
  - 반환: 포스트 3개의 리스트
  - 반환 예시:
  ```json
    [
      {
        "id": 3,
        "title": "포스트 제목 3",
        "previewContent": "포스트 본문 내용 3",
        "thumbnail": "썸네일 경로 3",
        "writingTime": "2023-08-23T17:10:58.012621"
      },
      {
        "id": 2,
        "title": "포스트 제목 2",
        "previewContent": "포스트 본문 내용 2",
        "thumbnail": "썸네일 경로 2",
        "writingTime": "2023-08-23T17:09:29.862541"
      },
      {
        "id": 1,
        "title": "포스트 제목 1",
        "previewContent": "포스트 본문 내용 1",
        "thumbnail": "썸네일 경로 1",
        "writingTime": "2023-08-23T17:02:55.416987"
      }
    ]
  ```
- `(POST) /api/post/file`
  - 역할: 에디터에서 업로드한 파일을 서버와 DB에 저장
  - 요청 시 필요한 데이터: 업로드할 파일 데이터
  - 반환: 업로드한 파일의 경로

### 프로젝트
- `(GET) /api/project`
  - 역할: 프로젝트 전체 목록 불러오기
  - 권한: 모든 사용자
  - 요청 시 필요한 데이터: -
  - 반환: 프로젝트 전체 목록
  - 반환 예시:
  ```json
  [
    {
        "id": 1,
        "title": "프로젝트 제목 1",
        "thumbnail": "프로젝트 썸네일 경로",
        "summary": "프로젝트 요약 1",
        "skills": "프로젝트에 사용한 기술",
        "learning": "프로젝트에서 배운 점",
        "duration": "2023.04 ~2023.08",
        "writingTime": "2023-08-15T14:33:59"
    },
    {
        "id": 2,
        "title": "프로젝트 제목 2",
        "thumbnail": "프로젝트 썸네일 경로",
        "summary": "프로젝트 요약 2",
        "skills": "프로젝트에 사용한 기술",
        "learning": "프로젝트에서 배운 점",
        "duration": "2023.04 ~2023.08",
        "writingTime": "2023-08-15T14:33:59"
    }
  ]
  ```
- `(GET) /api/project/{id}`
  - 역할: id에 해당하는 프로젝트의 상세정보 불러오기
  - 권한: 모든 사용자
  - 요청 시 필요한 데이터: 프로젝트 id
  - 반환: 프로젝트의 상세 정보
  - 반환 예시:
  ```json
  {
      "id": 2,
      "title": "프로젝트 제목 2",
      "thumbnail": "프로젝트 썸네일 경로",
      "summary": "프로젝트 요약 2",
      "skills": "프로젝트에 사용한 기술",
      "learning": "프로젝트에서 배운 점",
      "duration": "2023.04 ~2023.08",
      "writingTime": "2023-08-15T14:33:59"
  }
  ```
- `(POST) /api/project`
  - 역할: 프로젝트 추가
  - 권한: 관리자
  - 요청 시 필요한 데이터 예시:
  ```json
  {
    "title": "프로젝트명",
    "content": "프로젝트에 대한 본문 내용",
    "thumbnail": "프로젝트 썸네일",
    "summary": "프로젝트 요약",
    "duration": "프로젝트 기간",
    "skills": "사용한 기술들",
    "learning": "프로젝트에서 배웠던 점",
    "visible": 공개여부(true,false),
  }
  ```
  - 반환
    - 성공 시: true
    - 오류 발생 시: 500 에러
- `(DELETE) /api/project`
  - 역할: 프로젝트 공개여부 변경
  - 권한: 관리자
  - 요청 시 필요한 데이터 예시:
    ```json
    {
      "id": 1
    }
    ```
- `(PUT) /api/project`
  - 역할: 프로젝트 수정
  - 권한: 관리자
  - 요청 시 필요한 데이터 예시:
    ```json
     {
      "id": 프로젝트의 id,
      "title": "수정할 프로젝트명",
      "content": "수정할 프로젝트 본문 내용",
      "thumbnail": "프로젝트 썸네일 경로",
      "summary": "수정할 프로젝트 요약",
      "duration": "수정할 프로젝트 기간",
      "skills": "수정할 프로젝트에 사용된 기술들",
      "learning": "수정할 프로젝트에서 배웠던 점",
      "visible": 공개여부(true, false),
    }
  ```
- `(GET) /api/project/preview`
  - 역할: 메인페이지에서 공개할 최신 프로젝트 3개의 데이터 제공
  - 권한: 모든 사용자
  - 요청 시 필요한 데이터: -
  - 반환: 프로젝트 3개의 리스트
  - 반환 예시:
  ```json
  [
    {
        "id": 2,
        "title": "프로젝트명 2",
        "thumbnail": "프로젝트 썸네일 경로",
        "summary": "프로젝트 요약 2",
        "skills": "프로젝트에서 사용한 기술들",
        "learning": "프로젝트에서 배운 점들",
        "duration": "프로젝트 진행 기간",
        "writingTime": "2023-08-17T19:14:07.386337"
    },
    {
        "id": 1,
        "title": "프로젝트명 1",
        "thumbnail": "프로젝트 썸네일 경로",
        "summary": "프로젝트 요약 1",
        "skills": "프로젝트에서 사용한 기술들",
        "learning": "프로젝트에서 배운 점들",
        "duration": "프로젝트 진행 기간",
        "writingTime": "2023-08-15T14:33:59"
    }
  ]
  ```