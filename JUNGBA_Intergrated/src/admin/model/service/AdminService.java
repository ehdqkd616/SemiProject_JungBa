package admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import admin.model.dao.AdminDAO;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import member.model.vo.Member;

public class AdminService {

	public Member selectMember(String loginMemberId) {

		Connection conn = getConnection();

		Member member = new AdminDAO().selectMember(conn, loginMemberId);

		close(conn);

		return member;

	}

	public ArrayList<Board> selectRecentSupportList() {
		Connection conn = getConnection();

		ArrayList<Board> list = new AdminDAO().selectRecentSupportList(conn);

		close(conn);

		return list;
	}

	public ArrayList<Board> selectRecentExternalList() {
		Connection conn = getConnection();

		ArrayList<Board> list = new AdminDAO().selectRecentExternalList(conn);

		close(conn);

		return list;
	}

	public ArrayList<Board> selectRecentQAList() {
		Connection conn = getConnection();

		ArrayList<Board> list = new AdminDAO().selectRecentQAList(conn);

		close(conn);

		return list;
	}

	public ArrayList<Board> selectRecentCommuList() {
		Connection conn = getConnection();

		ArrayList<Board> list = new AdminDAO().selectRecentCommuList(conn);

		close(conn);

		return list;
	}

	public ArrayList<Board> selectAdminExternalList(PageInfo pi) {
		Connection conn = getConnection();

		ArrayList<Board> list = new AdminDAO().selectAdminExternalList(conn, pi);

		close(conn);

		return list;
	}

	public ArrayList<Board> selectAdminSupportList(PageInfo pi) {
		Connection conn = getConnection();

		ArrayList<Board> list = new AdminDAO().selectAdminSupportList(conn, pi);

		close(conn);

		return list;
	}

	public int getMemberListCount() {
		Connection conn = getConnection();

		int result = new AdminDAO().getMemberListCount(conn);

		close(conn);

		return result;
	}

	public ArrayList<Member> selectMemberList(PageInfo pi) {
		Connection conn = getConnection();

		ArrayList<Member> list = new AdminDAO().selectMemberList(conn, pi);

		close(conn);

		return list;
	}
	
	
	// 대외활동 검색 게시글 갯수
	public int getSearchExternalListCount(String opt) {

		Connection conn = getConnection();
		
		int result = new AdminDAO().getSearchExternalListCount(conn, opt);
		
		close(conn);
		
		return result;
		
	}
	
	
	// 지원정책 검색 게시글 갯수
	public int getSearchSupportListCount(String opt) {

		Connection conn = getConnection();
		
		int result = new AdminDAO().getSearchSupportListCount(conn, opt);
		
		close(conn);
		
		return result;
		
	}
	
	
	// 회원 검색 목록 갯수
	public int getSearchMemberListCount(String opt, String word){ 

		Connection conn = getConnection();
		
		int result = new AdminDAO().getSearchMemberListCount(conn, opt, word);
		
		close(conn);
		
		return result;
	}
	
	
	// 대외활동 검색 게시글 목록
	public ArrayList<Board> searchExternalList(String opt, PageInfo pi){ 

		Connection conn = getConnection();
		
		ArrayList<Board> list = new AdminDAO().selectSearchExternalList(conn, opt, pi);
		
		close(conn);
		
		return list;
	}
	
	
	// 지원정책 검색 게시글 목록
	public ArrayList<Board> searchSupportList(String opt, PageInfo pi){ 

		Connection conn = getConnection();
		
		ArrayList<Board> list = new AdminDAO().selectSearchSupportList(conn, opt, pi);
		
		close(conn);
		
		return list;
	}
	
	
	// 회원리스트 검색 목록
	public ArrayList<Member> searchMemberList(String opt, String word, PageInfo pi){ 

		Connection conn = getConnection();
		
		ArrayList<Member> list = new AdminDAO().searchMemberList(conn, opt, word, pi);
		
		close(conn);
		
		return list;
	}
	
	
}
