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

/* .popup{	position:absolute;}
.back{background-color:gray; opacity:0.5; width:100%; height:300%; overflow:hidden; z-index:1101;}
.front{z-index:1110; opacity:1; border:1px; margin:auto;}
.show{position:relative; max-width:1200px; max-height:800px; overflow:auto;} */

.nav-aaa{
	padding:1.5rem .15rem !important;
}
.footer{
	width: 100%;
    position:static !important; 
    bottom:0;
    left: 0;
    right:0;
}
</style>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
  <title>CINEPHILE - 국외 영화</title>
	
  <!-- Bootstrap core JavaScript -->
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Bootstrap core CSS -->
  <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom styles for this template -->
  <%-- <link href="${pageContext.request.contextPath }/resources/css/shop-homepage.css" rel="stylesheet"> --%>
  
  <!-- jQuery-UI (infinite scrolling) easing 효과 위하여. -->
  <!-- <script src="/resources/js/jquery-ui.min.js"></script> -->
  <!-- JSTL fmt태그 대용(Date Format) moment.js -->
  <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/af.js"></script> -->
  <!-- <script src="https://unpkg.com/infinite-scroll@3/dist/infinite-scroll.pkgd.js"></script> -->
  
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
<%@ include file="../forward/header.jsp" %>
</head>
<body>
  <!-- Page Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-3">
        <h3 class="my-4">영화 목록</h3>
        <div class="list-group">
          <a href="${path}/shop/product/product_list_korean" class="list-group-item">한국 영화</a>
          <a href="${path}/shop/product/product_list_abroad" class="list-group-item active">해외 영화</a>
          <a href="${path}/shop/product/product_list_etcetera" class="list-group-item">기타 영상</a>
          
          <c:if test="${sessionScope.adminId!=null }">
          <a href="${path}/shop/product/product_regist" class="list-group-item">상품 등록</a>
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
  
  	<!-- <div class="container1"> -->
  
        <div class="row">
        <%-- <c:forEach var="row" items="${list }" begin="1" end="6" step="1"> --%>
        <c:forEach var="row" items="${vo }">
	          <div class="col-lg-4 col-md-6 mb-4" data-bno="${row.product_id }" >
	            <div class="card h-100"  >            
	              <a href="/shop/product/detail/${row.product_id }">
	              	<img class="card-img-top" src="/shop/product/displayFile?fileName=${row.fullName}" style="width:700; height:400;" alt="포스터" title="클릭시 이동"/>
	              	<%-- <img class="card-img-top" src="${row.product_id}" style="width:700; height:400;" alt="포스터" title="클릭시 이동"> --%>
	              </a>
	              <div class="card-body">
	                <h4 class="card-title">
	                  <a href="${path }/shop/product/detail/${row.product_id }">${row.product_name }</a>
	                </h4>
	                <h5><fmt:formatNumber value="${row.product_price }" pattern="###,###,###" /></h5>
	                <p class="card-text">${row.product_desc }</p>
	              </div>
	              <div class="card-footer">
	                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
	              </div>
	            </div><!-- <div class="card h-100"> --> 
	          </div><!-- <div class="col-lg-4 col-md-6 mb-4"> -->
		  </c:forEach>
        </div><!-- /.row -->
	
	<!-- <div id="loader" class="active">
			<img src="/resources/images/loading.gif">
			LOADING...
	</div> -->	
	
      </div><!-- /.col-lg-9 -->
    </div><!-- /.row -->
  </div><!-- /.container -->
  
  	<script>
  	/* $('.container1').infiniteScroll({
  	  // options
  	  //path:'/shop/product/{{#}}',
  	  append: '.post',
  	  history: false,
	
  	   path: function() {
  		 return '/shop/product/product_list_korean/'; //+ ( ( this.loadCount + 1 ) * 10 );
  	  }
  	});
  	
  	$('.container1').removeClass(".active");
  	$('.container1').addClass(".active"); */
	</script>
	
<%@ include file="../forward/footer.jsp" %>
</body>
</html>