package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Reply;
import member.model.vo.Member;

@WebServlet("/replyInsert.re")
public class ReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int loginMemberNo = ((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
		int bId = Integer.parseInt(request.getParameter("boardNo"));
		String content = request.getParameter("replyContent");
		String bName = request.getParameter("bName");
		
		Reply reply = new Reply(loginMemberNo, bId, content);
		
		BoardService service = new BoardService();
		
		int result = service.insertReply(reply);
		
		if(result > 0) {
			if(bName.equals("공지사항")) {
				response.sendRedirect("detail.no?bId="+bId);
			} else if(bName.equals("QA")) {
				response.sendRedirect("q_detail.qa?bId="+bId);
			} else if(bName.equals("자유")) {
				response.sendRedirect("fDetail.cm?bId="+bId);
			} else if(bName.equals("대외")) {
				response.sendRedirect("detail.ea?bId="+bId);
			}else if(bName.equals("대외커뮤")) {
				response.sendRedirect("eaDetail.cm?bId="+bId);
			} else if(bName.equals("지원")) {
				response.sendRedirect("detail.sp?bId="+bId);
			}else if(bName.equals("지원커뮤")) {
				response.sendRedirect("spDetail.cm?bId="+bId);
			}
		} else {			
			request.setAttribute("msg", "댓글 등록에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/Common/errorPage.jsp");
			view.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
