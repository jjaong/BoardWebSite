package com.jw.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jw.member.model.service.MemberService;
import com.jw.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//POST
		//인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId"); 
		String userPwd = request.getParameter("userPwd"); 
		String userName = request.getParameter("userName"); 
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interestArr = request.getParameterValues("interest"); 
		String interest = "";
				
		if(interestArr != null) {		
			interest = String.join(", ", interestArr);
		}
		
		Member m = new Member(userId, userPwd, userName, phone, email, address, interest);
	
		//요청처리
		int result = new MemberService().insertMember(m);
		
		//응답화면
		if(result>0) { //성공
			request.getSession().setAttribute("alertMsg", "회원가입에 성공했습니다"); // 회원가입 성공시 alertMsg에 추가
			response.sendRedirect(request.getContextPath());
		} else { // 실패
			request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
