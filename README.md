## Spring Web Portfolio
스프링으로 만든 웹 어플리케이션 포트폴리오입니다.

## CINEPHILE (VOD 웹 사이트)

`개요`<br>
자바와 스프링 프레임워크를 이용한 MVC Model2 패턴의 웹 서비스 페이지 CINEPHILIE 입니다.<br>
넷플릭스, 왓챠, 디즈니 플러스같은 OTT, VOD 서비스에 착안하여 원하는 스트리밍 서비스를 구매할 수 있는 웹 애플리케이션을 설계하였습니다.

[주요 OTT/VOD 컨텐츠 예]
![OTT서비스](https://user-images.githubusercontent.com/44256670/71643024-2d7e0800-2cf7-11ea-8bc7-ec6c14aeec4f.jpg)

### 개발 환경
- IDE : Spring Tool Suite (STS) Version 4.2.4
- JDK : JAVA 1.8
- Language : Java Version 8, Java Script, JSTL, EL
- DATABASE : MySQL 5.7
- Web Server : AWS EC2 (ubuntu, Apache Tomcat 8.0)
- Library Management : MAVEN
- SQL Mapper : MyBatis

- FRONT-END : HTML, CSS, BootStrap, Materialize.css
- Open Source Library : JQuery, Ajax, Handlebars.js, DataTables

### 클래스 구성
  - Controller
  - Persistence, DAO
  - Repository, DTO (Model, Command Object)
  - Service
  - VO (데이터 수집용)
  - Interceptor
  
### DATABASE
- MySQL Table ER Diagram

CREATE DATABASE cine_db;<br>
USE cine_db;

![20200129_175141](https://user-images.githubusercontent.com/44256670/73346660-9b532a80-42c9-11ea-993a-64a63cc9251b.jpg)

### Function
1. 회원가입

2. 로그인 (OAuth2.0(네이버 아이디 로그인), Session, 관리자 로그인)
      - OAuth2.0 인증 표준의 네이버 로그인 API를 이용하여 토큰을 발급 받아 로그인 한다.
      - Interceptor를 이용한 Login Session을 관리한다.
      - Interceptor로 로그인 정보를 이용해 사용자가 데이터베이스에 존재하는지 확인한다.

3. 게시판 (Ajax 댓글, Disqus 소셜 댓글)
    - 기본 CRUD 게시판
      - 글 작성, 글 조회, 글 수정, 글 삭제
    - DISCUSS 소셜 댓글 서비스
    - Ajax 댓글 

4. 관리자 메뉴
    - 별도의 관리자 테이블에 로그인 정보를 저장한다.

    - 상품등록
      - 국내영상/해외영상/기타영상 각 목록에 맞는 상품을 추가한다.
          - 파일첨부는 오픈소스 Dropzone.js 를 사용한다. 

    - 회원관리
      - jQuery의 Datatables 라이브러리로 회원 목록을 조회, 수정, 삭제한다.
    
5. 장바구니 기능
    - 대여일 선택 → 장바구니에 담기 → 결제 → 내 정보에서 결제 확인
 
ref ) 코드로 배우는 스프링 웹 프로젝트(구멍가게 코딩단 지음),<br>
https://brunch.co.kr/@businessinsight/42
