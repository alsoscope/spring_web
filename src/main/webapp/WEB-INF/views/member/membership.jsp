<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<style type="text/css">
	.form{
		padding-top:80px;
		margin: auto;
		width:300px !important
	}
</style>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%@ include file="../forward/header.jsp" %>
</head>
<body>
	<form class="form" name="form1" method="post" action="${path }/member/insert">
	<h2 align="center">회원등록</h2><br>
		  <div class="form-group">
		    <label for="exampleInputEmail1">아이디 입력</label>
		    <input name="userId" class="form-control" id="userId" placeholder="아이디를 입력하세요">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">암호 입력</label>
		    <input type="password" name="userPw" class="form-control" id="userPw" placeholder="암호">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputEmail1">닉네임 입력</label>
		    <input name="userName" class="form-control" id="userName" placeholder="닉네임을 입력하세요">
		  </div>
		  <!-- <div class="form-group">
		    <label for="exampleInputFile">파일 업로드</label>
		    <input type="file" id="exampleInputFile">
		    <p class="help-block">여기에 블록레벨 도움말 예제</p>
		  </div> -->
		  <!-- <div class="checkbox">
		    <label>
		      <input type="checkbox"> 입력을 기억합니다
		    </label>
		  </div> -->
		  <br>
		  <div align="center">
			  <button type="button" class="btn btn-default" id="regist">회원가입</button>
			  <button type="reset" class="btn btn-default">회원가입 취소</button>
		  </div>
	</form>
	
	<!-- form 제출전 유효성 검사 -->
	<script>
		$(document).ready(function(){
			$("#regist").click(function(){
				if($("#userId").val()==""){
					alert("아이디를 입력해주세요");
					$("#userId").focus();
					return false;
				}
				if($("#userPw").val()==""){
					alert("비밀번호를 입력해주세요");
					$("#userPw").focus();
					return false;
				}
				
				if($("#userName").val()==""){
					alert("닉네임을 입력해주세요");
					$("#userName").focus();
					return false;
				}
				document.form1.submit();
			});
		});
	
	
	</script>
<%@ include file="../forward/footer.jsp" %>

<%-- <form name="form1" method="post" action="${path }/member/insert.do">
	<table>
	<div class="form-group">
		<tr>
			<label for="exampleInputEmail1">아이디</label>
			<td><input name="userId" class="form-control" id="exampleInputEmail1" placeholder="아이디  입력"></td>
		</tr>
	</div>
	<div class="form-group">
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="userPw" placeholder="비밀번호 입력"></td>
		</tr>
	</div>
	<div class="form-group">
		<tr>
			<td>닉네임</td>
			<td><input name="userName" placeholder="닉네임 입력"></td>
		</tr>
	</div>
	<div class="checkbox">
		<label>
			<input type="checkbox">입력을 기억합니다
		</label>
	</div>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="확인">
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>
</form> --%>
</body>
</html>