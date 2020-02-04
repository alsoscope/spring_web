<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품등록</title>
<style type="text/css">
.create-form{
	margin: auto;
	width:300px !important
}
#dropzone {
    border:2px dotted;
    text-align:center;
    font-size:24px;
     padding-top:12px;
    width:300px;
    height:150px;
    margin-top:10px;
}
.footer{
	position:relative !important;
	top:120px;   
}
</style>
<!-- <script src="//code.jquery.com/jquery-3.2.1.min.js"></script> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<%-- <script src="<c:url value="../../resources/js/dropzone.js"/>"></script> --%>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<!-- <link rel="stylesheet" href="https://rawgit.com/enyo/dropzone/master/dist/dropzone.css"> -->
<script src="<c:url value="../../resources/js/upload.js"/>"></script><!-- /resources/js 의 파일. 파일첨부 관련 자바 스크립트. -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.2/handlebars.js"></script>
<%@ include file="../forward/header.jsp" %>
</head>
<body>
<br>
<br>
<h2 style="text-align:center;">상품 등록</h2>

	<br>
	<!-- <form class="create-form" id="create-form" method="post" action="/shop/product/insertProduct" onsubmit="return validation();"> -->
	<form class="create-form" id="create-form" method="post" action="/shop/product/insertProduct">
		
		<label for="exampleInputEmail1">분류</label>
		<div style="align:center;">
			<label class="radio-inline">
				<input type="radio" name="radio_btn" id="korea" value="korea">국내영화
			</label>
			<label class="radio-inline">
				<input type="radio" name="radio_btn" id="abroad" value="abroad">국외영화
			</label>
			<label class="radio-inline">
				<input type="radio" name="radio_btn" id="etcetera" value="etcetera">기타
			</label>
			<!-- name: 라디오 버튼의 이름/value:선택 항목들이 가지는 고유한 값. submit으로 서버로 전송됨.
			input 태그 바로 밖에서 라디오 버튼을 설명한다./checked:기본으로 선택할 사항  -->
		</div><br>
		  
		  <div class="form-group">
		    <label for="exampleInputEmail1">상품명</label>
		    <input type="text" name="product_name" class="form-control" id="product_name">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">가격</label>
		    <input type="text" name="product_price" class="form-control" id="product_price" onkeydown="check_number()">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputEmail1">상품 상세</label>
		    <textarea id="product_desc" name="product_desc" class="form-control" rows="5" cols="60"></textarea>
		  </div>
		  
		  <div class="form-group">
		    <label for="exampleInputEmail1">상품 이미지</label>
		    <div class="dropzone" id="dropzone">Drag and Drop</div>
		  </div>
		  <!-- <div class="form-group">
		    <label for="exampleInputEmail1">상품 이미지</label>
		    <input type="file" name="product_photo" id="product_photo">
		  </div> -->
		  
		  <div align="center">
			  <!-- <button type="reset" class="btn btn-default" id="goBack">목록</button> -->
			  
		  	  <!-- <input type="button" class="btn btn-default" value="등록 테스트" onClick="radio_check();"/> -->
			  <input type="submit" class="create btn btn-success" value="등록">
			  <!-- <input type="submit" class="create btn btn-success" value="상품 등록"> -->
		  	  <input type="button" class="btn btn-default" value="뒤로가기" onclick="goBack();">
		  </div>
	
		<br>
		<ul class="uploadedList mailbox-attachments clearfix">
		</ul>
		
	</form>

	<!-- 첨부파일 템플릿 handlebars.js 를 이용해서 첨부할 파일을 템플릿으로 작성한다-->
	<script id="templateAttach" type="text/x-handlebars-template">
		<li>
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

