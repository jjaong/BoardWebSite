package com.jw.notice.model.service;

import static com.jw.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.jw.notice.model.dao.NoticeDao;
import com.jw.notice.model.vo.Notice;

import oracle.net.aso.l;

public class NoticeService {
	public ArrayList<Notice> selectNoticeList(){
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn);
		
		close(conn);
		
		return list;
	}

	public int insertNotice(Notice n) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().insertNotice(conn, n);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
}
