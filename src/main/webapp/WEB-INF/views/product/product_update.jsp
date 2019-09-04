<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품등록</title>
<style type="text/css">
	.edit-form{
		margin: auto;
		width:300px !important
	}
    #dropzone {
        border:2px dotted;
        /* width:90%;
        height:50px;
        color:#92AAB0;
        text-align:center;
        font-size:24px;
        padding-top:12px;
        margin-top:10px; */
    }
</style>
<!-- <script src="//code.jquery.com/jquery-3.2.1.min.js"></script> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<%-- <script src="<c:url value="../../resources/js/dropzone.js"/>"></script> --%>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<link rel="stylesheet" href="https://rawgit.com/enyo/dropzone/master/dist/dropzone.css">
<script src="<c:url value="/resources/js/upload.js"/>"></script><!-- /resources/js 의 파일. 파일첨부 관련 자바 스크립트. -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.2/handlebars.js"></script>
<%@ include file="../forward/header.jsp" %>
</head>
<body>
<br>
<br>
<h2 style="text-align:center;">관리자 - 수정하기</h2>

	<br>
	<form class="edit-form" id="edit-form" method="post" role="form" action="/shop/product/updatePost" onsubmit="return checkValidation()">
	<!-- <form class="form1" id="form1" method="post" action="/shop/product/insertProduct"> -->
		  <div data-src="${dto.product_id }"></div>
		  <div class="form-group">
		    <label for="exampleInputEmail1">상품명</label>
		    <input type="text" name="product_name" class="form-control" id="product_name" value="${dto.product_name }">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">가격</label>
		    <input type="text" name="product_price" class="form-control" id="product_price" value="${dto.product_price }">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputEmail1">상품 상세</label>
		    <textarea id="product_desc" name="product_desc" class="form-control" rows="5" cols="60">${dto.product_desc }</textarea>
		  </div>		  
		  
		  <div class="form-group">
		    <label for="exampleInputEmail1">상품 이미지</label>		    
		    <div class="dropzone" id="dropzone"></div>
		  </div>
		  <!-- <div class="form-group">
		    <label for="exampleInputEmail1">상품 이미지</label>
		    <input type="file" name="product_photo" id="product_photo">
		  </div> -->
		  
		  <div align="center">
			  <!-- <button type="submit" class="btn btn-default">등록</button> -->
			  <!-- <button type="reset" class="btn btn-default" id="goBack">목록</button> -->
		  	  <!-- <input type="button" class="btn btn-default" value="목록" onClick="goBack();"/> -->
			  <!-- <input type="submit" class="btn btn-default" value="상품 등록"> -->
			  <input type="submit" class="create btn btn-success btn-wide pull-right" value="수정 완료">
			  <!-- <button type="submit" class="btn btn-default" id="btnRemove">삭제</button> -->
		  	  <input type="button" class="btn btn-default" value="목록으로 돌아가기" id="btnList"/>
		  </div>
	
		<ul class="uploadedList mailbox-attachments clearfix">
		</ul>
		
	</form>

	<!-- 첨부파일 템플릿 handlebars.js 를 이용해서 첨부할 파일을 템플릿으로 작성한다-->
	<script id="editAttach" type="text/x-handlebars-template">
		<li data-src="{{fullName}}">
			<span class="mailbox-attachment-icon has-img">
				<img src="{{imgsrc}}" alt="Attachment">
			</span>
			<div class="mailbox-attachment-info">
				<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
				<a href="{{fullName}}" class="btn btn-default btn-xs pull-right del-btn">
					<small class="del-btn">X</small>
				</a>
			</div>
		</li>
	</script>

<!-- 첨부파일 업로드 -->	
<script>
$(function () {
    var obj = $(".dropzone");

    obj.on('dragenter', function (e) {
         e.stopPropagation();
         e.preventDefault();
         $(this).css('border', '2px solid');
    });

    obj.on('dragleave', function (e) {
         e.stopPropagation();
         e.preventDefault();
         $(this).css('border', '2px dotted');
    });

    obj.on('dragover', function (e) {
         e.stopPropagation();
         e.preventDefault();
    });
});
    
    $(".dropzone").on("drop", function(event) {
         event.preventDefault();//기본 효과 제한. 드래그해서 드롭하면 바로 파일이 실행되는 이벤트 제한.
         $(this).css('border', '2px dotted');

         var source=$("#editAttach").html();
         var template=Handlebars.compile(source);
         
         //기본 event를 불러오고, 기본 event로 전송된 데이터를 가져와서 .files를 찾는다
         var files = event.originalEvent.dataTransfer.files;//드래그한 파일들
    	 var file = files[0];//첫 번째 첨부파일. 하나를 drop했다고 가정.
		
    	 if(files.length > 1){
    		 alert("파일 한 개 이상 첨부 안됨");
    		 return; 
   		 }

    	 console.log(file);
    	 console.log("file : " + file);
    	 
    	 var formData=new FormData();//FormData 객체  	 
    	 formData.append("file", file);//첨부파일 추가. formData객체에 key:value 형태로 (event로 전송된)files를 넣어준다.

		 $.ajax({
			url:"/shop/product/uploadAjax",
			data:formData,
			dataType:"text",
			processData:false, //header가 아닌 body로 전달
			contentType:false, //기본값 있으나, multipart/form-data 방식으로 전송하기 위해 false로 지정
			type:"POST",
			success:function(data){
				alert(data);
				console.log(data);
				
				var fileInfo=getFileInfo(data);				
				var html=template(fileInfo);
					
				$(".uploadedList").append(html);
			}
		 });//$.ajax
    });
</script>

