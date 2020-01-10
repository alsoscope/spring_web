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
		if(confirm("수정 하시겠습니까?") == true){
			document.form1.action="${path}/member/member_update";
			document.form1.submit();
		}else{
			return;
		}
	}
	
	$(function(){
		$("#btnDelete").click(function(){
			if(confirm("회원 탈퇴하시겠습니까?")){
				document.form1.action="${path}/member/member_delete";
				document.form1.submit();
			}
		})
	});
</script>
</head>
<body>
<%@ include file="../forward/header.jsp" %>
	
	<!-- 결제한 내역이 있다면 보여줌 -->

		<table border="1">
			<c:forEach items="${list }" var="list" varStatus="i">
				<tr>
					<th>상품명</th>
					<th>결제 금액</th>
					<th>대여시작일</th>
					<th>대여기간 </th>
					<th>대여만료일</th>
				</tr>
			
			<tr>		
				<td>${list.product_name }</td>
				<td><fmt:formatNumber value="${list.allSum }"/></td>
				<%-- <td><fmt:formatNumber value="${list }"/>일</td> --%>
				<fmt:parseDate value="${list.insertDate }" var="insertDate" pattern='yyyy-MM-dd'></fmt:parseDate>
				<td><fmt:formatDate value="${insertDate }" pattern='yyyy-MM-dd'/>일</td>
				<td><fmt:formatNumber value="${list.amount }"/>일</td>
				<td></td>
			</tr>
			</c:forEach>
		</table>

	
	<!-- 회원 정보 조회/수정/탈퇴 -->
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
			<td><input type="password" name="userPw"></td>
		</tr>
		<!-- <tr>
			<td>비밀번호 확인</td>
			<td><input type="password" name="userPw"></td>
		</tr> -->
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
				<input type="button" value="회원탈퇴" id="btnDelete" class="btn btn-default">
				<div style="color:red">${message }</div>
			</td>
		</tr>
	</table>
	</form>
<%@ include file="../forward/footer.jsp" %>
</body>
</html>