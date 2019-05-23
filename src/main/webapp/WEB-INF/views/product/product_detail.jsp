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
  <link href="${pageContext.request.contextPath }/resources/css/shop-item.css" rel="stylesheet">
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
            <a class="nav-link" href="#">Home
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
            <a class="nav-link" href="/shop/cart/cart_list">장바구니</a>
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
          <img class="card-img-top img-fluid" style="width:900px; height:400px;" src="/resources/images/movie/${vo.product_url}"  alt="포스터" >
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

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2019</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- display comment count -->
  <script id="dsq-count-scr" src="//cinephile-1.disqus.com/count.js" async></script>
  
  <!-- Bootstrap core JavaScript -->
  <script src="/resources/vendor/jquery/jquery.min.js"></script>
  <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>
