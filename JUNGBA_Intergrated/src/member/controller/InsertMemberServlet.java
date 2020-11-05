package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

//@WebServlet("/insert.me")
@WebServlet(urlPatterns = "/insert.me", name="InsertMemberServlet")
public class InsertMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertMemberServlet() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("joinUserId");
		String memberPw = request.getParameter("joinUserPwd");
		String memberName = request.getParameter("userName");
		String memberNickName = request.getParameter("nickName");
		String memberGender = request.getParameter("gender");
		String date = request.getParameter("userBirthday");
		
		Date memberBirthDay = null;
		if(date != "") {
			String[] dateArr = date.split("-");
			
			int year = Integer.parseInt(dateArr[0]);
			int month = Integer.parseInt(dateArr[1]) - 1;
			int day = Integer.parseInt(dateArr[2]);
			
			memberBirthDay = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		}else {
			memberBirthDay = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		
	
		String memberPhone = request.getParameter("phone1");
		memberPhone += ("-");
		memberPhone += request.getParameter("phone2");
		memberPhone += ("-");
		memberPhone += request.getParameter("phone3");
		String memberEmail = request.getParameter("email");
		String memberAddress = request.getParameter("address1");
		memberAddress+=(" ");
		memberAddress += request.getParameter("address2");
		
		
		
		Member member = new Member(memberId, memberPw, memberName, memberNickName, memberGender, memberBirthDay, memberPhone, memberEmail,memberAddress);
		System.out.println(member);
		
		int result = new MemberService().insertMember(member);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath());
		} else {
			request.getSession().setAttribute("msg", "페이지 요청 실패");
			request.getRequestDispatcher("WEB-INF/views/Common/errorPage.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
