# instagram-spring-clone
인스타그램 클론 코딩 with 스프링
### **TODO**
- [ ] 스레드 관련 api
- [ ] 채팅
### **API 명세서**
|기능|분류|메소드|path|
|------|---|---|-----|
|회원 가입|user|POST|/user|
|로그인|user|POST|/user/login|
|회원 조회|user|GET|/user/{id}|
|회원 검색|user|GET|/user?email=|
|게시글 업로드|feed|POST|/feed|
|게시글 조회|feed|GET|/feed/{id}|
|채팅|chat|?|?|

### **Commit Convention**

| 태그 이름  | 설명                                                                 |
| ---------- | -------------------------------------------------------------------- |
| [Feat]     | 새로운 기능 구현                                                     |
| [Fix]      | 버그, 오류 수정                                                      |
| [Hotfix]   | issue나 QA에서 급한 버그 수정                                        |
| [Docs]     | 문서 수정                                                            |
| [Test]     | 테스트 코드 추가 및 업데이트                                         |
| [Chore]    | 코드 수정, 내부 파일 수정                                            |
| [Del]      | 불필요한 코드 삭제                                                   |
| [Refactor] | 전면 수정                                                            |
| [Merge]    | 다른 브랜치를 merge 할 때 사용                                       |
| [Add]      | Feat 이외의 부수적인 코드 추가, 라이브러리 추가, 새로운 파일 생성 시 |
| [Rename]   | 파일 이름 변경 시 사용                                               |
| [Move]     | 프로젝트 내 파일이나 코드의 이동                                     |
