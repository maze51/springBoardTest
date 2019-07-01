<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>

		<div class="col-sm-3 col-md-2 sidebar">
			<ul class="nav nav-sidebar">
				<li class="active"><a href="${pageContext.request.contextPath}/manageBoard">게시판 관리</a></li>
			</ul>	
		
			<ul id="blist" class="nav nav-sidebar">
				<c:forEach items="${BOARD_LIST}" var="board">
					<c:choose>
						<c:when test="${board.board_use == 1}">
							<li class="active">
							<a href="${pageContext.request.contextPath}/showBoard?boardId=${board.board_id}&boardName=${board.board_name}">${board.board_name}</a>
							</li>	
						</c:when>
					</c:choose>
				</c:forEach>
			</ul>
		</div>
</body>
