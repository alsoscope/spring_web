<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품등록</title>
<style type="text/css">
	.form1{
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
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%-- <script src="<c:url value="../../resources/js/dropzone.js"/>"></script> --%>
<link rel="stylesheet" href="https://rawgit.com/enyo/dropzone/master/dist/dropzone.css">
<script src="<c:url value="../../resources/js/upload.js"/>"></script><!-- /resources/js 의 파일. 파일첨부 관련 자바 스크립트. -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.2/handlebars.js"></script>
<%@ include file="../forward/header.jsp" %>
</head>
<body>
<br>
<br>
<h2 style="text-align:center;">상품 등록</h2>

	<br>
	<!-- <form class="form1" id="form1" method="post" enctype="multipart/form-data" action="/shop/product/insertProduct"> -->
	<form class="form1" id="form1" method="post" action="/shop/product/insertProduct">
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
		    <textarea id="product_desc" name="product_desc" class="form-control" rows="5" cols="60"></textarea>
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
			  <input type="submit" class="btn btn-default" id="btnSubmit" value="상품 등록">
		  	  <input type="button" class="btn btn-default" value="목록으로 돌아가기" id="btnList"/>
		  </div>
	
		<ul class="uploadedList">
		</ul>
		
	</form>

		<!-- handlebars.js 를 이용해서 첨부할 파일을 템플릿으로 작성한다. -->
		<script id="templateAttach" type="text/x-handlebars-template">
		<li>
			<span><img src="{{imgsrc}}" alt="Attachment"></span>
			<div>
				<a href="{{getLink}}">{{fileName}}</a>
				<a href="{{fullName}}" class="btn btn-default">
					<small class="delbtn">X</small></a>
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

         var template=Handlebars.compile($("#templateAttach").html());
         //var source=$("#templateAttach").html();
         //var template=Handlebars.compile(source);
         
         //기본 event를 불러오고, 기본 event로 전송된 데이터를 가져와서 .files를 찾는다
         var files = event.originalEvent.dataTransfer.files;//드래그한 파일들
    	 var file=files[0];//첫 번째 첨부파일. 하나를 drop했다고 가정.
		
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

<script type="text/javascript">
	//첨부파일. 최종 submit이 일어나면 서버에는 사용자가 업로드한 파일의 정보를 같이 전송하는데, 업로드 된 파일의 이름을 form태그 내부로 포함 시켜 전송한다.	
	$("#form1").submit(function(event){
	/* $("#btnSubmit").click(function (event){ */
		event.preventDefault();//먼저 기본 동작을 막는다.
		
		alert("form submit!!");
		console.log(fullName);
		
		var that=$(this);
		var str="";
		
		//현재까지 업로드 된 파일을 form태그 내부에 hidden으로 추가. 각 파일은 files[0]의 이름으로 추가됨.
		//이 배열 표시를 이용해 컨트롤러에서 ProductDTO의 files 파라미터를 수집하게 된다.
		//Handlebars 안에 있는 클래스는 직접 제어가 안된다. 바깥에 있는 아이디나 클래스를 가지고 제어.
		$(".uploadedList").each(function(index){
			str+="<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href")+"'>";
		});
		
		console.log("fileUpload submit");
		
		that.append(str);
		
		//모든 파일의 정보를 <input type='hidden'>으로 생성한 후, <form> 데이터의 submit()을 호출해서 서버를 호출한다.
		//jQuery의 get(0)은 순수한 DOM 객체를 얻어내기 위해 사용.
		that.get(0).submit();
	});	
</script>

<script>
	/* $(document).ready(function(){ */
		//상품 등록 유효검사
		$("#addBtn").click(function(){
			var product_name=$("#product_name").val();
			var product_price=$("#product_price").val();
			var product_desc=$("#product_desc").val(); //상품 description
			//var product_photo=$("#product_photo").val();
			
			//상품등록 클릭 이벤트가 발생하면 폼 내부 값 유효성 체크한 뒤 서버로 전송
			if(product_name==""){
				alert("상품명 미입력!");
				product_name.focus();
			}else if(product_price==""){
				alert("상품가격 미입력!");
				product_price.focus();
			}else if(product_desc==""){
				alert("상품설명 미입력!");
				product_desc.focus();
			}/* else if(product_photo==""){
				alert("상품사진 미등록!");
				product_photo.focus();
			} */
			
			//상품 정보 전송
			//document.form1.action="${path}/shop/product/insertProduct";
			//document.form1.submit();
		});		
	/* }); */	
	
    //첨부파일 삭제처리/ 태그.on("이벤트", "자손태그", 이벤트 핸들러)
    /* obj.on("#delBtn", "click", "small", function(event){ */
    /* $(".delbtn").click("small", function(){ */
    $(".uploadedList").on("click", "small", function(event){
    	
    	var that=$(this);
    	
    	$.ajax({
    		url:"/shop/product/deleteFile",
    		type:"post",
    		data:{fileName:$(this).attr("data-src")},
    		dataType:"text",
    		success:function(result){
    			if(result=='deleted'){
    				that.parent("div").remove();//jQuery의 remove() 이용하면 간단하게 원하는 요소(첨부파일 보여주기 위한<div>) 삭제
    				alert("deleted");
    			}
    		}
    	});    	
    });
	
  	//상품 목록 이동
	$("#btnList").click(function(){
		location.href="${path}/";
	});
	
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
/* function F_FileMultiUpload(files, obj) {
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