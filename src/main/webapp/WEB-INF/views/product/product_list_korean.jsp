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
  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath }/resources/css/shop-homepage.css" rel="stylesheet">
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

        <div class="row">
        
          <c:forEach var="row" items="${list }" begin="1" end="6" step="1">
          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="/shop/product/detail/${row.product_id }"><img class="card-img-top" src="/resources/images/movie/${row.product_url}" style="width:700; height:400;" alt="포스터" title="클릭시 이동"></a>
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

        </div>
        <!-- /.row -->
      </div>
      <!-- /.col-lg-9 -->
    </div>
    <!-- /.row -->
  </div>

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

	</ul>


  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2019</p>
    </div>
    <!-- /.container -->
  </footer>

	<!-- 상품등록 버튼 클릭 이벤트가 발생하면 상품 등록 페이지로 이동 -->
<!-- 	<script type="text/javascript">
	$(document).ready(function(){
		$("#btnAdd").click(function(){
			location.href="${path}/shop/product/product_write";	
		});
	});
	</script> -->
	
  <!-- Bootstrap core JavaScript -->
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>