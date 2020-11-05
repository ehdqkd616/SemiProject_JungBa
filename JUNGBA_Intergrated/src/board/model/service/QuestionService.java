package board.model.service;

import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.dao.BoardDAO;
import board.model.dao.NoticeDAO;
import board.model.dao.QuestionDAO;
import board.model.vo.Board;
import board.model.vo.FileVO;
import board.model.vo.PageInfo;

public class QuestionService {
	
	
	// Q/A 게시글 갯수
	public int getListCount() {
		
	Connection conn = getConnection();
		
		int result = new BoardDAO().getListCount(conn,"QA");
		
		close(conn);
		
		return result;
	}
	
	
	// Q/A 검색 게시글 갯수
	public int getSearchListCount(String opt, String word) {

		Connection conn = getConnection();
		
		int result = new QuestionDAO().getSearchListCount(conn, opt, word);
		
		close(conn);
		
		return result;
		
	}
	
	
	// Q/A 게시글 목록
	public ArrayList<Board> selectList(PageInfo pi){
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new QuestionDAO().selectList(conn,pi);
		
		close(conn);
		
		return list;
	}
	
	
	// Q/A 게시글 등록
	public int insertBoard(Board b) {
		
		Connection conn = getConnection();
		
		int result = new QuestionDAO().insertBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	
	// Q/A 게시글 보기
	public Board selectBoard(int bId) {
		Connection conn = getConnection();
		
		QuestionDAO dao = new QuestionDAO();
		
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
	

	// Q/A 게시글 수정
	public int modifyBoard(Board b) {
		Connection conn = getConnection();
		
		int result = new QuestionDAO().modifyBoard(conn, b);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


	// Q/A 게시글 등록 및 파일 등록
	// file을 안올렸을때 어떻게 할지 결정해야함
	public int insertBoardAndFiles(Board b, ArrayList<FileVO> fileList) {
		
		Connection conn = getConnection();
		
		QuestionDAO dao = new QuestionDAO();
		
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
		
		ArrayList<Board> list = new QuestionDAO().searchList(conn, opt, word, pi);
		
		close(conn);
		
		return list;
	}
	
}
