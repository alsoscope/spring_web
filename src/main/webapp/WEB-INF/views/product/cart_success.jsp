<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CINEPHILE / 대여 성공</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
	body{
		align:center;
	}
</style>
</head>
<%@ include file="../forward/header.jsp" %>
<body>

	대여상황

<table border="1">
<c:forEach var="row" items="${map.list }" varStatus="i">
	<tr>
		<th>상품명</th>
		<th>결제 금액</th>
		<th>총 대여일</th>	
	</tr>
	
	<tr>		
		<td>${row.product_name }</td>
		<td><fmt:formatNumber value="${map.allSum }" type="currency"/>원</td>
		<td><fmt:formatNumber value="${row.amount }"/>일</td>
	</tr>
</c:forEach>
</table>

	대여일 수가 경과하면 자동으로 구독 취소가 됩니다.<p>
	이용해주셔서 감사합니다.

<div style="text-align:center;">
	내 정보에서 확인하기<p>
	<button>이동</button>
</div>

<%@ include file="../forward/footer.jsp" %>
</body>
</html>