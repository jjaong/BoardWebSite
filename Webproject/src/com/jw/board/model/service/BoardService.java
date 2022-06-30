package com.jw.board.model.service;

import static com.jw.common.JDBCTemplate.close;
import static com.jw.common.JDBCTemplate.commit;
import static com.jw.common.JDBCTemplate.getConnection;
import static com.jw.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.jw.board.model.dao.BoardDao;
import com.jw.board.model.vo.Attachment;
import com.jw.board.model.vo.Board;
import com.jw.board.model.vo.Category;
import com.jw.board.model.vo.Reply;
import com.jw.common.model.vo.PageInfo;

public class BoardService {
	
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCount(conn);
		// SELECT문의 결과는 ResultSet
		// 상식적으로 생각해보면 게시글의 총 갯수는 정수형
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Category> selectCategoryList() {
		
		Connection conn = getConnection();
		
		ArrayList<Category> list = new BoardDao().selectCategoryList(conn);
		
		close(conn);
		
		return list;
	}
	
	public int insertBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
	
		int result1 = new BoardDao().insertBoard(conn, b);
		
		int result2 = 1; 
		if(at != null) {
			result2 = new BoardDao().insertAttachment(conn, at);
		}
		
		if((result1 * result2) > 0) {
			commit(conn);
		} else{
			rollback(conn);
		}
		
		close(conn);
		
		return (result1 * result2);
	}
	
	public int increaseCount(int boardNo) {
		
		Connection conn = getConnection();
		
		int result = new BoardDao().increaseCount(conn, boardNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	public Board selectBoard(int boardNo) {
		
		Connection conn = getConnection();
		
		Board b = new BoardDao().selectBoard(conn, boardNo);
		
		close(conn);
		
		return b;
	}
	public Attachment selectAttachment(int boardNo) {
		
		Connection conn = getConnection();
		
		Attachment at = new BoardDao().selectAttachment(conn, boardNo);
		
		close(conn);
		
		return at;
	}
	
	public int updateBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new BoardDao().updateBoard(conn, b);
		
		// ATTACHMENT테이블과 관련된 결과물
		int result2 = 1;
		
	
		if(at != null) {
			
			// 기존에 첨부파일이 있었을 경우
			if(at.getFileNo() != 0) {
				result2 = new BoardDao().updateAttachment(conn, at);
			} else {
				// 없었을 경우
				result2 = new BoardDao().insertNewAttachment(conn, at);
			}
			
		}
		
		if(result1 * result2 > 0) { 
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return (result1 * result2);
	}
	
	public int deleteBoard(int boardNo) {
		
		Connection conn = getConnection();
		
		int result = new BoardDao().deleteBoard(conn, boardNo);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	public int insertThumbnailBoard(Board b, ArrayList<Attachment> list) {
		
		Connection conn = getConnection();
		
	
		int result1 = new BoardDao().insertThumbnailBoard(conn, b);
		
		int result2 = new BoardDao().insertAttachmentList(conn, list);
		
		if(result1 * result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return (result1 * result2);
	}
	
	public ArrayList<Board> selectThumbnailList(){
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectThumbnailList(conn);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Attachment> selectAttachmentList(int boardNo){
		
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new BoardDao().selectAttachmentList(conn, boardNo);
		
		close(conn);
		
		return list;
		
	}
	
	public ArrayList<Reply> selectReplyList(int boardNo){
		
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new BoardDao().selectReplyList(conn, boardNo);
		
		close(conn);
		
		return list;
	}
	
	public int insertReply(Reply r) {
		
		Connection conn = getConnection();
		
		int result = new BoardDao().insertReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
