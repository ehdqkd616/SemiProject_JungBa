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
import board.model.vo.PageInfo;

public class ExternalDAO {

//	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		ArrayList<Board> list = null;
//		
//		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
//		int endRow = startRow + pi.getBoardLimit() - 1; 
//		
//		String query = "SELECT * FROM NOTICELIST WHERE RNUM BETWEEN ? AND ? ORDER BY B_NO DESC"; 
//									//QALIST - > NOTICE , BNUM
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, startRow);
//			pstmt.setInt(2, endRow);
//			rset = pstmt.executeQuery(); 
//			list = new ArrayList<Board>();
//			
//			while(rset.next()){
//				Board no = new Board(rset.getInt("B_NO"),				// 게시판 번호	
//									 rset.getString("B_TITLE"),  		// 게시판 제목	
//									 rset.getString("B_CONTENT"),		// 게시판 내용	
//									 rset.getDate("B_DATE"),			// 게시판 생성 날짜
//									 rset.getDate("B_RDATE"),			// 게시판 수정 날짜	
//									 rset.getInt("B_VIEW_COUNT"),		// 게시판 조회수
//									 rset.getInt("B_WRITER"),			// 게시판 글쓴이 회원 번호
//									 rset.getString("MEMBER_NICKNAME"),	// 게시판 글쓴이 회원	
//									 rset.getInt("B_REPLY_COUNT"),		// 게시판 댓글
//									 rset.getString("CG_NAME"));		// 카테고리 이름
//				list.add(no);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rset);
//			close(pstmt);
//		}
//		return list;
//	}

	public ArrayList selectBList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;

		String query = "SELECT * FROM EXTERNALLIST WHERE ENROLL_STATE='Y' ORDER BY B_NO DESC";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Board>();
			
			while(rset.next()) {
				list.add(new Board(rset.getInt("B_NO"),
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
						   rset.getDate("ACTIVITY_ENDDATE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList selectFList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<FileVO> list = null;
		
		String query= "SELECT * FROM FILES WHERE STATUS ='Y' AND FILE_LEVEL = 0";
		
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
		}finally {
			close(rset);
			close(stmt);
		}
		
	 	return list;
	}

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

	public Board selectBoard(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		
		String query = "SELECT * FROM EXTERNALLIST WHERE B_NO = ?";
		
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
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return board;
	}

	public ArrayList selectSearchBList(Connection conn, String category, String[] agearr, String[] localarr) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> list = null;
		
		String local = "";
		
//		String query = "SELECT * FROM EXTERNALLIST WHERE ENROLL_STATE='Y' ORDER BY B_NO DESC";
		String query = "SELECT * FROM EXTERNALLIST WHERE ENROLL_STATE='Y' ";

		//1.지역만 선택 됐을경우
		if(category.equals("선택") && agearr==null && localarr != null) {//카테고리 대상선택 null 지역만 선택했을경우!
			System.out.println("1.지역만 선택 됐을경우");
			if(localarr.length == 1) {//하나밖에 없을때
				query += " AND LC_NAME LIKE('%'||?||'%')";
			}else{
				query += " AND (LC_NAME ";
				for (int i = 0; i< localarr.length; i++) {
					if(i == localarr.length -1)//마지막일때
						query += " LIKE('%'||?||'%'))";
					else
						query += " LIKE('%'||?||'%') OR LC_NAME";
				}
			}
			query += " ORDER BY B_NO DESC ";
			
			try {
				pstmt = conn.prepareStatement(query);
				
				for (int i = 0; i< localarr.length; i++) {
					pstmt.setString(1+i, localarr[i]);
				}
				rset = pstmt.executeQuery();
				
				list = new ArrayList<Board>();
				list =  addList(rset,list);
			} catch (SQLException e) {
				e.printStackTrace();
				
			}finally {
				close(rset);
				close(pstmt);
			}
		}
		else if(category.equals("선택") && localarr==null && agearr!=null){//2.대상만 선택 됐을경우
			System.out.println("2.대상만 선택 됐을경우");
			if(agearr.length == 1) {//하나밖에 없을때
				query += " AND TC_NAME LIKE('%'||?||'%')";
			}else{
				query += " AND (TC_NAME ";
				for (int i = 0; i< agearr.length; i++) {
					if(i == agearr.length -1)//마지막일때
						query += " LIKE('%'||?||'%'))";
					else
						query += " LIKE('%'||?||'%') OR TC_NAME";
				}
			}
			query += " ORDER BY B_NO DESC ";
			
			try {
				pstmt = conn.prepareStatement(query);
				
				for (int i = 0; i< agearr.length; i++) {
					pstmt.setString(1+i, agearr[i]);
				}
				rset = pstmt.executeQuery();
				
				list = new ArrayList<Board>();
				list =  addList(rset,list);
			} catch (SQLException e) {
				e.printStackTrace();
				
			}finally {
				close(rset);
				close(pstmt);
			}
		}else if(agearr==null && localarr==null && !(category.equals("선택"))) {//3.카테고리만 선택했을경우
			System.out.println("3.카테고리만 선택 됐을경우");
			query = "SELECT * FROM EXTERNALLIST WHERE ENROLL_STATE='Y' AND CG_NAME IN (?)  ORDER BY B_NO DESC";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, category);
				rset = pstmt.executeQuery();
				
				list = new ArrayList<Board>();
				list =  addList(rset,list);
			} catch (SQLException e) {
				e.printStackTrace();
				
			}finally {
				close(rset);
				close(pstmt);
			}
		}else if(agearr != null && localarr != null && category.equals("선택")) {//4.지역과 대상이 선택됐을경우
			System.out.println("4.지역과 대상이 선택 됐을경우");
			if(agearr.length == 1) {//하나밖에 없을때
				query += " AND TC_NAME LIKE('%'||?||'%')";
			}else{
				query += " AND (TC_NAME ";
				for (int i = 0; i< agearr.length; i++) {
					if(i == agearr.length -1)//마지막일때
						query += " LIKE('%'||?||'%'))";
					else
						query += " LIKE('%'||?||'%') OR TC_NAME";
				}
			}
			
			if(localarr.length == 1) {//하나밖에 없을때
				query += " AND LC_NAME LIKE('%'||?||'%')";
			}else{
				query += " AND (LC_NAME ";
				for (int i = 0; i< localarr.length; i++) {
					if(i == localarr.length -1)//마지막일때
						query += " LIKE('%'||?||'%'))";
					else
						query += " LIKE('%'||?||'%') OR LC_NAME";
				}
			}
			query += " ORDER BY B_NO DESC ";
			System.out.println(query);
			try {
				pstmt = conn.prepareStatement(query);
				for (int i = 0; i< agearr.length; i++) {
					pstmt.setString(1+i, agearr[i]);
				}
				
				for (int i = 0; i< localarr.length; i++) {
					pstmt.setString(1+i+agearr.length, localarr[i]);
				}
				
				rset = pstmt.executeQuery();
				
				list = new ArrayList<Board>();
				list =  addList(rset,list);
			} catch (SQLException e) {
				e.printStackTrace();
				
			}finally {
				close(rset);
				close(pstmt);
			}
		}else if(agearr == null && localarr != null && !(category.equals("선택"))) {//5.지역과 카테고리가 선택됐을경우
			System.out.println("5.지역과 카테고리가 선택됐을경우");
			query = "SELECT * FROM EXTERNALLIST WHERE ENROLL_STATE='Y' AND CG_NAME IN (?)";
			if(localarr.length == 1) {//하나밖에 없을때
				query += " AND LC_NAME LIKE('%'||?||'%')";
			}else{
				query += " AND (LC_NAME ";
				for (int i = 0; i< localarr.length; i++) {
					if(i == localarr.length -1)//마지막일때
						query += " LIKE('%'||?||'%'))";
					else
						query += " LIKE('%'||?||'%') OR LC_NAME";
				}
			}
			query += " ORDER BY B_NO DESC ";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, category);
				for (int i = 0; i< localarr.length; i++) {
					pstmt.setString(2+i, localarr[i]);
				}
				rset = pstmt.executeQuery();
				
				list = new ArrayList<Board>();
				list =  addList(rset,list);
			} catch (SQLException e) {
				e.printStackTrace();
				
			}finally {
				close(rset);
				close(pstmt);
			}
		}else if(agearr != null && localarr == null && !(category.equals("선택"))) {//6.대상과 카테고리가 선택됐을경우
			System.out.println("6.대상과 카테고리가 선택됐을경우");
			query = "SELECT * FROM EXTERNALLIST WHERE ENROLL_STATE='Y' AND CG_NAME IN (?)";
			if(agearr.length == 1) {//하나밖에 없을때
				query += " AND TC_NAME LIKE('%'||?||'%')";
			}else{
				query += " AND (TC_NAME ";
				for (int i = 0; i< agearr.length; i++) {
					if(i == agearr.length -1)//마지막일때
						query += " LIKE('%'||?||'%'))";
					else
						query += " LIKE('%'||?||'%') OR TC_NAME";
				}
			}
			query += " ORDER BY B_NO DESC ";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, category);
				for (int i = 0; i< agearr.length; i++) {
					pstmt.setString(2+i, agearr[i]);
				}
				rset = pstmt.executeQuery();
				
				list = new ArrayList<Board>();
				list =  addList(rset,list);
			} catch (SQLException e) {
				e.printStackTrace();
				
			}finally {
				close(rset);
				close(pstmt);
			}
		}else {//7.전부 선택했을경우
			System.out.println("7.전부 선택됐을경우");
			query = "SELECT * FROM EXTERNALLIST WHERE ENROLL_STATE='Y' AND CG_NAME IN (?)";
			if(agearr.length == 1) {//하나밖에 없을때
				query += " AND TC_NAME LIKE('%'||?||'%')";
			}else{
				query += " AND (TC_NAME ";
				for (int i = 0; i< agearr.length; i++) {
					if(i == agearr.length -1)//마지막일때
						query += " LIKE('%'||?||'%'))";
					else
						query += " LIKE('%'||?||'%') OR TC_NAME";
				}
			}
			if(localarr.length == 1) {//하나밖에 없을때
				query += " AND LC_NAME LIKE('%'||?||'%')";
			}else{
				query += " AND (LC_NAME ";
				for (int i = 0; i< localarr.length; i++) {
					if(i == localarr.length -1)//마지막일때
						query += " LIKE('%'||?||'%'))";
					else
						query += " LIKE('%'||?||'%') OR LC_NAME";
				}
			}
			query += " ORDER BY B_NO DESC ";
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, category);
				for (int i = 0; i< agearr.length; i++) {
					pstmt.setString(2+i, agearr[i]);
				}
				
				for (int i = 0; i< localarr.length; i++) {
					pstmt.setString(2+i+agearr.length, localarr[i]);
				}
				rset = pstmt.executeQuery();
				
				list = new ArrayList<Board>();
				list =  addList(rset,list);
			} catch (SQLException e) {
				e.printStackTrace();
				
			}finally {
				close(rset);
				close(pstmt);
			}
			
		}
		return list;
	}

	private ArrayList<Board> addList(ResultSet rset, ArrayList<Board> list) {
		try {
			while(rset.next()) {
				list.add(new Board(rset.getInt("B_NO"),
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
						   rset.getDate("ACTIVITY_ENDDATE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int AddFile(Connection conn, ArrayList<FileVO> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO FILES VALUES(SEQ_FNO.NEXTVAL, ?, ?, ?, SYSDATE, ?, DEFAULT, DEFAULT,?,NULL)";
		try {
			for(int i = 0; i < fileList.size(); i++) {
			FileVO af = fileList.get(i);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1,  af.getOriginName());
				pstmt.setString(2,  af.getChangeName());
				pstmt.setString(3,  af.getFilePath());
				pstmt.setInt(4, af.getFileLevel());
				pstmt.setInt(5, af.getBoardNo());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int modifyBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE BOARD SET B_TITLE = ?, B_CONTENT = ?, B_RDATE=SYSDATE, LC_NAME = ?, TC_NAME = ?, CG_NAME=?, RECRUIT_STARTDATE = ?,RECRUIT_ENDDATE=?,ACTIVITY_STARTDATE=?,ACTIVITY_ENDDATE=?  WHERE B_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getLcName());
			pstmt.setString(4, b.getTcName());
			pstmt.setString(5, b.getCgName());
			pstmt.setDate(6, b.getReStartDate());
			pstmt.setDate(7, b.getReEndDate());
			pstmt.setDate(8, b.getAcStartDate());
			pstmt.setDate(9, b.getAcEndDate());
			pstmt.setInt(10, b.getBoardNo());
			
			result += pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int modifyFile(Connection conn, ArrayList<FileVO> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE FILES SET ORIGIN_NAME = ?, CHANGE_NAME = ?, UPLOAD_DATE=SYSDATE, FILE_PATH = ? WHERE B_NO = ? AND FILE_LEVEL = ?";
		try {
			for(int i = 0; i < fileList.size(); i++) {
				FileVO af = fileList.get(i);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1,  af.getOriginName());
				pstmt.setString(2,  af.getChangeName());
				pstmt.setString(3,  af.getFilePath());
				pstmt.setInt(4, af.getBoardNo());
				pstmt.setInt(5, af.getFileLevel());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO BOARD VALUES(SEQ_BNO.NEXTVAL,'대외',?,?,SYSDATE,SYSDATE,DEFAULT,DEFAULT,DEFAULT,?,DEFAULT,'접수중',?,'Y',NULL,?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getBoardWriterNo());
			pstmt.setString(4, b.getLcName());
			pstmt.setString(5, b.getTcName());
			pstmt.setString(6, b.getCgName());
			pstmt.setDate(7, b.getReStartDate());
			pstmt.setDate(8, b.getReEndDate());
			pstmt.setDate(9, b.getAcStartDate());
			pstmt.setDate(10, b.getAcEndDate());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertAddFile(Connection conn, ArrayList<FileVO> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "INSERT INTO FILES VALUES(SEQ_FNO.NEXTVAL, ?, ?, ?, SYSDATE, ?, DEFAULT, DEFAULT,SEQ_BNO.CURRVAL,NULL)";
		try {
			for(int i = 0; i < fileList.size(); i++) {
			FileVO af = fileList.get(i);
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1,  af.getOriginName());
				pstmt.setString(2,  af.getChangeName());
				pstmt.setString(3,  af.getFilePath());
				pstmt.setInt(4, af.getFileLevel());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
