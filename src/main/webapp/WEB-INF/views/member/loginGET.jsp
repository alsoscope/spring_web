<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<%-- <link rel="icon" type="image/png" href="${pageContext.request.contextPath }/images/icons/favicon.ico"/> --%>
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }../resources/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }../resources/vendor/bootstrap/css/bootstrap.min.css/">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }../resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }../resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }../resources/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }../resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }../resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }../resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }../resources/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/util.css"> --%>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }../resources/css/util.css">
	<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/main.css"> --%>
	<link rel="stylesheet" href="<c:url value="../resources/css/main.css"/>">
<!--===============================================================================================-->
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
				<form class="login100-form validate-form" name="form1" method="post" action="${path }/member/loginPOST">
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

					<br>
					
					<!-- 네이버 로그인 -->
					<div style="text-align:center">
					<%-- <a href="${url}"> --%>
					<a href="${url}" onClick="naverPop();" target="_blank">
					<img width="250" src="${path}../resources/images/login_naver.png"/></a></div>
					<!-- 네이버 로그인 -->
					
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
							회원가입
						</span>
						<a href="${pageContext.request.contextPath }/member/register" class="txt2 hov1">
							Sign up
						</a>
					</div>
					
					<div class="text-center">
						<span class="txt1">
							관리자로 접속하기
						</span>
						<a href="${pageContext.request.contextPath }/admin/adminlogin" class="txt2 hov1">
							Sign up
						</a>
					</div>
					
					<div class="text-center">
						<span class="txt1">
							메인으로 돌아가기
						</span>
						<a href="/" class="txt2 hov1">메인으로</a>
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
	<script src="<c:url value="../resources/vendor/animsition/js/animsition.min.js" />"></script>
<!--===============================================================================================-->
	<!-- <script src="vendor/bootstrap/js/popper.js"></script> -->
	<!-- <script src="../resources/vendor/bootstrap/js/bootstrap.min.js"></script> -->
	<script src="<c:url value="../resources/vendor/bootstrap/js/popper.js"/>"></script>
	<script src="<c:url value="../resources/vendor/bootstrap/js/bootstrap.min.js" />"></script>
<!--===============================================================================================-->
	<!-- <script src="vendor/select2/select2.min.js"></script> -->
	<script src="<c:url value="../resources/vendor/select2/select2.min.js" />"></script>
<!--===============================================================================================-->
	<!-- <script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script> -->
	<script src="<c:url value="../resources/vendor/daterangepicker/moment.min.js" />"></script>
	<script src="<c:url value="../resources/vendor/daterangepicker/daterangepicker.js" />"></script>
<!--===============================================================================================-->
	<!-- <script src="vendor/countdowntime/countdowntime.js"></script> -->
	<script src="<c:url value="../resources/vendor/countdowntime/countdowntime.js" />"></script>
<!--===============================================================================================-->
	<!-- <script src="../js/main.js"></script> -->
	<script src="<c:url value="../resources/js/main.js"/>"></script>
<!--===============================================================================================-->

	<!-- uid, pw null 체크 -->
	<script>
		$("#submit-btn").click(function() {
			var uid = $("#userId").val();
			var pw = $("#userPw").val();
			
			if (userId == null || userId == "") {
				alert("아이디를 입력해주세요.");
				return false;
			} else if (userPw == null || userPw == "") {
				alert("비밀번호를 입력해주세요.");
				return false;
			}
		});
	</script>

	<!-- 네이버 로그인 팝업창으로 띄움 -->
	<script type="text/javascript">
	/* function naverPop(){

 		var naver_id_login = new naver_id_login("CcHDOhNulVa7_LfwCQeM", "http://localhost:9000/member/loginGET");
 		var state = naver_id_login.getUniqState();
		
 		naver_id_login.setButton("green", 3, 40);
 		naver_id_login.setDomain("http://localhost:9000/member/loginGET");
 		naver_id_login.setState(state);
 		naver_id_login.setPopup();
 		naver_id_login.init_naver_id_login();

 		var url="${url}";
 		var name="popup_test";
 		var option="toolbar=yes, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400";
 		
 		window.open(url, name, option);
	} */
	
	function naverPop(){
		naver_id_login.setPopup();
	}
	</script>

</body>
</html>