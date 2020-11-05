package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.service.FreeService;
import board.model.vo.Board;
import board.model.vo.FileVO;
import board.model.vo.Reply;


@WebServlet("/fDetail.cm")
public class CommuFreeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CommuFreeDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		BoardService bService = new BoardService();
		FreeService fService = new FreeService();
		
		Board board = fService.selectBoard(bId);
		ArrayList<FileVO> imageList = bService.selectImageList(bId);
		ArrayList<FileVO> fileList = bService.selectFileList(bId);
		ArrayList<Reply> replyList = new BoardService().selectReplyList(bId);
		
		String page = null;
		
		if(board != null) {
			page = "WEB-INF/views/Community/자유게시판내용확인(커뮤니티).jsp";
			request.setAttribute("board", board);
			request.setAttribute("imageList", imageList);
			request.setAttribute("fileList", fileList);
			request.setAttribute("replyList", replyList);
		} else {
			page = "WEB-INF/views/Common/errorPage.jsp";
			request.setAttribute("msg", "게시판 상세조회에 실패하였습니다.");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
