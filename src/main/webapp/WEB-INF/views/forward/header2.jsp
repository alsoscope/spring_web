<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Navigation -->
  <c:if test="${sessionScope.adminId == null }">
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top z-depth-0">
    <div class="container">
      <a class="navbar-brand" href="/">CINEPHILE</a>
      <button class="navbar-toggler radius" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto navbar-expand-lg">
          <li class="nav-item">
            <a class="nav-link" href="/" style="color:white;">Home
              <!-- <span class="sr-only"></span> -->
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
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${path }/sboard/search_list">게시판</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/shop/cart/listCart">장바구니</a>
          </li>
          
          <li class="nav-item">
	          <c:if test="${ login.userId!=null || naverName ne null}">
		            <a class="nav-link" href="/member/member_view">내 정보</a>
	          </c:if>
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