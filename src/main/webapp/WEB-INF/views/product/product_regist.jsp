<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품등록</title>
<style type="text/css">
	.form{
		margin: auto;
		width:300px !important
	}
</style>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%@ include file="../forward/nav_bar.jsp" %>
</head>
<body>

<h2>상품 등록</h2>

	<br>
	<form class="form" name="form1" method="post" enctype="multipart/form-data">
		  <div class="form-group">
		    <label for="exampleInputEmail1">상품명</label>
		    <input type="text" name="product_name" class="form-control" id="product_name">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">가격</label>
		    <input type="text" name="product_price" class="form-control" id="product_price">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputEmail1">상품 상세</label>
		    <textarea id="product_desc" class="form-control" rows="5" cols="60"></textarea>
		  </div>
		  <div class="form-group">
		    <label for="exampleInputEmail1">상품 이미지</label>
		    <input type="file" name="product_photo" id="product_photo">
		  </div>
		  <div align="center">
			  <button type="submit" class="btn btn-default" id="addBtn">등록</button>
			  <button type="reset" class="btn btn-default" id="btnList">목록</button>
		  </div>
	</form>
	
<script>
	$(document).ready(function(){
		//상품 등록 유효검사
		$("#addBtn").click(function(){
			var product_name=$("#product_name").val();
			var product_price=$("#product_price").val();
			var product_desc=$("#product_desc").val(); //상품 description
			var product_photo=$("#product_photo").val();
			
			//상품등록 클릭 이벤트가 발생하면 폼 내부 값 유효성 체크한 뒤 서버로 전송
			if(product_name==""){
				alert("상품명 미입력!");
				product_name.focus();
			}else if(product_price==""){
				alert("상품가격 미입력!");
				product_price.focus();
			}else if(product_desc==""){
				alert("상품상세 미입력!");
				product_desc.focus();
			}else if(product_photo==""){
				alert("상품사진 미등록!");
				product_photo.focus();
			}
			
			//상품 정보 전송
			document.form1.action="${path}/shop/product/product_insert";
			document.form1.submit();
		});
		//상품 목록 이동
		$("#btnList").click(function{
			location.href="${path}/shop/product/product_list";
		});
	});
</script>
</body>
</html>