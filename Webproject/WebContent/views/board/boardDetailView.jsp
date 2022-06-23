<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.jw.board.model.vo.*" %>
<%
	Board b = (Board)request.getAttribute("b");
	//게시글번호, 카테고리명, 제목, 내용, 작성자 아이디, 작성일
	
	Attachment at = (Attachment)request.getAttribute("at");
	//파일번호, 원본명, 수정명, 저장경로
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<style>
	.outer{
	   background-color: rgb(126, 138, 192);
	   color:white;
	   width: 1000px;
	   height: 800px;
	   margin: auto;
	   margin-top: 50px;
   }
   #detail-area{
	   border:1px solid white;
   }
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
	
	<br>
	<h2 align="center">일반게시판 상세보기</h2>
	<br>
	
	<table id="detail-area" align="center" border="1">
	
		<tr>
			<th width="70">카테고리</th>
			<td width="70"><%=b.getCategory() %></td>
			<th width="70">제목</th>
			<td width="370"><%=b.getBoardTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=b.getBoardWriter() %></td>
			<th>작성일</th>
			<td><%=b.getCreateDate() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3">
				<p style="height:200px"><%=b.getBoardContent() %></p>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="3">
				<!-- 첨부파일이 없을수도 있다 -->
				<% if(at ==null) {%>
				첨부파일이 없습니다.
				<%} else { %>
					<!-- 첨부파일이 있을수도 있다 -->
					<a download="<%= at.getOriginName()%>" href="<%= contextPath%>/<%=at.getFilePath()%>/<%=at.getChangeName()%>"><%=at.getOriginName() %></a>
				<%} %>
			</td>
			<th></th>
			<td></td>
		</tr>
	
	</table>
	
	<br>
	
	<div align="center">
		<a href ="<%= contextPath %>/list.bo?cpage=1" class = "btn btn-sm btn-secondary">목록가기</a>
		<%if(loginUser != null && b.getBoardWriter().equals(loginUser.getUserId())) {%>
			<a href="<%= contextPath %>/updateForm.bo?bno=<%=b.getBoardNo() %>" class = "btn btn-sm btn-warning">수정하기</a>
			<a href="<%= contextPath %>/delete.bo" class="btn btn-sm btn-danger">삭제하기</a>
		<%} %>
	</div>
	
	</div>

</body>
</html>