<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html><!-- HTML5 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
<style>
.viewPage{
	align:center;
	margin: auto;
	padding:5% 35%;
}
</style>
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
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<%@ include file="../forward/header.jsp" %>
</head>
<body>
	<form role="form" method="post">
		<input type='hidden' name='bno' value="${dto.bno }">
		<input type='hidden' name='page' value="${cri.page}">
		<input type='hidden' name='perPageNum' value="${cri.perPageNum }">
		<input type="hidden" name="searchType" value="${cri.searchType }">
		<input type="hidden" name="keyword" value="${cri.keyword }">
	</form>
	
	<div class=viewPage>
	<h2 style="text-align:center;">게시글 보기</h2><br>
	<!-- <form name="form1" method="post"> -->
		<div class="form-group" style="text-align:right;"><!-- 원하는 날짜형식으로 출력하기 위해 fmt 태그 사용 -->
			<%-- <fmt:formatDate value="${dto.regdate }" pattern="yyyy-MM-dd a HH:mm:ss"/> --%>
			<!-- 날짜형식 yyyy 4자리연도 MM월 dd일 a오전/오후 HH24시간제 hh12시간제, mm분 ss초 -->
			
			<!-- MySQL에서 받아온 날짜(timestamp)값 jsp에서 출력하기 -->
			<dl>
				<dt>작성일자</dt>
				<dd><fmt:formatDate value="${dto.regdate }" type="date" dateStyle="default"/></dd>
			</dl>
		  		<span style="font-weight: bold;">조회수</span>
				<span>${dto.viewcnt }</span>
		</div>
		<div class="form-group">
			제목
			<input class="form-control" name="title" id="title" size="50" value="${dto.title }" readonly="readonly">
		</div>
		<div class="form-group">
			내용
			<textarea class="form-control" name="content" id="content" rows="4" cols="50" readonly="readonly">${dto.content }</textarea>
		</div>
		<div class="form-group" >
			이름
			<input class="form-control" name="writer" id="writer" value="${dto.writer }" readonly="readonly">
		</div>
		<br>
		<div style="text-align:center;">
			<!-- 게시물 번호를 hidden으로 처리 -->
			<input type="hidden" name="bno" value="${dto.bno }">
			<!-- <input type="button" id="btn_update" value="수정하기" onclick="btn_update"> -->
			
			<c:if test="${ login.userId eq dto.writer}">
				<button class="btn btn-default" type="button" id="btn_update">수정</button>
				<button class="btn btn-default" type="button" id="btnDelete">삭제</button>
			</c:if>
			<!-- <a href="javascript:history.back"><input type="button" value="뒤로가기"/></a> -->
			<!-- <input type="button" value="뒤로가기" onClick="goBack();"/> -->
			<button class="btn btn-default" type="submit" id="btn_back">뒤로가기</button><!-- viewcount 수정되어 나옴 -->
		</div>
	</div>
	
	<!-- 댓글 등록 -->
	<div class="form-group">
		<div class="col-md-12">
			<div class="box box-success">
				<div class="box-header">
					<h3 class="box-title">ADD NEW REPLY</h3>
				</div>
				<div class="box-body">
					<label for="newReplyWriter">Writer</label>
						<input class="form-control" type="text" placeholder="USER ID" id="newReplyWriter">
						<label for="newReplyText">ReplyText</label>
						<input class="form-control" type="text" placeholder="REPLY TEXT" id="newReplyText">
				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<button type="submit" class="btn btn-primary" id="replyAddBtn">ADD REPLY</button>
				</div>
			</div>		
		</div>
	</div>
	
	<!-- handlebars.js 적용 템플릿 -->
	<script id="template" type="text/x-handlebars-template">
	{{#each.}}
	<li class="replyLi" data-rno={{rno}}>
	<i class=""></i>
		<div>
			<span class="">
				<i class></i>{{prettifyDate regdate}}
			</span>
			<h3><strong>{{rno}}</strong> - {{replyer}}</h3>
			<div>{{replytext}}</div>
			<div>
				<a data-toggle="model" data-target="#modifyModal">Modify</a>
			</div>
		</div>
	</li>
	{{/each}}
	</script>
	
	<!-- 댓글 목록 The time line -->
	<ul class="timeline">
		<!-- timeline time label -->
		<li class="time-label" id="repliesDiv"><span class="bg-green">Replies List</span></li>
	</ul>
	
	<!-- 댓글 목록 페이징 처리 -->
	<div class='text-center'>
		<ul id="pagination" class="pagination pagination-sm no-margin "></ul>
	</div>

	<!-- 뒤로가기 처리 두 가지 방법 중 1번째 -->
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
			formObj.attr("action","/sboard/search_list");
			formObj.submit();
		});
		
		$("")
	});
	</script>
	
	<!-- prettifyDate regdate에 대한 handlebar 기능 확장 JavaScript 처리 -->
	<!-- helper 라는 기능을 이용해서 데이터의 상세한 처리에 필요한 기능 처리. 원하는 기능이 없을 경우, registerHelper()로 새로운 기능을 추가할 수 있다 -->
	<script>
	Handlebars.registerHelper("prettifyDate", function(timeValue){
		var dateObj=new Date(timeValue);
		var year=dateObj.getFullYear();
		var month=dateObj.getMonth()+1;
		var date=dateObj.getDate();
		return year+"/"+month+"/"+date;
	});
	
	var printData=function(replyArr, target, templateObject){
		var template=Handlebars.compile(templateObject.html());
		
		var html=template(replyArr);
		$(".replyLi").remove();
		target.after(html);
	}
	</script>
	
	<!-- 페이징 처리를 위한 함수. 내부적으로 jQuery로 JSON 타입의 데이터를 처리한다 -->
	<script>
	var bno=${dto.bno};
	var replyPage=1;
	
	function getPage(pageInfo){
		$.getJSON(pageInfo, function(data){
			printData(data.list, $("#repliesDiv"), $('#template'));
			printPaging(data.pageMaker, $('.pagination'));
		});
	}
	
	var printPaging=function(pageMaker, target){
		var str="";
		
		if(pageMaker.prev){
			str+="<li><a href='"+(pageMaker.startPage-1)+"'> << </li>";
		}
		
		for(var i=pageMaker.startPage, len=pageMaker.endPage; i<=len; i++){
			var strClass=pageMaker.cri.page==i?'class=active':'';
			str+="<li "+strClarr+"><a href='"+i+"'>"+i+"</a></li>";
		}
		
		if(pageMaker.next){
			str+="<li><a href='"+(pageMaker.endPage+1)+"'> >> </a></li>";
		}
		
		target.html(str);
	}
	
	</script>

<%@ include file="../forward/footer.jsp" %>
</body>
</html>