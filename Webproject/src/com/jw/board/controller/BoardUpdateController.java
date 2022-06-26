package com.jw.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.jw.board.model.service.BoardService;
import com.jw.board.model.vo.Attachment;
import com.jw.board.model.vo.Board;
import com.jw.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 1024* 1024*10;
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
			
			MultipartRequest multiRequest = new MultipartRequest(request,savePath,maxSize, "UTF-8", new MyFileRenamePolicy());
		
			int boardNo = Integer.parseInt(multiRequest.getParameter("bno"));
			String category = multiRequest.getParameter("category");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
		
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setCategory(category);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			
			Attachment at = null;
			
			if(multiRequest.getOriginalFileName("reUpfile") != null) {
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("reUpfile"));
				at.setChangeName(multiRequest.getFilesystemName("reUpfile"));
				at.setFilePath("resources/board_upfiles/");
				
				if(multiRequest.getParameter("originFileNo") != null) {
				
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
					
					new File(savePath+multiRequest.getParameter("originFileName")).delete();
				}else {
					
					at.setRefBno(boardNo);
				}
			}
			
			int result = new BoardService().updateBoard(b,at);
			
			if(result >0) {
				
				request.getSession().setAttribute("alertMsg", "게시글이 수정되었습니다");
				response.sendRedirect(request.getContextPath() + "/detail.bo?bno" + boardNo);
				
			}else {
				request.setAttribute("errorMsg", "게시글 수정 실패!");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				
			}
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
