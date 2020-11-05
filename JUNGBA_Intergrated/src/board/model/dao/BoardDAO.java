package board.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.model.vo.Board;
import board.model.vo.FileVO;
import board.model.vo.Reply;

public class BoardDAO {

	// 게시판 별 게시글 갯수
	public int getListCount(Connection conn, String boardName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = "SELECT COUNT(*) FROM BOARD WHERE B_NAME = ? AND B_ENABLE = 'Y'";
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, boardName);
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	
	// 게시글 삭제
	public int boardDelete(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		int result = 0;
				
		String query = "UPDATE BOARD SET BOARD.B_ENABLE='N' WHERE B_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	// 게시글 조회수
	public int updateCount(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE BOARD SET B_VIEW_COUNT = B_VIEW_COUNT+1 WHERE B_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	// 댓글 목록
	public ArrayList<Reply> selectReplyList(Connection conn, int bId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Reply> list = null;
		
		String sql = "SELECT * FROM REPLYLIST WHERE B_NO = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			rset = pstmt.executeQuery();
			list = new ArrayList<Reply>();
			
			while(rset.next()) {
				list.add(new Reply(
						rset.getInt("reply_no"),
						rset.getString("reply_content"),
						rset.getDate("reply_date"),
						rset.getDate("reply_rdate"),
						rset.getInt("r_writer"),
						rset.getString("member_nickname"),
						rset.getString("reply_enable"),
						rset.getInt("b_no"),
						rset.getString("change_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	

	// 댓글 작성
	public int insertReply(Connection conn, Reply r) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO REPLY VALUES(SEQ_RNO.NEXTVAL, ?, SYSDATE, SYSDATE, ?, DEFAULT, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getReplyWriterNo());
			pstmt.setInt(3, r.getRefBid());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}
	
	
	// 댓글 삭제
	public int deleteReply(Connection conn, int replyNo) {

		PreparedStatement pstmt = null;
		
		int result = 0;
				
		String query = "UPDATE REPLY SET REPLY_ENABLE='N' WHERE REPLY_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	public ArrayList<FileVO> selectFList(Connection conn) {
		
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<FileVO> list = null;
		
		String query = "SELECT * FROM FILES WHERE STATUS='Y' AND FILE_LEVEL=0";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<FileVO>();
			
			while(rset.next()) {
				list.add(new FileVO(rset.getInt("b_no"), 
									rset.getString("change_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return list;
	}
	
	
	public int insertFile(Connection conn, ArrayList<FileVO> fileList) {

		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO FILES VALUES(SEQ_FNO.NEXTVAL, ?, ?, ?, SYSDATE, ?, DEFAULT, DEFAULT, SEQ_BNO.CURRVAL, NULL)";
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				FileVO a = fileList.get(i);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, a.getOriginName());
				pstmt.setString(2, a.getChangeName());
				pstmt.setString(3, a.getFilePath());
				pstmt.setInt(4, a.getFileLevel());
				
				result += pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	// 썸네일 가져오기
	public ArrayList<FileVO> selectThumbnail(Connection conn, int bId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FileVO> list = null;
		
		String query = "SELECT * FROM FILES WHERE B_NO = ? AND STATUS = 'Y' ORDER BY FILE_NO";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<FileVO>();
			
			while(rset.next()) {
				FileVO f = new FileVO();
				f.setFileNo(rset.getInt("file_no"));
				f.setOriginName(rset.getString("origin_name"));
				f.setChangeName(rset.getString("change_name"));
				f.setFilePath(rset.getString("file_path"));
				f.setUploadDate(rset.getDate("upload_date"));
				
				list.add(f);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	// 이미지 목록
	public ArrayList<FileVO> selectImageList(Connection conn, int bId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FileVO> list = null;
		
		String query = "SELECT * FROM FILES WHERE B_NO = ? AND STATUS = 'Y' AND FILE_LEVEL=0 ORDER BY FILE_NO DESC";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<FileVO>();
			
			while(rset.next()) {
				FileVO f = new FileVO();
				f.setFileNo(rset.getInt("file_no"));
				f.setOriginName(rset.getString("origin_name"));
				f.setChangeName(rset.getString("change_name"));
				f.setFilePath(rset.getString("file_path"));
				f.setUploadDate(rset.getDate("upload_date"));
				
				list.add(f);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	// 파일 목록
	public ArrayList<FileVO> selectFileList(Connection conn, int bId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FileVO> list = null;
		
		String query = "SELECT * FROM FILES WHERE B_NO = ? AND STATUS = 'Y' AND FILE_LEVEL=1 ORDER BY FILE_NO DESC";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<FileVO>();
			
			while(rset.next()) {
				FileVO f = new FileVO();
				f.setFileNo(rset.getInt("file_no"));
				f.setOriginName(rset.getString("origin_name"));
				f.setChangeName(rset.getString("change_name"));
				f.setFilePath(rset.getString("file_path"));
				f.setUploadDate(rset.getDate("upload_date"));
				
				list.add(f);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}	
	
	
	public ArrayList<FileVO> selectFile(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FileVO> list = null;
		
		String query = "SELECT * FROM FILES WHERE B_NO=? AND STATUS='Y' ORDER BY FILE_NO";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,  bId);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<FileVO>();
			
			while(rset.next()) {
				FileVO af = new FileVO();
				af.setFileNo(rset.getInt("file_no"));
				af.setOriginName(rset.getString("origin_name"));
				af.setChangeName(rset.getString("change_name"));
				af.setFilePath(rset.getString("file_path"));
				af.setUploadDate(rset.getDate("upload_date"));
				
				list.add(af);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}	
	
	
	
	public int boardEnroll(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
				
		String query = "UPDATE BOARD SET ENROLL_STATE='Y' WHERE B_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getBoardNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
}
