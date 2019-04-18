<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Main</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="../forward/nav_bar.jsp" %>

<!-- controller로부터 리턴받은 문자열이 success이면 관리자명, 관리자id 출력 -->
<c:if test="${msg == 'success' }">
	<%-- <h2> 전달 메세지 확인 : ${msg }</h2> --%>
	<h2> 접속한 관리자 ID 확인 : ${sessionScope.userId }</h2>
	<h2> 접속한 관리자 PW 확인 : ${sessionScope.userPw }</h2>
	<h2> 접속한 관리자 NAME 확인 : ${sessionScope.userName }</h2>
</c:if>

</body>
</html>