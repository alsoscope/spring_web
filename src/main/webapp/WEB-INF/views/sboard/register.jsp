<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
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
</head>
<body>
<h2>게시글 작성</h2>
<form name="form1" method="post" action="${path }/sboard/registerPOST.do">
	<div>
		제목
		<input name="title" id="title" size="50" placeholder="제목을 입력해주세요">
	</div>
	<div>
		내용
		<textarea name="content" id="content" rows="4" cols="50" placeholder="내용을 입력해주세요"></textarea>
	</div>
	<div>
		이름
		<input name="writer" id="writer" placeholder="이름을 입력해주세요">
	</div>
	<div style="width:650px; text-align:center;">
		<button type="button" id="btnSave">작성완료</button>
		<!-- <button type="reset">취소</button> -->
		<!-- <a href="javascript:history.back">뒤뒤</a> -->
		<input type="button" value="뒤로가기" onClick="goBack();"/>
	</div>
</form>

<script type="text/javascript">
	function goBack(){
		window.history.back(); //window.history.back();
	}
</script>

</body>
</html>