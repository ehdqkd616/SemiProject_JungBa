package main.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.Board;
import main.model.service.MainService;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/Main.mi")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MainService mainService = new MainService();
		
		
		ArrayList<Board> noticeList = mainService.selectMainNoticeList();
		ArrayList<Board> supportList = mainService.selectMainSupportList();
		ArrayList<Board> externalList = mainService.selectMainExternalList();
		ArrayList<Board> commuList = mainService.selectMainCommuList();
		ArrayList<Board> qaList = mainService.selectMainQAList(); //test
		request.setAttribute("supportList", supportList);
		request.setAttribute("externalList", externalList);
		request.setAttribute("commuList", commuList);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("qaList", qaList);
		request.getRequestDispatcher("WEB-INF/views/Main/메인페이지.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
