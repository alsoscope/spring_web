<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="ko">
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<style type="text/css">
html, div, body, h3 {
  margin: 0;
  padding: 30;
  align:center;
}
h3 {
  display: inline-block;
  /* padding: 0.6em; */
}
</style>
<%@ include file="../forward/header.jsp" %>
</head>
<body>

<script type="text/javascript">
$(document).ready(function() {
    var name = ${result }.response.name;
    var email = ${result }.response.email;
    $("#name").html("환영합니다. " + name + "님");
    $("#email").html("이메일 확인 : " + email);
});
  //location.href = "${pageContext.request.contextPath}/";
</script>

<div style="background-color: #15a181; width:350px; height: 70px; text-align: center; color: white;">
    <h3>네이버 로그인 성공</h3>
</div>

<%-- ${result } --%>

  <br>
  <h3 id="name"></h3><br>
  <h3 id="email"></h3>

<c:if test="${vo != null }">
<div style="background-color: #15a181; width:180px; height: 50px; text-align: center; color: white;">
    <h3>회원 가입 성공</h3>
</div>
<h3>아이디 : ${vo.userId }</h3><br>
<h3>이름 : ${vo.userName }</h3>
</c:if>

  <script>
    $(function () {
      /* $("body").hide();
      $("body").fadeIn(1000);  // 1초 뒤에 사라 지자  */
     
      setTimeout(function(){$("body").fadeOut(1000);},100000);
      setTimeout(function(){location.href= "${pageContext.request.contextPath}/"},100000); //2초 뒤에 메인 화면  
    
    });
  </script>
  
<%@ include file="../forward/footer.jsp" %>
</body>
</html>