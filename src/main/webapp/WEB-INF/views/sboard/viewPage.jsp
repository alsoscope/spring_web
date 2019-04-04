<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html><!-- HTML5 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		var formObj=$("form[role='form']");
		console.log(formObj);
		
		/* $("#btnDelete").click(function(){
			if(confirm("삭제하시겠습니가?")){
				document.form1.action="${path}/board/delete.do";
				document.form1.submit();
			}
		}); */
		$("#btnDelete").on("click",function(){
			//formObj.attr("method","get");
			formObj.attr("action","/sboard/delete.do");
			formObj.submit();
		});
  		/* $("#btn_update").click(function(){
			if(confirm("수정하시겠습니까??")){
				document.form1.action="/board/updateGet.do";
				document.form1.submit();
			}
		}); */
		$("#btn_update").on("click", function(){
			formObj.attr("action", "/sboard/updateGet.do");//.attr(attributeName, value) 2개의 인자는 속성값을 요소에 부여하는 것
			formObj.attr("method","get");
			formObj.submit();
		});
		
	  /* $("#content").keyup(function(){
	    alert(this.value);
	  }); */
  		
	});
</script>
<!--<script>
document.getElementById('content').value
</script>
<script>
 	function btn_update(){
		alert("수정하기");
		document.form1.action="/board/updateGet";
	}
</script> -->
</head>
<body>
	<h2>게시글 보기</h2>
	
	<form role="form" method="post">
		<input type='hidden' name='bno' value="${dto.bno }">
		<input type='hidden' name='page' value="${cri.page}">
		<input type='hidden' name='perPageNum' value="${cri.perPageNum }">
		
		<input type="hidden" name="searchType" value="${cri.searchType }">
		<input type="hidden" name="keyword" value="${cri.keyword }">
	</form>
	<!-- <form name="form1" method="post"> -->
		<div><!-- 원하는 날짜형식으로 출력하기 위해 fmt 태그 사용 -->
			<%-- <fmt:formatDate value="${dto.regdate }" pattern="yyyy-MM-dd a HH:mm:ss"/> --%>
			<!-- 날짜형식 yyyy 4자리연도 MM월 dd일 a오전/오후 HH24시간제 hh12시간제, mm분 ss초 -->
			
			<!-- MySQL에서 받아온 날짜(timestamp)값 jsp에서 출력하기 -->
			작성일자:<fmt:formatDate value="${dto.regdate }" type="date" dateStyle="default"/>
		</div>
		<div>
			조회수:${dto.viewcnt }
		</div>
		<div>
			제목
			<input name="title" id="title" size="50" value="${dto.title }" readonly="readonly">
		</div>
		<div>
			내용
			<textarea name="content" id="content" rows="4" cols="50" readonly="readonly">${dto.content }</textarea>
		</div>
		<div>
			이름
			<input name="writer" id="writer" value="${dto.writer }" readonly="readonly">
		</div>
		<div style="width:650px; text-align:center;">
			<!-- 게시물 번호를 hidden으로 처리 -->
			<input type="hidden" name="bno" value="${dto.bno }">
			<!-- <input type="button" id="btn_update" value="수정하기" onclick="btn_update"> -->
			<button type="button" id="btn_update">수정</button>
			<button type="button" id="btnDelete">삭제</button>
			<!-- <a href="javascript:history.back"><input type="button" value="뒤로가기"/></a> -->
			<input type="button" value="뒤로가기" onClick="goBack();"/>
			<button type="submit" id="btn_back">뒤로가기2</button>
		</div>
	
	<!-- 뒤로가기 처리 두 가지 방법 -->
	<script type="text/javascript">
	function goBack(){
		window.history.back(); //window.history.go(-1);
	}
	</script>
	
	<script type="text/javascript">
	$(document).ready(function(){
		var formObj=$("form[role='form']");
		console.log(formObj);

		$("#btn_back").on("click", function(){
			formObj.attr("method","get");
			formObj.attr("action","/sboard/searchList");
			formObj.submit();
		});
		
		$("")
	});
	</script>

</body>
</html>