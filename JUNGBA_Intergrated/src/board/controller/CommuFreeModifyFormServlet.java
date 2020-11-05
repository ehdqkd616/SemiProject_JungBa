package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.FreeService;
import board.model.vo.Board;

@WebServlet("/fmodifyForm.cm")
public class CommuFreeModifyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommuFreeModifyFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
	         
		int no = Integer.parseInt(request.getParameter("no"));
		
		FreeService service = new FreeService();
		Board b = service.selectBoard(no);		
		
		request.setAttribute("board", b);	
		request.getRequestDispatcher("WEB-INF/views/Community/자유게시판글수정(커뮤니티).jsp").forward(request, response);
	
	}		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
