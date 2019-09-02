<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
<style>
body {
    background: #ddd;
}
form {
	align:center;
	width:500px;
	margin: auto;
	padding-top:100px;
}
</style>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
<%@ include file="../forward/header.jsp" %>
<form name="form1" method="post" action="${path }/sboard/registerPOST">
	<h2>게시글 작성</h2>
	<br>
	
	<!-- 로그인한 사용자면 가입시 저장한 닉네임(userName)으로 치환한다 -->
	<c:if test="${login.userName == null }">
		<div class="form-group">
			이름
			<input class="form-control" name="writer" id="writer" placeholder="이름을 입력해주세요">
		</div>
	</c:if>
	
	<c:if test="${login.userName != null }">
		<div class="form-group">닉네임 : ${login.userName }</div>
	</c:if>
	
	<div class="form-group">
		제목
		<input class="form-control" name="title" id="title" size="50" placeholder="제목을 입력해주세요" autofocus>
	</div>
	<div class="form-group">
		내용
		<textarea class="form-control" name="content" id="content" rows="4" cols="50" placeholder="내용을 입력해주세요" autofocus></textarea>
	</div>
	
	<div style="text-align:center;">
		<button class="btn btn-default" type="button" id="btnSave">작성완료</button>
		<button class="btn btn-default" type="reset">작성취소</button>
		<!-- <button class="btn btn-default" type="submit" id="btn_back">뒤로가기</button> --><!-- viewcount 수정되어 나옴 -->
		<!-- <a href="javascript:history.back">뒤뒤</a> -->
		<input class="btn btn-default" type="button" value="뒤로가기" onClick="goBack();"/>
	</div>
</form>

<%@ include file="../forward/footer.jsp" %>

	<script>
	$(document).ready(function(){
		$("#btnSave").click(function(){
			var title=$("#title").val();
			var content=$("#content").val();
			var writer=$("#writer").val();
			if(title==""){
				alert("제목을 입력하세요");
				document.form1.title.focus();
				return;
			}
			if(content==""){
				alert("내용을 입력하세요");
				document.form1.content.focus();
				return;
			}
			if(writer==""){
				alert("이름을 입력하세요");
				document.form1.writer.focus();
				return;
			}
			//폼에 입력한 데이터를 서버로 전송
			document.form1.submit();
			
			/* $(document).ready(function () {
		  		  if ($("content").val() != "") {
		  		    alert($("content").val());
		  		  }
		  		}); */
		});
	});
	</script>

	<script type="text/javascript">
/* 	$(document).ready(function(){
		var formObj=$("form[role='form']");
		console.log(formObj);

		$("#btn_back").on("click", function(){
			formObj.attr("method","get");
			formObj.attr("action","/sboard/search_list");
			formObj.submit();
		});
		
		$("")
	}); */
	</script>
	
	<script type="text/javascript">
		function goBack(){
			window.history.back(); //window.history.back();
		}
	</script>

</body>
</html>