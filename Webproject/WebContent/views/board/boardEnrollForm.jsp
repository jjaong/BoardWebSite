<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.jw.board.model.vo.Category" %>
<%
ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");



%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시글 작성하기</title>
<style>
	.outer{
		background-color: rgb(126, 138, 192);
		color:white;
		width:1000px;
		height:800px;
		margin:auto;
		margin-top:50px;
	}
	#enroll-form>table{border:1px solid white}
	#enroll-form input, #enroll-form textarea{
		width:100%;
		box-sizing: border-box;
	}
</style>

</head>
<body>

    <%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
	
	<br>
	<h2 align="center">일반게시글 작성하기</h2>
	<br>
	<!-- 파일 첨부하는 요청을 할 때는 반드시 enctype="multipart/form-data"를 추가해야한다. -->
		<form id="enroll-form" enctype="multipart/form-data" action="<%= contextPath %>/insert.bo" method="post">

			<!-- 제목, 내용, 카테고리, 첨부파일 입력받기 + userNO-->
			<!-- 작성자의 회원 번호를 hidden으로 같이 넘겨야 board테이블에 INSERT가능 -->
			<input type="hidden" name="userNo" value="<%= loginUser.getUserNo() %>">			
			
			<table align="center">
				<tr>
					<th width="150px">카테고리</th>
					<td width="600px">
						<select name="category">
							<% for(Category c : list) { %>
							<option value="<%= c.getCategoryNo() %>"><%= c.getCategoryName() %></option>
							<% } %>
						</select>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" required></td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea name="content" rows="10" required style="resize: none;"></textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="upfile"></td>
				</tr>
			</table>
			<br>
				<div align="center">
					<button type="submit">작성하기</button>
					<button type="reset">취소하기</button>
				</div>

		</form>
	</div>
</body>
</html>