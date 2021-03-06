<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html><!-- html5 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CINEPHILE / 수정</title>
<style>
form {
	align:center;
	width:500px;
	margin: auto;
	padding-top:80px;
}
</style>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
<%@ include file="../forward/header.jsp" %>
	<br>
	<h2 style="text-align:center;">게시글 수정</h2>
		<form name="updateForm" method="post" role="form" action="/sboard/updatePost">
			<div class="form-group" style="text-align:right;">
				<dl>
					<dt>작성일자</dt>
					<dd><fmt:formatDate value="${dto.regdate }" pattern="yyyy-MM-dd a HH:mm:ss"/></dd>
					<!-- fmt 태그날짜형식 yyyy 4자리연도 MM월 dd일 a오전/오후 HH24시간제 hh12시간제, mm분 ss초 -->
				</dl>
					<span style="font-weight: bold;">조회수</span>
					<span>${dto.viewcnt }</span>
			</div>
			
			<div class="form-group">
				이름
				<input class="form-control" name="writer" id="writer" value="${dto.writer }" readonly="readonly">
			</div>
			<div class="form-group">
				제목
				<input class="form-control" name="title" id="title" size="50" value="${dto.title }">
			</div>
			<div class="form-group">
				내용
				<textarea class="form-control" name="content" id="content" rows="4" cols="50">${dto.content }</textarea>
			</div>
			<div style="text-align:center;" class="form-group">
				<!-- 게시물 번호를 hidden으로 처리 -->
				<input type="hidden" name="bno" value="${dto.bno }">
				<button class="btn btn-default" type="submit" id="updatePost">수정</button>
				<input class="btn btn-default" type="button" value="뒤로가기" onClick="goBack();"/>
				<!-- <button type="submit" id="updatePost">수정</button> -->
				<!-- <a href="javascript:history.back"><input type="button" value="뒤로가기"/></a> -->
				<!-- <button class="btn btn-default" type="submit" id="btn_back">뒤로가기2</button> -->
			</div>
		</form>
		
			<input type="hidden" name="page" value="${cri.page}">
			<input type="hidden" name="perPageNum" value="${cri.perPageNum}">		
			<input type="hidden" name="searchType" value="${cri.searchType }">
			<input type="hidden" name="keyword" value="${cri.keyword }"> 

	<script type="text/javascript">
		function goBack(){
			window.history.back(); //window.history.back();
		}
	</script>
	
	<script>
		$(document).ready(function(){	
			var formObj=$("form[role='form']");
			console.log(formObj);
			
			$("#btn_back").on("click", function(){
				self.location="/sboard/searchList?page=${cri.page}&perPageNum=${cri.perPageNum}"
						+"&searchType=${cri.searchType}&keyword=${cri.keyword}";
			});
			
		});
	</script>
	
	<!-- <script>
$(document).ready(function(){
		$("#updatePost").click(function(){
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
			document.form1.action="updatePost";
			//폼에 입력한 데이터를 서버로 전송
			document.form1.submit();
		});
});
</script> -->
	<%@ include file="../forward/footer.jsp" %>
</body>
</html>