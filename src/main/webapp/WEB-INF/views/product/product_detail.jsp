<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  
  <title>CINEPHILE</title>

  <!-- Bootstrap core CSS -->
  <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Bootstrap core JavaScript -->
  <script
  src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
  <!-- display comment count -->
  <script id="dsq-count-scr" src="//cinephile-1.disqus.com/count.js" async></script>
  <!-- 첨부파일의 조회 -->
  <script src="<c:url value="/resources/js/upload.js"/>"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.2/handlebars.js"></script>
  <!-- Custom styles for this template -->
  <%-- <link href="${pageContext.request.contextPath }/resources/css/shop-item.css" rel="stylesheet"> --%>
  
<style type="text/css">
.popup{
	position:absolute;
}
.back{
	/* background-color:gray;
	opacity:0.5;
	width:100%;
	height:100%;
	overflow:hidden;
	z-index:1101; */
}
.front{
	z-index:1110;
	opacity:1;
	boarder:1px;
	margin:auto;
}
.show{
	position:relative;
	max-width:1200px;
	max-height:800px;
	overflow:auto;
}
</style>
</head>
<%@ include file="../forward/header.jsp" %>
<body>
  <!-- Page Content -->
  <div class="container">
    <div class="row">

      <div class="col-lg-3">
        <h3 class="my-4">영화 목록</h3>
        <div class="list-group">
          <a href="/shop/product/product_list_korean" class="list-group-item">한국 영화</a>
          <a href="/shop/product/product_list_abroad" class="list-group-item">해외 영화</a>
          <a href="/shop/product/product_list_etcetera" class="list-group-item">기타 영상</a>
          
          <c:if test="${sessionScope.adminId!=null }">
          <a href="${path}/shop/product/product_write" class="list-group-item">상품 등록</a>
          <!-- <button type="submit" id="btnAdd" class="list-group-item">상품등록</button> -->
          </c:if>
        </div>
      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">
        <div class="card mt-4">
        
    <!-- --------- 파일 첨부 리스트 --------- 조회 페이지에서 업로드 된 파일들이 보여질 영억 -->
	<ul class="uploadedList"></ul>
	
	<div class="popup back" style="display:none;"></div>
		<div id="popup_front" class="popup front" style="display:none;">
			<img id="popup_img">
		</div>	
	<!-- </div> -->
	
	<script id="templateAttach" type="text/x-handlebars-template">
	<span data-src='{{fullName}}' class="card-img-top img-fluid">
		<span><img class="card-img-top img-fluid" style="width:900px; height:400px;" src="{{getLink}}" alt="attachments"></span>
		<div class="img-pop">
			<a href="{{getLink}}">클릭하면 확대됩니다  → {{fileName}}</a>
		</div>
	</span>
	</script>
	<!-- --------- 파일 첨부 리스트 --------- 조회 페이지에서 업로드 된 파일들이 보여질 영억 -->
	
          <%-- <img class="card-img-top img-fluid" style="width:900px; height:400px;" src="${vo.product_id}" alt="포스터" > --%>
		<!-- <img class="card-img-top img-fluid" src="http://placehold.it/900x400" alt=""> -->
          <div class="card-body">
            <h3 class="card-title">${vo.product_name }</h3>
            <h4>가격 : <fmt:formatNumber value="${vo.product_price }" pattern="###,###,###" /></h4>
            <p class="card-text">${vo.product_desc }</p>
            <span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span>
            4.0 stars
            
            <!-- 상품을 장바구니에 추가시키기 위해 상품id번호, 수량을 form태그 전송 -->
			<form name="form1" method="post" action="${path}/shop/cart/insertCart">
				<!-- 현재의 상품id를 입력받기 위해 hidden속성으로 처리 -->
				<input type="hidden" name="product_id" value="${vo.product_id }">
				
				<!-- select태그를 forEach문으로 1~10까지 수량을 선택할 수 있도록 처리 -->
				<select name="amount">
					<c:forEach begin="1" end="10" var="i">
						<option value="${i}">${i}</option>
					</c:forEach>
				</select>&nbsp;일 동안 대여&nbsp;&nbsp;
				
				<c:if test="${login.userId == null }">
					<p>로그인 뒤 대여가능 <a href="/member/loginGET">로그인</a></p>
				</c:if>
				<c:if test="${login.userId != null }">
					<input type="submit" value="장바구니에 담기">
				</c:if>
			</form>
          </div>
        </div>
        <!-- /.card -->

        <div class="card card-outline-secondary my-4">
          <div class="card-header">
            <a href="http://foo.com/bar.html#disqus_thread">Review Comments</a>
          </div>
          
          <div class="card-body">
            
            <div id="disqus_thread"></div>
		<script>
		
		/**
		*  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
		*  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables*/
		/*
		var disqus_config = function () {
		this.page.url = PAGE_URL;  // Replace PAGE_URL with your page's canonical URL variable
		this.page.identifier = PAGE_IDENTIFIER; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
		};
		*/
		
		(function() { // DON'T EDIT BELOW THIS LINE
		var d = document, s = d.createElement('script');
		s.src = 'https://cinephile-1.disqus.com/embed.js';
		s.setAttribute('data-timestamp', +new Date());
		(d.head || d.body).appendChild(s);
		})();
		</script>
		<noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>
          </div>
          
          <!-- <div class="card-body">
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Omnis et enim aperiam inventore, similique necessitatibus neque non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum. Sequi mollitia, necessitatibus quae sint natus.</p>
            <small class="text-muted">Posted by Anonymous on 3/1/17</small>
            <hr>
            <a href="#" class="btn btn-success">Leave a Review</a>
          </div> -->
        </div>
        <!-- /.card -->
      </div>
      <!-- /.col-lg-9 -->
    </div>
  </div>
  <!-- /.container -->

	<!-- 첨부파일에 대한 템플릿. 업로드 된 파일이 보여지도록 upload.js 와 handlebars 설정 -->
	<script>
		 var bno="${vo.product_id}";
		 var template=Handlebars.compile($("#templateAttach").html());
		 
		 //컨트롤러에서 문자열의 리스트를 반환, JSON 형태로 데이터 전송하면 getJSON을 이용해 처리한다.
		 $.getJSON("/shop/product/getAttach/"+bno, function(list){
			$(list).each(function(){
				var fileInfo=getFileInfo(this);
				
				var html=template(fileInfo);
				
				$(".uploadedList").append(html);
			});
		 });
	</script>
	
	<script>
	$(".uploadedList").on("click", ".img-pop a", function(event){
		var fileLink=$(this).attr("href");
		
		if(checkImageType(fileLink)){
			event.preventDefault();
			
			var imgTag=$("#popup_img");
			imgTag.attr("src", fileLink);
			
			console.log(imgTag.attr("src"));
			
			$(".popup").show('slow');
			imgTag.addClass("show");
		}
	});
	
	$("#popup_img").on("click", function(){
		$(".popup").hide('slow');
	});
	</script>

<%@ include file="../forward/footer.jsp" %>
</body>
</html>
