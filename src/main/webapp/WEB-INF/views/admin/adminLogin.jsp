<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 로그인</title>
<style type="text/css">
	form{
		display: block;
		margin-left: auto;
		margin-right: auto;
  		}
  	div{
  		width: auto;
  		margin:0 auto !important;
		align:center !important;
  	}
</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="../forward/nav_bar.jsp" %>
<br><br>

<div style="align:center;">
<h3 class="bg-info" style="width:200px;">관리자 로그인</h3>
<br>
<form class="form-inline" name="form1">
  <div class="form-group">
&nbsp;&nbsp;<label for="exampleInputName2">관리자 아이디</label>
    <input type="text" class="form-control" id="userId" name="userId" placeholder="ID 입력">
  </div>
  <div class="form-group">
&nbsp;&nbsp;<label for="exampleInputEmail2">관리자 비밀번호</label>
    <input type="password" class="form-control" id="userPw" name="userPw" placeholder="password 입력">
  </div>
&nbsp;<button type="submit" class="btn btn-default" id="btnLogin">관리자 확인
  	<c:if test="${msg=='failure' }">
		<div style="color:red">
			아이디 또는 비밀번호가 일치하지 않습니다.
		</div>
	</c:if>
	<c:if test="${msg=='logout' }">
		<div style="color:red">
			관리자 로그아웃
		</div>
	</c:if>
  </button>
</form>
</div>

<script>
	$(document).ready(function(){
		$("#btnLogin").click(function(){
			//태그.val() : 태그에 입력된 값
			//태그.val("값") : 태그의 값을 변경
			var userId=$("#userId").val();
			var userPw=$("#userPw").val();
			if(userId==""){
				alert("아이디를 입력하세요");
				$("#userId").focus(); //입력 포커스 이동
				return; //함수 종료
			}
			if(userPw==""){
				alert("비밀번호를 입력하세요");
				$("#userPw").focus();
				return;
			}
			//폼 내부의 데이터를 전송할 주소
			document.form1.action="${path}/admin/logincheck";
			//제출
			document.form1.submit();
		});
	});
</script>

<%-- 	<form name="form1" method="post">
		<table border="1" width="400px">
			<tr>
				<td>아이디</td>
				<td><input name="userId" id="userId"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="userPw" id="userPw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" id="btnLogin">로그인</button>
				<c:if test="${msg=='failure' }">
					<div style="color:red">
						아이디 또는 비밀번호가 일치하지 않습니다.
					</div>
				</c:if>
				<c:if test="${msg=='logout' }">
					<div style="color:red">
						로그아웃 되었습니다.
					</div>
				</c:if>
				</td>
			</tr>
		</table>
	</form> --%>

</body>
</html>