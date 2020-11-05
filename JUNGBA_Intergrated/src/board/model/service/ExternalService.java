package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDAO;
import board.model.dao.CommunityDAO;
import board.model.dao.ExternalDAO;
import board.model.vo.Board;
import board.model.vo.FileVO;

public class ExternalService {

//	public ArrayList<Board> selectList(PageInfo pi) {
//		Connection conn = getConnection();
//		
//		ArrayList<Board> list = new ExternalDAO().selectList(conn,pi);
//		
//		close(conn);
//		
//		return list;
//	}

	public ArrayList selectExList(int i) {
		Connection conn = getConnection();
		
		ArrayList list = null;
		
		ExternalDAO dao = new ExternalDAO();	
		if(i == 1) {
			list = dao.selectBList(conn);
		} else {
			list = dao.selectFList(conn);
		}
		
		close(conn);
		
		return list;
	}

	public ArrayList<Board> selectExSearchList(int i, String category, String[] agearr, String[] localarr) {
		Connection conn = getConnection();
		ArrayList list = null;
		
		ExternalDAO dao = new ExternalDAO();	
		if(i == 1) {
			System.out.println("리스트 가져오기 실행");
			list = dao.selectSearchBList(conn,category,agearr,localarr);
		} else {
			System.out.println("파일 가져오기 실행");
			list = dao.selectFList(conn);
		}
		
		close(conn);
		
		return list;
	}

	public Board selectBoard(int bId) {
Connection conn = getConnection();
		
	ExternalDAO dao = new ExternalDAO();
		
		int result = dao.updateCount(conn, bId);
		Board board = null;
		if(result > 0) {
			board = dao.selectBoard(conn, bId);
			if(board != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
		}else {
			rollback(conn);
		}
		close(conn);
		
		return board;
	}

	public ArrayList<FileVO> selectFile(int bId) {
		Connection conn = getConnection();
		
		ArrayList<FileVO> list = null;
		list  = new BoardDAO().selectFile(conn, bId);
		
		if(list != null) {
			commit(conn);
		}else {
			rollback(conn);
			System.out.println("selectFile Rollback!!!!!!!!!!!!!!!");
		}
		
		close(conn);
		
		return list;
	}

	public int AddFile(Board b, ArrayList<FileVO> fileList) {
Connection conn = getConnection();
		
ExternalDAO dao = new ExternalDAO();
		int result = dao.AddFile(conn, fileList);
		
		if(result>0) {
			commit(conn);
		} else {
			System.out.println("AddFile Rollback!!!!!!!!!!!!!!!");
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int modifyBoard(Board b, ArrayList<FileVO> fileList) {
Connection conn = getConnection();
		
ExternalDAO dao = new ExternalDAO();
		int result2 = 0; 
		System.out.println("b : " + b);
		int result1 = dao.modifyBoard(conn,b);
		
		System.out.println("fileList : " + fileList.size());
		System.out.println("result1 : " + result1);
		result2 = result1;
		if(fileList.size()==0 && result1 > 0) {
			result2 = result1;
		}else {
			result2 = dao.modifyFile(conn, fileList);
		}
		
		if(result1 >0 && result2 >0) {
			commit(conn);
		} else {
			rollback(conn);
			System.out.println("modifyBoard Rollback!!!!!!!!!!!!!!!");
		}
		
		close(conn);
		
		return result1;
	}

	public int insertAddFile(Board b, ArrayList<FileVO> fileList) {
		Connection conn = getConnection();
				
		ExternalDAO dao = new ExternalDAO();
		
		int result1 = dao.insertBoard(conn,b);
		int result2 = dao.insertAddFile(conn, fileList);
		
		if(result1 > 0 && result2 >0) {
			commit(conn);
		} else {
			rollback(conn);
			System.out.println("insertAddFile Rollback!!!!!!!!!!!!!!!");
		}
		
		close(conn);
		
		return result1;
	}

}
