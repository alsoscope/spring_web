<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- handlebars CDN. 화면에 반복적으로 처리되는(목록 갱신되어 화면에 출력) 템플릿 코드 처리 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.0/handlebars.js"></script>
</head>
<body>
<!-- handlebars 사용하는 템플릿 코드 -->
<script id="template" type="text/x-handlebars-template">
{{#each.}}
<li class="replyLi" data-rno={{rno}}>

</li>
{{/each}}
</script>

<!-- 댓글 등록에 필요한 <div> -->
<div class="row">
	<div class="col-md-12">
		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">ADD NEW REPLY</h3>
			</div>
			<div class="box-body">
				<label for="newReplyWriter">Writer</label>
					<input class="form-control" type="text" placeholder="USER ID" id="newReplyWriter">
					<label for="newReplyText">ReplyText</label>
					<input class="form-control" type="text" placeholder="REPLY TEXT" id="newReplyText">
			</div>
			<!-- /.box-body -->
			<div class="box-footer">
				<button type="submit" class="btn btn-primary" id="replyAddBtn">ADD REPLY</button>
			</div>
		</div>		
	</div>
</div>

<!-- 댓글 목록과 페이징 처리에 필요한 <div> -->
<!-- The time line -->
<ul class="timeline">
	<!-- timeline time label -->
	<li class="time-label" id="repliesDiv"><span class="bg-green">Replies List</span></li>
</ul>

<div class='text-center'>
	<ul id="pagination" class="pagination pagination-sm no-margin "></ul>
</div>

</body>
</html>