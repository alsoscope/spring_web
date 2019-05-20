# spring_web

스프링으로 만든 웹 어플리케이션
=============================

자바와 스프링 프레임워크를 이용해 MVC패턴의 웹 서비스 페이지 개발.

객체지향의 가장 기본적인 방식으로 제작.

회원가입, 로그인, 로그아웃 : OAuth, HttpSession

게시판 : Ajax 계층형 댓글, Disqus 소셜 댓글

관리자 : 별도 관리자 테이블로 구분


### 개발환경
- IDE : STS 버전 4.2.4
- JDK 1.8
- Language : MyBatis
- DATABASE : MySQL 5.7
- FRONT-END : BootStrap (HTML, CSS)
- Libary Management : MAVEN
- WAS : Tomcat
- JavaScript, JQuery, Ajax
- JSTL, EL

### 목적
- 영화 VOD 구매 사이트
- 로그인 화면(회원가입/관리자 로그인)
- 자유 게시판(크리틱)
- 관리자 메뉴(회원목록 확인, 게시글 등록)

### 분류
- .java
  - Controller
  - Persistence, DAO
  - Repository, DTO
  - Service
  - VO (MemberVO : 데이터 수집용)
- .jsp
  - sboard
  - member
  - product
  - admin
  - naverlogin
  - forward
- /resources/**

### DATABASE
- table
  - tbl_board
  - tbl_member2
  - tbl_admin
  - tbl_reply
  
### ERMaster
