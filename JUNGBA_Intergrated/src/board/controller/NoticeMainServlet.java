package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.NoticeService;
import board.model.vo.Board;
import board.model.vo.PageInfo;

@WebServlet("/main.no")
public class NoticeMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeMainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageInfo pi = Page.PageInfo("공지사항", currentPage, "/main.no");
		//페이징
		
		ArrayList<Board> list = new NoticeService().selectList(pi); //Notice만 들어갈 수 있는 ArrayList가 반환 될 것이다.

		String page = null;
			
		if(list != null) {
			page = "WEB-INF/views/Notice/공지사항메인.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);//페이징	
		}else {
			page = "WEB-INF/views/Common/errorPage.jsp";
			request.setAttribute("msg", "공지사항 게시판 조회에 실패하였습니다.");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
