package com.jw.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.jw.common.JDBCTemplate;
import com.jw.member.model.vo.Member;

public class MemberDao{
	private Properties prop = new Properties(); 
	
	public MemberDao() {
		
		String file = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 로그인
	 */
	public Member loginMember(Connection conn, String userId, String userPwd) {
	
		Member m =null;
		PreparedStatement pstmt=null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				
				m = new Member(rset.getInt("USER_NO"),
							   rset.getString("USER_ID"),
							   rset.getString("USER_PWD"),
							   rset.getString("USER_NAME"),
							   rset.getString("PHONE"),
							   rset.getString("EMAIL"),
							   rset.getString("ADDRESS"),
							   rset.getString("INTEREST"),
							   rset.getDate("ENROLL_DATE"),
							   rset.getDate("MODIFY_DATE"),
							   rset.getString("STATUS")); 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

	/**
	 * 
	 * 회원가입
	 */
	public int insertMember(Connection conn, Member m) {
	
		int result =0;
		PreparedStatement pstmt= null;
		String sql = prop.getProperty("insertMember");
		
		
		try{
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest());
			
			result = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	/**
	 * 회원 정보 수정
	 */
	public int updateMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 회원 선택
	 */
	public Member selectMember(Connection conn, String userId) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset= pstmt.executeQuery();
			
			if(rset.next()) {
				
				m = new Member(rset.getInt("USER_NO"),
								rset.getString("USER_ID"),
								rset.getString("USER_PWD"),
								rset.getString("USER_NAME"),
								rset.getString("PHONE"),
								rset.getString("EMAIL"),
								rset.getString("ADDRESS"),
								rset.getString("INTEREST"),
								rset.getDate("ENROLL_DATE"),
								rset.getDate("MODIFY_DATE"),
								rset.getString("STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}
	
	/**
	 * 비밀번호 수정
	 */
	public int updatePwdMember(Connection conn, String userId, String userPwd, String updatePwd) {
	
		int result =0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwdMember");
		
			
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,updatePwd);
			pstmt.setString(2,userPwd);
			pstmt.setString(3,userId);
			
			result = pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}
	
	/**
	 * 회원 삭제
	 */
	public int deleteMember(Connection conn, String userId, String userPwd) {
		int result =0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
			
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,userId);
			pstmt.setString(2,userPwd);
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;

	}
	
	
}
