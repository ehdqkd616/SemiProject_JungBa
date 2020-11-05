package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.controller.Page;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/myPageQA.me")
public class MyPageQAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyPageQAServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginUser");
		String loginMemberId = loginMember.getMemberId();
		int loginMemberNo = loginMember.getMemberNo();
		
		Member member = new MemberService().selectMember(loginMemberId);
		
		MemberService memberService = new MemberService();
		
		int listCount = memberService.getListCount("QA", loginMemberNo);
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageInfo pi = Page.pageInfo2(listCount, currentPage, "/myPageQA.me");
		
		ArrayList<Board> list = memberService.selectMyQAList(loginMemberNo, pi);
		
    	String page = null;
    	if(member != null) {
    		page = "WEB-INF/views/Member/마이_페이지(QA).jsp";
    		request.setAttribute("member", member);
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
    		request.getRequestDispatcher(page).forward(request, response);
    	}else {
			page = "WEB-INF/views/Common/errorPage.jsp";
			request.setAttribute("msg", "회원조회에 실패했습니다.");
		}		
    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}