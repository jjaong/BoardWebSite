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
 * Servlet implementation class MemberUpdatePwdContent
 */
@WebServlet("/updatePwd.me")
public class MemberUpdatePwdContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePwdContent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userId=request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		Member updateMem = new MemberService().updatePwdMember(userId, userPwd, updatePwd);
	
		HttpSession session = request.getSession();
		if(updateMem ==null) { //실패
			session.setAttribute("alertMsg","비밀번호 변경 실패!");
		}else {//성공
			session.setAttribute("alertMsg","비밀번호 변경 성공!");
			session.setAttribute("loginUser", updateMem);
		}

		response.sendRedirect(request.getContextPath()+ "/myPage.me");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
