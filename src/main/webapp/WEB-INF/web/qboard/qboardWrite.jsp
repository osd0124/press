<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/ckeditor/ckeditor.js"></script>
</head>
<body>
	<form id="frm" name="frm" enctype="multipart/form-data">
		<table class="board_view">
			<colgroup>
				<col width="15%" />
				<col width="*" />
			</colgroup>
			<caption>게시글 작성</caption>
			<tbody>
				<tr>
					<th scope="row">제목</th>
					<td><input type="text" id="TITLE" name="TITLE" class="wdp_90" /></td>
				</tr>
				<tr>
					<td colspan="2" class="view_text">
						<textarea rows="20" cols="100" title="내용" id="CONTENTS" name="CONTENTS"></textarea>
						<script type="text/javascript">
                		CKEDITOR.replace( 'CONTENTS' );
            			</script>
					</td>	
				</tr>		
			</tbody>	
		</table>	
		<div id="fileDiv">
			<p>
				<input type="file" id="file" name="file_0" >
				<a href="#this" class="btn" id="delete" name="delete">삭제</a>
			</p>	
		</div>
		
		<br/><br/>
		<a href="#this" class="btn" id="addFile">파일 첨부</a>
		<a href="#this" class="btn" id="write">등록</a>
		<a href="#this" class="btn" id="list">목록</a>
	</form>
	<form id="commonForm" name="commonForm"></form>
	<script src="<c:url value='/resources/js/common.js'/>"></script>
	<script>
		var gfv_count = 1;
		$(document).ready(function(){
			$('#list').on("click",function(e){
				e.preventDefault();
				fn_openBoardList();
			});
			$('#write').on("click",function(e){
				e.preventDefault();
				fn_insertBoard();
			});
			$('#addFile').on("click",function(e){
				e.preventDefault();
				fn_addFile();
			});
			$("a[name='delete']").on("click",function(e){
				e.preventDefault();
				fn_deleteFile($(this));
			});
		});		
		
		function fn_openBoardList(){
			var submit = new ComSubmit(1);
			submit.setUrl("<c:url value='/qboard/openBoardList.do'/>");
			submit.submit();
		}
		
		function fn_insertBoard(){
			var submit = new ComSubmit("frm");
			submit.setUrl("<c:url value='/qboard/insertBoard.do'/>");
			submit.submit();
		}
		
		function fn_addFile(){
			var str = "<p><input type='file' name='file_"+(gfv_count++)+"'><a href='#this' class='btn' name='delete'>삭제</a></p>";
			$("#fileDiv").append(str);
			$("a[name='delete']").on("click", function(e){ //삭제 버튼
				e.preventDefault();
				fn_deleteFile($(this));
			});
		}
		
		function fn_deleteFile(obj){
			obj.parent().remove();
		}
	</script>
</body>
</html>