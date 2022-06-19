<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.jw.notice.model.vo.Notice" %>
<% 
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
    .outer{
        background-color: rgb(126, 138, 192);
        color:white;
        width: 1000px;
        margin: auto;
        margin-top: 30px;
    }

    .list-area{
        border : 1px solid white;
        text-align: center;
    }
    .list-area>tbody>tr:hover{
        cursor : pointer;
        background-color: rgb(76, 89, 124);
    }
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	  <div class="outer">

        <br>
        <h2 align="center">공지사항</h2>
        <br>

		<!-- 관리자만 글작성 버튼이 보임 -->
        <% if(loginUser != null && loginUser.getUserId().equals("admin")) { %>
	        <div align="right" style="width:850px;">
	        
		        <a href="<%=contextPath %>/enrollForm.no" class="btn btn-info btn-sm">글작성</a>
	            <br><br>
	            
	        </div>
	        
        <% } %>

        <table class="list-area" align="center">
            <thead>
                <tr>
                    <th>글번호</th>
                    <th width="400">글제목</th>
                    <th width="100">작성자</th>
                    <th>조회수</th>
                    <th width="100">작성일</th>
                </tr>
            </thead>
            <tbody>
                <% if(list.isEmpty()) { %>
                    <!-- 공지사항이 존재하지 않을 경우 -->
                    <tr>
                        <td colspan="5">공지사항이 존재하지 않습니다</td>
                    </tr>
                <% } else { %>
                    <!-- 공지사항이 존재할 경우 -->
                    <% for(Notice n : list) { %>
                        <tr>
                            <td><%= n.getNoticeNo() %></td>
                            <td><%= n.getNoticeTitle() %></td>
                            <td><%= n.getNoticeWriter() %></td>
                            <td><%= n.getCount() %></td>
                            <td><%= n.getCreateDate() %></td>
                        </tr>
                    <% } %>
                <% } %>
            </tbody>
        </table>
 		<br><br><br>
    </div>
	
</body>
</html>