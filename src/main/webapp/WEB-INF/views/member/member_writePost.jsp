<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원등록 성공</title>
<c:set var="path" value="${pageContext.request.contextPath }"/>
</head>
<body>
<h2>아이디 : ${vo.userId } 님 회원등록 성공</h2>
<h3>이름 : ${vo.userName }</h3>
<a href="${path}/member/loginGET.do">로그인</a>
<a href="${path}/member/list.do">회원목록</a>
</body>
</html>