<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CINEPHILE</title>
<style type='text/css'>
	table{
		margin: auto;
		width:600px !important
	}
</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%-- <%@ include file="../forward/nav_bar.jsp" %> --%>
<%@ include file="../forward/test.jsp" %>
</head>
<body>
<br>
<%-- <form name="form1" method="post" action="${path }/board/list.do">
	<select name="searchOption">
		<!-- 검색조건을 검색처리 후 결과화면에 보여주기 위해 c:out 출력태그 사용, 삼항연산자 -->
		<!-- 삼항연산자 문법 첫번째 연산자 ? 두번째 피연산자 : 세번째 피연산자;
			첫번째 연산자는 boolean값으로 평가됨, 첫번째 피연산자 값이 true면 두번째 피연산자 값이 반환,
			첫번째 연산자 값이 false면 세번째 피연산자 값 반환. 뒤에 나올 if 조건문과 비슷한 결과를 얻을  수 있음 -->
		<option value="all" <c:out value="${map.searchOption== 'all' ? "selected":""}"/>>제목+이름+제목</option>
		
		<option value="writer" <c:out value="${map.searchOption=='writer'?'selected':"}"/>>이름</option>
		<option value="content" <c:out value="${map.searchOption=='content'?'selected':"}"/>>내용</option>
		<option value="title" <c:out value="${map.searchOption=='title'?'selected':"}"/>>제목</option>	
	</select>
</form> --%>
<div class="container">
<h2 align="center">Q & A</h2><br>
<div style="text-align:center;">총 (${map.count })개의 게시물이 있습니다<!-- 레코드의 개수를 출력 -->&nbsp;
<button type="button" id="btnWrite" class="btn btn-default">글쓰기</button></div>
<br>

<%-- <form name="form1" method="post" action="${path }/sboard/search_list"> --%>
<form id="form1" method="post">
	<div style="text-align:center;">
		<select name=searchType><!-- eq (==) -->
			<option value="n"
				<c:out value="${cri.searchType == null ? 'selected':'' }"/>>검색 구분</option>
			<option value="t"
				<c:out value="${cri.searchType eq 't' ? 'selected':'' }"/>>Title</option>
			<option value="c"
				<c:out value="${cri.searchType eq 'c' ? 'selected':'' }"/>>Content</option>
			<option value="w"
				<c:out value="${cri.searchType eq 'w' ? 'selected':'' }"/>>Writer</option>
			<option value="tc"
				<c:out value="${cri.searchType eq 'tc' ? 'selected':'' }"/>>Title or Content</option>
			<option value="cw"
				<c:out value="${cri.searchType eq 'cw' ? 'selected':'' }"/>>Content or Writer</option>
			<option value="tcw"
				<c:out value="${cri.searchType eq 'tcw' ? 'selected':'' }"/>>Title or Content or Writer</option>
		</select>
	<input type="text" name="keyword" id="keywordInput" placeholder="검색어 입력" value="${cri.keyword }">
	<input type="submit" class="btn btn-default" value="검색">
	<!-- <button id="newBtn" class="btn btn-default">새 글</button> -->
	</div>
</form>
	
	<!-- SearchCriteria 내부에 있는 searchType과 keyword를 이용헤 화면 검색에 필요한 화면 구성 -->
	<%-- <div style="text-align:center;">
		<select name=searchType><!-- eq (==) -->
			<option value="n"
				<c:out value="${cri.searchType == null ? 'selected':'' }"/>>검색 구분</option>
			<option value="t"
				<c:out value="${cri.searchType eq 't' ? 'selected':'' }"/>>Title</option>
			<option value="c"
				<c:out value="${cri.searchType eq 'c' ? 'selected':'' }"/>>Content</option>
			<option value="w"
				<c:out value="${cri.searchType eq 'w' ? 'selected':'' }"/>>Writer</option>
			<option value="tc"
				<c:out value="${cri.searchType eq 'tc' ? 'selected':'' }"/>>Title or Content</option>
			<option value="cw"
				<c:out value="${cri.searchType eq 'cw' ? 'selected':'' }"/>>Content or Writer</option>
			<option value="tcw"
				<c:out value="${cri.searchType eq 'tcw' ? 'selected':'' }"/>>Title or Content or Writer</option>
		</select>
	<input type="text" name="keyword" id="keywordInput" placeholder="검색어 입력" value="${cri.keyword }">
	<button id="searchBtn" class="btn btn-default">검색</button>
	<!-- <button id="newBtn" class="btn btn-default">새 글</button> -->
	</div> --%>
<br>
<table class="table table-hover">
	<tr align="center">
		<th>번호</th>
		<th>제목</th>
		<th>이름</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<c:forEach var="row" items="${list }">
	<tr>
		<td>${row.bno }</td>
		<td><a href="/sboard/viewPage${pageMaker.makeSearch(pageMaker.cri.page) }&bno=${row.bno}">${row.title }</a></td>
		<td>${row.writer }</td>
		<td>
			<!-- 원하는 날짜형식으로 출력하기 위해 fmt 태그 사용 -->
			<%-- <fmt:formatDate value="${row.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
			<fmt:formatDate value="${row.regdate }" type="both" dateStyle="short" timeStyle="short"/>
		</td>
		<td>${row.viewcnt }</td>
	</tr>			
	</c:forEach>
