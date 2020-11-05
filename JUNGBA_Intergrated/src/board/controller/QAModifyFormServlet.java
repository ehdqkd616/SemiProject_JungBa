package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.QuestionService;
import board.model.vo.Board;

@WebServlet("/modifyForm.qa")
public class QAModifyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QAModifyFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
        
		int no = Integer.parseInt(request.getParameter("no"));
		
		QuestionService service = new QuestionService();
		Board b = service.selectBoard(no);		
		
		request.setAttribute("board", b);	
		request.getRequestDispatcher("WEB-INF/views/Question_Answer/QA_글수정.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
