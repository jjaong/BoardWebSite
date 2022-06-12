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
	
	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn, m);
		
		if(result>0) { //성공
			JDBCTemplate.commit(conn);
		} else { //실패
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public Member updateMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMem = null;
		
		if(result>0) { // 성공
			JDBCTemplate.commit(conn);
			// 갱신된 회원 객체를 다시 조회해오기
			updateMem= new MemberDao().selectMember(conn,m.getUserId());
		} else { // 실패
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return updateMem;
	}
	
public Member updatePwdMember(String userId, String userPwd, String updatePwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updatePwdMember(conn, userId, userPwd, updatePwd);
		
		Member updateMem = null;
		
		if(result>0){//성공
			JDBCTemplate.commit(conn);
			updateMem = new MemberDao().selectMember(conn, userId);
		
		}else {//실패
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return updateMem;
	}
}
