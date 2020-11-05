package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDAO;
import board.model.dao.NoticeDAO;
import board.model.vo.Board;
import board.model.vo.FileVO;
import board.model.vo.PageInfo;

public class NoticeService {
	
	
	// 공지사항 게시글 갯수
	public int getListCount() {
		
		Connection conn = getConnection();
		
		int result = new BoardDAO().getListCount(conn,"공지사항");
		
		close(conn);
		
		return result;
	}
	
	
	// 공지사항 검색 게시글 갯수
	public int getSearchListCount(String opt, String word) {

		Connection conn = getConnection();
		
		int result = new NoticeDAO().getSearchListCount(conn, opt, word);
		
		close(conn);
		
		return result;
		
	}
	
	// 공지사항 게시글 목록
	public ArrayList<Board> selectList(PageInfo pi){ 

		Connection conn = getConnection();
		
		ArrayList<Board> list = new NoticeDAO().selectList(conn,pi);
		
		close(conn);
		
		return list;
	}
	
	
	// 공지사항 게시글 등록
	public int insertBoard(Board b) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDAO().insertBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	
	// 공지사항 게시글 보기
	public Board selectBoard(int bId) {
		Connection conn = getConnection();
		
		NoticeDAO dao = new NoticeDAO();
		
		int result = new BoardDAO().updateCount(conn, bId);
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

	
	// 공지사항 게시글 수정
	public int modifyBoard(Board b) {
		Connection conn = getConnection();
		
		int result = new NoticeDAO().modifyBoard(conn, b);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	
	// 공지사항 게시글 등록 및 파일 등록
	// file을 안올렸을때 어떻게 할지 결정해야함
	public int insertBoardAndFiles(Board b, ArrayList<FileVO> fileList) {
		
		Connection conn = getConnection();
		
		NoticeDAO dao = new NoticeDAO();
		
		int result1 = dao.insertBoard(conn, b);
		int result2 = new BoardDAO().insertFile(conn, fileList);
		
		if(result1 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1;
	}	
	
	
	// 공지사항 검색 게시글 목록
	public ArrayList<Board> searchList(String opt, String word, PageInfo pi){ 

		Connection conn = getConnection();
		
		ArrayList<Board> list = new NoticeDAO().searchList(conn, opt, word, pi);
		
		close(conn);
		
		return list;
	}

	// 파일 수정 로직
//	public int modifyBoard(Board b, ArrayList<FileVO> fileList) {
//		Connection conn = getConnection();
//		
//		NoticeDAO dao = new NoticeDAO();
//		int result2 = 0; 
//		System.out.println("b : " + b);
//		int result1 = dao.modifyBoard(conn,b);
//		
//		System.out.println("fileList : " + fileList.size());
//		System.out.println("result1 : " + result1);
//		result2 = result1;
//		if(fileList.size()==0 && result1 > 0) {
//			result2 = result1;
//		}else {
//			result2 = dao.modifyFile(conn, fileList);
//		}
//		
//		if(result1 >0 && result2 >0) {
//			commit(conn);
//		} else {
//			rollback(conn);
//			System.out.println("modifyBoard Rollback!!!!!!!!!!!!!!!");
//		}
//		
//		close(conn);
//		
//		return result1;
//	}
	
	
	// 파일 추가 등록
//	public int AddFile(Board b, ArrayList<FileVO> fileList) {
//		Connection conn = getConnection();
//		
//		NoticeDAO dao = new NoticeDAO();
//		System.out.println("왔다감");
//		int result = dao.AddFile(conn, fileList);
//		
//		if(result>0) {
//			commit(conn);
//		} else {
//			System.out.println("AddFile Rollback!!!!!!!!!!!!!!!");
//			rollback(conn);
//		}
//		
//		close(conn);
//		
//		return result;
//	}
	
	
}
