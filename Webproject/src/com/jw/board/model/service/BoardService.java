package com.jw.board.model.service;

import static com.jw.common.JDBCTemplate.*;

import java.sql.Connection;

import com.jw.board.model.dao.BoardDao;

public class BoardService {

	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
	}

}
