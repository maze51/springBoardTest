<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>게시판</title>

<!-- css, js -->
<%@include file="../common/basicLib.jsp"%>
<style>
	.selectArticle:hover{
		cursor : pointer;
	}
</style>

<script>
	$(document).ready(function(){
		$(".selectArticle").on("click", function(){
			
			var aNum = $(this).find(".aNum").text();
			console.log(aNum);
			$("#aNumber").val(aNum);
			
			$("#form1").submit();
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
			
			<form id="form1" action="${pageContext.request.contextPath}/showArticle"
						method="get">
							<input type="hidden" id="aNumber" name="aNumber" />
			</form>
						
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header" id="bName">${param.boardName}</h2>
						
						<div class="table-responsive">
							<table class="table table-striped table-bordered">
								<tr>
									<th>게시글 번호</th>
									<th>제목</th>
									<th>작성자 아이디</th>
									<th>작성일시</th>
								</tr>

								<c:forEach items="${articleList }" var="list">
									<c:choose>
										<c:when test="${list.article_use == 1}">
											<tr class="selectArticle">
												<td class="aNum">${list.article_number }</td>
												<td><c:forEach begin="1" end="${list.lv-1}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>${list.article_title }</td>
												<td>${list.article_user }</td>
												<td>${list.article_dateStr }</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr>
												<td>${list.article_number }</td>
												<td>삭제된 게시글입니다</td>
												<td></td>
												<td></td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</table>
						</div>

						<a href="${pageContext.request.contextPath}/writeArticle?boardId=${param.boardId}"
							class="btn btn-default pull-right">새글 등록</a>

						<div class="text-center">
							<ul class="pagination">
								<c:choose>
									<c:when test="${PageVo.page == 1 }">
										<li class="disabled"><span>««</span></li>
										<li class="disabled"><span>«</span></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="${pageContext.request.contextPath}/showBoard?page=1&pageSize=${PageVo.pageSize}&boardId=${param.boardId}&boardName=${param.boardName}">««</a>
											<a
											href="${pageContext.request.contextPath}/showBoard?page=${PageVo.page - 1}&pageSize=${PageVo.pageSize}&boardId=${param.boardId}&boardName=${param.boardName}">«</a>
										</li>
									</c:otherwise>
								</c:choose>

								<c:forEach begin="1" end="${paginationSize}" var="i">
									<c:choose>
										<c:when test="${PageVo.page == i }">
											<li class="active"><span>${i }</span></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pageContext.request.contextPath}/showBoard?page=${i}&pageSize=${PageVo.pageSize }&boardId=${param.boardId}&boardName=${param.boardName}">${i}</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:choose>
									<c:when test="${PageVo.page == paginationSize }">
										<li class="disabled"><span>»</span></li>
										<li class="disabled"><span>»»</span></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="${pageContext.request.contextPath}/showBoard?page=${PageVo.page + 1}&pageSize=${PageVo.pageSize}&boardId=${param.boardId}&boardName=${param.boardName}">»</a></li>
										<li><a
											href="${pageContext.request.contextPath}/showBoard?page=${paginationSize}&pageSize=${PageVo.pageSize}&boardId=${param.boardId}&boardName=${param.boardName}">»»</a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
