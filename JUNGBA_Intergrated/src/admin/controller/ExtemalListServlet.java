package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.model.service.AdminService;
import board.controller.Page;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/eaList.ad")
public class ExtemalListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExtemalListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginUser");
		String loginMemberId = loginMember.getMemberId();
		
		Member member = new MemberService().selectMember(loginMemberId);
				
		AdminService adminService = new AdminService();
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageInfo pi = Page.PageInfo("대외", currentPage, "/eaList.ad");
		
		ArrayList<Board> list = adminService.selectAdminExternalList(pi);
		
    	String page = null;
    	if(member != null) {
    		page = "WEB-INF/views/Admin/관리자(대외 활동 신청).jsp";
    		request.setAttribute("member", member);
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
    		request.getRequestDispatcher(page).forward(request, response);
    	}else {
			page = "WEB-INF/views/Common/errorPage.jsp";
			request.setAttribute("msg", "게시판조회에 실패했습니다.");
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
