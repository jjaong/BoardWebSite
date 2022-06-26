package com.jw.board.model.dao;

import static com.jw.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.jw.board.model.vo.Attachment;
import com.jw.board.model.vo.Board;
import com.jw.board.model.vo.Category;
import com.jw.common.model.vo.PageInfo;

public class BoardDao {
	private Properties prop = new Properties();
	
	public BoardDao() {
		String fileName = BoardDao.class.getResource("/sql/board/board-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	}

	public int selectListCount(Connection conn) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
		ArrayList<Board> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectList");
		 
		try {
			pstmt= conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() +1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Board(rset.getInt("BOARD_NO"),
								   rset.getString("CATEGORY_NAME"),
								   rset.getString("BOARD_TITLE"),
								   rset.getString("USER_ID"),
								   rset.getInt("COUNT"),
								   rset.getDate("CREATE_DATE")));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	
		return list;
	}

	public ArrayList<Category> selectCategoryList(Connection conn) {
		ArrayList<Category> list= new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectCategoryList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset= pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Category(rset.getInt("CATEGORY_NO"),
									  rset.getString("CATEGORY_NAME")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int insertBoard(Connection conn, Board b) {

		int result = 0;
		PreparedStatement pstmt= null;
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4, Integer.parseInt(b.getBoardWriter()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}


	public int insertAttachment(Connection conn,Attachment at) {
		
		// insert문 => 처리된 행의갯수
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int increaseCount(Connection conn, int boardNo) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
public Board selectBoard(Connection conn, int boardNo) {
		
		Board b =null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBoard");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset=pstmt.executeQuery();
			if(rset.next()) {
				b=new Board(rset.getInt("BOARD_NO"),
							rset.getString("CATEGORY_NAME"),
							rset.getString("BOARD_TITLE"),
							rset.getString("BOARD_CONTENT"),
							rset.getString("USER_ID"),
							rset.getDate("CREATE_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return b;
	}
	
	public Attachment selectAttachment(Connection conn, int boardNo) {
		
		// SELECT => ResultSet => 첨부파일 한개(일반게시판) => Attachment
		Attachment at = null;
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				at=new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));	
				at.setChangeName( rset.getString("CHANGE_NAME"));			
				at.setFilePath(rset.getString("FILE_PATH"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return at;
	}
	
	public int updateBoard(Connection conn, Board b) {
		
		// UPDATE => 처리된 행의 갯수
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4, b.getBoardNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteBoard(Connection conn, int boardNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
public int updateAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
		
	public int insertAttachmentList(Connection conn, ArrayList<Attachment> list) {
		
		int result = 1;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachmentList");
		
		try {
			
			// 리스트의 요소만큼 Attachment테이블에 행을 추가
			for(Attachment at : list) {
				// 반복문이 돌 때마다 미완성된 SQL문을 담은 pstmt 객체 생성
				pstmt = conn.prepareStatement(sql);
				
				// 완성형태로 만들기 => at객체로부터 뽑아오기
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileLevel());
				
				// 실행
				result *= pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int insertNewAttachment(Connection conn, Attachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNewAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, at.getRefBno());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


}
