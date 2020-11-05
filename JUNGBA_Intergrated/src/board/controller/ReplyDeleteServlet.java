package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

@WebServlet("/replyDelete.re")
public class ReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int bId = Integer.parseInt(request.getParameter("boardNo"));
		int replyNo = Integer.parseInt(request.getParameter("replyNo"));
		String bName = request.getParameter("bName");
		
		int result = new BoardService().deleteReply(replyNo);
		
		if(result > 0) {
			if(bName.equals("공지사항")) {
				response.sendRedirect("detail.no?bId="+bId);
			} else if(bName.equals("QA")) {
				response.sendRedirect("q_detail.qa?bId="+bId);
			} else if(bName.equals("자유")) {
				response.sendRedirect("fDetail.cm?bId="+bId);
			} else if(bName.equals("대외커뮤")) {
				response.sendRedirect("eaDetail.cm?bId="+bId);
			}else if(bName.equals("지원커뮤")) {
				response.sendRedirect("spDetail.cm?bId="+bId);
			}else if(bName.equals("대외")) {
				response.sendRedirect("detail.ea?bId="+bId);
			}else if(bName.equals("지원")) {
				response.sendRedirect("detail.sp?bId="+bId);
			}
			
		} else {
			request.setAttribute("msg", "삭제에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp");
			view.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
