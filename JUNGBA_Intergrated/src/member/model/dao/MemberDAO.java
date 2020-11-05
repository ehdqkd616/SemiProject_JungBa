package member.model.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import board.model.vo.Board;
import board.model.vo.FileVO;
import board.model.vo.PageInfo;
import member.model.vo.Member;

public class MemberDAO {

	public MemberDAO() {}

	public Member loginMember(Connection conn, Member member) {
		// loginMember = SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PW = ?
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member loginUser = null;

		String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PW = ? AND MEMBER_ENABLE = 'Y' ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			rset = pstmt.executeQuery();

			if (rset.next()) {
				loginUser = new Member(rset.getInt("MEMBER_NO"), rset.getString("MEMBER_ID"),
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

		return loginUser;
	}

	public int insertMember(Connection conn, Member member) {
		// insertMember = INSERT INTO MEMBER VALUES(SEQ_MNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, DEFAULT, DEFAULT)
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO MEMBER VALUES(SEQ_MNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, DEFAULT, DEFAULT)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberNickName());
			pstmt.setString(5, member.getMemberGender());
			pstmt.setDate(6, (Date) member.getMemberBirthDay());
			pstmt.setString(7, member.getMemberPhone());
			pstmt.setString(8, member.getMemberEmail());			
			pstmt.setString(9, member.getMemberAddress());					
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
		
	}

	public Member selectMember(Connection conn, String loginMemberId) {
		// selectMember = SELECT * FROM MEMBER WHERE MEMBER_ID = ?
		
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


	public Member selectMember(Connection conn, int memberNo) {
		
		// selectMember = SELECT * FROM MEMBER WHERE MEMBER_ID = ?
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;

		String query = "SELECT * FROM MEMBER WHERE MEMBER_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
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


	// 게시판 별 게시글 갯수
	public int getListCount(Connection conn, String boardName, int mNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = "SELECT COUNT(*) FROM BOARD WHERE B_NAME = ? AND B_WRITER = ? AND B_ENABLE = 'Y'";
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, boardName);
			pstmt.setInt(2, mNo);
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
	

	public ArrayList<Board> selectMyCommuFreeList(Connection conn, int loginMemberNo, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		String query = "SELECT * FROM FREELIST WHERE RNUM BETWEEN ? AND ? AND B_WRITER = ? ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, loginMemberNo);
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

	public ArrayList<Board> selectMyQAList(Connection conn, int loginMemberNo, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		String query = "SELECT * FROM QALIST WHERE RNUM BETWEEN ? AND ? AND B_WRITER = ? ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, loginMemberNo);
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

	public ArrayList<Board> selectMySupportList(Connection conn, int loginMemberNo, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		String query = "SELECT * FROM SUPPORTLIST WHERE RNUM BETWEEN ? AND ? AND B_WRITER = ? ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, loginMemberNo);
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

	public ArrayList<Board> selectMyExternalList(Connection conn, int loginMemberNo, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		String query = "SELECT * FROM EXTERNALLIST WHERE RNUM BETWEEN ? AND ? AND B_WRITER = ? ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, loginMemberNo);
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

	public ArrayList<Board> selectMyRecentSupportList(Connection conn, int loginMemberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
				
		String query = "SELECT * FROM SUPPORTLIST WHERE B_WRITER = ? ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, loginMemberNo);
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

	public ArrayList<Board> selectMyRecentExternalList(Connection conn, int loginMemberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
				
		String query = "SELECT * FROM EXTERNALLIST WHERE B_WRITER = ? ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, loginMemberNo);
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

	public ArrayList<Board> selectMyRecentCommuFreeList(Connection conn, int loginMemberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
				
		String query = "SELECT * FROM FREELIST WHERE B_WRITER = ? ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, loginMemberNo);
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

	public ArrayList<Board> selectMyRecentQAList(Connection conn, int loginMemberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
				
		String query = "SELECT * FROM QALIST WHERE B_WRITER = ? ORDER BY B_NO DESC";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, loginMemberNo);
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

	public FileVO selectProfile(Connection conn, int memberNo) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FileVO profile = null;
		
		String query = "SELECT * FROM FILES WHERE MEMBER_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				profile = new FileVO(rset.getString("change_name"), 
						 			 rset.getInt("member_no"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return profile;
	}
	
	public int insertProfile(Connection conn, FileVO profile, int loginMemberNo) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO FILES VALUES(SEQ_FNO.NEXTVAL, ?, ?, ?, SYSDATE, 0, DEFAULT, DEFAULT, NULL, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, profile.getOriginName());
			pstmt.setString(2, profile.getChangeName());
			pstmt.setString(3, profile.getFilePath());
			pstmt.setInt(4, loginMemberNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int deleteProfile(Connection conn, int fileNo, int loginMemberNo) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "DELETE FILES WHERE FILE_NO = ? AND MEMBER_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, fileNo);
			pstmt.setInt(2, loginMemberNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	public int deleteProfile(Connection conn, int loginMemberNo) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "DELETE FILES WHERE MEMBER_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, loginMemberNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	//아이디 중복처리
	public int checkId(Connection conn, String userId) {
		PreparedStatement pstmt=null;
		ResultSet rset= null;
		int result = 0;
		
		String query = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return result; // result 즉 있느지없는지만 판단하기 위해 result를 통해 알수있다
	}
	//닉네임 중복처리
	public int checkNickName(Connection conn, String nickName) {
		PreparedStatement pstmt=null;
		ResultSet rset= null;
		int result = 0;
		
		String query = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_NICKNAME = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  nickName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return result;
	}
	//회원 탈퇴
	public int deleteMember(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE MEMBER SET MEMBER.MEMBER_ENABLE='N' WHERE MEMBER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
//			pstmt.setString(2, memberId.getMemberPw());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			close(pstmt);
		}

		return result;
	}

	//아이디 찾기
	public ArrayList<Member> searchId(Connection conn, String name) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		ArrayList<Member> list = null;
			
		String query = "SELECT * FROM MEMBER WHERE MEMBER_NAME = ? AND MEMBER_ENABLE = 'Y' ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Member>();
			while(rset.next()) {
				Member mb = new Member(rset.getString("MEMBER_ID"),
									   rset.getString("MEMBER_NAME"),
									   rset.getString("MEMBER_PHONE"),
									   rset.getString("MEMBER_EMAIL"));
				list.add(mb);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			close(pstmt);
		}

		return list;
	}
	
	//비밀번호찾기
	public ArrayList<Member> searchPwd(Connection conn, String name) {
		PreparedStatement pstmt=null;
		ResultSet rset= null;
		ArrayList<Member> list = null;
		
		String query = "SELECT * FROM MEMBER WHERE MEMBER_NAME = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Member>();
			while(rset.next()) {
				Member mb = new Member(rset.getString("MEMBER_ID"),
									   rset.getString("MEMBER_NAME"),
									   rset.getString("MEMBER_PHONE"),
									   rset.getString("MEMBER_EMAIL"));
				list.add(mb);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return list;
	}

	public int modifyPwdMember(Connection conn, Member m ) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE MEMBER SET MEMBER.MEMBER_PW=? WHERE MEMBER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberPw());
			pstmt.setString(2, m.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			close(pstmt);
		}

		return result;
	}

	public int updateInfo(Connection conn, Member m) {
		PreparedStatement pstmt = null;

		int result = 0;
		
		String query = "UPDATE MEMBER SET MEMBER.MEMBER_NAME=?, MEMBER.MEMBER_NICKNAME=? , MEMBER.MEMBER_GENDER=? , MEMBER.MEMBER_BIRTHDAY=?, MEMBER.MEMBER_PHONE=?, MEMBER.MEMBER_EMAIL=?, MEMBER.MEMBER_ADDRESS = ? WHERE MEMBER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberName());
			pstmt.setString(2, m.getMemberNickName());
			pstmt.setString(3, m.getMemberGender());
			pstmt.setDate(4, (Date) m.getMemberBirthDay());
			pstmt.setString(5, m.getMemberPhone());
			pstmt.setString(6, m.getMemberEmail());
			pstmt.setString(7, m.getMemberAddress());
			pstmt.setString(8, m.getMemberId());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			close(pstmt);
		}

		return result;
	}
	
	public Member overlapCheck(Connection conn, String userId, String userNickName) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String query="";
		
		if(userNickName=="") {
			query = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
		}else {
			query = "SELECT * FROM MEMBER WHERE MEMBER_NICKNAME = ?";
		}

		try {
			pstmt = conn.prepareStatement(query);
			if(userNickName=="") {
				pstmt.setString(1, userId);
			}else {
				pstmt.setString(1, userNickName);
			}
			
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

	public Member kakaoLogin(Connection conn, Member member) {
		// loginMember = SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PW = ?
		
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				Member loginUser = null;
				System.out.println("카카오 : "+member);
				String query = "SELECT * FROM MEMBER WHERE MEMBER_NAME = ? AND MEMBER_EMAIL = ?";
				
				try {
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, member.getMemberId());
					pstmt.setString(2, member.getMemberPw());
					rset = pstmt.executeQuery();

					if (rset.next()) {
						loginUser = new Member(rset.getInt("MEMBER_NO"), rset.getString("MEMBER_ID"),
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

				return loginUser;
	}

	
}