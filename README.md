## Spring Web Portfolio
스프링으로 만든 웹 어플리케이션 포트폴리오의 소개입니다.

## CINEPHILE (VOD 웹 사이트)

`개요`<br>
자바와 스프링 프레임워크를 이용한 MVC Model2 패턴의 웹 서비스 페이지 CINEPHILIE 입니다.

넷플릭스, 왓챠, 디즈니 플러스같은 OTT-VOD 서비스같이
자신이 원하는 시청물을 선택하여 볼 수 있는 VOD(Video On Demand)-OTT(Over The Top)
서비스에 착안하여 영화 스트리밍 서비스를 제공할 수 있는 웹 서비스 페이지를 설계하였습니다.

[주요 OTT/VOD 컨텐츠 예]
![OTT서비스](https://user-images.githubusercontent.com/44256670/71643024-2d7e0800-2cf7-11ea-8bc7-ec6c14aeec4f.jpg)

### 개발 환경
- IDE : Spring Tool Suite (STS) Version 4.2.4
- JDK : 1.8
- Language : Java Version 8, Java Script, JSTL, EL
- DATABASE : MySQL 5.7
- WAS : Tomcat 8.0
- FRONT-END : HTML, BootStrap, CSS, Materialize.css
- Library Management : MAVEN
- Open Source Library : JQuery, Ajax, Handlebars.js, Dropzone.js
- SQL Mapper : MyBatis

### 구성
  - Controller
  - Persistence, DAO
  - Repository, DTO (Model, Command Object)
  - Service
  - VO (MemberVO : 데이터 수집용)
  
### DATABASE
- MySQL Table ERDiagram

CREATE DATABASE cine_db;<br>
USE cine_db;

![20200101_191120](https://user-images.githubusercontent.com/44256670/71642596-3d92e900-2cf1-11ea-9d79-4b7335cba3bf.jpg)

### Function
1. 회원가입
    - 

2. 로그인 (OAuth2.0(네이버 아이디 로그인), Session, 관리자 로그인)
    - OAuth2.0 의 네이버 로그인 API를 이용하여 네이버 로그인.
    - Interceptor를 이용한 Login Session을 관리한다.
    - Interceptor로 로그인 정보를 가로채서 데이터베이스에 존재하는지 확인.

3. 게시판 (Ajax 계층형 댓글, Disqus 소셜 댓글, Dropzone.js)
  - CRUD 게시판

- 관리자 메뉴(회원목록 확인, 상품 등록)
  - 별도의 tbl_admin 테이블에 로그인 정보를 저장한다.

- 국내영상/해외영상/기타영상 (장바구니 담기 기능)
  각 영상 카테고리에 맞는 상품을 추가한다.

ref ) 코드로 배우는 스프링 웹 프로젝트(구멍가게 코딩단 지음),<br>
https://brunch.co.kr/@businessinsight/42
