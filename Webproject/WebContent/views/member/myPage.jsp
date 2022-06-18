<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 변경</title>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
    .outer{
        background-color: rgb(224, 234, 255);
        color:white;
        width: 1000px;
        margin: auto;
        margin-top: 30px;
    }
    
    #mypage-form table{margin:auto;}
    #mypage-form input{margin:5px;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<%
		String userId = loginUser.getUserId();
		String userName = loginUser.getUserName();
	
		String phone = (loginUser.getPhone() == null) ? "" : loginUser.getPhone();
		String email = (loginUser.getEmail() == null) ? "" : loginUser.getEmail();
		String address = (loginUser.getAddress() == null) ? "" : loginUser.getAddress();
		String interest = (loginUser.getInterest() == null) ? "" : loginUser.getInterest();
		
		String userPwd = loginUser.getUserPwd();
	
	%>

	<div class="outer">
		<br>
		<h2 align="center">마이페이지</h2>
		
		<form id="mypage-form" action="<%= contextPath%>/update.me" method="post">
			
			<!-- 아이디, 비밀번호, 이름, 전화번호, 이메일주소, 주소, 취미-->
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input readonly type="text" required maxlength="12" name="userId" value="<%= userId %>"></td>
					<td></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="userName" maxlength="6" required value="<%= userName %>"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;전화번호</td>
					<td><input type="text" name="phone" value="<%= phone %>"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이메일</td>
					<td><input type="email" name="email" value="<%= email %>"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td><input type="text" name="address" value="<%= address %>"></td>
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

			<script>
         
            var interest = "<%= interest %>";
            $('input[type=checkbox]').each(function(){
           
            	//indexOf(javascript) == search(jquery)
               if(interest.search($(this).val()) != -1){
                  $(this).attr("checked", true);
               }
            })
         </script>

            <br><br>

            <div align="center">
            	<button type="submit" class="btn btn-light">정보변경</button>
				<button type="button" class="btn btn-warning" style="color:white" data-toggle="modal" data-target="#updatePwdForm">비밀번호변경</button>
				<button type="button" class="btn btn-danger" style="color:white" data-toggle="modal" data-target="#deleteMember">회원탈퇴</button>
            </div>

			<br><br>
        </form>
	</div>

	<!-- 비밀번호변경 -->
	<!-- The Modal -->
	<div class="modal" id="updatePwdForm">
		<div class="modal-dialog">
		<div class="modal-content">
	
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">비밀번호 변경</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
	
			<!-- Modal body -->
			<div class="modal-body">
				
				<form action="<%= contextPath %>/updatePwd.me" method="post">

					
					<input type="hidden" name="userId" value ="<%= userId %>">

					<table>
						<tr>
							<td>현재 비밀번호</td>
							<td><input type="password" required name="userPwd"></td>
						</tr>
						<tr>
							<td>변경할 비밀번호</td>
							<td><input type="password" name="updatePwd" required></td>
						</tr>
						<tr>
							<td>변경할 비밀번호 재입력</td>
							<td><input type="password" name="checkPwd" required></td>
						</tr>
					</table>

					<br>

					<button type="submit" class="btn-btn-sm btn-secondary" onclick="return validatePwd();" align="center">비밀번호 변경</button>
				</form>

				<script>
					function validatePwd(){

						if($("input[name=updatePwd]").val() != $("input[name=checkPwd]").val()){
							alert("비밀번호가 일치하지 않습니다");
							return false;
						} 
						else {
							return true;
						}

					}

				</script>

			</div>
		</div>
		</div>	
	</div>
			
	<!-- 회원탈퇴 -->
	<!-- The Modal -->
	<div class="modal" id="deleteMember">
		<div class="modal-dialog">
		<div class="modal-content">
	
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">회원탈퇴</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
	
			<!-- Modal body -->
			<div class="modal-body">
				
				<form action="<%= contextPath %>/updateOut.me" method="post">

					<table>
						<tr>
							<td>비밀번호를 입력해주세요</td>
							<td><input type="password" required name="userPwd"></td>
						</tr>
					</table>

					<br>

					<button type="submit" class="btn-btn-sm btn-danger">회원탈퇴</button>
				</form>
	
			</div>
		</div>
		</div>	
	</div>

</body>
</html>