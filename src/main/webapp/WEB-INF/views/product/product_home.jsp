<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
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
            <a class="nav-link" href="about">Contact</a>
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
          <a href="${path}/shop/product/product_list_korean" class="list-group-item">한국 영화</a>
          <a href="${path}/shop/product/product_list_abroad" class="list-group-item">해외 영화</a>
          <a href="${path}/shop/product/product_list_etcetera" class="list-group-item">기타 영상</a>
          
          <c:if test="${sessionScope.adminId!=null }">
          <a href="${path}/shop/product/product_write" class="list-group-item">《관리자 상품 등록》</a>
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
              <img class="d-block img-fluid" src="/resources/images/movie/movie_image.jpg" width="900" height="350" alt="First slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="/resources/images/movie/movie_image (1).jpg" width="900" height="350" alt="Second slide">
            </div>
            <div class="carousel-item">
              <img class="d-block img-fluid" src="/resources/images/movie/movie_image (2).jpg" width="900" height="350" alt="Third slide">
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
          <div class="col-lg-4 col-md-6 mb-4">
            <!-- <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">AVENGERS</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div> -->
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <!-- <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">전혜린</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor sit amet.</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div> -->
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <!-- <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">뻐꾸기 둥지 위로 날아간 새 - 켄 키지</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div> -->
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <!-- <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">파우스트 - 괴테</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div> -->
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
           <!--  <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">젊은 느티나무 - 강신재</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor sit amet.</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div> -->
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <!-- <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">변신/시골의사 - 프란츠 카프카</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div> -->
          </div>
       
        </div>
        <!-- /.row -->
      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->
  </div>
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

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품목록</title>
</head>
<body>
<!-- 상품 목록 화면에서 상품ID번호, 상품 이미지, 상품명, 가격을 리스트로 출력 -->
<table border="1">
	<tr>
		<th>상품번호</th>
		<th>상품이미지</th>
		<th>상품명</th>
		<th>가격</th>
	</tr>
	<c:forEach var="row" items="${list }">
	<tr>
		<td>
			${row.product_id }
		</td>
		<td>
			<!-- 상품이미지와 상품명을 클릭하면 해당 상품의 상세 화면으로 이동할 수 있도록 <a href/>태그로 링크 -->
			<a href="${path }/shop/product/detail/${row.product_id}">
				<img src="${path}/images/${row.product_url}" width="120px" height="110px"><!-- 잘 돌아가다가 갑자기 404 not found -->
				
				<!-- 아래 둘 중 한 가지를 사용할 수 있다. -->
				<img src="/resources/images/${row.product_url}" width="120px" height="110px">
				<img src="../../resources/images/${row.product_url}" width="120px" height="110px">
				
				절대경로. 로컬에서만 접근가능
				<img src="C:\_java\05_sts_work\00_Portfolio\src\main\webapp\resources\images/${row.product_url}" width="120px" height="110px">
			</a>
		</td>
		<td>
			<a href="${path }/shop/product/detail/${row.product_id }">${row.product_name }</a>
		</td>
		<td>
			<!-- JSLT fmt태그로 숫자 포맷 변경. 상품가격의 가독성을 높이기 위해 숫자3자리마다 콤마, 찍어주도록 처리 -->
			<fmt:formatNumber value="${row.product_price }" pattern="###,###,###" />
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html> --%>