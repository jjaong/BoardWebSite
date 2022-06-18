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
    #login-form, #user-info{float:right;}

	#user-info a{
	text-decoration : none;
	color:black;
	font-size:12px;
	}
	
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

    #pageName{
        text-align: center;
       
    }
    
    #apageName{
        text-decoration: none;
        font-size: 50px;
        font-weight: bold;
        color: black;
    }
    

</style>
<!-- 부트스트랩 사용 -->
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
	<br><br>
	<div id="pageName">
	<a href="<%= contextPath%>" id="apageName">JAWON'S PAGE</a>
	</div>

    <div class="login-area">
    	
    <%if(loginUser ==null) {%>
    	<!-- 로그인 전에 보여지는 로그인 form -->
        <form id="login-form" method="post" action="<%=contextPath%>/login.me">
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
                    <th colspan="2" align="right">
                        <button type="submit" class="btn btn-light">로그인</button>
                        <button type="button" onclick="enrollPage();" class="btn btn-light">회원가입</button>
                    </th>
                </tr>
            </table>
        </form>

        <script>
            function enrollPage(){
            	
        
                location.href= "<%= contextPath %>/enrollForm.me";
            }

        </script>
        <% } else { %>
			<!-- 로그인 성공 후 화면 -->
			<div id="user-info">
				<b><%= loginUser.getUserName() %></b>님 환영합니다.<br><br>
				<div align="right">
					<a href="<%= contextPath %>/myPage.me">마이페이지</a>
					<a href="<%= contextPath %>/logout.me">로그아웃</a>
				</div>
			</div>
		<% } %>
    </div>


    <br clear="both"><br><br>

    <div class="nav-area" align="center">
        <div class="menu"><a href="<%= contextPath%>">HOME</a></div>
        <div class="menu"><a href="#">공지사항</a></div>
        <div class="menu"><a href="#">일반게시판</a></div>
        <div class="menu"><a href="#">사진게시판</a></div>
    </div>
</body>
</html>