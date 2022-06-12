<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.jw.member.model.vo.Member" %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");

	String alertMsg = (String)session.getAttribute("alertMsg");
	
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴바</title>
<style>
    #login-form{float:right;}

    .nav-area{
        background-color: rgb(22, 70, 111);
    }

    .menu{
        display: table-cell;
        height: 50px;
        width: 150px;
    }

    .menu a{
        text-decoration: none;
        color: white;
        font-size: 20px;
        font-weight: bold;
        display: block;
        width: 100%;
        height: 100%;
        line-height: 50px;
    }
    .menu a:hover{
        background-color: rgb(207, 185, 228);
    }

</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<script>
	
	var msg = "<%= alertMsg %>";
	
	if(msg != "null"){
		alert(msg);
		
		<% session.removeAttribute("alertMsg"); %>
	}
	
	</script>

	<h1 style=" text-align: center;"> jawon's Page </h1>

    <div class="login-area">
        <form action="login-form" method="post" action="<%=contextPath%>/login.me">
            <table>
                <tr>
                    <th>아이디</th>
                    <td><input type="text" name="userId" required></td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td><input type="password" name="userPwd" required></td>
                </tr>
                <tr>
                    <th colspan="2">
                        <button type="submit">로그인</button>
                        <button type="button" onclick="enrollPage();">회원가입</button>
                    </th>
                </tr>
            </table>
        </form>

        <script>
            function enrollPage(){
                location.href= "<%= contextPath %>/enrollForm.me";
            }

        </script>
    </div>


    <br clear="both"><br>

    <div class="nav-area" align="center">
        <div class="menu"><a href="<%= contextPath%>">HOME</a></div>
        <div class="menu"><a href="#">공지사항</a></div>
        <div class="menu"><a href="#">일반게시판</a></div>
        <div class="menu"><a href="#">사진게시판</a></div>
    </div>
</body>
</html>