package com.jw.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jw.board.model.service.BoardService;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int listCount; //일반게시판 게시글 총 갯수
		int currentPage; //현재 페이지
		int pageLimit =10; //페이지 하단에 보여질 페이징바의 최대 갯수 => 10개
		int boardLimit =10; //한 페이지에 보여질 게시글의 최대 갯수 => 10개
		
		int maxPage; //가장 마지막 페이지가 몇번 페이지인지
		int startPage; //페이지 하단에 보여질 페이징바의 시작 수
		int endPage; //페이지 하단에 보여질 페이징 바의 끝 수
		
		listCount = new BoardService().selectListCount();
		
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		//마지막 페이지 
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
