<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 상세</title>
</head>
<body>
	<h2>상품 상세 정보</h2>
	<table border="1">
		<tr>
			<td>
				<img src="${path }/resources/${vo.product_url}" width="340" height="300">
			</td>
			<td>
				<table border="1" style="height:300px; width:400px;">
					<tr align="center">
						<td>상품명</td>
						<td>${vo.product_name }</td>
					</tr>
					<tr align="center">
						<td>가격</td>
						<td><fmt:formatNumber value="${vo.product_price }" pattern="###,###,###" /></td>
					</tr>
					<tr align="center">
						<td>상품소개</td>
						<td>${vo.product_desc }</td>
					</tr>
					<tr align="center">
						<td colspan="2">
							
							<!-- 상품을 장바구니에 추가시키기 위해 상품id번호, 수량을 form태그 전송 -->
							<form name="form1" method="post" action="${path}/shop/cart/insert.do">
								<!-- 현재의 상품id를 입력받기 위해 hidden속성으로 처리 -->
								<input type="hidden" name="product_id" value="${vo.product_id }">
								
								<!-- select태그를 forEach문으로 1~10까지 수량을 선택할 수 있도록 처리 -->
								<select name="amount">
									<c:forEach begin="1" end="10" var="i">
										<option value="${i}">${i}</option>
									</c:forEach>
								</select>&nbsp;개
								<input type="submit" value="장바구니에 담기">
							</form>
							
							<a href="${path }/shop/product/list.do">상품목록</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>