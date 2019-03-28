<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품목록</title>
</head>
<body>
<!-- 상품 목록 화면에서 상품ID번호, 상품 이미지, 상품명, 가격을 리스트로 출력 -->
<table border="1">
	<tr>
		<th>상품번호</th>
		<th>상품이미지</th>
		<th>상품명</th>
		<th>가격</th>
	</tr>
	<c:forEach var="row" items="${list }">
	<tr>
		<td>
			${row.product_id }
		</td>
		<td>
			<!-- 상품이미지와 상품명을 클릭하면 해당 상품의 상세 화면으로 이동할 수 있도록 <a href/>태그로 링크 -->
			<a href="${path }/shop/product/detail/${row.product_id}">
				<img src="${path}/images/${row.product_url}" width="120px" height="110px">
			</a>
		</td>
		<td>
			<a href="${path }/shop/product/detail/${row.product_id }">${row.product_name }</a>
		</td>
		<td>
			<!-- JSLT fmt태그로 숫자 포맷 변경. 상품가격의 가독성을 높이기 위해 숫자3자리마다 콤마, 찍어주도록 처리 -->
			<fmt:formatNumber value="${row.product_price }" pattern="###,###,###" />
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>