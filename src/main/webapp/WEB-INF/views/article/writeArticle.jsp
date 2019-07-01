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
<script type="text/javascript" src="${pageContext.request.contextPath}/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<title>게시글 작성</title>
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
</style>
<script type="text/javascript">
$(function() {

	  var max_file_number = 5,
      // form id
      $form = $('#wafrm'), 
      // upload field 
      $file_upload = $('#appendF', $form), 
      // submit
      $button = $('#savebutton', $form); 

	  $file_upload.on('change', function () {
	    var number_of_images = $(this)[0].files.length;
	    if (number_of_images > max_file_number) {
	      alert('첨부파일은 총 5개까지 업로드 가능합니다');
	      $(this).val('');
	    }
	  });
	});

var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

$(document).ready(function() {
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "/board/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 전송버튼 클릭이벤트
	$("#savebutton").click(function(){
		if(confirm("저장하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#wafrm").submit();
			}
		}
	})
	
});

// 필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}
	return true;
}

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
				<form id="wafrm" class="form-horizontal"
					action="${pageContext.request.contextPath }/writeArticle"
					method="post" enctype="multipart/form-data">
					
					<table class="table">
						<tr>
							<td class="th">제목</td>
							<td><input type="text" id="title" name="title"></td>
						</tr>
						<tr>
							<td class="th">글내용</td>
							<td>
						    	<textarea name="content" id="smarteditor" rows="10" cols="100" style="width:766px; height:412px;"></textarea>
							</td>
						</tr>
						<tr>
							<td class="th">첨부파일</td>
							<td><input type="file" name="profile" id="appendF" multiple/> 파일은 최대 5개까지 첨부 가능합니다</td>
						</tr>
					</table>
					
					<input type="hidden" name="boardId" value="${param.boardId}">
					<button type="button" id="savebutton" class="btn btn-lg btn-primary">저장</button>
					
				</form>
			</div>
			<div class="container">
			</div>
		</div>
	</div>
</body>
</html>