<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CINEPHILE / 회원 정보</title>
<style>
	form{
		margin:auto;
		padding:10px;
		width:600px;
	}
	h2{
		text-align:center;
	}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
//수정. 삭제 버튼 클릭 이벤트 jQuery코드 작성. 비밀번호 일치할 때 요청 처리 불일치하면 div에 불일치 문구 출력
	/* $(document).ready(function(){
		$("#btnUpdate").click(function(){
			if(confirm("회원 정보를 수정 하시겠습니까?")){				
			document.form1.action="${path}/member/member_update";
			document.form1.submit();
			}
		});
	}); */

	function btnUpdate(){
		if(confirm("해당 회원을 수정하시겠습니까?") == true){
			document.form1.action="${path}/member/admin_update";
			document.form1.submit();
		}else{
			return;
		}
	}
	
	$(function(){
		$("#btnDelete").click(function(){
			if(confirm("해당 회원을 삭제하시겠습니까?")){
				document.form1.action="${path}/member/admin_delete";
				document.form1.submit();
			}
		})
	});
</script>
</head>
<body>
<%@ include file="../forward/header.jsp" %>
	
	<form name="form1" method="post">
	<h2>회원 정보</h2><br>
	<table class="table table-bordered">
		<tr>
			<td>아이디</td>
			<!-- 아이디는 수정이 불가능 하도록 readonly 속성 추가 -->
			<td><input name="userId" value="${dto.userId }" readonly="readonly"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="userPw" value="${dto.userPw }"></td>
		</tr>
		<%-- <tr>
			<td>비밀번호 확인</td>
			<td><input type="password" name="userPw" value="${dto.userPw }"></td>
		</tr> --%>
		<tr>
			<td>이름</td>
			<td><input name="userName" value="${dto.userName }"></td>
		</tr>
		<tr>
			<td>회원가입일자</td>
			<td><fmt:formatDate value="${dto.userRegdate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td>회원수정일자</td>
			<td><fmt:formatDate value="${dto.userUpdatedate }" pattern="yyyy-MM-dd kk:mm:ss"/></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="수정" class="btn btn-default" onClick="Javascript:btnUpdate();">
				<input type="button" value="삭제" id="btnDelete" class="btn btn-default">
				<div style="color:red">${message }</div>
			</td>
		</tr>
	</table>
	</form>
<%@ include file="../forward/footer.jsp" %>
</body>
</html>