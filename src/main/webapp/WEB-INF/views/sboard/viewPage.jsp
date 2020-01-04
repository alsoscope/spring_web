<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html><!-- HTML5 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CINEPHILE / 게시글</title>
<style>
.viewPage{
	align:center;
	margin: auto;
	padding:5% 35%;
	cursor: default !important;
}
.footer{
	width: 100%;
    position:static !important; 
    bottom:0;
    left: 0;
    right:0;
}
.pt-5, .py-5 {
    padding-top: 2rem !important;
    padding-bottom: 2rem !important;
}
.pagination li.active {
    background-color: #b0bec5 !important;
}
.navbar{
	padding: 0rem !important;
	border-radius: 4px !important;
	height: 58px !important;
	line-height: 50px !important;
}
.navbar-brand{
	font-size: 21px !important;
}
</style>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.2/handlebars.js"></script>

<!-- Materialize -->
<!-- Compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- Compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.js"></script>
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="../resources/css/nav_bar.css">

<!-- 합쳐지고 최소화된 최신 CSS -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
부가적인 테마
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
합쳐지고 최소화된 최신 자바스크립트
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>

<%-- <%@ include file="../forward/header.jsp" %> --%>
<%@ include file="../forward/header2.jsp" %>
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
	<h4 style="text-align:center;">게시글 보기</h4><br>
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
		<div class="form-group" style="align:right;">
			이름
			<input class="form-control" name="writer" id="writer" value="${dto.writer }" readonly="readonly" style="width:180px; color:#4d4d4d;">
		</div>
		<div class="form-group">
			제목
			<input class="form-control" name="title" id="title" size="50" value="${dto.title }" readonly="readonly" style="color:#4d4d4d;">
		</div>		
		<div class="form-group">
		</div>
			내용
			<textarea class="form-control" name="content" id="content" rows="4" cols="50" disabled="disabled">${dto.content }</textarea>
		<br>
		<div style="text-align:center;">
			<!-- 게시물 번호를 hidden으로 처리 -->
			<input type="hidden" name="bno" value="${dto.bno }">
			<!-- <input type="button" id="btn_update" value="수정하기" onclick="btn_update"> -->
			
			<c:if test="${ login.userId eq dto.writer}">
				<button class="btn btn-default" type="button" id="btn_update">수정</button>
				<button class="btn btn-default" type="button" id="btn_delete">삭제</button>
			</c:if>
			<!-- <a href="javascript:history.back"><input type="button" value="뒤로가기"/></a> -->
			<!-- <input type="button" value="뒤로가기" onClick="goBack();"/> -->
			<button class="btn btn-default blue-grey lighten-3" type="submit" id="btn_back">뒤로가기</button><!-- viewcount 수정되어 나옴 -->
		</div>
	
		<!-- 댓글 등록 -->
		<!-- <div>
			<div>
				<div>
					<h3>ADD NEW REPLY</h3>
				</div>
				<div>
					<label for="newReplyWriter">Writer</label>
						<input class="form-control" placeholder="USER ID" name="newReplyWriter" id="newReplyWriter" >
					<label for="newReplyText">ReplyText</label>
						<input class="form-control" placeholder="REPLY TEXT" name="newReplyText" id="newReplyText">
				</div><p>
				<div>
					<button type="submit" class="btn btn-default" id="replyAddBtn">ADD REPLY</button>
				</div>
			</div>		
		</div> -->
		
	 <br>
	 
	 <!-- 댓글 등록. 로그인 한 회원만 댓글 작성 -->
	 <c:if test="${ login.userId != null}">
	 <div class="row">
			<form class="col s12" id="replyForm">
				
					<div>
						<h5>댓글 등록</h5>
					</div>
						<%-- <div class="row">
							<div class="input-field col s6">
								<input id="input_text" name="newReplyWriter" type="text" data-length="10" value="${login.userName}" style="color:#4d4d4d;">
								<div class="form-group">
								<label for="input_text" style="color:blue-grey;">Writer ${login.userName}</label></div>
							</div>
						</div> --%>
						
						<div class="row">
							<div class="input-field col s6">
								<!-- <input id="input_text" name="newReplyWriter" type="text" data-length="10" > -->
								<label for="input_text" style="color:black;">글쓴이  ${login.userId }</label>
							</div>
						</div>
				
				<div class="row">
					<div class="input-field col s12">
						<!-- <textarea id="newReplyText" name="newReplyText" class="materialize-textarea" data-length="120" ></textarea> -->
						<input id="textarea2" name="newReplyText" class="materialize-textarea" type="text" data-length="120" >
						<label for="textarea2" style="color:blue-grey;">ReplyText</label>
					</div>
				</div>
				
				<!-- replyer 댓글 비밀번호 -->
				<div class="row" style="align:right;">
		        <div class="col s12">
		          	비밀번호:
		          <div class="input-field inline">
		            <input id="email_inline" name="replyPw" type="password" onkeydown="check_replyPw()">
		            <label for="email_inline">Password</label>
		          </div>
		        </div>
		      </div>
				
			<div style="float: right;">
				<a href="javascript:void(0);" class="btn-floating btn-large waves-effect waves-light blue-grey material-icons"
					onClick="replyAddBtn(this);">+</a>
				
				<!-- <i class="material-icons">+</i></a> -->
				
				<!-- <button type="button" class="btn-floating btn-large waves-effect waves-light blue-grey material-icons"
				id="replyAdd"><i class="material-icons">+</i></button> -->
			</div>			
		</form>
		</div>
		</c:if>
		
	<!-- 지속적인 목록 갱신. <li>가 반복적으로 구성, 이를 <ul>태그의 내용물로 추가하는 방식. 문자열로 이루어지기에 지저분한 코드. JS 템플릿을 적용. -->
	<!-- 댓글 목록 The time line -->
	<a id="trigger" class="waves-effect waves-light btn-large blue-grey lighten-3">
		<i class="material-icons left">view_list</i>댓글 목록 보기
	</a>
		<ul class="timeline">
			<li id="repliesDiv">
			<!-- <span class="btn btn-default" id="replyList">Replies List</span> -->
			</li>
		</ul>
	<br>
	
	<!-- 댓글 목록 페이징 처리 -->
	<div class='text-center'>
		<ul id="pagination" class="pagination pagination-sm no-margin"></ul>
	</div>
	
	</div><!-- <div class=viewPage> -->
	
	<!-- handlebars.js 적용 템플릿 코드. 기존 div의 ul, li의 반복적인 문자열을 위한 템플릿.
	템플릿 선언부의 {{each.}}를 이용하는데, 'each'는 배열의 루프를 순환함. '.'은 배열을 도는 동안의 해당 객체를 의미함
	arr이라는 배열이라면 arr[i] 번째의 객체를 의미할 때 사용함 -->
	<script id="template" type="text/x-handlebars-template">
	{{#each.}}
	<li class="replyLi" data-rno={{rno}} data-replyer={{replyer}}>
		<br>
		<div>
			<dt>{{rno}} {{prettifyDate regdate}}</dt>
			<i class="material-icons prefix">account_circle&nbsp;&nbsp;</i>
			<span class="timeline-replyer">{{replyer}}</span>
			<br>
			<i class="material-icons prefix">mode_comment&nbsp;</i>
			<span class="timeline-body">{{replytext}}</span>
			<div>
				<button data-target="modal1" data-replyer="{{replyer}}" class="waves-effect waves-light btn-small blue-grey btn modal-trigger">
				<i class="material-icons left">edit</i>수정</button>
			</div>
		</div>
	</li>
	{{/each}}
	</script>
<!-- 				<a class="waves-effect waves-light btn-small blue-grey modal-trigger" id="modifyModal">
				<i class="material-icons left">edit</i>수정</a> -->
	
	<!-- 수정, 삭제 Modal Structure --------------------------------------->
	<div id="modal1" class="modal">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal">&times;</button>-->
				<!-- HTML Entities : &times; means 'x' -->
				<h5 class="modal-title"></h5><!-- <h4 class="modal-replyer"></h4> -->
			</div>
			<div class="modal-body" data-rno>
				<!-- <p><i class="material-icons prefix">account_circle</i><input type="text" name="replyer" id="replyer"></p> -->
				<i class="material-icons prefix">mode_comment</i><p><input type="text" id="replytext"></p>
			</div>
			<form name="pwConfirm">
			<div class="modal-footer">
				<div class="row">					
				<!-- 댓글 작성자만 비밀번호를 이용해 수정, 삭제 할 수 있다. -->
			        <div class="col s12">
			          	비밀번호:
			          <div class="input-field inline">
			            <input id="replyPw" type="password">
			            <label for="email_inline">Password</label>
			          </div>
			          
			          <div>${message }</div>
			          
			        <button type="button" id="replyModBtn">MODIFY</button>
					<button type="button" id="replyDelBtn">DELETE</button>
				
						<button type="button" class="modal-close" data-dismiss="modal">CLOSE</button>				
			        </div>
			      </div>
			</div>
			</form>		
		</div>
	</div>
	
	<script>
	
	/* function pwConfirm(){
		document.modal1.action="/replies/replyModal";
		document.modal1.submit();
		console.log("/replies/replyModal");
	} */
	
	//각 댓글의 버튼 이벤트. 수정/삭제를 위한 Modal OPEN-------------------------------------
	$(".timeline").on("click", ".replyLi", function(event){
		var reply=$(this);
		$("#replytext").val(reply.find(".timeline-body").text());
		$(".modal-title").html(reply.attr("data-rno"));
		
		/* alert($(this).parent().find('replyer')); */
		
		//event.relatedTarger : 해당 이벤트와 관련된 다른 DOM 요소 선택
		//replyer=$(event.relatedTarget).data('replyer');
		//alert("replyer : " + replyer);
		
		/* $("#replyer").val(reply.find(".timeline-replyer").text());		
		$(".modal-replyer").html(reply.attr("data-replyer")); */
		
		/* var replyer = "<c:out value="${replyer}"></c:out>";
		console.log("replyer : " + replyer); */
		
		/* $.ajax({
			type:'get',
			url:'/replies/' + bno + "/1",
			contentType:'application/json; charset=utf-8',
			dataType:'text',
			data:JSON.stringify({
				bno:bno,
				replyer:replyer,
				replytext:replytext,
				replyPw:replyPw
			}),
			success:function(data){
				console.log("/replies/bno/page");
				console.log("result : " + data);
				
				var jsonObj=JSON.stringify(data);
				console.log("------------modal click------------");
				console.log(jsonObj);

			}
		}); */
		
	});
	
	$('.modal').modal({
	      dismissible: true, // Modal can be dismissed by clicking outside of the modal
	      opacity: .5, // Opacity of modal background
	      inDuration: 300, // Transition in duration
	      outDuration: 200, // Transition out duration
	      startingTop: '4%', // Starting top style attribute
	      endingTop: '10%', // Ending top style attribute
	      ready: function(modal, trigger) { // Callback for Modal open. Modal and trigger parameters available.
	        alert("Ready");
	        console.log(modal, trigger);
	      },
	      complete: function() { alert('Closed'); } // Callback for Modal close
	    }
	);
	</script>
	
	<!--  Handlebars 템플릿 ------------------------------------- -->
	<!-- prettifyDate regdate에 대한 handlebar 기능 확장 JavaScript 처리 -->
	<!-- helper 라는 기능을 이용해서 데이터의 상세한 처리에 필요한 기능 처리. 원하는 기능이 없을 경우, registerHelper()로 새로운 기능을 추가할 수 있다 -->
	<script>
	Handlebars.registerHelper("prettifyDate", function(timeValue){
		//date 객체 : 날짜, 시간을 위한 메소드를 제공하는 빌트인 객체이면서 생성자 함수이다.
		var dateObj=new Date(timeValue);
		var year=dateObj.getFullYear();
		var month=dateObj.getMonth()+1;
		var date=dateObj.getDate();
		return year+"/"+month+"/"+date;
	});
	
	//댓글 목록 확인
	var printData=function(replyArr, target, templateObject){
		//핸들바 템플릿을 가져와서 precompile한다
		var template=Handlebars.compile(templateObject.html());
		//핸들바 템플릿에 데이터를 바인딩해서 HTML 생성
		var html=template(replyArr);
	
		//JSON 객체 확인. Array 배열에 데이터가 담겨있음.
		console.log("------------replyArr result------------");
		console.log(replyArr);
		//console.log("replyArr[0].replyer : " + replyArr[0].replyer);
		
		//JSON객체로 파싱
		/* var jsonObj=JSON.parse(replyArr);
		console.log("------------replyArr result------------");
		console.log(jsonObj); */	
		
		//JSON 포맷 데이터를 문자열로 파싱
		var jsonObj=JSON.stringify(replyArr);
		console.log("------------stringify result------------");
		console.log(jsonObj);
		
		$(".replyLi").remove();
		//.after() 메소드는 선택한 요소의 바로 뒤쪽에 새로운 요소나 콘텐츠를 추가한다.
		target.after(html);//생성된 HTML을 뿌려줌
	}
	
	//댓글 목록의 이벤트 처리.Replies List 버튼을 클릭하면 댓글 목록을 가져온다.
	/* $("#repliesDiv").on("click", function(){
		//목록의 size()를 체크하는 코드는 목록을 가져오는 버튼이 보여지는 <li>만 있는 경우, 1페이지의 댓글 목록을 가져오기 위해 처리한 코드.
		if($(".timeline li").length > 1){
			return;
		}
		getPage("/replies/" + bno + "/1");
	}); */

	//댓글 목록 버튼의 toggle 처리, 템플릿을 이용한 페이징 처리된 댓글 목록이 표시된다.
	$("#trigger").click(function(){
		$(".timeline li").toggle("slow");
			if($(".timeline li").length > 1){
				return;
			}
			getPage("/replies/" + bno + "/1");	
	});
	
	//댓글 페이징의 이벤트 처리. 전체 댓글 수와 페이지 수 목록이 계산되어 출력된다.
	$(".pagination").on("click", "li a", function(event){
		event.preventDefault();
		
		replyPage=$(this).attr("href");
		
		getPage("/replies/"+bno+"/"+replyPage);
	});	
	</script>
		
	<!-- 템플릿의 페이징 처리를 위한 함수. 내부적으로 jQuery로 JSON 타입의 데이터를 처리한다 -->
	<script>
	var bno=${dto.bno};//bno : JSP에 처리되는 문자열로 해당 게시물의 번호를 의미
	var replyPage=1;//replyPage는 수정이나 삭제 작업 이후에 사용자가 보던 댓글의 페이지를 가지고 다시 목록을 출력하기 위해 유지되는 데이터
	
	//getPage()는 특정한 게시물에 대한 페이징 처리를 위해 호출되는 함수. 페이지 번호를 파라미터로 전달받고, jQuery의 getJSON()을 이용해 댓글의 목록 데이터를 처리.
	function getPage(pageInfo){
		$.getJSON(pageInfo, function(data){
			printData(data.list, $("#repliesDiv"), $('#template'));
			printPaging(data.pageMaker, $('.pagination'));
		});
	}
	
	//댓글의 목록 데이터는 'pageMaker'와 'list'로 구성되므로 이를 printPaging(), printData()에서 처리함.
	var printPaging=function(pageMaker, target){
		var str="";		
		
		if(pageMaker.prev){
			str+="<li><a href='"+(pageMaker.startPage-1)+"'> << </li>";
		}
		
		for(var i=pageMaker.startPage, len=pageMaker.endPage; i<=len; i++){
			var strClass=pageMaker.cri.page==i?'class=active':'';
			str+="<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
		}
		
		if(pageMaker.next){
			str+="<li><a href='"+(pageMaker.endPage+1)+"'> >> </a></li>";
		}
		/* document.getElementById("pageMaker").style.color="red"; */
		target.html(str);
		/* document.getElementById("active").style.color="blue"; */
	}
	</script>
	
	<!-- 댓글등록 처리 JavaScript------------------------------------- -->	
	<script>	
	//password에 숫자만 입력할 수 있도록 유효성 검사
	function check_replyPw(){
		var replyPw_input = $("#replyPw").val();
		
		if(!((event.keyCode>=48 && event.keyCode<=57) || (event.keyCode>=96 && event.keyCode<=105)
				|| event.keyCode==8)){
			alert("숫자를 입력해주세요.");
			event.returnValue=false;
		}
	}
	
	//댓글 등록 처리-------------------------------------
	function replyAddBtn(){
		/* $("#replyAddBtn").on("click", function(){ */
			/* var replyerObj=$("#newReplyWriter");
			var replytextObj=$("#newReplyText");
			var replyer=replyerObj.val();
			var replytext=replytextObj.val();
			
			if(replyer == "" || replyer == null){
				alert("작성자명을 입력해주세요");
				replyForm.replyer.focus();
			}else if(replytext == "" || replytext == null){
				alert("내용을 입력해주세요");
				replyForm.replytext.focus();
			} */
			
			var replyPw=$('input[name=replyPw]').val();
			var replytext=$('input[name=newReplyText]').val();
				
				if(replytext == "" || replytext == null){
					alert("내용을 입력해주세요");
					replyForm.replytext.focus();
					return false;
				}else if(replyPw == "" || replyPw == null){
					alert("비밀번호를 입력해주세요");
					replyForm.replyPw.focus();
					return false;
				}
			
			//jQuery를 이용해 $.ajax() 를 통해 서버를 호출. 전송하는 데이터는 JSON으로 구성된 문자열 사용. 전송받는 결과는 단순 문자열.
			$.ajax({
				type:'post',
				url:'/replies/',
				headers:{"Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"},
				dataType:'text',
				data:JSON.stringify({
					bno:bno,
					//replyer:replyer,
					replytext:replytext,
					replyPw:replyPw
				}),
				success:function(result){
					console.log("result : " + result);
					if(result=='SUCCESS'){
						alert("등록 되었습니다");
						/* getAllList(); *///댓글 등록 후 전체 댓글 목록의 갱신
						replyPage=1;
						getPage("/replies/"+bno+"/"+replyPage);

						$("#replyAddBtn").submit();	
					}
				}});
			document.getElementById("replyForm").reset();//input form을 비워준다.
		/* }); */
		};
	
	//댓글 수정. HTTP의 PUT방식을 이용해 처리한다.-------------------------------------
	$("#replyModBtn").on("click", function(){
		var rno = $(".modal-title").html();
		var replytext=$("#replytext").val();	
		var replyPw=$("#replyPw").val();
		
		$.ajax({
			type:'put',
			url:'/replies/' + rno,
			headers:{
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PUT"
			},
			data:JSON.stringify({
				replytext : replytext,
				replyPw : replyPw
			}),
			dataType:'text',
			success:function(result){
				console.log("Reply Update result : " + result);
				if(result == 'SUCCESS'){
					alert("수정 되었습니다.");
					console.log("수정 SUCCESS");
					getPage("/replies/" + bno + "/" + replyPage);							
					
					// $(".modal").close();
				}
			}
		});
		$("#replyPw").val('');
		
		/* document.pwConfirm.action="/replies/replyModal/" + rno;
		document.pwConfirm.submit(); */
		console.log("/replies/replyModal");
	});
	
	//댓글 삭제-------------------------------------
	$("#replyDelBtn").on("click", function(){
		var rno = $(".modal-title").html();
		var replytext = $("#replytext").val();
		
		$.ajax({
			type:'delete',
			url:'/replies/' + rno,
			headers:{
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType:'text',
			success:function(result){
				console.log("Reply Delete Result : " + result);
				if(result == 'SUCCESS'){
					alert("삭제 되었습니다");
					getPage("/replies/"+bno+"/"+replyPage);			
					
					/* $(".modal").hide(); */					
				}
			}
		});
	});
	</script>
	
	<script>
	//Materialize 초기화. 댓글 등록 INPUT 태그의 추가 사항.-------------------------------------
	//플러그인을 위한 옵션이 없기 때문에 동적으로 추가하고 싶다면 초기화시킨다.
	 $(document).ready(function() {	 
		    $('input#input_text, textarea#textarea2').characterCounter();		    
		    /* document.getElementById("input#input_text, textarea#textarea2").style.color="blue-grey lighten-3"; */
	 });
	</script>
	
	<script>
	//목록으로 돌아가기, 수정, 삭제 버튼 이벤트 처리-------------------------------------
	$(document).ready(function(){
		var formObj=$("form[role='form']");
		/* console.log(formObj); */
		
		$("#btn_back").on("click", function(){
			formObj.attr("method","get");
			formObj.attr("action","/sboard/search_list");
			formObj.submit();
		});
		
		$("#btn_update").click(function(){
 			var bno="${dto.bno}";
			if(confirm("수정하시겠습니까??")){
				formObj.attr("method","get");
				formObj.attr("action", "/sboard/updateGet/");
				formObj.submit();
			}
		});
		
		$("#btn_delete").on("click",function(){
			if(confirm("삭제하시겠습니가?")){
				formObj.attr("action", "${path}/sboard/delete");
				formObj.submit();
			}
		});
  		 
		/*	
		$("#btn_update").on("click", function(){
			formObj.attr("action", "/sboard/updateGet");//.attr(attributeName, value) 2개의 인자는 속성값을 요소에 부여하는 것
			formObj.attr("method","get");
			formObj.submit();
		});*/	
	});
		
	  /* 
	  //keyup 이벤트는 글자 입력 수에 따라 입력 가능 글자수를 동적으로 세어준다
	  $("#content").keyup(function(){
	    alert(this.value);
	  }); */
  		
</script>

	<!-- 뒤로가기 처리 두 가지 방법 중 1번째
	<script type="text/javascript">
		function goBack(){
		window.history.back(); //window.history.go(-1);
	}
	</script> -->
	
<!--<script>
document.getElementById('content').value
 	function btn_update(){
		alert("수정하기");
		document.form1.action="/board/updateGet";
	}
</script> -->

<%@ include file="../forward/footer2.jsp" %>
</body>
</html>