<script>
$(function () {
    var obj = $(".dropzone");

    obj.on('dragenter', function (e) {
         e.stopPropagation();
         e.preventDefault();
         /* $(this).css('border', '2px solid #5272A0'); */
         $(this).css('border', '2px solid');
    });

    obj.on('dragleave', function (e) {
         e.stopPropagation();
         e.preventDefault();
         /* $(this).css('border', '2px dotted #8296C2'); */
         $(this).css('border', '2px dotted');
    });

    obj.on('dragover', function (e) {
         e.stopPropagation();
         e.preventDefault();
    });
});
    
    $(".dropzone").on("drop", function(event) {
    /* obj.on('drop', function (event) { */
         event.preventDefault();//기본 효과 제한. 드래그해서 드롭하면 바로 파일이 실행되는 이벤트 제한.
         /* $(this).css('border', '2px dotted #8296C2'); */
         $(this).css('border', '2px dotted');

         //var template=Handlebars.compile($("#templateAttach").html());
         var source=$("#templateAttach").html();
         var template=Handlebars.compile(source);
         
         //event.dataTransfer=event.originalEvent.dataTransfer;
   		 //var files=event.target.files || event.dataTransfer.files;
         
         //기본 event를 불러오고, 기본 event로 전송된 데이터를 가져와서 .files를 찾는다
         var files = event.originalEvent.dataTransfer.files;//드래그한 파일들
    	 var file = files[0];//첫 번째 첨부파일. 하나를 drop했다고 가정.
		
    	 if(files.length > 1){
    		 alert("파일 한 개 이상 첨부 안됨");
    		 return; 
   		 }
    	 
    	 /* console.log(files);
    	 console.log("files : " + files); */
    	 console.log(file);
    	 console.log("file : " + file);
    	 
    	 var formData=new FormData();//FormData 객체  	 
    	 formData.append("file", file);//첨부파일 추가. formData객체에 key:value 형태로 (event로 전송된)files를 넣어준다.
    	 
         /* if(files.length < 1)
              return; */

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

<script>
	/* $(document).ready(function(){
		//유효성 체크
		function validation(){
			document.form1.action="${path}/shop/product/insertProduct";
			document.form1.submit();
		};
		
		function radio_check(){		
		}
	 }); */

	//password에 숫자만 입력할 수 있도록 유효성 검사
	function check_number(){
		var product_price = $("#product_price").val();
		
		if(!((event.keyCode>=48 && event.keyCode<=57) || (event.keyCode>=96 && event.keyCode<=105)
				|| event.keyCode==8)){
			alert("숫자를 입력해주세요.");
			event.returnValue=false;
		}
	}
		
	//첨부파일. 최종 submit이 일어나면 서버에는 사용자가 업로드한 파일의 정보를 같이 전송하는데, 업로드 된 파일의 이름을 form태그 내부로 포함 시켜 전송한다.
	$("#create-form").submit(function(event){
	/* $("#create-form").click(function (event){ */
		event.preventDefault();//먼저 기본 동작을 막는다.
		
		//폼 내부 값 유효성 체크
		var product_name=$("#product_name").val();
		var product_price=$("#product_price").val();
		
		if(product_name==null || product_name==""){
			alert("상품 이름 미입력!");
			$("#product_name").focus();
			return false;
		}else if(product_price==null || product_price==""){
			alert("상품 가격 미입력!");
			$("#product_price").focus();
			return false;
		}else if($("#product_desc").val()==""){
			alert("상품 설명 미입력!");
			$("#product_desc").focus();
			return false;
		}/* else if($("#dropzone").val()==""){
			alert("상품 사진 미등록!");
			return false;
		} */
		
		//라디오 버튼 name 가져오기
		var radio_btn=document.getElementsByName("radio_btn");
		
		//라디오 버튼 세트의 id값 가져오기0
		var korea=$('input[id=korea]').val();
		var abroad=$('input[id=abroad]').val();
		var etcetera=$('input[id=etcetera]').val();

		//라디오 버튼이 체크되었는지 확인하기 위한 변수
		var radio_btn_check=0;
		
		for(var i=0; i<radio_btn.length; i++){
			//만약 라디오 버튼이 체크되었다면 true
			if(radio_btn[i].checked==true){
				//라디오 버튼 값
				alert(radio_btn[i].value);
				//라디오 버튼이 체크 되었다면 radio_btn_check를 1로 만들어줌.
				radio_btn_check=1;
				
				if(radio_btn[i].value == korea){
					console.log("국내영화");		
					
					event.preventDefault();//먼저 기본 동작을 막는다.
					//form submit
					alert("form submit - 국내영화");
				
					var that = $(this);
					var str = "";
					
					$(".uploadedList .del-btn").each(function (index) {
						str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "' >";
					});
					
					that.append(str);
					
					document.getElementById("create-form").action="/shop/product/insertProduct";
					that.get(0).submit();
					
					console.log("국내영화 insert 성공");
					
				}else if(radio_btn[i].value == abroad){
					console.log("국외영화");
					
					event.preventDefault();//먼저 기본 동작을 막는다.
					//form submit
					alert("form submit - 국외영화");
				
					var that = $(this);
					var str = "";
					
					$(".uploadedList .del-btn").each(function (index) {
						str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "' >";
					});
					
					that.append(str);
					
					document.getElementById("create-form").action="/shop/product/insertAbroad";
					that.get(0).submit();
					
					console.log("국외영화 insert 성공");
				}else if(radio_btn[i].value == etcetera){
					console.log("기타");
					
					event.preventDefault();//먼저 기본 동작을 막는다.
					//form submit
					alert("form submit - 기타");
				
					var that = $(this);
					var str = "";
					
					$(".uploadedList .del-btn").each(function (index) {
						str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "' >";
					});
					
					that.append(str);
					
					document.getElementById("create-form").action="/shop/product/insertEtcetera";
					that.get(0).submit();
					
					console.log("기타영상 insert 성공");
				}
			}
		}
		
		if(radio_btn_check==0){
			alert("분류 항목을 선택해주세요.");
		}
		
		/* alert("form submit!!");
		//console.log(fullName);
		
		var that = $(this);
		var str = "";
		
		//현재까지 업로드 된 파일을 form태그 내부에 hidden으로 추가. 각 파일은 files[0]의 이름으로 추가됨.
		//이 배열 표시를 이용해 컨트롤러에서 ProductDTO의 files 파라미터를 수집하게 된다.
		//Handlebars 안에 있는 클래스는 직접 제어가 안된다. 바깥에 있는 아이디나 클래스를 가지고 제어.
		$(".uploadedList .del-btn").each(function (index) {
			str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "' >";
		});
		
		that.append(str);
		
		//모든 파일의 정보를 <input type='hidden'>으로 생성한 후, <form> 데이터의 submit()을 호출해서 서버를 호출한다.
		//jQuery의 get(0)은 순수한 DOM 객체를 얻어내기 위해 사용.
		that.get(0).submit(); */
	});	
</script>
	
	<script>
    //첨부파일 삭제처리/ 태그.on("이벤트", "자손태그", 이벤트 핸들러)
    /* obj.on("#delBtn", "click", "small", function(event){ */
    /* $(".delbtn").click("small", function(){ */
    $(".uploadedList").on("click", ".del-btn", function(event){
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
    });
	
  	//상품 목록 이동
	/* $("#btnList").click(function(){
		location.href="${path}/";
	}); */
</script>

<script>
	function goBack(){
		window.history.back(); //window.history.go(-1);
	}
</script>

<!-- //일반 파일의 이름이 너무 길게 출력될 때 이를 줄여주는 기능
function getOriginalName(fileName){
	if(checkImageType(fileName)){
		return;
	}
	
	//첨부파일의 이름이 uuid와 결합될 때 '_'가 사용되는 것을 이용해 원래의 파일 이름만을 추출.
	var idx=fileName.indexOf("_")+1;
	
	return fileName.substr(idx);
}

//파일의 링크 처리
function getImageLink(fileName){
	if(!checkImageType(fileName)){
		return;
	}
	var front=fileName.substr(0,12);// 년/원/일의 경로를 추출하는 용도
	var end=fileName.substr(14);//썸네일을 나타내는 파일 이름 앞의 '_s' 를 제거하는 용도
	
	return front+end;
} -->

<script>
//파일 멀티 업로드
/*  function F_FileMultiUpload(files, obj) {
     if(confirm(files.length + "개의 파일을 업로드 하시겠습니까?") ) {
         var data = new FormData();
         for (var i = 0; i < files.length; i++) {
            data.append('file', files[i]);
         }

         var url = "<서버 파일업로드 URL>";
         $.ajax({
            url: url,
            method: 'post',
            data: data,
            dataType: 'json',
            processData: false,
            contentType: false,
            success: function(res) {
                F_FileMultiUpload_Callback(res.files);
            }
         });
     }
}

// 파일 멀티 업로드 Callback
function F_FileMultiUpload_Callback(files) {
     for(var i=0; i < files.length; i++)
         console.log(files[i].file_nm + " - " + files[i].file_size);
} */

</script>

<script>
/* var myDropzone = new Dropzone("div#fileDropzone",
		{ 
		  url:"파일 업로드 경로",
		  addRemoveLinks:true,
		  maxFiles:5,
		  success:function(){
			var imgName=response.serFileNm;
			var oriName=response.orgFileNm;
			if(imgName!=''){
				var adClass=imgName.replace('.',"");
					file.previewElement.classList.add("dz-success");
					file.previewElement.classList.add(adClass);
				var html+='<input type="hidden" name="serFileNm" value="'+imgName+'">';
					html+='<input type="hidden" name="orgFileNm" value="'+oriName'">';
				$("."+adClass).append(html);
			}
		  },error:function(){
			file.previewElement.classList.add("dz-maxsize-error");
			alert("파일은 최대 5개까지만 업로드 가능합니다.");
			$(".dz-maxsize-error").empty();
		  }
		}); */
</script>

<script>
  /* Dropzone.options.dropzone = {
    url: '/insertProduct',
    autoProcessQueue: false,
    uploadMultiple: true,
    parallelUploads: 2,
    maxFiles: 2,
    maxFilesize: 1,
    acceptedFiles: 'image/*',
    addRemoveLinks: true,
    removedfile: function(file) {
        var srvFile = $(file._removeLink).data("srvFile");
        $.ajax({
            type: 'POST',
            async: false,
            cache: false,
            url: './deleteFile',
            data: { file: srvFile }
        });
        var _ref;
        (_ref = file.previewElement) != null ? _ref.parentNode.removeChild(file.previewElement) : void 0;
        setFilesName();
        return;
    },
    init: function() {
        var dropzone = this;
        // Uploaded images
        
        // First change the button to actually tell Dropzone to process the queue.
        document.querySelector("button[type=submit]").addEventListener("click", function(e) {
            // Make sure that the form isn't actually being sent.
            e.preventDefault();
            e.stopPropagation();
            // Form check
            if(checkForm()) {
                if (dropzone.getQueuedFiles().length > 0) {
                	dropzone.processQueue();
                } else {
                    setFilesName();
                    submitForm();
                }
            }
        });
        // Append all the additional input data of your form here!
        this.on("sending", function(file, xhr, formData) {
            formData.append("token", $("input[name=token]").val());
        });
        // Listen to the sendingmultiple event. In this case, it's the sendingmultiple event instead
        // of the sending event because uploadMultiple is set to true.
        this.on("sendingmultiple", function() {
            // Gets triggered when the form is actually being sent.
            // Hide the success button or the complete form.
        });
        this.on("successmultiple", function(files, response) {
            // Gets triggered when the files have successfully been sent.
            // Redirect user or notify of success.
            var obj = JSON.parse(response);
            for(i=0; i<files.length; i++) {
                $(files[i]._removeLink).data('srvFile', obj[files[i].name]);
            }
            setFilesName();
            // form submit
            submitForm();
        });
        this.on("errormultiple", function(files, response) {
            // Gets triggered when there was an error sending the files.
            // Maybe show form again, and notify user of error
        });
    }
}; */
</script>

<%@ include file="../forward/footer.jsp" %>
</body>
</html>