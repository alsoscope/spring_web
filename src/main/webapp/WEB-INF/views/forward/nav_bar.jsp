<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<style>
/* .bg-dark {
    background-color: #343a40 !important;
} */
</style>
<nav class="navbar navbar-default bg-dark">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${path}/">CINEPHILE</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">영화목록<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="product_list_korean">한국 영화</a></li>
            <li class="divider"></li>
            <li><a href="#">해외 영화</a></li>
            <li class="divider"></li>
            <li><a href="#">기타 영상</a></li>           
       	  </ul>
        </li>
        <li><a href="${path }/sboard/search_list">게시판</a></li>
       	<li><a href="#">장바구니</a></li>
      </ul>
      
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">검색</button>
      </form>
     
      <!-- <ul class="nav navbar-nav navbar-right"> -->
      
	<ul class="nav navbar-nav navbar-right">	
        <li>
				<c:if test="${login.userId==null }">
				GUEST 접속중
				<li><a href="${path }/member/loginGET">로그인</a></li>
				</c:if>
		</li>
		<li>
				<c:if test="${login.userName!=null }">
				${login.userId }님이 로그인 중입니다.
				
				<li><a href="${path }/member/logout">로그아웃</a></li>
				</c:if>
		</li>

	<!-- 관리자 로그인 -->
		<li class="active">
			<c:if test="${sessionScope.adminId == null }">
				<li><a href="${path}/admin/adminlogin">관리자 로그인</a></li>
			</c:if>
		</li>
		
		<li class="active"><a href="../about.html">About<span class="sr-only">(current)</span></a></li>
	</ul>
	
	<!-- 관리자 권한일 경우 -->
	<ul class="nav navbar-nav navbar-right">
     <li class="dropdown">
     	<c:if test="${sessionScope.adminId != null }">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">관리자 도구<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="${path }/shop/product/product_write">관리자 상품등록</a></li>
            <li class="divider"></li>
            <li><a href="/member/member_list">관리자 회원관리</a></li>
            <li class="divider"></li>
            <li><a href="${path }/admin/adminlogout">관리자 로그아웃</a></li>
          </ul>
         </c:if>
        </li>
       </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>