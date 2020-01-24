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
	#order{
		margin: auto;
	    width: 50%;
	    border: 3px solid #b0bec5;
	    padding: 10px;
	}
	#form1{
		margin:auto;
		padding:10px;
		width:500px;
	}
	#table2{
		margin:auto;
		padding:10px;
		width:800px;
	}
	h2{
		text-align:center;
	}
	.footer{
	width: 100%;
    position:relative !important; 
    bottom:0;
    left: 0;
    right:0;
    top:450px;  
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
	<br><br>
	<form id="order">
	<input type="hidden" name="count" value="${map.count }">
	
	<c:if test="${map.count == 0 }">
		<h4>대여 품목이 없습니다.</h4>
	</c:if>
	
	<c:if test="${map.count != 0}">	
	<!-- 결제한 내역이 있다면 보여줌 -->
		<h2>대여 목록</h2><br>
		<%-- <c:forEach items="${list }" var="list" varStatus="status"> --%>
		<table class="table table-bordered" id="table2">
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
				
				<!-- DB에서 가져온 날짜를 JSP 화면에 띄우기 : 대여시작일-->
				
				<!-- 1. parseDate로 String → Date 형 변환 -->
				<fmt:parseDate value="${list.insertDate }" var="insertDate" pattern='yyyy-MM-dd'></fmt:parseDate>
				<!-- 2. parseNumber로 Date → Number 형 변환 -->
				<%-- <fmt:parseNumber value="${list.insertDate }" integerOnly="true"/> --%>
				
				<!-- 3. 숫자로 변환된 두 데이터를 연산 후 formatNumber 등으로 원하는 포맷과 단위로 화면에 출력 -->
				<td><fmt:formatDate value="${insertDate }" pattern='yyyy-MM-dd'/>일</td>

				<td><fmt:formatNumber value="${list.amount }"/>일</td>
				
				<%-- <fmt:parseDate value="${list.exprDate }" var="exprDate" pattern='yyyy-MM-dd'></fmt:parseDate> --%>
				<td>${list.exprDate }일</td>
				
				<%-- <fmt:parseDate value="${expr[status.index].exprDate }" var="exprDate" pattern='yyyy-MM-dd'></fmt:parseDate>
				<td><fmt:formatDate value="${exprDate }" pattern='yyyy-MM-dd'/>일</td> --%>
				<!-- <td>일</td> -->
			</tr>
		</c:forEach>
		</table>
	</c:if>
	</form>
	<br><br>
	
	<!-- 회원 정보 조회/수정/탈퇴 -->
	<form name="form1" method="post" id="form1">
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