<!DOCTYPE html>
<html lang="ko">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<%-- <link rel="icon" type="image/png" href="${pageContext.request.contextPath }/images/icons/favicon.ico"/> --%>
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/vendor/bootstrap/css/bootstrap.min.css/">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/util.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/main.css">
<!--===============================================================================================-->
</head>

<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
				<form class="login100-form validate-form" name="form1" method="post" action="${path }/member/loginPost">
					<span class="login100-form-title p-b-33">
						Account Login
					</span>

					<div class="wrap-input100 validate-input" data-validate = "Valid Id is required">
						<input class="input100" type="text" name="userId" placeholder="Id">
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>

					<div class="wrap-input100 rs1 validate-input" data-validate="Password is required">
						<input class="input100" type="password" name="userPw" placeholder="Password">
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>

					<div class="container-login100-form-btn m-t-20">
						<!-- <button class="login100-form-btn">
							Sign in
						</button> -->
						<input id="submit-btn" type="submit" value="Sign in" class="login100-form-btn">						
					</div>

					<div id="naver_id_login" style="text-align:center"><a href="${url}">
					<img width="223" src="${path}/images/login_naver.png"/></a></div>

					<div class="text-center p-t-45 p-b-4">
						<span class="txt1">
							Forgot
						</span>

						<a href="#" class="txt2 hov1">
							Username / Password?
						</a>
					</div>

					<div class="text-center">
						<span class="txt1">
							Create an account?
						</span>

						<a href="${pageContext.request.contextPath }/member/register.do" class="txt2 hov1">
							Sign up
						</a>
					</div>
					
					<div class="text-center">
						<span class="txt1">
							관리자로 접속하기
						</span>
						<a href="${pageContext.request.contextPath }/admin/adminlogin.do" class="txt2 hov1">
							Sign up
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	

<!--===============================================================================================-->
	<!-- script src="/resources/vendor/jquery/jquery-3.2.1.min.js"></script> -->
	<%-- <script src="<c:url value="/vendor/jquery/jquery.3.2.1.min.js" />"></script> --%>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<!-- <script src="vendor/animsition/js/animsition.min.js"></script> -->
	<script src="<c:url value="/vendor/animsition/js/animsition.min.js" />"></script>
<!--===============================================================================================-->
	<!-- <script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script> -->
	<script src="<c:url value="/vendor/bootstrap/js/popper.js" />"></script>
	<script src="<c:url value="/vendor/bootstrap/js/bootstrap.min.js" />"></script>
<!--===============================================================================================-->
	<!-- <script src="vendor/select2/select2.min.js"></script> -->
	<script src="<c:url value="/vendor/select2/select2.min.js" />"></script>
<!--===============================================================================================-->
	<!-- <script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script> -->
	<script src="<c:url value="/vendor/daterangepicker/moment.min.js" />"></script>
	<script src="<c:url value="/vendor/daterangepicker/daterangepicker.js" />"></script>
<!--===============================================================================================-->
	<!-- <script src="vendor/countdowntime/countdowntime.js"></script> -->
	<script src="<c:url value="/vendor/countdowntime/countdowntime.js" />"></script>
<!--===============================================================================================-->
	<!-- <script src="js/main.js"></script> -->
	<script src="<c:url value="/js/main.js"/>"></script>

	<!-- uid, pw null ì²´í¬ -->
	<script>
		$("#submit-btn").click(function() {
			var uid = $("#userId").val();
			var pw = $("#userPw").val();
			
			if (userId == null || userId == "") {
				alert("ìì´ëë¥¼ ìë ¥í´ì£¼ì¸ì.");
				return false;
			} else if (userPw == null || userPw == "") {
				alert("ë¹ë°ë²í¸ë¥¼ ìë ¥í´ì£¼ì¸ì.");
				return false;
			}
		});
	</script>

	<!-- "ë¤ì´ë² ìì´ëë¡ ë¡ê·¸ì¸" ë²í¼ ë¸ì¶ ìì­ -->
	<script type="text/javascript">
 		var naver_id_login = new naver_id_login("CcHDOhNulVa7_LfwCQeM", "http://localhost:9000/");
 		var state = naver_id_login.getUniqState();
		
 		naver_id_login.setButton("green", 3, 40);
 		naver_id_login.setDomain("http://localhost:9000/");
 		naver_id_login.setState(state);
 		naver_id_login.init_naver_id_login();
	</script>
</body>
	
</html>