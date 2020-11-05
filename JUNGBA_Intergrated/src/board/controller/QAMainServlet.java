package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.Board;
import board.model.vo.PageInfo;
import board.model.service.QuestionService;

@WebServlet("/main.qa")
public class QAMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QAMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuestionService qServuce = new QuestionService();
		
		//페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageInfo pi = Page.PageInfo("QA", currentPage, "/main.qa");
		//페이징
		
		ArrayList<Board> list =  qServuce.selectList(pi);
				
		String page = null;
		if(list != null) {
			page = "WEB-INF/views/Question_Answer/QA_게시판.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);//페이징
			
		}else {
			page = "WEB-INF/views/Common/errorPage.jsp";
			request.setAttribute("msg", "Q/A 게시판 조회에 실패하였습니다.");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
