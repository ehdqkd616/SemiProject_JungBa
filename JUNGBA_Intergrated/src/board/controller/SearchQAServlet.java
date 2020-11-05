package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.NoticeService;
import board.model.service.QuestionService;
import board.model.vo.Board;
import board.model.vo.PageInfo;

@WebServlet("/search.qa")
public class SearchQAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchQAServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		request.setCharacterEncoding("UTF-8");
    	
    	String opt = request.getParameter("opt");
    	String word = request.getParameter("word");
    	
    	System.out.println("선택된 select : " + opt);
    	System.out.println("검색된 검색어 : " + word);
    	
    	QuestionService service = new QuestionService();

    	int listCount = service.getSearchListCount(opt, word);
    	
		//페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageInfo pi = Page.searchPageInfo(listCount, currentPage, "/search.qa?opt="+opt+"&word="+word);
		//페이징
    	
    	
		ArrayList<Board> list = service.searchList(opt, word, pi);
		
		String page = null;
		
		if(list != null) {
			page = "WEB-INF/views/Question_Answer/QA_검색.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);//페이징	
			request.setAttribute("searchWord", word);
			request.setAttribute("selectedOpt", opt);
		}else {
			page = "WEB-INF/views/Common/errorPage.jsp";
			request.setAttribute("msg", "Q/A 게시판 검색에 실패하였습니다.");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
