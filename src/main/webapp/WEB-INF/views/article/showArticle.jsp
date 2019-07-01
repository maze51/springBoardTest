<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>게시글 상세보기</title>
<!-- css, js -->
<%@include file="../common/basicLib.jsp"%>
<style>
	table{
		margin: 50px 0 0 0;
	}
	td{
		font-weight: bold;
	}
	.th{
		width : 150px;
	}
	input{
		width : 500px;
		margin : 0 10px 0 0;
	}
	.download:hover{
		cursor : pointer;
	}
</style>
<script>
	$(document).ready(function(){
		$("#deleteBtn").on("click", function(){
			var yn = confirm("게시글을 삭제하시겠습니까?");
			if(yn == true){
				$("#dform").submit();
			}
		})
		
		$("#modifyBtn").on("click", function(){
			$("#mform").submit();
		})
		
		$("#writeR").on("click", function(){
			var yn2 = confirm("댓글을 입력하시겠습니까?");
			if(yn2 == true){
				$("#rform").submit();
			}
		})
		
		$("#replyArticleBtn").on("click", function(){
			$("#raform").submit();
		})
		
		$(".download").on("click", function(){
			var id = $(this).children().val();
			//alert(id);
			$("#aId").val(id);
			$("#file").submit();
		})
		
	})
</script>

</head>
<body>
	<!-- header -->
	<%@include file="../common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@include file="../common/left.jsp"%>
			<div class="container">
				<table class="table">
					<tr>
						<td class="th">제목</td>
						<td>${article.article_title}</td>
					</tr>
					<tr>
						<td class="th">글내용</td>
						<td>
						${article.article_content}
						</td>
					</tr>
					<tr>
						<td class="th">첨부파일</td>
						<td>
							<c:forEach items="${append}" var="append">
								<span class="download">${append.append_filename}
									<input type="hidden" id="si" value="${append.append_id}">
								</span><br>
							</c:forEach>
						</td>
					</tr>
				</table>
				
			<%-- 전송용 hidden form --%>
			<form action="${pageContext.request.contextPath}/deleteArticle" 
				id="dform" method="get">
				<input type="hidden" name="articleNum" value="${article.article_number}"/>
			</form>
			
			<form action="${pageContext.request.contextPath}/modifyArticle" 
				id="mform" method="get">
				<input type="hidden" name="arNum" value="${article.article_number}"/>
			</form>
			
			<form action="${pageContext.request.contextPath}/writeReplyArticle" 
				id="raform" method="get">
				<input type="hidden" name="articleNumRA" value="${article.article_number}"/>
				<input type="hidden" name="groupId" value="${article.article_group}"/>
				<input type="hidden" name="boardId" value="${article.article_board}"/>
				<input type="hidden" name="pId" value="${article.article_number}"/>
			</form>
			
			<form action="${pageContext.request.contextPath}/downloadFile"
				id="file" method="post" enctype="multipart/form-data">
				<input type="hidden" name="aId" id="aId">
			</form>
			<%-- hidden form 끝 --%>
			
			<c:if test="${article.article_user == USER_INFO.userId}">
				<button type="button" class="btn btn-default" id="modifyBtn">수정</button>
				<button type="button" class="btn btn-default" id="deleteBtn">삭제</button>
			</c:if>
			<button type="button" class="btn btn-default" id="replyArticleBtn">답글</button>
			
			</div>
			<div class="container">
			<table class="table">
					<tr>
						<td class="th"></td>
						<td>댓글</td>
					</tr>
					<c:forEach items="${reply}" var="reply">
						<c:choose>
							<c:when test="${reply.reply_use == 1}">
								<tr>
									<td></td>
									<td>
										${reply.reply_content } [${reply.reply_user } / ${reply.reply_dateStr }]
										<c:if test="${reply.reply_user == USER_INFO.userId}">
											<a href="${pageContext.request.contextPath}/deleteReply?replyId=${reply.reply_id}&aNumber=${article.article_number}"><img src="images/delete-sign.png"></a>
										</c:if>
									</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td></td>
									<td>삭제된 댓글입니다</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<tr>
						<td class="th"></td>
						<td>
						<form id="rform" method="post" action="${pageContext.request.contextPath}/writeReply">
							<input type="text" id="reply" name="reply" maxlength="500"/>
							<input type="hidden" name="aNum" value="${article.article_number}"/>
							<button type="button" id="writeR" class="btn btn-default">댓글저장</button>
						</form>
						</td>
					</tr>
			</table>
			</div>
		</div>
	</div>
</body>
</html>