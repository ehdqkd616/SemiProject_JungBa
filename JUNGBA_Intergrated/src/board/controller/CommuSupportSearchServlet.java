package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import board.model.service.CommunityService;
import board.model.vo.Board;

/**
 * Servlet implementation class ExternalSearch
 */
@WebServlet("/spSearchList.cm")
public class CommuSupportSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommuSupportSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommunityService service = new CommunityService();

		String[] acarr = request.getParameterValues("acState[]");
		String[] emarr = request.getParameterValues("emState[]");
		String[] agearr = request.getParameterValues("age[]");
        String[] localarr = request.getParameterValues("local[]");
        ArrayList<Board> bList = new ArrayList<Board>(); // 게시판 리스트 가져오기
        String category = request.getParameter("cate");
        
        System.out.println("category : " + category);
		 System.out.println("age : " + agearr);
		 System.out.println("local : " +localarr );
		 System.out.println("acarr : " +acarr );
		 System.out.println("emarr : " +emarr );
        
        if(acarr == null && emarr == null &&agearr == null && localarr==null && (category==null || category.equals("선택"))) {
        	System.out.println("전체 리스트 가져오기");
        	bList = service.selectSpList(1); // 게시판 리스트 가져오기
        }else {
		 bList = service.selectSpSearchList(1,acarr,emarr,category,agearr,localarr); // 게시판 리스트 가져오기
        }
        System.out.println("bList" + bList);
        response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(bList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
