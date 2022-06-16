<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
	.outer{
		background-color : rgb(162, 193, 255);
		color: white;
		width: 1000px;
		margin: auto;
		margin-top: 30px;
	}

	#enroll-form table{margin : auto;}
	#enroll-form input{margin: 5px;}
</style>
</head>
<body>

	<%@include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>

		<form id="enroll-form" action="<%= contextPath%>/insert.me" method="post">
			
			<!-- 아이디, 비밀번호, 이름, 전화번호, 이메일주소, 주소, 취미-->
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" required maxlength="12" name="userId"></td>
					<td><button type="button">중복확인</button></td>
					<!--Ajax기술을 배울때 구현할 것-->
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="userPwd" maxlength="15" required></td>
					<td></td>
				</tr>
				<tr>
					<td>* 비밀번호 확인</td>
					<td><input type="password" maxlength="15" required></td>
					<td></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="userName" maxlength="6" required></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;전화번호</td>
					<td><input type="text" name="phone" placeholder="- 포합해서 입력"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이메일</td>
					<td><input type="email" name="email"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td><input type="text" name="address"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;관심분야</td>
					<td colspan="2">
						<input type="checkbox" name="interest" value="운동" id="sports"><label for="sports">운동</label>
						<input type="checkbox" name="interest" value="노래" id="song"><label for="song">노래</label>
						<input type="checkbox" name="interest" value="여행" id="travel"><label for="travel">여행</label>
						<br>
						<input type="checkbox" name="interest" value="게임" id="game"><label for="game">게임</label>
						<input type="checkbox" name="interest" value="영화" id="movie"><label for="movie">영화</label>
						<input type="checkbox" name="interest" value="악기" id="mi"><label for="mi">악기</label>
					</td>
				</tr>
			</table>

			<br><br>

			<div align="center">
				<button type="submit">회원가입</button>
				<button type="reset">초기화</button>
			</div>

		</form>
	
	</div>

</body>
</html>