package com.jw.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jw.member.model.service.MemberService;
import com.jw.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userPwd = request.getParameter("userPwd");
		
		HttpSession session = request.getSession();
		String userId = ((Member)session.getAttribute("loginUser")).getUserId();
	
		int result = new MemberService().deleteMember(userId, userPwd);
		
		if(result>0) {//성공 =>  탈퇴가 된 것 => 로그인이 유지 되어야하지 않음 
			
			session.removeAttribute("loginUser");
			
			response.sendRedirect(request.getContextPath());
			
		}else { //실패=> 탈퇴가 아직 안된것 => 오류페이지
			request.setAttribute("errorMsg", "회원 탈퇴에 실패했습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
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
