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
  
    
- 프로젝트 생성 url : https://start.spring.io/


- logging : 상세 로그 사용
  - ```
    logging:
        level:
            org.springframework: debug
    ```
    
- Versioning - URI를 이용한 REST API Version 관리
    - 참조 링크
      1. https://developers.facebook.com/docs/apps/versions
      2. https://developers.kakao.com/docs/restapi/kakaotalk-api
    - /admin/users/{id} → /v1/admin/users/{id} & 메서드도 뒤에 V1
    - Add a new user bean → UserV2 extends User


- Versioning - Request Parameter와 Header를 이용한 API Version 관리
  - Request Parameter versioning
    - Amazon
    - @GetMapping(value = "/users/{id}/", params = "version=1")
    - localhost:8080/users/1?version=1 으로 요청
  - (Custom) headers versioning
    - Microsoft
    - @GetMapping(value = "/users/{id}/", headers="X-API-VERSION=2")
    - header - key : X-API-VERSION, value : 2

- Versioning 공통
  - Factors
    - URI Pollution
    - Misurse of HTTP Headers
    - Caching : 캐시가 남아있다면 이전에 반영된 정보로 전달 될 수 있음.
    - Can we execute the request on the browser?
  - No Perfect solution


- resources/data.sql 생성 → query 작성하여 실행 가능!!!!
  - 이슈 사항
    - create-drop 설정으로는 테이블이 생성되기 전에 쿼리가 실행되어 오류 발생
      - spring.jpa.defer-datasource-initialization=true 설정을 추가 하여 성공!
        
      - ↓ 설정 파일
      ```
        spring:
          messages:
            basename: messages
          application:
            name: catalog-service
          h2:
            console:
              enabled: true
            settings:
              web-allow-others: true
            path: /h2-console
          jpa:
            hibernate:
              ddl-auto: create-drop
            show-sql: true
            generate-ddl: true
            defer-datasource-initialization: true
          datasource:
            driver-class-name: org.h2.Driver
            url: jdbc:h2:mem:testdb
        ```
---
### 3. JUNIT

- MockMvc
    - @WebMvcTest


---
### 4. Java8

- lambda
  - .map(PostListResponseDto::new) <-- .map(new PostListResponseDto(posts))

---
### 5. Security

- 

---
### 6. Web Service & Web Application

- 네트워크 상에서 서로 다른 종류의 컴퓨터들 간에 상호작용하기 위한 소프트웨어 시스템
- client -> web server -> web application -> database
- Web Service
  - request(input) & response(output)
  - Data exchange 2 format : xml, 🌟json🌟
  - Service consumer or Client -request(input)-> <-response(output)- Service provider or Server
    
    🍌 SOAP | ☕️ RESTful
- SOAP Simple Object Access Protocol 
  - xml 기반
- REST (REpresentational State Transfer)
  - Resource의 Representation에 의한 상태(컴퓨터가 가지고 있는 자원) 전달
  - HTTP Method를 통해 Resource를 처리하기 위한 아키텍쳐
- RESTful
  - REST API를 제공하는 웹 서비스
- SOAP vs 🌟REST🌟 (REST가 짱이다)
  - 접근 제한성 vs 시스템 아키텍쳐
  - 사용 되는 데이터 문서 포맷
  - 서비스를 정의하는 방법
  - 전송되는 방법 & 전송 규약
  - 개발의 용의성
---
###7. Annotation

- @Data -> Setter + Getter + ToString
- @AllArgsConstructor : 클래스변수의 생성자 자동 생성
- @NoArgsConstructor : 매개변수없는 기본 생성자 생성
- @PathVariable String name ⬅ "/api/posts/{name}"
- 

---
###8. Spring Boot API
- HATEOAS
  - Hypermedia As the Engin of Application State
  - 현재 리소스와 연관된(호출 가능한) 자원 상태 정보를 제공
    - 🚀 Glory of REST
    - ↑ Level 3: Hypermedia Controls
    - | Level 2: HTTP Verbs
    - | Level 1: Resources
    - | Level 0: The Swamp of POX
- Swagger
  - 참조 url
    - http://localhost:8080/v2/api-docs
    - http://localhost:8080/swagger-ui/index.html/
  - 이슈 사항
    - Swagger & Swagger UI 둘다 추가 ➡ io.springfox:springfox-boot-starter 하나로 통합됨!

- REST API Monitoring - Actuator
  - 모니터링 : 서버가 가동 중인지 아닌지도 파악 할 수 있다.
  - localhost:8080/actuator
  - application.yml ➡ 설정 시, 더 많은 정보를 모니터링 할 수 있다.
    - ```
      management:
        endpoints:
          web:
            exposure:
              include: "*"
      ```
  - HAL Browser를 이용한 HATEOAS 기능 구현
    - HAL Browser
      - Hypertext Application Language
      - API 리소스들 사이에서 일관적인 Hyperlink 를 제공하는 방식
      - API 간에 쉽게 검색이 가능하다는 장점
- Spring Security
- Configuration → 
---
###9. JPA
- 구성도
  - Spring Data JPA
  - JPA
  - Hibernate
  - JDBC
  

- JPA → 인터페이스!!!!
  - Java Persistence API
  - 자바 ORM 기술에 대한 API 표준 명세
  - 자바 어플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스.
  - EntityManager를 통해 CRUD 처리
  

- Hibernate
  - JPA의 구현체, 인터페이스를 직접 구현한 라이브러리
  - 생산성, 유지보수, 비종속성


- Spring Data JPA
  - Spring Module
  - JPA를 추상화한 Repository 인터페이스 제공
---
###10. Richardson Maturity Model
- Leonard Richardson
- "A way to grade your API according to the constraints of REST."
- LEVEL 
  - 🚀 Glory of REST 
  - ↑ Level 3: Hypermedia Controls
    - Level2 + HATEOAS
    - DATA + NEXT POSSIBLE ACTIONS
  - | Level 2: HTTP Verbs
    - Level1 + HTTP Methods
  - | Level 1: Resources
    - Expose resources with proper uri
    - http://server/accounts/10
    - note: improper use of http methods
  - | Level 0: The Swamp of POX 
    - Expose soap web services in rest style
    - http://server/getPosts
    - http://server/deletePosts ...

---
### Extra. 이슈사항
1. (2021.11.15) Test 소스에서 Transaction이 걸린 상태로 url로 update를 날리니, Transaction 상태라 저장된 정보를 조회할 수 없으니 오류 발생.

   ==> Transaction을 빼고, teardown메서드로 deleteAll() 추가로 해결 완료