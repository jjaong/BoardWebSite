<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.jw.notice.model.vo.Notice" %>
<%
	Notice n = (Notice)request.getAttribute("n");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세보기</title>
<style>
	 .outer{
        background-color : rgb(126, 138, 192);
        color : white;
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
		<h2 align="center">공지사항 상세보기</h2>
		<br><br>

		<table id="detail-area" align="center" border="1">
			<tr>
				<th width="50">제목</th>
				<td width="380" colspan="3"><%= n.getNoticeTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%= n.getNoticeWriter() %></td>
				<th>작성일</th>
				<td><%= n.getCreateDate() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p style="height:150px;"><%= n.getNoticeContent() %></p>
				</td>
			</tr>
		</table>
		<br><br>

		<div align="center">
			<a href="<%= contextPath %>/list.no" class="btn btn-sm btn-secondary">목록가기</a>

			<% if(loginUser != null && loginUser.getUserId().equals(n.getNoticeWriter()) )  { %>
				<a href="<%= contextPath %>/updateForm.no?nno=<%= n.getNoticeNo() %>" class="btn btn-sm btn-warning">수정하기</a> <!--updateForm.no-->
				<a href="<%= contextPath %>/delete.no?nno=<%= n.getNoticeNo() %>" class="btn btn-sm btn-danger">삭제하기</a> <!--/delete.no-->
			<% } %>

		</div>

		<br><br><br>
	</div>

</body>
</html>