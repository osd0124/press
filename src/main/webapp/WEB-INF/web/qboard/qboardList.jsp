<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>
<body>
	<h2>Q&A 게시판</h2>
	<table class="board_list">
		<colgroup>
			<col width="10%" />
			<col width="20%" />	
			<col width="15%" />
			<col width="10%" />
			<col width="20%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">조회수</th>
				<th scope="col">작성일</th>
			</tr>
		</thead>
		<tbody>
		
		</tbody>			
	</table>
	<select id="KEYTAG">
		<option value="" selected>선택</option>
		<option value="TITLE" selected>제목</option>
		<option value="CREA_ID" selected>작성자</option>
		<option value="CONTENTS" selected>내용</option>
		<option value="ALL" selected>전체</option>	
	</select>
	<input type="text" id="KEYWORD" name="KEYWORD" />
	<a href="#this" class="btn" id="search">검색</a>
	
	<div id="PAGE_NAVI"></div>
	<input type="hidden" id="PAGE_INDEX" name="PAGE_INDEX" />
	<br/>
	<a href="#this" class="btn" id="write">글쓰기</a>
	
	<form id="commonForm" name="commonForm"></form>
	<script src="<c:url value='/resources/js/page.js'/>"></script>
	<script src="<c:url value='/resources/js/common.js'/>"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			fn_selectBoardList(1);
			
			$('#search').on("click",function(e){
				e.preventDefault();
				fn_selectBoardList(1);
			});
			
			$('#write').on("click",function(e){
				e.preventDefault();
				fn_openBoardWrite();
			});
		});
	
		function fn_openBoardWrite(){
			var submit = new ComSubmit();
			submit.setUrl("<c:url value='/qboard/openBoardWrite.do' />");
			submit.submit();
		}
		
		function fn_selectBoardList(pageNo){
			var comAjax = new ComAjax();
			comAjax.setUrl("<c:url value='/qboard/selectBoardList.do' />");
			comAjax.setCallback("fn_selectBoardListCallback");
			comAjax.addParam("KEYTAG",$("#KEYTAG").val());
			comAjax.addParam("KEYWORD",$("#KEYWORD").val());
			comAjax.addParam("PAGE_INDEX",pageNo);
			comAjax.addParam("PAGE_ROW",10);
			comAjax.ajax();
		}
		
		function fn_selectBoardListCallback(data){
			var total= data.TOTAL;
			var body = $("table>tbody");
			body.empty();
			if(total == 0){
				var str ="<tr>" +
							"<td colspan='5'>조회된 게시물이 없습니다.</td>" +
						 "</tr>";
				body.append(str);
			}
			else{
				var params = {
						divId : "PAGE_NAVI",
						pageIndex : "PAGE_INDEX",
						totalCount : total,
						eventName : "fn_selectBoardList"
				};
				gfn_renderPaging(params);
				
				var str="";
				$.each(data.list,function(key,value){
					str += "<tr>" +
								"<td>" +value.RNUM + "</td>" +
								"<td class='title'>" +
									"<a href='<c:url value='/qboard/openBoardDetail.do?IDX=" +value.IDX + "' />' name='title'>" +value.TITLE+ "</a>" +
								"</td>" +
								"<td>" + value.CREA_ID+ "</td>" +
								"<td>" + value.HIT_CNT+ "</td>" +
								"<td>" + value.CREA_DTM+ "</td>" +
							"</tr>";	
				});
				body.append(str);
			}
		} 
	</script>
</body>
</html>