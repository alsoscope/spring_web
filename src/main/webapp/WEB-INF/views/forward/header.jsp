<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Bootstrap core CSS -->
  <!-- <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet"> -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Custom styles for this template -->
<%-- <link href="${pageContext.request.contextPath }/resources/css/shop-homepage.css" rel="stylesheet"> --%>
  
<style type="text/css">
.bg-dark {
    background-color: #343a40!important;
}
.navbar-dark .navbar-brand {
    color: #fff;
}
.fixed-top {
    position: fixed;
    top: 0;
    right: 0;
    left: 0;
    z-index: 1030;
}
.navbar {
    position: relative;
    display: -ms-flexbox;
    /* display: flex; */
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    -ms-flex-align: center;
    align-items: center;
    -ms-flex-pack: justify;
    justify-content: space-between;
    /* padding: .3rem 1rem; */
    padding: .2rem;
}
.navbar-brand {
    display: inline-block;
    padding-top: .8rem;
    padding-bottom: .3125rem;
    margin-right: 1rem;
    font-size: 2.1rem;
    line-height: inherit;
    white-space: nowrap;
}
.nav-link {
	color:rgba(255,255,255,.5);
    text-decoration: none;
    background-color: transparent;
}
.nav-link:hover{
	color:gray;
    text-decoration: none;
    background-color: transparent;
}
.navbar-expand-lg {
    -ms-flex-flow: row nowrap;
    flex-flow: row nowrap;
    -ms-flex-pack: start;
    justify-content: flex-start;
}
li {
    display: list-item;
    text-align: -webkit-match-parent;
}
.navbar-nav {
    display: -ms-flexbox;
    display: flex;
    -ms-flex-direction: column;
    flex-direction: column;
    padding-left: 0;
    margin-bottom: 0;
    list-style: none;
}
body {
	font-size:16px;
    margin: 0;
    font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,"Noto Sans",sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol","Noto Color Emoji";
	/* line-height: 1.5; */
	/* font-weight: 400; */
}
.navbar-toggler:not(:disabled):not(.disabled) {
    cursor: pointer;
}
.navbar-dark .navbar-toggler {
    color: rgba(255,255,255,.5);
    border-color: rgba(255,255,255,.1);
}
/* @media (min-width: 992px) */
.navbar-expand-lg .navbar-toggler {
    display: none;
}
.navbar-toggler {
    /* padding: .25rem .75rem; */
    padding: 25 25;
    font-size: 1.25rem;
    line-height: 1;
    background-color: transparent;
    border: 1px solid transparent;
    /* border-radius: .25rem; */
}
/* [type=button], [type=reset], [type=submit],  */
button {
    -webkit-appearance: button;
}
button, select {
    text-transform: none;
}
button, input {
    overflow: visible;
}
button, input, optgroup, select, textarea {
    margin: 0;
    font-family: inherit;
    font-size: inherit;
    line-height: inherit;
}
button {
    -webkit-writing-mode: horizontal-tb !important;
}
.ml-auto, .mx-auto {
    margin-left: auto!important;
}
dl, ol, ul {
    margin-top: 0;
    margin-bottom: 1rem;
}
ul {
    display: block;
    list-style-type: disc;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 40px;
}
li {
    display: list-item;
    text-align: -webkit-match-parent;
}
/* @media (min-width: 992px) */
.navbar-expand-lg .navbar-nav .nav-link {
    padding-right: .8rem;
    padding-left: .8rem;
}
/* @media (min-width: 992px) */
.navbar-expand-lg .navbar-nav {
    -ms-flex-direction: row;
    flex-direction: row;
}
/* @media (min-width: 992px) */
.navbar-expand-lg .navbar-collapse {
    display: -ms-flexbox!important;
    display: flex!important;
    -ms-flex-preferred-size: auto;
    flex-basis: auto;
}
.navbar-expand-lg>.container, .navbar-expand-lg>.container-fluid {
    -ms-flex-wrap: nowrap;
    flex-wrap: nowrap;
}
.navbar>.container, .navbar>.container-fluid {
    display: flex;
	align-items: center;
    justify-content: space-between;
}
.container {
    width: 100%;
    /* padding-right: 15px;
    padding-left: 15px; */
    /* margin-right: auto;
    margin-left: auto; */
}
.nav-aaa {
    display: block;
    /* padding: .5rem .5rem; */
    color:rgba(255,255,255,.5);
    font-size:16px;
}
.radius {
  border-radius: 50px 20px!important;
}
</style>
</head>
<body>
<!-- Navigation -->
  <c:if test="${sessionScope.adminId == null }">
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="/">CINEPHILE</a>
      <button class="navbar-toggler radius" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto navbar-expand-lg">
          <li class="nav-item active">
            <a class="nav-link" href="/" style="color:white;">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          
          <li class="nav-item">          
          	<c:if test="${login.userId == null && naverName eq null }">
            <li class="nav-aaa">GUEST 접속중</li>
            </c:if>
         </li>
          
          <li class="nav-item">        
          	<c:if test="${login.userId == null && naverName eq null}">
            <a class="nav-link" href="${path }/member/loginGET">로그인</a>
            </c:if>
          </li>
          
          <li class="nav-item">
          	<li class="nav-aaa">         
          	<c:choose>
	          	<c:when test="${login.userId!=null}">
	            	<strong>${login.userId }</strong>님 접속중
	            </c:when>
	            <c:when test="${naverName != null}">
	           		<strong>${naverName }</strong>님 접속중
	            </c:when>
        	</c:choose>
          	</li>
          </li>
          
          <li class="nav-item">
          	<c:if test="${login.userId!=null }">
            <a class="nav-link" href="${path }/member/logout">로그아웃</a>
            </c:if>
            <c:if test="${naverName!=null }">
            <a class="nav-link" href="${path }/member/naverLogout">로그아웃</a>
            </c:if>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="${path }/sboard/search_list">게시판</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/shop/cart/listCart">장바구니</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${path }/about">Contact</a>
          </li>
   		 </ul>
   		</div>
   	  </div>
   	</nav>
   	</c:if>     
	
	<!-- 관리자 -->
	<c:if test="${sessionScope.adminId != null }">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="/">CINEPHILE</a>
      <button class="navbar-toggler radius" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto navbar-expand-lg">
          <li class="nav-item active">
            <a class="nav-link" href="/" style="color:white;">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <li class="nav-aaa">관리자 접속중</li>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${path }/member/loginGET"></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${path }/admin/adminlogout">관리자 로그아웃</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${path }/shop/product/product_regist">상품등록</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/member/member_list">회원관리</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${path }/sboard/search_list">게시판</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${path }/about">Contact</a>
          </li>                
        </ul>
      </div>
    </div>
  </nav>
  </c:if>
<!-- Navigation -->
</body>
</html>