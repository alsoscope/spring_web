<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CINEPHILE</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
	span{
		text-align:left !important;
	}
	.about{
		padding-top:40px;
	}
</style>
<body>
<%@ include file="./forward/header.jsp" %>

<div class="about" style="text-align:center;">
	<b>CINEPHILE</b> 은 영화 스트리밍 서비스를 제공하는 웹사이트입니다.<p>
	원하는 컨텐츠를 구매하여 일정 기간 동안 시청하실 수 있습니다.<p><br><br>
	
	<img src="/resources/images/movie/about.jpg"><br>
	<span style="color:grey;">Illustration by Alex Castro / The Verge</span>
</div>

<%@ include file="./forward/footer.jsp" %>
</body>
</html>