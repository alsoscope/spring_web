<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장바구니 목록</title>
<style>
	#form1{
		margin: auto;
	    width: 60%;
	    border: 3px solid #b0bec5;
	    padding: 10px;
		/* padding-top:20px;
		align:center; */
	}
</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<%@ include file="../forward/header.jsp" %>
<body>
<!-- 장바구니 화면/장바구니 목록 -->
	<form name="form1" id="form1" method="post" action="${path }/shop/cart/update">
	<h2>장바구니</h2>
	
	<%-- <c:choose>
	<c:when test="${empty map.count }">
		<h4>장바구니가 비어있습니다.</h4>
	</c:when> --%>
	<c:if test="${map.count == 0 }">
		<h4>장바구니가 비어있습니다.</h4>
	</c:if>
	
	<%-- <c:otherwise> --%>
	<c:if test="${map.count != 0}">	
		<!-- 장바구니에 담긴 상품수량 업데이트 처리 -->
			<table  border="1">
				<tr>
					<th>상품명</th>
					<th>단가</th>
					<th>수량</th>
					<th>금액</th>
					<th>취소</th>
				</tr>
				
				<!-- forEach 반복문을 통해 장바구니 레코드 출력 -->
				<c:forEach var="row" items="${map.list }" varStatus="i">
				<tr>
					<td>${row.product_name }</td>
					<td style="width:80px" align="right">
						<fmt:formatNumber pattern="###,###,###" value="${row.product_price }"/>
					</td>
					<td>
						<!-- 상품 수량 변경, 최소값 1로 설정. 상품수량 변경을 위해 상품id번호를 hidden 속성으로 입력 -->
						<!-- name속성을 배열첨자[]로 하지 않는 이유는, 동일한 name속성이 반복적으로 입력되면 서버에서 배열로 쌓아서 작업을 처리해주기 때문.
							즉, 화면으로부터 동일한 name속성의 값들이 CartController의 updateCart() 장바구니 수정 메서드의
							매개변수 int[] amount, int[] product_id에 배열로 저장되어 처리된다 -->
						<input type="number" style="width:40px" name="amount" value="${row.amount }" min="1">
						<input type="hidden" name="product_id" value="${row.product_id }">
					</td>
					<td style="width:100px" align="right">
						<fmt:formatNumber pattern="###,###,###" value="${row.money }"/>
					</td>
					<td>
						<!-- 장바구니에 담긴 상품 삭제 처리 -->
						<a href="${path }/shop/cart/delete?cartId=${row.cartId}">삭제</a>
					</td>
				</tr>				
				</c:forEach>
				<tr>
					<td colspan="5" align="right">
						대여 금액 합계 : <fmt:formatNumber pattern="###,###,###" value="${map.sumMoney }"/><br>
						수수료 : ${map.fee }<br>
						전체 주문 금액 : <fmt:formatNumber pattern="###,###,###" value="${row.allSum }"/>
					</td>
				</tr>
			</table>
			<input type="hidden" name="count" value="${map.count }">
			<button type="submit" id="btnUpdate">수정</button>		
	</c:if>
	<%-- </c:otherwise>
	</c:choose> --%>
	
	<br>
	<input type="button" class="btn btn-default" value="뒤로가기" onClick="goBack();"/>
	<!-- <button type="button" class="btn btn-default" id="btnList">뒤로</button> -->
	</form>

<script type="text/javascript">
	function goBack(){
		window.history.back(); //window.history.go(-1);
	}
</script>
	
<script>
	/* $(document).ready(function(){
		//리스트 페이지로 이동
		$("#btnList").click(function(){
			location.href="${path}/shop/product/product_";
		});
	}); */
</script>

<%@ include file="../forward/footer.jsp" %>
</body>
</html>