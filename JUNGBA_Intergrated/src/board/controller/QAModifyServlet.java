package board.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.QuestionService;
import board.model.vo.Board;
import member.model.vo.Member;

@WebServlet("/modify.qa")
public class QAModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QAModifyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		
		Board b = new Board();
		b.setBoardNo(no);
		b.setBoardTitle(title);
		b.setBoardContent(content);
		b.setCgName(category);
		
		int result = new QuestionService().modifyBoard(b);		
		
		if (result > 0) {
			response.sendRedirect("q_detail.qa?bId=" + no);
		} else {
			request.setAttribute("msg", "Q/A 게시글 수정에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/Common/errorPage.jsp");
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
