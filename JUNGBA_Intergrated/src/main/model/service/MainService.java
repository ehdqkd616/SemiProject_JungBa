package main.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import board.model.vo.Board;
import main.model.dao.MainDAO;
import member.model.dao.MemberDAO;

public class MainService {

	public ArrayList<Board> selectMainNoticeList() {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new MainDAO().selectMainNoticeList(conn);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Board> selectMainSupportList() {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new MainDAO().selectMainSupportList(conn);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Board> selectMainExternalList() {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new MainDAO().selectMainExternalList(conn);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Board> selectMainCommuList() {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new MainDAO().selectMainCommuList(conn);
		
		close(conn);
		
		return list;
	}

	public ArrayList<Board> selectMainQAList() {
Connection conn = getConnection();
		
		ArrayList<Board> list = new MainDAO().selectMainQAList(conn);
		
		close(conn);
		
		return list;
	}

}
