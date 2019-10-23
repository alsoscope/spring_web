<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
	<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

<%@ include file="../forward/header.jsp" %>
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
		width:800px;
		margin: auto;  
	    border: 3px solid #b0bec5;
	    padding: 10px;
	}
</style>
</head>
<body>
	<form>
	<h2>회원 목록</h2>
	<br>
	<%-- <input type="button" value="회원등록" onclick="location.href='${path}/member/write'"> --%>
	<table id="myTable" class="display" cellspacing="0" width="100%">
	<!-- <table class="table table-hover"> -->
		<thead>
		<tr>
			<th>아이디</th>
			<th>닉네임</th>
			<th>회원가입일자</th>
			<th>회원수정일자</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="row" items="${list }">
			<tr>
				<td><a href="${path }/member/admin_view?userId=${row.userId}">${row.userId }</a></td>
				<!-- 회원정보 상세 조회를 위해 a태그 추가 -->
				<td><a href="${path }/member/admin_view?userId=${row.userId}">${row.userName }</a></td>
				<td>${row.userRegdate }</td>
				<td>${row.userUpdatedate }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</form>
	
	<script>
		jQuery.noConflict();
		
		$(document).ready( function () {
		    $('#myTable').DataTable();
		} );
	</script>
<%@ include file="../forward/footer.jsp" %>
</body>
</html>