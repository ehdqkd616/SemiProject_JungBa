package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDAO;
import board.model.dao.FreeDAO;
import board.model.vo.Board;
import board.model.vo.FileVO;
import board.model.vo.PageInfo;

public class FreeService {

	
	// 자유게시판 게시글 갯수
	public int getListCount() {
		
		Connection conn = getConnection();
		
		int result = new BoardDAO().getListCount(conn,"자유");
		
		close(conn);
		
		return result;
	}
	
	
	// 자유게시판 검색 게시글 갯수
	public int getSearchListCount(String opt, String word) {

		Connection conn = getConnection();
		
		int result = new FreeDAO().getSearchListCount(conn, opt, word);
		
		close(conn);
		
		return result;
		
	}
	
	
	// 자유게시판 글 목록
	public ArrayList<Board> selectList(PageInfo pi){ 

		Connection conn = getConnection();
		
		ArrayList<Board> list = new FreeDAO().selectList(conn,pi);
		
		close(conn);
		
		return list;
	}
	
	
	// 자유게시판 게시글 등록
	public int insertBoard(Board b) {
		
		Connection conn = getConnection();
		
		int result = new FreeDAO().insertBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	
	// 자유게시판 게시글 보기
	public Board selectBoard(int bId) {
		Connection conn = getConnection();
		
		FreeDAO dao = new FreeDAO();
		
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

	
	// 자유게시판 게시글 수정
	public int modifyBoard(Board b) {
		Connection conn = getConnection();
		
		int result = new FreeDAO().modifyBoard(conn, b);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	

	// 자유게시판 게시글 등록 및 파일 등록
	// file을 안올렸을때 어떻게 할지 결정해야함
	public int insertBoardAndFiles(Board b, ArrayList<FileVO> fileList) {
		
		Connection conn = getConnection();
		
		FreeDAO dao = new FreeDAO();
		
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
	
	
	// 자유게시판 검색 게시글 목록
	public ArrayList<Board> searchList(String opt, String word, PageInfo pi){ 

		Connection conn = getConnection();
		
		ArrayList<Board> list = new FreeDAO().searchList(conn, opt, word, pi);
		
		close(conn);
		
		return list;
	}
	
}
