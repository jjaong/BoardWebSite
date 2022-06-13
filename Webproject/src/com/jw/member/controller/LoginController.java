package com.jw.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jw.member.model.service.MemberService;
import com.jw.member.model.vo.Member;


/**
 * Servlet implementation class loginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//인코딩 설정 (POST방식)
		request.setCharacterEncoding("UTF-8"); 
		
		//input태그의 name속성을 통해 전달값(키값)을 꺼내서 변수에 기록
		String userId= request.getParameter("userId");
		String userPwd= request.getParameter("userPwd");
		
		//요청 처리하는 서비스 클래스의 메소드 호출
		Member loginUser = new MemberService().loginMember(userId, userPwd);
	
		if(loginUser == null) { //로그인 실패
			request.setAttribute("errorMsg", "로그인에 실패했습니다.");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
		
			view.forward(request, response);
		}else {
			HttpSession session = request.getSession();
			
			session.setAttribute("loginUser", loginUser);
			
			session.setAttribute("alertMsg", "로그인성공 ~ ");
			
			response.sendRedirect(request.getContextPath());
		}
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
