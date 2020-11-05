package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.vo.Board;
import board.model.vo.FileVO;
import member.model.service.MemberService;
import member.model.vo.Member;


@WebServlet("/myPage.me")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginUser");
		String loginMemberId = loginMember.getMemberId();
		int loginMemberNo = loginMember.getMemberNo();
		
		Member member = new MemberService().selectMember(loginMemberId);
		
		MemberService memberService = new MemberService();
		
		ArrayList<Board> supportList = memberService.selectMyRecentSupportList(loginMemberNo);
		ArrayList<Board> externalList = memberService.selectMyRecentExternalList(loginMemberNo);
		ArrayList<Board> commuFreeList = memberService.selectMyRecentCommuFreeList(loginMemberNo);
		ArrayList<Board> qaList = memberService.selectMyRecentQAList(loginMemberNo);
		
		FileVO profile = memberService.selectProfile(loginMemberNo);
		
    	String page = null;
    	if(member != null) {
    		page = "WEB-INF/views/Member/마이_페이지(메인).jsp";
    		request.setAttribute("member", member);
    		request.setAttribute("supportList", supportList);
    		request.setAttribute("externalList", externalList);
    		request.setAttribute("commuFreeList", commuFreeList);
    		request.setAttribute("qaList", qaList);
    		request.setAttribute("profile", profile);
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
