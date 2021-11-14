## 스프링 JPA 공부를 위한 공간입니다.

---

### 1. TDD
- TDD란?
    - 테스트 주도 개발 (Test-Driven Development)
    - 테스트 코드를 먼저 작성
        1. 항상 실패하는 테스트를 먼저 작성하고 (Red)
        2. 테스트가 통과하는 프로덕션 코드를 작성하고 (Green)
        3. 테스트가 통과하면 프로덕션 코드를 리팩토링합니다. (Refactor)


- TDD를 공부해야 되는 이유
    - 대부분의 서비스 회사가 테스트 코드에 관해 요구

      [우대사항] - 빌드/테스트/배포 자동화 경험이 있으신분
    - 빠른 피드백
    - 자동 검증
    - 개발자가 만든 기능을 안전하게 보호
---
### 2. 스프링 부트

- 내장 WAS를 사용하는 것을 권장합니다.
    - '언제 어디서나 같은 환경에서 스프링 부트를 배포' 할 수 있습니다.
    - 새로운 서버가 추가 되면 모든 서버가 같은 환경을 구축해야만 하는 단점을 배제할 수 있습니다.


- 파일 트리
    - web : 컨트롤러와 관련된 클래스 들은 모두 이 패키지에 담기
      - 뷰 템플릿 영역
      - **외부 요청과 응답**에 대한 전반적인 영역을 이야기합니다.
    - domain : 도메인을 담을 패키지 -> Entity, Repository
      - 추상화 개념?
    - service
      - @Service에 사용되는 서비스 영역
      - 일반적으로 Controllor와 Repository의 중간 영역에서 사용됩니다.
      - @Transactional이 사용되어야 되는 영역

---
### 3. JUNIT

- MockMvc
    - @WebMvcTest


---
### 4. Java8

- lambda
  - .map(PostListResponseDto::new) <-- .map(new PostListResponseDto(posts))


---
### Extra. 이슈사항
1. (2021.11.15) Test 소스에서 Transaction이 걸린 상태로 url로 update를 날리니, Transaction 상태라 저장된 정보를 조회할 수 없으니 오류 발생.

   ==> Transaction을 빼고, teardown메서드로 deleteAll() 추가로 해결 완료