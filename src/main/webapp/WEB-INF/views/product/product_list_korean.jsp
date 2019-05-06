<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html lang="en">
<head>
<style>
/* .pagination {
  display: inline-block;
  text-align: center;
  margin-left : 100px;
}
.aa {
  color: black;
  float: left;
  padding: 10px 16px;
  text-decoration: none;
  transition: background-color .3s;
  border: 1px solid #ddd;
}
.aa:hover {
  background-color: #ddd;
}
.aa:active {
  background-color: #343a40;
  color: white;
  border: 1px solid #343a40;
}
.aa.active-color { color:#f67599; }
.aa:hover:not(.active) {background-color: #ddd;} */
</style>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>CINEPHILE</title>
	
  <!-- Bootstrap core JavaScript -->
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Bootstrap core CSS -->
  <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath }/resources/css/shop-homepage.css" rel="stylesheet">
  
  <!-- jQuery-UI (infinite scrolling) easing 효과 위하여. -->
  <script src="/resources/js/jquery-ui.min.js"></script>

  <!-- JSTL fmt태그 대용(Date Format) moment.js -->
  <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/af.js"></script> -->
  
  <!-- jScroll -->
  <script src="//unpkg.com/jscroll/dist/jquery.jscroll.min.js"></script>
  
	<script>

	/* a.active-color { color:#000; }

	$("a").click(function() {
	    toggleClass(".active-color");
	}); 

	$(document).ready(function(){		
		$(".aa").click(function() {
			$(this).toggleClass(".active-color");	
		});
	}); */
	</script>
  
</head>
<body>
  <!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="/">CINEPHILE</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="/">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
          	<c:if test="${login.userId==null }">
            <li>GUEST 접속중</li>
            </c:if>
          </li>
          <li class="nav-item">
          	<c:if test="${login.userId==null }">
            <a class="nav-link" href="${path }/member/loginGET">로그인</a>
            </c:if>
          </li>
          <li class="nav-item">
          	<c:if test="${login.userId!=null }">
            ${login.userId }님이 로그인 중입니다.
            </c:if>
          </li>
          <li class="nav-item">
          	<c:if test="${login.userId!=null }">
            <a class="nav-link" href="${path }/member/logout">로그아웃</a>
            </c:if>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${path }/sboard/search_list">게시판</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">장바구니</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/about">About</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">
        <h3 class="my-4">영화 목록</h3>
        <div class="list-group">
          <a href="${path}/shop/product/product_list_korean" class="list-group-item active">한국 영화</a>
          <a href="${path}/shop/product/product_list_abroad" class="list-group-item">해외 영화</a>
          <a href="${path}/shop/product/product_list_etcetera" class="list-group-item">기타 영상</a>
          
          <c:if test="${sessionScope.adminId!=null }">
          <a href="${path}/shop/product/product_write" class="list-group-item">상품 등록</a>
          <!-- <button type="submit" id="btnAdd" class="list-group-item">상품등록</button> -->
          </c:if>
        </div>
      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">
        <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
          <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
          </ol>
          <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
              <img class="d-block img-fluid" src="/resources/images/slide/netflix.jpg" width="900" height="350" alt="First slide" title="Netflix">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="/resources/images/slide/watcha2.jpg" width="900" height="350" alt="Second slide" title="WATCHA">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="/resources/images/slide/disneyplus.jpg" width="900" height="350" alt="Third slide" title="DisneyPLUS">
            </div>
          </div>
          <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>

        <div class="row" id="autoScroll">
        <c:forEach var="row" items="${list }">
	          <div class="col-lg-4 col-md-6 mb-4" data-bno="${row.product_id }" >
	            <div class="card h-100 "  >
	              <a href="/shop/product/detail/${row.product_id }"><img class="card-img-top" id="images" src="/resources/images/movie/${row.product_url}"
	              													 style="width:700; height:400;" alt="포스터" title="클릭시 이동"></a>
	              <div class="card-body">
	                <h4 class="card-title">
	                  <a href="${path }/shop/product/detail/${row.product_id }">${row.product_name }</a>
	                </h4>
	                <h5><fmt:formatNumber value="${row.product_price }" pattern="###,###,###" /></h5>
	                <p class="card-text">${row.product_desc }</p>
	              </div>
	              <div class="card-footer">
	              <div><a href="product_list_korean" class="nextPage">다음 페이지</a></div>

	                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
	              </div>
	            </div><!-- <div class="card h-100"> --> 
	          </div><!-- <div class="col-lg-4 col-md-6 mb-4"> -->
		  </c:forEach>
        </div>
        <!-- /.row -->
        
    <!-- JavaScript로 링크 처리 (기존방식은 JSP내에서 PageMaker의 메소드makeQuery를 이용하여 수정. 페이지 정보를 유지할 수 있도록)-->
