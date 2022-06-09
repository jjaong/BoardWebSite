package com.jw.member.model.service;

import java.sql.Connection;

import com.jw.member.model.dao.MemberDao;
import com.jw.member.model.vo.Member;
import com.jw.common.JDBCTemplate;

public class MemberService {
	
	public Member loginMember(String userId, String userPwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		
		JDBCTemplate.close(conn);
		
		return m;
	}
		
}
