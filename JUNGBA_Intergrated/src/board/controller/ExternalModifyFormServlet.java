package board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.CommunityService;
import board.model.vo.FileVO;
import board.model.vo.Board;
import common.MyFileRenamePolicy;
import member.model.vo.Member;

/**
 * Servlet implementation class CommuExternalModifyFormServlet
 */
@WebServlet("/modifyForm.ea")
public class ExternalModifyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExternalModifyFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	      
	      // encType 이 multipart/form-data 로 전송되었는지 확인
	      if(ServletFileUpload.isMultipartContent(request)) {
	         int maxSize = 1024 * 1024 * 10;
	         String root = request.getSession().getServletContext().getRealPath("/");
	         String savePath = root + "/UploadFolder/external_uploadFiles/";
	         
	         File f = new File(savePath);
	         if(!f.exists()) {
	            f.mkdirs();
	         }
	         /* 
	           파일 명 변환 및 저장 작업
	              사용자가 올린 파일 명을 그대로 저장하지 않는 것이 원칙
	                    1) 같은 파일 명이 있는 경우 기존 파일을 덮어쓰거나 시스템이 지정한 이름대로 바꿔서 저장될 수 있기 때문
	                    2) 특수기호나 띄어쓰기 등 서버에 들어가면 문제가 생기는 이름으로 저장될 수 있기 때문
	                    
	                    
	                 DefaultFileRenamePolicy (cos.jar 안에 존재하는 클래스)
	                    같은 파일명이 있는지 확인 후 있을 경우 파일 명 뒤에 숫자를 붙여줌
	                       ex. aaa.png, aaa1.png, aaa2.png ...
	                    MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	                                                          //매개변수설명   요청받는클래스, 어디에저장할지, 들어오는 파일의최대크기 ,인코딩, 내가 저장한 파일에 대해 rename을 할 거라면 어떤 법칙을 따를 것인지. 
	          * */
	         MultipartRequest multiRequest 
	         = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
	         
	         ArrayList<String> saveFiles = new ArrayList<String>(); // 바뀐 파일의 이름을 저장 할 ArrayList
	         ArrayList<String> originFiles = new ArrayList<String>(); //원본 파일의 이름을 저장할 ArrayList
	         
	         Enumeration<String> files = multiRequest.getFileNames(); // getFileNames() : 폼에서 전송된 File의 name 반환 //여러개 가능
	         
	         while(files.hasMoreElements()) {
	            String name = files.nextElement();
	            
	            if(multiRequest.getFilesystemName(name)!= null) { // getFilesystemName() : RenamePolicy의 rename()에서 작성한대로 rename된 파일 명
	               saveFiles.add(multiRequest.getFilesystemName(name));
	               originFiles.add(multiRequest.getOriginalFileName(name));
	            }
	         }
	         
		int no = Integer.parseInt(multiRequest.getParameter("no"));
		String title = multiRequest.getParameter("title");
		String content = multiRequest.getParameter("content");
		int userId = ((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
		String category = multiRequest.getParameter("category");
		String acState = multiRequest.getParameter("acState");
		String tcName = multiRequest.getParameter("tcName");
		String lcName = multiRequest.getParameter("lcName");
		int viewCount = Integer.parseInt(multiRequest.getParameter("viewCount"));
		int reCommend = Integer.parseInt(multiRequest.getParameter("reCommend"));
		String writer = multiRequest.getParameter("writer");
		ArrayList<FileVO> fileList = new CommunityService().selectFile(no);
		
		String strea_res_date = multiRequest.getParameter("ea_res_date"); 
 		Date ea_res_date = null;
 		if(strea_res_date != "") {
 			String[] dateArr = strea_res_date.split("-");
 			
 			int year = Integer.parseInt(dateArr[0]);
 			int month = Integer.parseInt(dateArr[1]) - 1;
 			int day = Integer.parseInt(dateArr[2]);
 			
 			ea_res_date = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
 		}else {
 			ea_res_date =new Date(new GregorianCalendar().getTimeInMillis());
 		} 
 		
 		String strea_ree_date = multiRequest.getParameter("ea_ree_date"); 
 		Date ea_ree_date = null;
 		if(strea_ree_date != "") {
 			String[] dateArr = strea_ree_date.split("-");
 			
 			int year = Integer.parseInt(dateArr[0]);
 			int month = Integer.parseInt(dateArr[1]) - 1;
 			int day = Integer.parseInt(dateArr[2]);
 			
 			ea_ree_date = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
 		}else {
 			ea_ree_date =new Date(new GregorianCalendar().getTimeInMillis());
 		} 
 		
 		String strea_acs_date = multiRequest.getParameter("ea_acs_date"); 
 		Date ea_acs_date = null;
 		if(strea_acs_date != "") {
 			String[] dateArr = strea_acs_date.split("-");
 			
 			int year = Integer.parseInt(dateArr[0]);
 			int month = Integer.parseInt(dateArr[1]) - 1;
 			int day = Integer.parseInt(dateArr[2]);
 			
 			ea_acs_date = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
 		}else {
 			ea_acs_date =new Date(new GregorianCalendar().getTimeInMillis());
 		} 
 		
 		String strea_ace_date = multiRequest.getParameter("ea_ace_date"); 
 		Date ea_ace_date = null;
 		if(strea_ace_date != "") {
 			String[] dateArr = strea_ace_date.split("-");
 			
 			int year = Integer.parseInt(dateArr[0]);
 			int month = Integer.parseInt(dateArr[1]) - 1;
 			int day = Integer.parseInt(dateArr[2]);
 			
 			ea_ace_date = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
 		}else {
 			ea_ace_date =new Date(new GregorianCalendar().getTimeInMillis());
 		} 
		
//		
//		System.out.println("category : " +category);
//		System.out.println("acState : " +acState);
//		System.out.println("tcName : " +tcName);
//		System.out.println("lcName : " +lcName);
		
		Board b = new Board(no,title,content, null,null, viewCount,reCommend, userId,writer, 0, acState, lcName, null,tcName, category,ea_res_date,ea_ace_date,ea_acs_date,ea_ace_date);
		String page = null;
//		System.out.println("Board,Board : " +b);
		page ="WEB-INF/views/External_Activities/대외활동글수정.jsp";
		request.setAttribute("board", b);
		request.setAttribute("fileList", fileList);
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	    }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
