package board.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.model.vo.Board;
import board.model.vo.PageInfo;

public class FreeDAO {

	// 공지사항 검색 게시글 갯수
	public int getSearchListCount(Connection conn, String opt, String word) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;
		int result = 0;
		
		try {
			
			if(opt.equals("all")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM FREELIST WHERE B_TITLE LIKE ? OR B_CONTENT LIKE ? OR MEMBER_NICKNAME LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				pstmt.setString(3, "%" + word + "%");
				
			} else if (opt.equals("writer")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM FREELIST WHERE MEMBER_NICKNAME LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				
			} else if (opt.equals("title")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM FREELIST WHERE B_TITLE LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
			
			} else if (opt.equals("content")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM FREELIST WHERE B_CONTENT LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				
			} else if (opt.equals("title_content")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM FREELIST WHERE B_TITLE LIKE ? OR B_CONTENT LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				
			} else {
				query = "SELECT COUNT(*) FROM FREELIST";
				pstmt = conn.prepareStatement(query);
			}
			
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	// 자유게시판 게시글 목록
	public ArrayList<Board> selectList(Connection conn, PageInfo pi){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1; 
		
		String query = "SELECT * FROM FREELIST WHERE RNUM BETWEEN ? AND ? ORDER BY B_NO DESC"; 
									
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery(); 
			list = new ArrayList<Board>();
			
			while(rset.next()){
				Board no = new Board(rset.getInt("B_NO"),				// 게시판 번호	
									 rset.getString("B_TITLE"),  		// 게시판 제목	
									 rset.getString("B_CONTENT"),		// 게시판 내용	
									 rset.getDate("B_DATE"),			// 게시판 생성 날짜
									 rset.getDate("B_RDATE"),			// 게시판 수정 날짜	
									 rset.getInt("B_VIEW_COUNT"),		// 게시판 조회수
									 rset.getInt("B_RECOMMEND"),		// 게시판 추천수
									 rset.getInt("B_WRITER"),			// 게시판 글쓴이 회원 번호
									 rset.getString("MEMBER_NICKNAME"),	// 게시판 글쓴이 회원	
									 rset.getInt("B_REPLY_COUNT"));		// 게시판 댓글
				list.add(no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
			
	}
	
	
	// 자유게시판 게시글 보기
	public Board selectBoard(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		
		String query = "SELECT * FROM FREEDETAIL WHERE B_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				board = new Board(rset.getInt("B_NO"),
						 rset.getString("B_TITLE"),
						 rset.getString("B_CONTENT"),
						 rset.getDate("B_DATE"),
						 rset.getDate("B_RDATE"),
						 rset.getInt("B_VIEW_COUNT"),
						 rset.getInt("B_RECOMMEND"),
						 rset.getInt("B_WRITER"),
						 rset.getString("MEMBER_NICKNAME"),
						 rset.getInt("B_REPLY_COUNT"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return board;
	}
	
	
	// 자유게시판 게시글 등록
	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
//		String query = "INSERT INTO BOARD VALUES(SEQ_NNO.NEXTVAL, 게시판이름, 제목, 내용, 생성날짜, 수정날짜, 조회수, 추천수, 삭제여부, 글쓴이번호, 댓글수, AC_SATA, LC_NAME, ENROLL_STATE, EM_STATE, TC_NAME, CG_NAME, )";
		String query = "INSERT INTO BOARD VALUES(SEQ_BNO.NEXTVAL, '자유', ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, ?, DEFAULT, NULL, NULL, DEFAULT, NULL, NULL, ?, NULL, NULL, NULL, NULL)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getBoardWriterNo());
			pstmt.setString(4, b.getCgName());
			
			result =  pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}

	
	// 자유게시판 게시긆 수정
	public int modifyBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE BOARD SET B_TITLE = ?, B_CONTENT = ?, B_RDATE = SYSDATE WHERE B_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getBoardNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	// 검색된 리스트 반환
	public ArrayList<Board> searchList(Connection conn, String opt, String word, PageInfo pi){
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;		
		String query = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			
			if(opt.equals("all")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, F.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_RECOMMEND,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT "
						+ 	"FROM BOARD "
						+ 		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+ 	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='자유' AND (B_TITLE LIKE ?  OR B_CONTENT LIKE ? OR MEMBER_NICKNAME LIKE ?) "
						+ 	"ORDER BY B_NO DESC) F) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				pstmt.setString(3, "%" + word + "%");
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
				
			} else if (opt.equals("writer")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, F.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_RECOMMEND,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT "
						+	"FROM BOARD "
						+		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='자유' AND MEMBER_NICKNAME LIKE ? " 
						+	"ORDER BY B_NO DESC) F) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
			} else if (opt.equals("title")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, F.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_RECOMMEND,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT "
						+ 	"FROM BOARD "
						+		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='자유' AND B_TITLE LIKE ? "
						+	"ORDER BY B_NO DESC) F) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			
			} else if (opt.equals("content")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, F.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_RECOMMEND,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT "
						+	"FROM BOARD "
						+		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='자유' AND B_CONTENT LIKE ? "
						+	"ORDER BY B_NO DESC) F) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
			} else if (opt.equals("title_content")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, F.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_RECOMMEND,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT "
						+	"FROM BOARD "
						+		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='자유' AND (B_TITLE LIKE ? OR B_CONTENT LIKE ?) "
						+	"ORDER BY B_NO DESC) F) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
				
			} else {
				query = "SELECT * FROM FREELIST WHERE RNUM BETWEEN ? AND ? ORDER BY B_NO DESC";
				pstmt = conn.prepareStatement(query);
			}
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rset.next()){
				Board no = new Board(rset.getInt("B_NO"),				// 게시판 번호	
									 rset.getString("B_TITLE"),  		// 게시판 제목	
									 rset.getString("B_CONTENT"),		// 게시판 내용	
									 rset.getDate("B_DATE"),			// 게시판 생성 날짜
									 rset.getDate("B_RDATE"),			// 게시판 수정 날짜	
									 rset.getInt("B_VIEW_COUNT"),		// 게시판 조회수
									 rset.getInt("B_RECOMMEND"),		// 게시판 추천수
									 rset.getInt("B_WRITER"),			// 게시판 글쓴이 회원 번호
									 rset.getString("MEMBER_NICKNAME"),	// 게시판 글쓴이 회원	
									 rset.getInt("B_REPLY_COUNT"));		// 게시판 댓글
				list.add(no);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
}
