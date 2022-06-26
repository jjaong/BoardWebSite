package com.jw.board.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class ThumbnailInsertController
 */
@WebServlet("/insert.th")
public class ThumbnailInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			int maxSize = 1024 * 1024 * 10;
			
			String savePath = request.getServletContext().getRealPath("/resources/thumbnail_upfiles/");
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			String userNo = multiRequest.getParameter("userNo");
			
			Board b = new Board();
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			b.setBoardWriter(userNo);
			
			ArrayList<Attachment> list = new ArrayList();
			
			for(int i = 1; i <= 4; i++) {
				
				String key = "file" + i;
				
				if(multiRequest.getOriginalFileName(key) != null) { // 원본 파일이 존재한다
					
					Attachment at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(key)); // 원본명
					at.setChangeName(multiRequest.getFilesystemName(key)); // 수정명
					at.setFilePath("resources/thumbnail_upfiles/"); // 경로명
					
					if(i == 1) {
						// 대표이미지
						at.setFileLevel(1);
					} else {
						// 상세이미지
						at.setFileLevel(2);
					}
					list.add(at);
					
				}
			}
			
			
			// 4) Service단으로 토스
			int result = new BoardService().insertThumbnailBoard(b, list);
			
			// 5) 결과에 따른 응답뷰 지정
			if(result > 0) { //성공 => list.th로 요청(sendRedirect)
				
				request.getSession().setAttribute("alertMsg", "게시글작성성공 ~");
				response.sendRedirect(request.getContextPath() + "/list.th");
				
			} else { // 실패 => 에러페이지
				
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
