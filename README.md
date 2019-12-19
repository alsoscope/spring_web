## Spring Web Portfolio
스프링으로 만든 웹 어플리케이션

## CINEPHILE (VOD 웹 사이트)
---

`개요`<br>
자바와 스프링 프레임워크를 이용한 MVC Model2 패턴의 웹 서비스 페이지 개발.
객체지향의 기본적인 방식을 따랐다.

넷플릭스, 왓챠, 디즈니 플러스같은 OTT-VOD 서비스같이
자신이 원하는 시청물을 선택하여 볼 수 있는 VOD(Video On Demand)-OTT(Over The Top)
서비스에 착안하여 영화 스트리밍 서비스를 제공할 수 있는 웹 서비스 페이지를 설계하였습니다.

ref ) 코드로 배우는 스프링 웹 프로젝트(구멍가게 코딩단 지음)

### 개발환경
- IDE : Spring Tool Suite (STS) Version 4.2.4
- JDK : 1.8
- Language : Java Version 8, Java Script, JSTL, EL
- DATABASE : MySQL 5.7
- WAS : Tomcat 8.0
- FRONT-END : BootStrap, HTML, CSS, Materialize
- Library Management : MAVEN
- Open Source Library : JQuery, Ajax, Handlebars.js
- Mybatis

### 목적
- 국내/해외/기타영살 카테고리
- 로그인 화면(회원가입/네이버 로그인/관리자 로그인)
- 자유 게시판(댓글 기능)
- 관리자 메뉴(회원목록 확인, 상품 등록(첨부파일 - Ajax/Drag & Drop/Handlebars.js))

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
  - tbl_member (회원관리)
  - tbl_admin (관리자)
  - tbl_reply (댓글)
  - tbl_cart (장바구니)
  - tbl_attach (첨부파일)
  - tbl_attach_ab (첨부파일)
  - tbl_attach_etc (첨부파일)
  - tbl_product
  - tbl_product_abroad
  - tbl_product_etcetera
  
### ERMaster

사진첨부

### 기본 기능

- 회원가입, 로그인, 로그아웃 : OAuth2.0(네이버 아이디 로그인), Session

- 게시판 : Ajax 계층형 댓글, Disqus 소셜 댓글

- 관리자 : 별도 관리자 테이블로 구분
