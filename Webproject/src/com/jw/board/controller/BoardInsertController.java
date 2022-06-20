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
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 10 * 1024 * 1024;
			
			//HttpSession session = request.getSession();
			//ServletContext application = session.getServletContext();
			//String savePath = application.getRealPath("/resources/board_upfiles/");
		
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
		
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());

			String category = multiRequest.getParameter("category");
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String userNo = multiRequest.getParameter("userNo");
			
			Board b = new Board();
			b.setCategory(category);
			b.setBoardTitle(title);
			b.setBoardContent(content);
			b.setBoardWriter(userNo);
			
			Attachment at = null;
			
			//첨부파일 유무
			if(multiRequest.getOriginalFileName("upfile")!=null) {
				at = new Attachment();
				
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));//원본명
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
			}
			int result = new BoardService().insertBoard(b,at);
			
			if(result > 0) {
				request.getSession().setAttribute("alertMsg","게시글 작성 성공");
				response.sendRedirect(request.getContextPath() + "/list.bo?cpage=1");
			}else {
				if(at != null) { //첨부파일 등록 실패 => 서버에 보관x
					new File(savePath + at.getChangeName()).delete();
				}
				
				request.setAttribute("errorMsg", "게시글 작성 실패");
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