<%--     <form id="jobForm">
    	<input type="hidden" name="page" value=${pageMaker.cri.perPageNum }>
    	<input type="hidden" name="perPageNum" value=${pageMaker.cri.perPageNum }>
    </form> --%>
    
<%--     <div>
		<ul class="pagination" style="text-align:center; margin: 10; padding:10;">
			<c:if test="${pageMaker.prev }">
				<li><a href="product_list_korean?page=${pageMaker.startPage-1 }">&laquo;</a></li>
			</c:if>
			
			<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
			
				<li <c:out value="${pageMaker.cri.page==idx?'class=active':'' }"/>>
					<a href="product_list_korean?page=${idx }">${idx }</a>			
				</li>
			</c:forEach>
			
			<c:if test="${pageMaker.next&&pageMaker.endPage>0 }">
				<li><a href="product_list_korean?page=${pageMaker.endPage+1 }">&raquo;</a></li>
			</c:if>
		</ul>
	</div> --%>
    
    <!-- 단순히 게시물의 번호를 전송하는 링크에서 페이지 정보를 유지할 수 있도록 변경됨 -->
<%-- 	<div>
		<ul class="pagination" >
			<c:if test="${pageMaker.prev }">
				<li ><a class="aa" href="product_list_korean${pageMaker.makeQuery(pageMaker.startPage-1) }">&laquo;</a></li>
			</c:if>
			
			<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">			
				<li <c:out value="${pageMaker.cri.page==idx?'class=active':'' }"/>>
					<a class="aa" href="product_list_korean${pageMaker.makeQuery(idx) }">${idx }</a>			
				</li>
			</c:forEach>
			
			<c:if test="${pageMaker.next&&pageMaker.endPage>0 }">
				<li><a class="aa" href="product_list_korean${pageMaker.makeQuery(pageMaker.endPage+1) }">&raquo;</a></li>
			</c:if>
		</ul>
	</div> --%>
	<%-- "<h5>" + "'<fmt:formatNumber value='" + this.product_price + "pattern=" + "'###,###,###'" + ">" + this.product_price + "</h5>" --%>
      </div>
      <!-- /.col-lg-9 -->
    </div>
    <!-- /.row -->
  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; CINEPHILE 2019</p>
    </div>
    <!-- /.container -->
  </footer>
  
	<script>
		/* moment().format(); */
	</script>

	<!-- jScroll -->
	<script>
	//<![CDATA[ $(document).ready(function () { 
		$('#autoScroll').jscroll({ 
			autoTrigger: true,
			loadingHtml: '<div class="next"><img src="/resources/images/loading.gif" alt="Loading" /></div>', 
			nextSelector: 'a.nextPage:last' });
	//]]>
	</script>
	
	<!-- 페이지 번호를 클릭하면 처리하는 javascript -->
<!-- 	<script>
		$(".pagination li a").on("click", function(event){
			event.preventDefault();
			
			var targetPage=$(this).attr("href");
			
			var jobForm=$("#jobForm");
			jobForm.find("[name='page']").val(targetPage);
			jobForm.attr("action", "/product/product_list_korean").attr("method", "get");
			jobForm.submit();
		});	
	</script> -->
	
	<!-- 상품등록 버튼 클릭 이벤트가 발생하면 상품 등록 페이지로 이동 -->
<!-- 	<script type="text/javascript">
	$(document).ready(function(){
		$("#btnAdd").click(function(){
			location.href="${path}/shop/product/product_write";	
		});
	});
	</script> -->
	
   <!-- Bootstrap core JavaScript -->
  <!--<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script> -->
</body>
</html>