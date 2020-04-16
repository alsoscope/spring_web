## Spring Web PORTFOLIO
스프링으로 만든 웹 어플리케이션 포트폴리오입니다.

### 포트폴리오 PDF : https://github.com/alsoscope/spring_web/blob/master/PortfolioPDF.pdf<br>
웹 주소 : []

## CINEPHILE / OTT·VOD 웹 사이트
자바와 스프링 프레임워크를 이용한 MVC Model2 패턴의 웹 서비스 페이지 CINEPHILE 시네필 입니다.<br>
넷플릭스, 왓챠같은 OTT-VOD 서비스에 착안하여 원하는 스트리밍 서비스를 구매할 수 있는 웹 애플리케이션을 설계하였습니다.

[주요 OTT/VOD 컨텐츠 예]
![OTT서비스](https://user-images.githubusercontent.com/44256670/71643024-2d7e0800-2cf7-11ea-8bc7-ec6c14aeec4f.jpg)
[출처:https://brunch.co.kr/@businessinsight/42]

### 개발 환경
- IDE : Spring Tool Suite (STS) Version 4.2.4
- JDK, JRE : 1.8
- Language : Java Version 8, Java Script, JSTL, EL, HTML, CSS
- DATABASE : MySQL 5.7
- Web Server : AWS EC2 (ubuntu, Apache Tomcat 8.0(WAS))
- Library Management : MAVEN
- SQL Mapper : MyBatis
- Open Source Library : JQuery, Ajax, Handlebars.js, DataTables, BootStrap, Materialize.css
  
### DATABASE
- MySQL Table ER Diagram

CREATE DATABASE cine_db;<br>
USE cine_db;

![20200129_175141](https://user-images.githubusercontent.com/44256670/73346660-9b532a80-42c9-11ea-993a-64a63cc9251b.jpg)

### Function
1. 회원가입
    - Form의 Input 태그로 전달된 파라미터 값을 데이터베이스에 Insert한다.

2. 로그인
      - OAuth2.0 인증 표준의 네이버 로그인 API를 이용하여 Access 토큰을 발급 받아 로그인 한다.
      - Interceptor로 로그인 폼에서 전달된 사용자 파라미터가 데이터베이스에 존재하는지 확인한다.
      - 로그인 정보는 Session에 저장하여 관리한다.

3. 게시판
    - 로그인한 회원만 서비스를 이용할 수 있다.
      - 게시판과 댓글 이용
        - 글 작성·조회·수정·삭제 (CRUD)
      - DISCUSS 소셜 댓글 서비스
      - Rest API - Ajax 댓글
        - 댓글 등록하면 jQuery-Ajax에서 JSON.stringify로 댓글 문자열 데이터가 JSON 데이터로 파싱된다. controller의 ResponseBody 어노테이션이 붙은 메소드로 요청이 전송되어 HTTP 요청 Body로 응답하는 REST 방식으로 처리된다.
        - 댓글 조회, 수정, 삭제도 마찬가지로 Ajax를 통해 REST 방식-비동기로 처리된다.
      
4. 관리자 권한
    - 별도의 관리자 테이블에 로그인 정보를 저장하여 관리자 로그인을 한다.
    - 상품 등록
      - 국내/해외/기타영상 각 목록에 맞는 상품을 추가하면 해당 테이블로 Insert된다.
      - 파일첨부는 Drag & Drop 방식으로 Form에 이미지를 끌어놓으면 배열에 담겨, Ajax로 파일을 전송할 수 있는 FormData 객체를 이용해 form 내부에 파일의 이름을 포함시켜 전송한다. 해당 게시판 테이블과 트랜잭션으로 첨부 파일용 테이블에도 파일 데이터가 전송된다.
    - 파일 업로드와 동시에 날짜 폴더가 생성되며 UUID로 파일이름이 랜덤 지정된다.
    - 썸네일 이미지와 원본 파일이 서버의 지정한 경로에 저장되며 등록된 상품의 수정, 삭제도 같은 REST-Ajax 방식으로 처리된다.
    - 회원관리
       - jQuery의 Datatables 라이브러리로 관리자 권한으로 회원 목록을 조회, 수정, 삭제한다.
    
5. 장바구니/결제
    - 각 카테고리 마다 해당 DB 테이블에 저장된 상품 목록들을 확인 할 수 있으며, 원하는 상품을 클릭하면 정보가 보여진다. 디테일 이미지는 파일 이름으로 식별하여 handlebars.js 템플릿을 통해 보여진다.
    - 대여일 선택 → 장바구니에 담기 → 결제 → 내 정보에서 결제 확인
    - 결제하면 tbl_order 테이블로 Insert되고 장바구니 목록에서 Delete 된다.
 
ref ) 코드로 배우는 스프링 웹 프로젝트(구멍가게 코딩단 지음)
