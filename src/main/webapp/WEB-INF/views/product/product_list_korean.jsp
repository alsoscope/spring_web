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

        <div class="row listToChange scrollLocation"><!-- class 두 개 추가(scrolling) -->
        <c:forEach var="row" items="${list }">
          <%-- <c:forEach var="row" items="${list }" begin="1" end="6" step="1"> --%>
	          <div class="col-lg-4 col-md-6 mb-4 scrolling" data-bno="${row.product_id }"><!-- data-bno 추가(scrolling) -->
	            <div class="card h-100">
	              <a href="/shop/product/detail/${row.product_id }"><img class="card-img-top" src="/resources/images/movie/${row.product_url}"
	              													 style="width:700; height:400;" alt="포스터" title="클릭시 이동"></a>
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
  
	<!-- javascript로 무한 스크롤링. 스크롤 이벤트 -->
	<script>
	
		var result = '${msg}';
	    
	    if(result="success"){
	       alert(result);
	       console.log(result);
	   }
	    
	   var lastScrollTop = 0;
	   var easeEffect = 'easeInQuint';
	    
	   // 1. 스크롤 이벤트 발생
	   $(window).scroll(function(){ // ① 스크롤 이벤트 최초 발생
	        
	       var currentScrollTop = $(window).scrollTop();
	        
	       /* ================= 다운 스크롤인 상태  ================ */
	       if( currentScrollTop - lastScrollTop > 0 ){
	           // down-scroll : 현재 게시글 다음의 글을 불러온다.
	           console.log("down-scroll");
	            
	           // 2. 현재 스크롤의 top 좌표가  > (게시글을 불러온 화면 height - 윈도우창의 height) 되는 순간
	           if ($(window).scrollTop() >= ($(document).height() - $(window).height()) ){ //② 현재스크롤의 위치가 화면의 보이는 위치보다 크다면
	                
	               // 3. class가 scrolling인 것의 요소 중 마지막인 요소를 선택한 다음 그것의 data-bno속성 값을 받아온다.
	               //      즉, 현재 뿌려진 게시글의 마지막 bno값을 읽어오는 것이다.( 이 다음의 게시글들을 가져오기 위해 필요한 데이터이다.)
	               var lastbno = $(".scrolling:last").attr("data-bno");
	                
	               // 4. ajax를 이용하여 현재 뿌려진 게시글의 마지막 bno를 서버로 보내어 그 다음 20개의 게시물 데이터를 받아온다.
	               $.ajax({
	                   type : 'post',  // 요청 method 방식
	                   url : 'infiniteScrollDown',// 요청할 서버의 url
	                   headers : { "Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST" },
	                   dataType : 'json', // 서버로부터 되돌려받는 데이터의 타입을 명시하는 것이다.
	                   data : JSON.stringify({ // 서버로 보낼 데이터 명시
	                	   product_id : lastbno
	                   }),
	                   success : function(data){// ajax 가 성공했을시에 수행될 function이다. 이 function의 파라미터는 서버로 부터 return받은 데이터이다.
	                        
	                       var str = "";
	                        
	                       // 5. 받아온 데이터가 ""이거나 null이 아닌 경우에 DOM handling을 해준다.
	                       if(data != ""){
	                           //6. 서버로부터 받아온 data가 list이므로 이 각각의 원소에 접근하려면 each문을 사용한다.
	                           $(data).each(
	                               // 7. 새로운 데이터를 갖고 html코드형태의 문자열을 만들어준다.
	                               function(){
	                                   console.log(this);     
	                                   str +=  "<div class=" + "'listToChange'" + ">"
	                                       +       "<div class=" +  "'scrolling'" + " data-bno='" + this.product_id +"'>"
	                                       +           this.product_id
	                                       +       "<img>" + this.product_url      
	                                       +       "<h4>" + this.product_name + "</h4>"
	                                       +       "<h5>" + this.product_price + "</h5>"
	                                       +       "<p>" + this.product_desc + "</p>"
	                                       +       "</div>"
	                                       +   "</div>";
	                                        
	                           });// each
	                           // 8. 이전까지 뿌려졌던 데이터를 비워주고, <th>헤더 바로 밑에 위에서 만든 str을  뿌려준다.
	                           $(".listToChange").empty();// 셀렉터 태그 안의 모든 텍스트를 지운다.                       
	                           $(".scrollLocation").after(str);
	                                
	                       }// if : data!=null
	                       else{ // 9. 만약 서버로 부터 받아온 데이터가 없으면 그냥 아무것도 하지말까..
	                           alert("더 불러올 데이터가 없습니다. scrolldown");
	                       }// else
	        
	                   }// success
	               });// ajax
	                
	               // 여기서 class가 listToChange인 것중 가장 처음인 것을 찾아서 그 위치로 이동하자.
	               var position = $(".listToChange:first").offset();// 위치 값
	                
	               // 이동  위로 부터 position.top px 위치로 스크롤 하는 것이다. 그걸 500ms 동안 애니메이션이 이루어짐.
	               $('html,body').stop().animate({scrollTop : position.top }, 600, easeEffect);
	    
	           }//if : 현재 스크롤의 top 좌표가  > (게시글을 불러온 화면 height - 윈도우창의 height) 되는 순간
	            
	           // lastScrollTop을 현재 currentScrollTop으로 갱신해준다.
	           lastScrollTop = currentScrollTop;
	       }// 다운스크롤인 상태
	        
	       /* ================= 업 스크롤인 상태   ================ */
	       else{
	           // up- scroll : 현재 게시글 이전의 글을 불러온다.
	           console.log("up-scroll");          
	
	           // 2. 현재 스크롤의 top 좌표가  <= 0 되는 순간
	           if ($(window).scrollTop() <= 0 ){ //
	                
	               // 3. class가 scrolling인 것의 요소 중 첫 번째 요소를 선택한 다음 그것의 data-bno속성 값을 받아온다.
	               //      즉, 현재 뿌려진 게시글의 첫 번째 bno값을 읽어오는 것이다.( 이 전의 게시글들을 가져오기 위해 필요한 데이터이다.)
	               var firstbno = $(".scrolling:first").attr("data-bno");
	                
	               // 4. ajax를 이용하여 현재 뿌려진 게시글의 첫 번째 bno를 서버로 보내어 그 이전의 20개의 게시물 데이터를 받아온다.
	               $.ajax({
	                   type : 'post',  // 요청 method 방식
	                   url : 'infiniteScrollUp',// 요청할 서버의 url
	                   headers : { "Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST" },
	                   dataType : 'json', // 서버로부터 되돌려받는 데이터의 타입을 명시하는 것이다.
	                   data : JSON.stringify({ // 서버로 보낼 데이터 명시
	                       bno : firstbno
	                   }),
	                   success : function(data){// ajax 가 성공했을시에 수행될 function이다. 이 function의 파라미터는 서버로 부터 return받은 데이터이다.
	                        
	                       var str = "";
	                        
	                       // 5. 받아온 데이터가 ""이거나 null이 아닌 경우에 DOM handling을 해준다.
	                       // 이때 서버에서 값이 없으면 null을 던질줄 알았는데 ""를 던진다. 따라서 (data != null) 이라는 체크를 해주면 안되고, (data != "") 만 해주어야한다.
	                       // 이건아마 컨트롤러의 리턴타입이 @ResponseBody로 json형태로 던져지는데 이때 아마 아무것도 없는게 ""로 명시되어 날아오는것 같다.
	                       if(data != ""){
	                            
	                           //6. 서버로부터 받아온 data가 list이므로 이 각각의 원소에 접근하려면 each문을 사용한다.
	                           $(data).each(
	                               // 7. 새로운 데이터를 갖고 html코드형태의 문자열을 만들어준다.
	                               function(){
	                                   console.log(this);     
	                                   str +=  "<div class=" + "'listToChange'" + ">"
	                                   +       "<div class=" +  "'scrolling'" + " data-bno='" + this.product_id +"'>"
	                                   +           this.product_id
	                                   +       "<img>" + this.product_url      
	                                   +       "<h4>" + this.product_name + "</h4>"
	                                   +       "<h5>" + this.product_price + "</h5>"
	                                   +       "<p>" + this.product_desc + "</p>"
	                                   +       "</div>"
	                                   +   "</div>";
	                                        
	                           });// each
	                           // 8. 이전까지 뿌려졌던 데이터를 비워주고, <th>헤더 바로 밑에 위에서 만든 str을  뿌려준다.
	                           $(".listToChange").empty();// 셀렉터 태그 안의 모든 텍스트를 지운다.                       
	                           $(".scrollLocation").after(str);
	                                
	                       }//if : data != ""
	                       else{ // 9. 만약 서버로 부터 받아온 데이터가 없으면 그냥 아무것도 하지말까..??
	                           alert("더 불러올 데이터가 없습니다. scrollup");
	                       }// else
	                   }// success
	               });// ajax
	                
	               // 스크롤 다운이벤트 때  ajax통신이 발생하지 않을때 까지의 좌표까지 스크롤을 내려가주기.
	               var position =($(document).height() - $(window).height()) -10;
	                
	               // 이동  위로 부터 position.top px 위치로 스크롤 하는 것이다. 그걸 500ms 동안 애니메이션이 이루어짐.
	               $('html,body').stop().animate({scrollTop : position}, 600, easeEffect);
	                
	           }//if : 현재 스크롤의 top 좌표가  <= 0 되는 순간
	        
	           // lastScrollTop을 현재 currentScrollTop으로 갱신해준다.
	           lastScrollTop = currentScrollTop;
	       }// else : 업 스크롤인 상태
	        
		   });// scroll event
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