package com.jw.board.model.service;

import static com.jw.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.jw.board.model.dao.BoardDao;
import com.jw.board.model.vo.Attachment;
import com.jw.board.model.vo.Board;
import com.jw.board.model.vo.Category;
import com.jw.common.model.vo.PageInfo;

public class BoardService {

	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectList(PageInfo pi){
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList(conn,pi);
		
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
		
		int result1 =new BoardDao().insertBoard(conn, b);
		
		int result2 = 1;
		if(at != null) {
			result2 = new BoardDao().insertAttachment(conn, at);
		}
		
		if((result1 * result2) >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return (result1 * result2);
	}
	
	public int increaseCount(int boardNo) {
		Connection conn = getConnection();
		int result = new BoardDao().increaseCount(conn, boardNo);
		if(result>0) commit(conn);
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
		Connection conn=getConnection();
		Attachment at = new BoardDao().selectAttachment(conn, boardNo);
		close(conn);
		return at;
	}

	public int updateBoard(Board b, Attachment at) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
