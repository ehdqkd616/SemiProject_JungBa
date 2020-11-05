package admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.AdminService;
import board.controller.Page;
import board.model.vo.PageInfo;
import member.model.vo.Member;

@WebServlet("/searchMember.ad")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchMemberServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	request.setCharacterEncoding("UTF-8");
    	
    	String opt = request.getParameter("opt");
    	String word = request.getParameter("word");
    	
    	System.out.println("선택된 select : " + opt);
    	System.out.println("검색된 검색어 : " + word);
    	
    	AdminService service = new AdminService();

    	int listCount = service.getSearchMemberListCount(opt, word);
    	
		//페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageInfo pi = Page.searchPageInfo(listCount, currentPage, "/searchMember.ad?opt="+opt+"&word="+word);
		//페이징
    	
    	
		ArrayList<Member> list = service.searchMemberList(opt, word, pi);
		
		String page = null;
		
		if(list != null) {
			page = "WEB-INF/views/Admin/관리자(회원 목록 조회)검색.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);//페이징	
			request.setAttribute("searchWord", word);
			request.setAttribute("selectedOpt", opt);
		}else {
			page = "WEB-INF/views/Common/errorPage.jsp";
			request.setAttribute("msg", "회원 검색에 실패하였습니다.");
		}
		request.getRequestDispatcher(page).forward(request, response);
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
