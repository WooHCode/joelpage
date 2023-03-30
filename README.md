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

#### &#x1F4D8; 개발과정 중 

1. CORS에러
- 문제 : 로컬환경에서 개발할때는 신경쓰지않아도 될 문제였지만, 서버를 ec2에 배포를 한 후 진행을 하니 지속적으로 CORS에러 발생
- 해결 : proxy서버를 통해 요청을 보내던 api요청을 직접연결로 변경하여 CORS문제를 해결
- 관련링크 : https://github.com/WooHCode/joeladminPage/blob/master/src/scripts/api.js

2. 렌더링 시점 문제
- 문제: chart.js를 활용하여 매출데이터를 화면에 출력하는데 chart.js가 화면에 먼저 출력이 되고 그 이후에 데이터가 바인딩되어 화면이 출력되었을 때 빈 데이터 출력
- 해결 : async, await로 메소드를 변경하여 api로 데이터가 완전히 받아진 후 데이터를 바인딩하게 하였음.
- 관련링크 : https://github.com/WooHCode/joeladminPage/blob/master/src/pages/Home.vue   --> 44~54 Line

---


