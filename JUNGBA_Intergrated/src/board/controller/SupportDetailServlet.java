package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.service.CommunityService;
import board.model.service.SupportService;
import board.model.vo.Board;
import board.model.vo.FileVO;
import board.model.vo.Reply;

/**
 * Servlet implementation class CommuSupportDetailServlet
 */
@WebServlet("/detail.sp")
public class SupportDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bId = Integer.parseInt(request.getParameter("bId"));
		SupportService service = new SupportService();
		Board board = service.selectBoard(bId);
		ArrayList<FileVO> fileList = service.selectFile(bId);
		ArrayList<Reply> replyList = new BoardService().selectReplyList(bId);
		
		String page = null;
		if(board != null) {
			page = "WEB-INF/views/Support_Policy/지원정책내용확인.jsp";
			request.setAttribute("board", board);
			request.setAttribute("fileList", fileList);
			request.setAttribute("replyList", replyList);
		} else {
			page = "WEB-INF/views/Common/errorPage.jsp";
			request.setAttribute("msg", "게시판 상세조회에 실패하였습니다.");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
