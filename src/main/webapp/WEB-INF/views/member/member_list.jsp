<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%@ include file="../forward/nav_bar.jsp" %>
<!-- <style type="text/css">
	form{
		align:center;
	}
 	table{
		width:300px;
		height:200px;
		border: 1px solid #bcbcbc;
		margin-left: auto;
    	margin-right: auto;
		border-collapse: collapse;
	}
	th, td{
		padding: 10px;
	}
	h2{
		align:center;
	}
</style> -->
<style type="text/css">
	form{
		width:800px !important
	}
</style>
</head>
<body>
<%-- <%@ include file="../include/member_menu.jsp" %> --%>

	<h2>회원 목록</h2>

	<form>
	<input type="button" value="회원등록" onclick="location.href='${path}/member/write'">
	<table class="table table-hover">
		<tr>
			<th>아이디</th>
			<th>닉네임</th>
			<th>회원가입일자</th>
		</tr>
		<c:forEach var="row" items="${list }">
			<tr>
				<td>${row.userId }</td>
				<!-- 회원정보 상세 조회를 위해 a태그 추가 -->
				<td><a href="${path }/member/view.do?userId=${row.userId}">${row.userName }</a></td>
				<td>${row.userRegdate }</td>
			</tr>
		</c:forEach>
	</table>
	</form>
	
</body>
</html>