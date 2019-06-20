# spring_web

스프링으로 만든 웹 어플리케이션

CINEPHILE (VOD 웹 사이트)
=============================


자바와 스프링 프레임워크를 이용해 MVC패턴의 웹 서비스 페이지 개발.

객체지향의 가장 기본적인 방식으로 제작.

Ref : 코드로 배우는 스프링 웹 프로젝트(구멍가게 코딩단 지음)


## 기본 기능

회원가입, 로그인, 로그아웃 : OAuth2.0(네이버 아이디 로그인), HttpSession

게시판 : Ajax 계층형 댓글, Disqus 소셜 댓글

관리자 : 별도 관리자 테이블로 구분


### 개발환경
- IDE : Spring Tool Suite (STS) Version 4.2.4
- JDK : 1.8
- Language : Java Version 8
- DATABASE : MySQL 5.7
- FRONT-END : BootStrap (HTML, CSS)
- Libary Management : MAVEN
- WAS : Tomcat 8.0
- JavaScript, JQuery, Ajax, MyBatis
- JSTL, EL

### 목적
- 영화 VOD 웹사이트(국내/해외/기타)
- 로그인 화면(회원가입/관리자 로그인)
- 자유 게시판
- 관리자 메뉴(회원목록 확인, 상품 등록(첨부파일 - Ajax/Drag & Drop))

### 분류
- .java
  - Controller
  - Persistence, DAO
  - Repository, DTO (Model, Command Object)
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
  - tbl_board (게시판)
  - tbl_member2 (회원관리)
  - tbl_admin (관리자)
  - tbl_reply (댓글)
  - tbl_cart (장바구니)
  - tbl_attach (첨부파일)
  
### ERMaster
