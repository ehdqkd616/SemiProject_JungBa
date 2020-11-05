package member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

/**
 * Servlet implementation class ChangePwdServlet
 */
@WebServlet(urlPatterns = "/changePwd.me", name="ChangePwdServlet")
public class ChangePwdServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
      request.setCharacterEncoding("UTF-8");
      
      HttpSession session = request.getSession();
      Member loginMember = (Member)session.getAttribute("loginUser");
      
      String id = request.getParameter("id");
      String pwd1 = request.getParameter("userPwd1");
      String pwd2 = request.getParameter("userPwd2");
      
      System.out.println("pwd1"+pwd1);
      System.out.println("pwd2"+pwd2);
      
      String page = null;
      Member m = new Member(id,pwd1);
      System.out.println("로그인멤버 : " + loginMember);
      
      //마이 페이지로 가기 위해 정의
      int loginMemberNo = loginMember.getMemberNo();
      
      MemberService memberService = new MemberService();
      
      ArrayList<Board> supportList = memberService.selectMyRecentSupportList(loginMemberNo);
      ArrayList<Board> externalList = memberService.selectMyRecentExternalList(loginMemberNo);
      ArrayList<Board> commuFreeList = memberService.selectMyRecentCommuFreeList(loginMemberNo);
      ArrayList<Board> qaList = memberService.selectMyRecentQAList(loginMemberNo);
      // 여기까지
      
      FileVO profile = memberService.selectProfile(loginMemberNo);
      
      
		/*
		 * boolean result1 = pwd1.matches("^[a-zA-Z0-9\\!\\*\\$]{5,11}$"); boolean
		 * result2 = pwd1.matches ("^[a-zA-Z]([a-zA-Z0-9\\!\\*\\$]){5,11}$");
		 */
      
//      String pattern1 = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{5,11}$";

      

      if(pwd1.equals(null) || pwd2.equals(null)) {
          request.setAttribute("msg", "비밀번호 입력이 되지않았습니다.");
          page = "WEB-INF/views/Common/errorPage.jsp";
      } else {
         if(pwd1.equals(pwd2)) {
            int result = new MemberService().modifyPwdMember(m);
            if(result > 0) {
               Member member = new Member(loginMember.getMemberId(), pwd1); 
               Member loginUser = new MemberService().loginMember(member);
               
               System.out.println("비밀번호 변경 전 loginUser 정보" + loginMember);
               System.out.println("비밀번호 변경 후 loginUser 정보" + loginUser);
               session.setMaxInactiveInterval(600);
               session.setAttribute("loginUser", loginUser);
               
               //마이  페이지로 가기 위해 정의
                request.setAttribute("member", member);
                request.setAttribute("supportList", supportList);
                request.setAttribute("externalList", externalList);
                request.setAttribute("commuFreeList", commuFreeList);
                request.setAttribute("qaList", qaList);
                request.setAttribute("profile", profile);
                //여기까지
                
               page = "WEB-INF/views/Member/마이_페이지(메인).jsp";
            } else {
               request.setAttribute("msg", "비밀번호가 재설정에 실패하였습니다.");
               page = "WEB-INF/views/Common/errorPage.jsp";
            }
         } else {
            request.setAttribute("msg", "비밀번호가 일치하지않습니다.");
            page = "WEB-INF/views/Common/errorPage.jsp";
         }
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