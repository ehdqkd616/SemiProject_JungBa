package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.service.NoticeService;
import board.model.vo.Board;
import board.model.vo.FileVO;
import board.model.vo.Reply;


@WebServlet("/detail.no")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public NoticeDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bId = Integer.parseInt(request.getParameter("bId"));
		
		BoardService bService = new BoardService();
		NoticeService nService = new NoticeService();
		
		Board board = nService.selectBoard(bId);
		ArrayList<FileVO> imageList = bService.selectImageList(bId);
		ArrayList<FileVO> fileList = bService.selectFileList(bId);
		ArrayList<Reply> replyList = bService.selectReplyList(bId);
				
		String page = null;
		
		if(board != null) {
			page = "WEB-INF/views/Notice/공지사항내용확인.jsp";
			request.setAttribute("board", board);
			request.setAttribute("imageList", imageList);
			request.setAttribute("fileList", fileList);
			request.setAttribute("replyList", replyList);
		} else {
			page = "WEB-INF/views/Common/errorPage.jsp";
			request.setAttribute("msg", "공지사항 상세조회에 실패하였습니다.");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
