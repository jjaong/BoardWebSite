<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판</title>
<style>
    .outer{
        background-color: rgb(126, 138, 192);
        color:white;
        width: 1000px;
        height: 600px;
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
        <h2 align="center">일반게시판</h2>
        <br>
        <table class="list-area" align="center">
			<thead>
				<tr>
					<th width="70">글번호</th>
					<th width="70">카테고리</th>
					<th width="300">제목</th>
					<th width="100">작성자</th>
					<th width="50">조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				
				<!-- 게시글 출력 : 게시글이 있는지 없는지 => isEmpty() 이용해서 없는 경우 먼저 조건 부여 -->
				
				<% if(list.isEmpty()) { %> <!-- 조회글 없음 -->
					<tr>
						<td colspan="6">조회된 게시글이 없습니다.</td>
					</tr>
				<% } else { %>
					<!-- 반복 : list에 있는 값을 순차적으로 접근해서 뽑아오기 -->
					<% for(Board b : list) { %>
						<tr>
							<td><%= b.getBoardNo() %></td>
							<td><%= b.getCategory() %></td>
							<td><%= b.getBoardTitle() %></td>
							<td><%= b.getBoardWriter() %></td>
							<td><%= b.getCount() %></td>
							<td><%= b.getCreateDate() %></td>
						</tr>
					<% } %>
				<% } %>
			</tbody>
		</table>

        <br><br>

        <div align="center">
            <button> &lt;</button>

            <button> &gt;</button>
        </div>


	</div>

    
</body>
</html>