package com.jw.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jw.board.model.service.BoardService;
import com.jw.board.model.vo.Attachment;
import com.jw.board.model.vo.Board;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		BoardService bService = new BoardService();
		
		int result = bService.increaseCount(boardNo);
		
		if(result>0) {
			
			// board조회
			Board b = bService.selectBoard(boardNo);
			
			// attachment조회
			Attachment at = bService.selectAttachment(boardNo);
			
			// 조회했던 b,at넘기기
			request.setAttribute("b", b);
			request.setAttribute("at", at);
			
			// 화면=> 포워딩
			request.getRequestDispatcher("views/board/boardDetailView.jsp").forward(request, response);
		}else {
			request.setAttribute("errorMsg", "조회불가능");
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