<!-- 첨부파일 리스트 얻기 -->
<script>
	getAttachList();
	
	function getAttachList(){
		var product_id="${dto.product_id}";		
		var source=$("#editAttach").html();
        var template=Handlebars.compile(source);
		
		$.ajax({
			type:"GET",
			url:"/shop/product/getAttach/"+product_id,
			dataType:"JSON",
			processData:false, //header가 아닌 body로 전달
			contentType:false,
			success:function(result){
				$(result).each(function(){
					var fileInfo=getFileInfo(this);
					var html=template(fileInfo);
					
					$(".uploadedList").append(html);
				});
			}
		});
	};
	
	/* var bno="${dto.product_id}";
	 var template=Handlebars.compile($("#editAttach").html());
	 
	 //컨트롤러에서 문자열의 리스트를 반환, JSON 형태로 데이터 전송하면 getJSON을 이용해 처리한다.
	 $.getJSON("/shop/product/getAttach/"+bno, function(list){
		$(list).each(function(){
			var fileInfo=getFileInfo(this);
			
			var html=template(fileInfo);
			
			$(".uploadedList").append(html);
		});
	 }); */
</script>

<!-- 수정된 상품 게시글과 첨부파일 등록 submit -->
<script type="text/javascript">
	var arr=[];

	//파일명 클릭하면 리스트에서 삭제시킴
	$(".uploadedList").on("click", ".del-btn", function(event){
		event.preventDefault();
		
		$(this).closest("li").each(function (index){
			arr.push($("this").attr("data-src"));//push() : 배열의 맨 뒤에 값을 추가하는 내장 함수
		});
		
		this.closest("li").remove();
	});

	//첨부파일. 최종 submit이 일어나면 서버에는 사용자가 업로드한 파일의 정보를 같이 전송하는데, 업로드 된 파일의 이름을 form태그 내부로 포함 시켜 전송한다.	
	$("#edit-form").submit(function(event){
		event.preventDefault();//먼저 기본 동작을 막는다.
		
		var that = $(this);
		var str = "";

		$(".uploadedList .del-btn").each(function (index) {
			str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "' >";
		});
		
		if(arr.length > 0){
			$.ajax({
				type:"POST",
				url:"/shop/product/deleteAllFiles",
				data:{files:arr}
			});
		}
		
		console.log("fileUpload submit");
		
		that.append(str);
		
		//모든 파일의 정보를 <input type='hidden'>으로 생성한 후, <form> 데이터의 submit()을 호출해서 서버를 호출한다.
		//jQuery의 get(0)은 순수한 DOM 객체를 얻어내기 위해 사용.
		that.get(0).submit();
	});	
	
	//삭제 화면의 처리. 게시물 삭제 시 Ajax를 이용하여 첨부파일을 삭제하고, <form> 방식을 이용해 삭제한다.
	/* $("#btnRemove").on("click", function(){
		var formObj=$("form[role='form']");
		console.log(formObj);
		
		//현재 첨부파일의 이름을 배열로 작성, Ajax로 첨부파일에 대한 삭제를 지시함.
		
		$(".uploadedList").each(function (index){
			arr.push($("this").attr("data-src"));//push() : 배열의 맨 뒤에 값을 추가하는 내장 함수
		});
		
		if(arr.length > 0){ //<form> 태그로 데이터 베이스 삭제를 처리.
			$.post("/shop/product/deleteAllFiles", {files:arr}, function(){//$.post : HTTP POST 요청을 이용해 서버에서 데이터를 가져온다.
				//$.ajax({type:"POST"})의 shorthand Ajax function.
				
			});
		}
		
		alert("delete all files");
		formObj.attr("action", "/shop/product/product_remove");
		formObj.submit();
	}); */
	

    //첨부파일 삭제처리/ 태그.on("이벤트", "자손태그", 이벤트 핸들러)
    /* $(".uploadedList").on("click", ".del-btn", function(event){
    	event.preventDefault();
    	
    	var that=$(this);
    	
    	$.ajax({
    		url:"/shop/product/deleteFile",
    		type:"post",
    		data:{fileName:that.attr("href")},
    		dataType:"text",
    		success:function(result){
	    		console.log("deleted file : " + result);
    			if(result=='deleted'){
    				that.closest("li").remove();//jQuery의 remove() 이용하면 간단하게 원하는 요소(첨부파일 보여주기 위한<div>) 삭제
    				alert("deleted");
    			}
    		}
    	});    	
    }); */
	
  	//이전 페이지로 이동
	$("#btnList").click(function(){
		var bno = "${dto.product_id}";
		//return confirm("작성 취소 하시겠습니까?");
		if (confirm("작성 취소 하시겠습니까?")){
			location.href="${path}/shop/product/detail/"+bno;		
		}else{
			return false;
		}
	});
</script>

<script>
	/* $(document).ready(function(){ */
		//상품 등록 유효검사
		function checkValidation(){
			var product_name=$("#product_name").val();
			var product_price=$("#product_price").val();
			var product_desc=$("#product_desc").val(); //상품 description
			//var product_photo=$("#product_photo").val();
			
			//상품등록 클릭 이벤트가 발생하면 폼 내부 값 유효성 체크한 뒤 서버로 전송
			if(product_name==""){
				alert("상품명 미입력!");
				product_name.focus();
				return false;
			}else if(product_price==""){
				alert("상품가격 미입력!");
				product_price.focus();
				return false;
			}else if(product_desc==""){
				alert("상품설명 미입력!");
				product_desc.focus();
				return false;
			}/* else if(product_photo==""){
				alert("상품사진 미등록!");
				product_photo.focus();
			} */
			
			//상품 정보 전송
			//document.form1.action="${path}/shop/product/insertProduct";
			//document.form1.submit();
		};		
	/* }); */
</script>
<%@ include file="../forward/footer.jsp" %>
</body>
</html>