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

public class NoticeDAO {
	
	// 공지사항 검색 게시글 갯수
	public int getSearchListCount(Connection conn, String opt, String word) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;
		int result = 0;
		
		try {
			
			if(opt.equals("all")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM NOTICELIST WHERE B_TITLE LIKE ? OR B_CONTENT LIKE ? OR MEMBER_NICKNAME LIKE ? OR CG_NAME LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				pstmt.setString(3, "%" + word + "%");
				pstmt.setString(4, "%" + word + "%");
				
			} else if (opt.equals("writer")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM NOTICELIST WHERE MEMBER_NICKNAME LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				
			} else if (opt.equals("title")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM NOTICELIST WHERE B_TITLE LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
			
			} else if (opt.equals("content")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM NOTICELIST WHERE B_CONTENT LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				
			} else if (opt.equals("title_content")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM NOTICELIST WHERE B_TITLE LIKE ? OR B_CONTENT LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				
			} else if (opt.equals("category")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM NOTICELIST WHERE CG_NAME LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				
			} else {
				query = "SELECT COUNT(*) FROM NOTICELIST";
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
	
	// 공지사항 게시글 목록
	public ArrayList<Board> selectList(Connection conn, PageInfo pi){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1; 
		
		String query = "SELECT * FROM NOTICELIST WHERE RNUM BETWEEN ? AND ? ORDER BY B_NO DESC"; 
									//QALIST - > NOTICE , BNUM
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
									 rset.getInt("B_WRITER"),			// 게시판 글쓴이 회원 번호
									 rset.getString("MEMBER_NICKNAME"),	// 게시판 글쓴이 회원	
									 rset.getInt("B_REPLY_COUNT"),		// 게시판 댓글
									 rset.getString("CG_NAME"));		// 카테고리 이름
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
	
	
	// 공지사항 게시글 확인
	public Board selectBoard(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		
		String query = "SELECT * FROM NOTICEDETAIL WHERE B_NO = ?";
		
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
						 rset.getInt("B_WRITER"),
						 rset.getString("MEMBER_NICKNAME"),
						 rset.getInt("B_REPLY_COUNT"),
						 rset.getString("CG_NAME"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return board;
	}
	
	
	// 공지사항 게시글 등록
	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
//		String query = "INSERT INTO BOARD VALUES(SEQ_NNO.NEXTVAL, 게시판이름, 제목, 내용, 생성날짜, 수정날짜, 조회수, 추천수, 삭제여부, 글쓴이번호, 댓글수, AC_SATA, LC_NAME, ENROLL_STATE, EM_STATE, TC_NAME, CG_NAME)";
		String query = "INSERT INTO BOARD VALUES(SEQ_BNO.NEXTVAL, '공지사항', ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, DEFAULT, ?, DEFAULT, NULL, NULL, DEFAULT, NULL, NULL, ?, NULL, NULL, NULL, NULL)";
		
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
	
	// 공지사항 게시글 수정
	public int modifyBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE BOARD SET B_TITLE = ?, B_CONTENT = ?, CG_NAME = ?, B_RDATE = SYSDATE WHERE B_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getCgName());
			pstmt.setInt(4, b.getBoardNo());
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
						+ "(SELECT ROWNUM RNUM, N.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT,CG_NAME "
						+ 	"FROM BOARD "
						+ 		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+ 	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='공지사항' AND (B_TITLE LIKE ?  OR B_CONTENT LIKE ? OR MEMBER_NICKNAME LIKE ? OR CG_NAME LIKE ?) "
						+ 	"ORDER BY B_NO DESC) N) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				pstmt.setString(3, "%" + word + "%");
				pstmt.setString(4, "%" + word + "%");
				pstmt.setInt(5, startRow);
				pstmt.setInt(6, endRow);
				
			} else if (opt.equals("writer")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, N.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT,CG_NAME "
						+	"FROM BOARD "
						+		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='공지사항' AND MEMBER_NICKNAME LIKE ? " 
						+	"ORDER BY B_NO DESC) N) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
			} else if (opt.equals("title")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, N.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT,CG_NAME "
						+ 	"FROM BOARD "
						+		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='공지사항' AND B_TITLE LIKE ? "
						+	"ORDER BY B_NO DESC) N) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			
			} else if (opt.equals("content")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, N.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT,CG_NAME "
						+	"FROM BOARD "
						+		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='공지사항' AND B_CONTENT LIKE ? "
						+	"ORDER BY B_NO DESC) N) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
			} else if (opt.equals("title_content")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, N.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT,CG_NAME "
						+	"FROM BOARD "
						+		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='공지사항' AND (B_TITLE LIKE ? OR B_CONTENT LIKE ?) "
						+	"ORDER BY B_NO DESC) N) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);
				
			} else if (opt.equals("category")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, N.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT,CG_NAME "
						+	"FROM BOARD "
						+		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='공지사항' AND CG_NAME LIKE ? "
						+	"ORDER BY B_NO DESC) N) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
			} else {
				query = "SELECT * FROM NOTICELIST WHERE RNUM BETWEEN ? AND ? ORDER BY B_NO DESC";
				pstmt = conn.prepareStatement(query);
			}
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while (rset.next()) {
				Board no = new Board(rset.getInt("B_NO"), 						// 게시판 번호
									 rset.getString("B_TITLE"), 				// 게시판 제목
									 rset.getString("B_CONTENT"), 				// 게시판 내용
									 rset.getDate("B_DATE"), 					// 게시판 생성 날짜
									 rset.getDate("B_RDATE"), 					// 게시판 수정 날짜
									 rset.getInt("B_VIEW_COUNT"), 				// 게시판 조회수
									 rset.getInt("B_WRITER"),					// 게시판 글쓴이 회원 번호
									 rset.getString("MEMBER_NICKNAME"), 		// 게시판 글쓴이 회원
									 rset.getInt("B_REPLY_COUNT"), 				// 게시판 댓글
									 rset.getString("CG_NAME")); 				// 카테고리 이름
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
	
	
	
	
	// 파일 변경 로직
//	public int modifyFile(Connection conn, ArrayList<FileVO> fileList) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		String query = "UPDATE FILES SET ORIGIN_NAME = ?, CHANGE_NAME = ?, UPLOAD_DATE=SYSDATE, FILE_PATH = ? WHERE B_NO = ? AND FILE_LEVEL = ?";
//		try {
//			for(int i = 0; i < fileList.size(); i++) {
//				FileVO af = fileList.get(i);
//				System.out.println("af"+i+" : " + af);
//				pstmt = conn.prepareStatement(query);
//				pstmt.setString(1,  af.getOriginName());
//				pstmt.setString(2,  af.getChangeName());
//				pstmt.setString(3,  af.getFilePath());
//				pstmt.setInt(4, af.getBoardNo());
//				pstmt.setInt(5, af.getFileLevel());
//				
//				result += pstmt.executeUpdate();
//			}
//		} catch (SQLException e) {
//				e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		return result;
//	}
	
	
//	public int modifyImage(Connection conn, ArrayList<FileVO> fileList) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		String query = "UPDATE FILES SET ORIGIN_NAME = ?, CHANGE_NAME = ?, UPLOAD_DATE=SYSDATE, FILE_PATH = ? WHERE B_NO = ? AND FILE_LEVEL = ?";
//		try {
//			for(int i = 0; i < fileList.size(); i++) {
//				FileVO af = fileList.get(i);
//				System.out.println("af"+i+" : " + af);
//				pstmt = conn.prepareStatement(query);
//				pstmt.setString(1,  af.getOriginName());
//				pstmt.setString(2,  af.getChangeName());
//				pstmt.setString(3,  af.getFilePath());
//				pstmt.setInt(4, af.getBoardNo());
//				pstmt.setInt(5, af.getFileLevel());
//				
//				result += pstmt.executeUpdate();
//			}
//		} catch (SQLException e) {
//				e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		return result;
//	}
	
	
	// 파일 추가 로직
//	public int AddFile(Connection conn, ArrayList<FileVO> fileList) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		
//		String query = "INSERT INTO FILES VALUES(SEQ_FNO.NEXTVAL, ?, ?, ?, SYSDATE, ?, DEFAULT, DEFAULT,?,NULL)";
//		try {
//			for(int i = 0; i < fileList.size(); i++) {
//			FileVO af = fileList.get(i);
//				pstmt = conn.prepareStatement(query);
//				pstmt.setString(1,  af.getOriginName());
//				pstmt.setString(2,  af.getChangeName());
//				pstmt.setString(3,  af.getFilePath());
//				pstmt.setInt(4, af.getFileLevel());
//				pstmt.setInt(5, af.getBoardNo());
//				
//				result += pstmt.executeUpdate();
//			}
//		} catch (SQLException e) {
//				e.printStackTrace();
//		} finally {
//			close(pstmt);
//		}
//		return result;
//	}
	
}
