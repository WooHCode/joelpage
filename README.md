## -- joelpageApiServer --
### &#x2615; 개인카페의 상품, 직원, 출근을 관리하기 위한 AdminPage의 API서버입니다.

##### 개발기간 : 22.12.18 ~ 23.03.25
##### 개발인원 : 1인

---

### &#x1F4BB; 사용 기술스택:
<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white">

- Java

<img src="https://img.shields.io/badge/springboot-BFF0B6?style=flat-square&logo=springboot&logoColor=green">

- Spring, SpringBoot, Spring web Mvc, Spring Data JPA, Spring Validation 

<img src="https://img.shields.io/badge/postgresql-51ADCE?style=flat-square&logo=postgresql&logoColor=">

- Postgresql, Amazno RDS

<img src="https://img.shields.io/badge/AWS-F2E1B9?style=flat-square&logo=AWS&logoColor=">

- AWS EC2, Apache Tomcat

##### 추가 라이브러리

- P6spy(로그 콘솔창 출력 라이브러리)

---

### 🛠️ 개발 도구:
- Intellij, putty, aws console, vscode(프론트 개발), postman, pgAdmin, git

## &#x1F517; 링크:

#### 프론트 페이지 깃허브 링크: https://github.com/WooHCode/joeladminPage

#### 배포된 홈페이지 링크 : http://joeladmin.store/login

---

#### &#x1F4D8; 개발과정 중 트러블 슈팅

1. Transaction
- 문제 : 트랜잭션 단위를 클래스 단위로 설정하니 읽기 전용 작업 시에도 EntityManager에서 flush, commit을 진행하여 성능이 저하
- 해결 : 클래스 단위에서 @Transactional(readOnly = true)로 설정하여 읽기전용 작업을 하는 메서드는 1차캐시에서 프록시로 생성된 객체를 읽어와 작업을 하고
쓰기 등 DB에 변경이 되는 작업들은 메서드에 별도로 트랜잭션을 걸어주어서 정상적으로 DB에 반영되도록 함.
- 관련링크 : https://github.com/WooHCode/joelpage/blob/master/src/main/java/joel/joelpage/service/ItemService.java

2. N+1
- 문제: Employee 엔티티와 Attendance 엔티티는 1 : 다 관계로 매핑되어있음. 그리하여 Employee를 전체조회하면 각 Employee에 관련된 Attendance가 추가로 여러번 쿼리가 발생되어 성능이 저하됨.
- 해결 : Default값인 @ManyToOne(fetch = FetchType.EAGER)에서 @ManyToOne(fetch = FetchType.LAZY)로 변경하여 실제 데이터가 필요한 시점에서 데이터를 가져올 수 있도록 변경 
- 관련링크 : https://github.com/WooHCode/joelpage/blob/master/src/main/java/joel/joelpage/entity/Attendance.java ==> 24 Line

3. 재귀호출
- 문제 : LoginMember 엔티티와 연관된 필드가 JSON으로 serialize(직렬화) 될 때 무한 재귀 호출이 발생함. 그로인해 서버가 다운됨. LoginMember 엔티티가 Employee엔티티 내부에 있고, LoginMember 엔티티에 Employee가 내부에 있기때문에 LoginMember를 JSON으로 직렬화 하려고 할때, 내부에 있는 Employee필드를 다시 직렬화 하게되고 Employee필드를 직렬화 하게 될때, LoginMember를 다시 직렬화하는 과정을 반복하면서 발생함.
- 해결 : @JsonIgnore 어도테이션을 사용하여 LoginMember필드가 JSON으로 다시 직렬화 되지 않도록 설정
- 관련링크 : https://github.com/WooHCode/joelpage/blob/master/src/main/java/joel/joelpage/entity/Employee.java  ==> 55 ~ 58 Line 
4. CORS 문제
- 문제 : 로컬 개발 환경에서는 프론트 서버와 통신에 문제가 없었지만, 서버를 EC2에 배포하다 보니 서버로 api요청 시 CORS문제가 발생함.
- 해결 : API controller에 @CrossOrigin 을 지정해줘서 응답 헤더에 Access-Control-Allow-Origin의 값을 응답가능한 도메인을 추가해줘서 다른 도메인으로 부터 리소스 요청이 왔을 때 교차 오리진 자원 공유를 가능하게 함.
- 관련 링크 : https://github.com/WooHCode/joelpage/blob/master/src/main/java/joel/joelpage/api/EmpApiController.java  ==> 23 Line
5. LocalDateTime 사용 문제
- 문제 : LocalDateTime으로 시간 비교 시 duration을 사용해서 비교를 하게되면 다음날이 되어 시간이 00시 이후가 되면 차이 값이 -가된다.
- 해결: difference.getSeconds()/3600 의 값이 - 로 나온다면 +24를 더해서 하루가 지났다는것을 인지시켜준다.
- 관련링크 : https://github.com/WooHCode/joelpage/blob/master/src/main/java/joel/joelpage/service/EmployeeService.java 
- ==>  117 ~ 129 Line

---
### 📘 추가 예정 사항
1. Oauth2를 활용하여 Kakao, Google, Naver로 로그인이 가능하도록 구현
2. Spring Security를 도입하여 별도로 나눠서 구현한 Jwt와 통합, CSRF(Cross-Site Request Forgery) 공격 방지, XSS(Cross-Site Scripting) 방어, 세션 관리, 브라우저 캐싱 방지 등 보안 기능을 강화.
3. QueryDSL을 도입하여 동적쿼리 생성, 컴파일 시점의 문법 오류 검출 등 API추가.

