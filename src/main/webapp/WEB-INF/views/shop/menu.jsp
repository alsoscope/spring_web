<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 상단 메뉴 -->

<!-- 일반 메뉴 -->
<a href="${path }/board/list.do">게시판</a>
<a href="${path }/upload/uploadForm">업로드</a>
<a href="${path }/upload/uploadAjax">업로드(Ajax)</a>
<a href="${path }/shop/product/list.do">상품목록</a>
<a href="${path }/shop/cart/list.do">장바구니</a>

<!-- 관리자 권한일 경우 -->
<c:if test="${sessionScope.adminId!=null }">
	<a href="${path }/shop/product/write.do">상품등록</a>
</c:if>

<!-- 로그인, 로그아웃 -->
<c:choose>
	<c:when test="${sessionScope.userId==null }">
		<a href="${path }/member/login.do">로그인</a>
		<a href="${path }/admin/login.do">관리자 로그인</a>
	</c:when>
	<c:otherwise>
		${sessionScope.userName }님이 로그인 중입니다.
		<a href="${path }/member/logout.do">로그아웃</a>
	</c:otherwise>
</c:choose>
</body>
</html>