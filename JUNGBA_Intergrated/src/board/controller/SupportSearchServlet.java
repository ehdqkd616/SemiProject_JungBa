package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import board.model.service.CommunityService;
import board.model.service.ExternalService;
import board.model.service.SupportService;
import board.model.vo.Board;
import board.model.vo.FileVO;
import board.model.vo.PageInfo;

/**
 * Servlet implementation class ExternalSearch
 */
@WebServlet("/searchList.sp")
public class SupportSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupportSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SupportService service = new SupportService();

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
        	bList = service.selectSpList(1); // 게시판 리스트 가져오기
        }else {
		 bList = service.selectSpSearchList(1,acarr,emarr,category,agearr,localarr); // 게시판 리스트 가져오기
        }
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