</table>

	<!-- 단순히 게시물의 번호를 전송하는 링크에서 페이지 정보를 유지할 수 있도록 변경됨 -->
<%-- 	<div class="text-center">
		<ul class="pagination">
			<c:if test="${pageMaker.prev }">
				<li><a href="/sboard/search_list${pageMaker.makeQuery(pageMaker.startPage-1) }">&laquo;</a></li>
			</c:if>
			
			<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
			
				<li <c:out value="${pageMaker.cri.page==idx?'class=active':'' }"/>>
					<a href="/sboard/search_list${pageMaker.makeQuery(idx) }">${idx }</a>			
				</li>
			</c:forEach>
			
			<c:if test="${pageMaker.next&&pageMaker.endPage>0 }">
				<li><a href="/sboard/search_list${pageMaker.makeQuery(pageMaker.endPage+1) }">&raquo;</a></li>
			</c:if>
		</ul>
	</div> --%>
	<br>
	<!-- 페이징 처리를 아래와 같이 만들어진 문자열이 적용될 수 있도록 작성 -->
	<ul class="pagination" style="display: table; margin: auto; padding:0;">
	
		<c:if test="${pageMaker.prev }">
		<c:choose>
			<c:when test="${searchType eq null && keyword eq null }"><!-- 변수 앞에 makeSearch.searchType 해도 됨 -->
					<li><a href="/sboard/search_list${pageMaker.makeQuery(pageMaker.startPage-1) }">&laquo;</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="/sboard/search_list${pageMaker.makeSearch(pageMaker.startPage-1) }">&laquo;</a></li>
			</c:otherwise>
		</c:choose>
		</c:if>
		
		<%-- <c:if test="${pageMaker.prev }">
			<li><a href="/sboard/search_list${pageMaker.makeSearch(pageMaker.startPage-1) }">&laquo;</a></li>
		</c:if> --%>
	
<%-- 		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
		<c:choose>
			<c:when test="${searchType eq null && keyword eq null }"><!-- 변수 앞에 makeSearch.searchType 해도 됨 -->
					<a href="/sboard/search_list${pageMaker.makeQuery(idx) }">${idx }</a>
			</c:when>
			<c:otherwise>
				<li <c:out value="${pageMaker.cri.page == idx?'class =active':'' }"/>>
				<a href="search_list${pageMaker.makeSearch(idx) }">${idx }</a>
				</li>
			</c:otherwise>
		</c:choose>
		</c:forEach> --%>
		
		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
			<li <c:out value="${pageMaker.cri.page == idx?'class =active':'' }"/>>
				<a href="search_list${pageMaker.makeSearch(idx) }">${idx }</a>
			</li>
		</c:forEach>
		
		<c:if test="${pageMaker.next && pageMaker.endPage>0 }">
		<c:choose>
			<c:when test="${searchType eq null && keyword eq null }"><!-- 변수 앞에 makeSearch.searchType 해도 됨 -->
					<li><a href="/sboard/search_list${pageMaker.makeQuery(pageMaker.endPage+1) }">&raquo;</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="search_list${pageMaker.makeSearch(pageMaker.endPage+1) }">&raquo;</a></li>
			</c:otherwise>
		</c:choose>
		</c:if>
		
		<%-- <c:if test="${pageMaker.next && pageMaker.endPage>0 }">
			<li><a href="search_list${pageMaker.makeSearch(pageMaker.endPage+1) }">&raquo;</a></li>
		</c:if> --%>
	</ul>
	
	</div>

<%-- <%@ include file="../forward/test_footer.jsp" %> --%>
	
	<script>
		var result='${msg}';
		
		if(result=='success'){
			alert("처리가 완료 되었습니다");
		}
	</script>
	
	<script>
		$(document).ready(function(){
			$("#form1").attr("action", ""search_list"+'${pageMaker.makeQuery(1)}'+"&searchType="+$("select option:selected").val()+"&keyword="+$('#keywordInput').val()");
			
			/* $('#searchBtn').on("click", function(event){
					self.location="search_list"+'${pageMaker.makeQuery(1)}'+"&searchType="+$("select option:selected").val()+"&keyword="+$('#keywordInput').val();
				}); */
				
			$('#newBtn').on("click", function(ent){
				self.location="register";
			});
		});
	</script>
	
	<script>
	$(document).ready(function(){
		$("#btnWrite").click(function(){
			//페이지 주소 변경(이동)
			location.href="register";
		});
	});
	</script>
	
	<%-- <%@ include file="../forward/test_footer.jsp" %> --%>
</body>
</html>