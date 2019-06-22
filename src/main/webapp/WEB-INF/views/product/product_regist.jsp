<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품등록</title>
<style type="text/css">
	.form{
		margin: auto;
		width:300px !important
	}
    #dropzone
    {
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

<script src="<c:url value="../../resources/js/upload.js"/>"></script><!-- /resources/js 의 파일 -->
<%@ include file="../forward/header.jsp" %>
</head>
<body>
<br>
<br>
<h2 style="text-align:center;">상품 등록</h2>

	<br>
	<form class="form" name="form1" method="post" enctype="multipart/form-data">
	<!-- <form class="form" name="form1" method="post"> -->
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
			  <button type="submit" class="btn btn-default" id="addBtn">등록</button>
			  <!-- <button type="reset" class="btn btn-default" id="goBack">목록</button> -->
		  	  <input type="button" class="btn btn-default" value="목록" onClick="goBack();"/>
		  </div>
	
	<ul class="mailbox-attachments clearfix uploadedList">
	</ul>
	
	<!-- handlebars.js 를 이용해서 각 파일을 템플릿으로 작성한다. -->
	<script id="template" type="text/x-handlebars-template">
		<li>
			<span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
			<div class="mailbox-attachment-info">
				<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
				<a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn">
					<i class="fa fa-fw fa-remove"></i></a>
			</div>
		</li>
	</script>
 
</form>

<script>
$(function () {
    var obj = $("#dropzone");

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
    
    //첨부파일 삭제처리
    obj.on("click", "small", function(event){
    	var that=$(this);
    	
    	$.ajax({
    		url:"deleteFile",
    		type:"post",
    		data:{fileName:$(this).attr("data-src")},
    		dataType:"text",
    		success:function(result){
    			if(result=='delete'){
    				that.parent("div").remove();
    				alert("deleted");
    			}
    		}
    	});    	
    });   

    obj.on('drop', function (e) {
         e.preventDefault();
         /* $(this).css('border', '2px dotted #8296C2'); */
         $(this).css('border', '2px dotted');

         var files = e.originalEvent.dataTransfer.files;
    	 var file=files[0];
         
    	 console.log("file : " + file);
    	 console.log(file);
         
    	 var formData=new FormData();
    	 
    	 formData.append("file", file);
    	 
         if(files.length < 1)
              return;

		 $.ajax({
			url:'/uploadAjax',
			data:formData,
			dataType:'text',
			processData:false,
			contentType:false,
			type:'POST',
			success:function(data){
				
				alert(data)
				
				var str="";
				
				console.log(data);
				console.log(checkImageType(data));
				
					if(checkImageType(data)){
						str="<div>"+"<img src='displayFile?fileName="+data+"'/>"+data+"</div>";
					}else{
						str="<div>"+data+"</div>";
					}
					
				$(".uploadedList").append(str);//위의 코드를 이용해 파일을 업로드하면 이미지 파일의 경우 썸네일 이미지가 보여짐.
				//일반파일인 경우 파일의 이름만 출력됨.
				
				var fileInfo=getFileInfo(data);
				
				var html=template(fileInfo);
				
					if(checkImageType(data)){
						str="<div><a href=displayFile?fileName="+getImageLink(data)+">"
							+"<img src='displayFile?fileName="+data+"'/>"
							+"</a><small data-src="+data+">X</small></div>";
					}else{
						str="<div><a href='displayFile?fileName="+data+"'>"
							+getOriginalName(data)+"</a>"
							+"<small data-src"+data+">X</small></div>";
					}
					
				$(".uploadedList").append(html);
			}
		 });
         
         /* F_FileMultiUpload(files, obj); */
    });
});
</script>

<script>
	$(document).ready(function(){
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
			
			//첨부파일. 최종 submit이 일어나면 서버에는 사용자가 업로드한 파일의 정보를 같이 전송하는데, 업로드 된 파일의 이름을 form태그 내부로 포함 시켜 전송한다.
			
			$("#addBtn").submit(function(event){
				event.preventDefault();
				
				var that=$(this);
				var str="";
				
				$(".uploadedList .delbtn").each(function(index){
					str+="<input type='hidden' name='files["+index+"]' value='"$(this).attr("href")+"'>";
				});
				
				that.append(str);
				
				that.get(0).submit();
			});
			
		});
		//상품 목록 이동
		$("#btnList").click(function(){
			location.href="${path}/shop/product/product_list";
		});
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
            url: './fileDelete.jsp',
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