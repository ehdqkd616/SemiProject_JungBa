package admin.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.model.vo.Board;
import board.model.vo.PageInfo;
import member.model.vo.Member;

public class AdminDAO {

	public Member selectMember(Connection conn, String loginMemberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;

		String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginMemberId);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				member = new Member(rset.getInt("MEMBER_NO"), rset.getString("MEMBER_ID"),
						rset.getString("MEMBER_PW"), rset.getString("MEMBER_NAME"), rset.getString("MEMBER_NICKNAME"),
						rset.getString("MEMBER_GENDER"), rset.getDate("MEMBER_BIRTHDAY"), rset.getString("MEMBER_PHONE"),
						rset.getString("MEMBER_EMAIL"),rset.getString("MEMBER_ADDRESS"),rset.getDate("MEMBER_REGDATE"), 
						rset.getString("MEMBER_ENABLE"), rset.getString("MEMBER_GRADE"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return member;
	}

	
	public ArrayList<Member> selectMemberList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = null;
		
		String query = "SELECT * FROM MEMBERLIST WHERE RNUM BETWEEN ? AND ?";
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Member>();
			while(rset.next()) {
				Member m = new Member(rset.getInt("member_no"),
									rset.getString("member_id"),
									rset.getString("member_name"),
									rset.getString("member_nickname"),
									rset.getString("member_gender"),
									rset.getDate("member_birthday"),
									rset.getString("member_phone"),
									rset.getString("member_email"),
									rset.getString("member_address"),
									rset.getDate("member_regdate"),
									rset.getString("member_enable"),
									rset.getString("member_grade"),
									rset.getInt("member_board"));
				list.add(m);
						
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
		
	public ArrayList<Board> selectRecentSupportList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
				
		String query = "SELECT * FROM MAINSUPPORTLIST WHERE RNUM BETWEEN 1 AND 6 ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rset.next()) {
				Board bo = new Board(rset.getInt("B_NO"),
									 rset.getString("B_TITLE"),
									 rset.getString("B_CONTENT"),
									 rset.getDate("B_DATE"),
									 rset.getDate("B_RDATE"),
									 rset.getInt("B_VIEW_COUNT"),
									 rset.getInt("B_RECOMMEND"),
									 rset.getInt("B_WRITER"),
									 rset.getString("MEMBER_NICKNAME"),
									 rset.getInt("B_REPLY_COUNT"),
									 rset.getString("AC_STATE"),
									 rset.getString("LC_NAME"),
									 rset.getString("ENROLL_STATE"),
									 rset.getString("TC_NAME"),
									 rset.getString("CG_NAME"),
									 rset.getDate("RECRUIT_STARTDATE"),
									 rset.getDate("RECRUIT_ENDDATE"),
									 rset.getDate("ACTIVITY_STARTDATE"),
									 rset.getDate("ACTIVITY_ENDDATE"));
				list.add(bo); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<Board> selectRecentExternalList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
				
		String query = "SELECT * FROM MAINEXTERNALLIST WHERE RNUM BETWEEN 1 AND 6 ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rset.next()) {
				Board bo = new Board(rset.getInt("B_NO"),
									 rset.getString("B_TITLE"),
									 rset.getString("B_CONTENT"),
									 rset.getDate("B_DATE"),
									 rset.getDate("B_RDATE"),
									 rset.getInt("B_VIEW_COUNT"),
									 rset.getInt("B_RECOMMEND"),
									 rset.getInt("B_WRITER"),
									 rset.getString("MEMBER_NICKNAME"),
									 rset.getInt("B_REPLY_COUNT"),
									 rset.getString("AC_STATE"),
									 rset.getString("LC_NAME"),
									 rset.getString("ENROLL_STATE"),
									 rset.getString("TC_NAME"),
									 rset.getString("CG_NAME"),
									 rset.getDate("RECRUIT_STARTDATE"),
									 rset.getDate("RECRUIT_ENDDATE"),
									 rset.getDate("ACTIVITY_STARTDATE"),
									 rset.getDate("ACTIVITY_ENDDATE"));
				list.add(bo); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public ArrayList<Board> selectRecentCommuList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
				
		String query = "SELECT * FROM COMMULIST WHERE RNUM BETWEEN 1 AND 6 ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rset.next()) {
				Board bo = new Board(rset.getInt("B_NO"),
									 rset.getString("B_NAME"),
									 rset.getString("B_TITLE"),
									 rset.getString("B_CONTENT"),
									 rset.getDate("B_DATE"),
									 rset.getDate("B_RDATE"),
									 rset.getInt("B_VIEW_COUNT"),
									 rset.getInt("B_WRITER"),
									 rset.getString("MEMBER_NICKNAME"),
									 rset.getInt("B_REPLY_COUNT"));
									 list.add(bo); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<Board> selectRecentQAList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
				
		String query = "SELECT * FROM QALIST WHERE RNUM BETWEEN 1 AND 6 ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rset.next()) {
				Board bo = new Board(rset.getInt("B_NO"),
						 rset.getString("B_TITLE"),
						 rset.getString("B_CONTENT"),
						 rset.getDate("B_DATE"),
						 rset.getDate("B_RDATE"),
						 rset.getInt("B_VIEW_COUNT"),
						 rset.getInt("B_WRITER"),
						 rset.getString("MEMBER_NICKNAME"),
						 rset.getInt("B_REPLY_COUNT"),
						 rset.getString("CG_NAME"));
				list.add(bo); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<Board> selectAdminExternalList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		String query = "SELECT * FROM EXTERNALLIST WHERE RNUM BETWEEN ? AND ?  ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rset.next()) {
				Board bo = new Board(rset.getInt("B_NO"),
									 rset.getString("B_TITLE"),
									 rset.getString("B_CONTENT"),
									 rset.getDate("B_DATE"),
									 rset.getDate("B_RDATE"),
									 rset.getInt("B_VIEW_COUNT"),
									 rset.getInt("B_RECOMMEND"),
									 rset.getInt("B_WRITER"),
									 rset.getString("MEMBER_NICKNAME"),
									 rset.getInt("B_REPLY_COUNT"),
									 rset.getString("AC_STATE"),
									 rset.getString("LC_NAME"),
									 rset.getString("ENROLL_STATE"),
									 rset.getString("TC_NAME"),
									 rset.getString("CG_NAME"),
									 rset.getDate("RECRUIT_STARTDATE"),
									 rset.getDate("RECRUIT_ENDDATE"),
									 rset.getDate("ACTIVITY_STARTDATE"),
									 rset.getDate("ACTIVITY_ENDDATE"));
				list.add(bo); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<Board> selectAdminSupportList(Connection conn, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		String query = "SELECT * FROM SUPPORTLIST WHERE RNUM BETWEEN ? AND ? ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rset.next()) {
				Board bo = new Board(rset.getInt("B_NO"),
									 rset.getString("B_TITLE"),
									 rset.getString("B_CONTENT"),
									 rset.getDate("B_DATE"),
									 rset.getDate("B_RDATE"),
									 rset.getInt("B_VIEW_COUNT"),
									 rset.getInt("B_RECOMMEND"),
									 rset.getInt("B_WRITER"),
									 rset.getString("MEMBER_NICKNAME"),
									 rset.getInt("B_REPLY_COUNT"),
									 rset.getString("AC_STATE"),
									 rset.getString("LC_NAME"),
									 rset.getString("ENROLL_STATE"),
									 rset.getString("EM_STATE"),
									 rset.getString("TC_NAME"),
									 rset.getString("CG_NAME"),
									 rset.getDate("RECRUIT_STARTDATE"),
									 rset.getDate("RECRUIT_ENDDATE"));
				list.add(bo); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int getMemberListCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_ENABLE = 'Y' AND MEMBER_GRADE='user'";
		
		try {
			pstmt= conn.prepareStatement(query);
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
	
	
	// 대외활동 검색 게시글 갯수
	public int getSearchExternalListCount(Connection conn, String opt) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;
		int result = 0;
		
		try {
			
			if(opt.equals("전체")) {
				query = "SELECT COUNT(*) FROM BOARD WHERE B_NAME='대외' AND B_ENABLE='Y'";
				pstmt = conn.prepareStatement(query);
				
			} else if(opt.equals("승인 대기")) {
				query = "SELECT COUNT(*) FROM BOARD WHERE B_NAME='대외' AND B_ENABLE='Y' AND ENROLL_STATE = 'N'";
				pstmt = conn.prepareStatement(query);
				
			} else if(opt.equals("승인 완료")) {
				query = "SELECT COUNT(*) FROM BOARD WHERE B_NAME='대외' AND B_ENABLE='Y' AND ENROLL_STATE = 'Y'";
				pstmt = conn.prepareStatement(query);
			}
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
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
	
	
	// 지원정책 검색 게시글 갯수
	public int getSearchSupportListCount(Connection conn, String opt) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;
		int result = 0;
		
		try {
			
			if(opt.equals("전체")) {
				query = "SELECT COUNT(*) FROM BOARD WHERE B_NAME='지원' AND B_ENABLE='Y'";
				pstmt = conn.prepareStatement(query);
				
			} else if(opt.equals("승인 대기")) {
				query = "SELECT COUNT(*) FROM BOARD WHERE B_NAME='지원' AND B_ENABLE='Y' AND ENROLL_STATE = 'N'";
				pstmt = conn.prepareStatement(query);
				
			} else if(opt.equals("승인 완료")) {
				query = "SELECT COUNT(*) FROM BOARD WHERE B_NAME='지원' AND B_ENABLE='Y' AND ENROLL_STATE = 'Y'";
				pstmt = conn.prepareStatement(query);
			}
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
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
	
	
	// 회원 검색 목록 갯수
	public int getSearchMemberListCount(Connection conn, String opt, String word) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = null;
		int result = 0;
		
		try {
			
			if(opt.equals("전체")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM MEMBERLIST WHERE MEMBER_NO LIKE ? OR MEMBER_ID LIKE ? OR MEMBER_NICKNAME LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				pstmt.setString(3, "%" + word + "%");
				
			} else if (opt.equals("회원 번호")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM MEMBERLIST WHERE MEMBER_NO LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				
			} else if (opt.equals("아이디")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM MEMBERLIST WHERE MEMBER_ID LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
			
			} else if (opt.equals("닉네임")) {
				query = "SELECT COUNT(*) FROM (SELECT * FROM MEMBERLIST WHERE MEMBER_NICKNAME LIKE ?)";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");

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
	
	
	// 대외활동 검색 게시물 목록
	public ArrayList<Board> selectSearchExternalList(Connection conn, String opt, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		String query = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			
			if(opt.equals("전체")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, E.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_RECOMMEND,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT,AC_STATE,LC_NAME,ENROLL_STATE,TC_NAME,CG_NAME,RECRUIT_STARTDATE,RECRUIT_ENDDATE,ACTIVITY_STARTDATE,ACTIVITY_ENDDATE " 
						+ 	"FROM BOARD "
						+ 		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+ 	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='대외'"
						+ 	"ORDER BY B_NO DESC) E) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
			} else if (opt.equals("승인 대기")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, E.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_RECOMMEND,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT,AC_STATE,LC_NAME,ENROLL_STATE,TC_NAME,CG_NAME,RECRUIT_STARTDATE,RECRUIT_ENDDATE,ACTIVITY_STARTDATE,ACTIVITY_ENDDATE " 
						+ 	"FROM BOARD "
						+ 		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+ 	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='대외' AND ENROLL_STATE='N'"
						+ 	"ORDER BY B_NO DESC) E) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
			} else if (opt.equals("승인 완료")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, E.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_RECOMMEND,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT,AC_STATE,LC_NAME,ENROLL_STATE,TC_NAME,CG_NAME,RECRUIT_STARTDATE,RECRUIT_ENDDATE,ACTIVITY_STARTDATE,ACTIVITY_ENDDATE " 
						+ 	"FROM BOARD "
						+ 		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+ 	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='대외' AND ENROLL_STATE='Y'"
						+ 	"ORDER BY B_NO DESC) E) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			}
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rset.next()) {
				Board bo = new Board(rset.getInt("B_NO"),
									 rset.getString("B_TITLE"),
									 rset.getString("B_CONTENT"),
									 rset.getDate("B_DATE"),
									 rset.getDate("B_RDATE"),
									 rset.getInt("B_VIEW_COUNT"),
									 rset.getInt("B_RECOMMEND"),
									 rset.getInt("B_WRITER"),
									 rset.getString("MEMBER_NICKNAME"),
									 rset.getInt("B_REPLY_COUNT"),
									 rset.getString("AC_STATE"),
									 rset.getString("LC_NAME"),
									 rset.getString("ENROLL_STATE"),
									 rset.getString("TC_NAME"),
									 rset.getString("CG_NAME"),
									 rset.getDate("RECRUIT_STARTDATE"),
									 rset.getDate("RECRUIT_ENDDATE"),
									 rset.getDate("ACTIVITY_STARTDATE"),
									 rset.getDate("ACTIVITY_ENDDATE"));
				list.add(bo); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	// 지원정책 검색 게시물 목록
	public ArrayList<Board> selectSearchSupportList(Connection conn, String opt, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		String query = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			
			if(opt.equals("전체")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, S.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_RECOMMEND,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT,AC_STATE,LC_NAME,ENROLL_STATE,TC_NAME,CG_NAME,RECRUIT_STARTDATE,RECRUIT_ENDDATE,ACTIVITY_STARTDATE,ACTIVITY_ENDDATE " 
						+ 	"FROM BOARD "
						+ 		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+ 	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='지원'"
						+ 	"ORDER BY B_NO DESC) S) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
			} else if (opt.equals("승인 대기")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, S.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_RECOMMEND,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT,AC_STATE,LC_NAME,ENROLL_STATE,TC_NAME,CG_NAME,RECRUIT_STARTDATE,RECRUIT_ENDDATE,ACTIVITY_STARTDATE,ACTIVITY_ENDDATE " 
						+ 	"FROM BOARD "
						+ 		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+ 	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='지원' AND ENROLL_STATE='N'"
						+ 	"ORDER BY B_NO DESC) S) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
			} else if (opt.equals("승인 완료")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, S.* "
						+ "FROM (SELECT B_NO,B_TITLE,B_CONTENT,B_DATE,B_RDATE,B_VIEW_COUNT,B_RECOMMEND,B_WRITER,MEMBER_NICKNAME,B_REPLY_COUNT,AC_STATE,LC_NAME,ENROLL_STATE,TC_NAME,CG_NAME,RECRUIT_STARTDATE,RECRUIT_ENDDATE,ACTIVITY_STARTDATE,ACTIVITY_ENDDATE " 
						+ 	"FROM BOARD "
						+ 		"JOIN MEMBER ON(MEMBER_NO = B_WRITER) "
						+ 	"WHERE BOARD.B_ENABLE='Y' AND B_NAME='지원' AND ENROLL_STATE='Y'"
						+ 	"ORDER BY B_NO DESC) S) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			}
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Board>();
			
			while(rset.next()) {
				Board bo = new Board(rset.getInt("B_NO"),
									 rset.getString("B_TITLE"),
									 rset.getString("B_CONTENT"),
									 rset.getDate("B_DATE"),
									 rset.getDate("B_RDATE"),
									 rset.getInt("B_VIEW_COUNT"),
									 rset.getInt("B_RECOMMEND"),
									 rset.getInt("B_WRITER"),
									 rset.getString("MEMBER_NICKNAME"),
									 rset.getInt("B_REPLY_COUNT"),
									 rset.getString("AC_STATE"),
									 rset.getString("LC_NAME"),
									 rset.getString("ENROLL_STATE"),
									 rset.getString("TC_NAME"),
									 rset.getString("CG_NAME"),
									 rset.getDate("RECRUIT_STARTDATE"),
									 rset.getDate("RECRUIT_ENDDATE"),
									 rset.getDate("ACTIVITY_STARTDATE"),
									 rset.getDate("ACTIVITY_ENDDATE"));
				list.add(bo); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	
	// 검색된 회원 리스트 반환
	public ArrayList<Member> searchMemberList(Connection conn, String opt, String word, PageInfo pi){
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = null;		
		String query = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		try {
			
			if(opt.equals("전체")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, MEM.* "
						+ 	"FROM (SELECT MEMBER_NO,MEMBER_ID,MEMBER_NAME,MEMBER_NICKNAME,MEMBER_GENDER,MEMBER_BIRTHDAY,MEMBER_PHONE,MEMBER_EMAIL,MEMBER_ADDRESS,MEMBER_REGDATE,MEMBER_ENABLE,MEMBER_GRADE,"
						+ 	"(SELECT COUNT(*) FROM BOARD B WHERE M.MEMBER_NO=B.B_WRITER) MEMBER_BOARD FROM MEMBER M "
						+ 	"WHERE MEMBER_GRADE='user' AND (MEMBER_NO LIKE ? OR MEMBER_ID LIKE ? OR MEMBER_NICKNAME LIKE ?) "
						+ 	"ORDER BY MEMBER_NO DESC) MEM) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setString(2, "%" + word + "%");
				pstmt.setString(3, "%" + word + "%");
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, endRow);
				
			} else if (opt.equals("회원 번호")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, MEM.* "
						+ 	"FROM (SELECT MEMBER_NO,MEMBER_ID,MEMBER_NAME,MEMBER_NICKNAME,MEMBER_GENDER,MEMBER_BIRTHDAY,MEMBER_PHONE,MEMBER_EMAIL,MEMBER_ADDRESS,MEMBER_REGDATE,MEMBER_ENABLE,MEMBER_GRADE,"
						+ 	"(SELECT COUNT(*) FROM BOARD B WHERE M.MEMBER_NO=B.B_WRITER) MEMBER_BOARD FROM MEMBER M "
						+ 	"WHERE MEMBER_GRADE='user' AND MEMBER_NO LIKE ? "
						+ 	"ORDER BY MEMBER_NO DESC) MEM) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
			} else if (opt.equals("아이디")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, MEM.* "
						+ 	"FROM (SELECT MEMBER_NO,MEMBER_ID,MEMBER_NAME,MEMBER_NICKNAME,MEMBER_GENDER,MEMBER_BIRTHDAY,MEMBER_PHONE,MEMBER_EMAIL,MEMBER_ADDRESS,MEMBER_REGDATE,MEMBER_ENABLE,MEMBER_GRADE,"
						+ 	"(SELECT COUNT(*) FROM BOARD B WHERE M.MEMBER_NO=B.B_WRITER) MEMBER_BOARD FROM MEMBER M "
						+ 	"WHERE MEMBER_GRADE='user' AND MEMBER_ID LIKE ? "
						+ 	"ORDER BY MEMBER_NO DESC) MEM) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			
			} else if (opt.equals("닉네임")) {
				query = "SELECT * FROM "
						+ "(SELECT ROWNUM RNUM, MEM.* "
						+ 	"FROM (SELECT MEMBER_NO,MEMBER_ID,MEMBER_NAME,MEMBER_NICKNAME,MEMBER_GENDER,MEMBER_BIRTHDAY,MEMBER_PHONE,MEMBER_EMAIL,MEMBER_ADDRESS,MEMBER_REGDATE,MEMBER_ENABLE,MEMBER_GRADE,"
						+ 	"(SELECT COUNT(*) FROM BOARD B WHERE M.MEMBER_NO=B.B_WRITER) MEMBER_BOARD FROM MEMBER M "
						+ 	"WHERE MEMBER_GRADE='user' AND MEMBER_NICKNAME LIKE ? "
						+ 	"ORDER BY MEMBER_NO DESC) MEM) "
						+ "WHERE RNUM BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%" + word + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);

			}
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Member>();
			
			while(rset.next()) {
				Member m = new Member(rset.getInt("member_no"),
									rset.getString("member_id"),
									rset.getString("member_name"),
									rset.getString("member_nickname"),
									rset.getString("member_gender"),
									rset.getDate("member_birthday"),
									rset.getString("member_phone"),
									rset.getString("member_email"),
									rset.getString("member_address"),
									rset.getDate("member_regdate"),
									rset.getString("member_enable"),
									rset.getString("member_grade"),
									rset.getInt("member_board"));
				list.add(m);
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
