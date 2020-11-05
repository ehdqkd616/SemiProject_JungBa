package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.QuestionService;
import board.model.vo.Board;
import board.model.vo.FileVO;
import common.MyFileRenamePolicy;
import member.model.vo.Member;


@WebServlet("/insert.qa")
public class QAInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QAInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
				
		if(ServletFileUpload.isMultipartContent(request)) {
						
			int maxSize = 1024 * 1024 * 10; // 10MByte로 전송파일 용량을 제한
			String root = request.getSession().getServletContext().getRealPath("/"); // 웹 서버 컨테이너 경로 추출
			String savePath = root + "UploadFolder/QA_uploadFiles/";
			
			File f = new File(savePath);
			if(!f.exists()) {
				f.mkdirs();
			}
		 	
			MultipartRequest multiRequest 
				= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
			
			ArrayList<String> saveFiles = new ArrayList<String>();		// 바뀐 파일의 이름 저장
			ArrayList<String> originFiles = new ArrayList<String>();	// 원본 파일의 이름 저장
			
			Enumeration<String> files = multiRequest.getFileNames(); // 폼에서 전송된 파일 리스트들의 name 반환
			while(files.hasMoreElements()) {
				String name = files.nextElement();
				System.out.println("files.nextElement : "+name);
				System.out.println("multiRequest.getFilesystemName(name) : "+multiRequest.getFilesystemName(name));
				// multiRequest.getFilesystemName() : MyRenameFilePolicy의 rename메소드에서 작성한대로 rename된 파일 명
				if(multiRequest.getFilesystemName(name) != null) {
					saveFiles.add(multiRequest.getFilesystemName(name));
					originFiles.add(multiRequest.getOriginalFileName(name)); // getOriginalFileName() : 실제 사용자가 업로드 할때의 파일명
				}
			}

			String title = multiRequest.getParameter("title"); //HTTP 요청의 파라미터 값을 얻기 위해 사용하는 것이 request.getParameter() 메쏘드입니다.
			String content = multiRequest.getParameter("content");
			int userId = ((Member)request.getSession().getAttribute("loginUser")).getMemberNo();
			String category = multiRequest.getParameter("category");
			
			System.out.println("title 값 : "+title);
			System.out.println("content 값 : "+content);
			System.out.println("userId 값 : "+userId);
			System.out.println("category 값 : "+category);
			
			Board b = new Board(title, content, userId, category);
			
			ArrayList<FileVO> fileList = new ArrayList<FileVO>();
			for(int i = originFiles.size() - 1; i >= 0; i--) {
				FileVO file = new FileVO();
				file.setFilePath(savePath);
				file.setOriginName(originFiles.get(i));
				file.setChangeName(saveFiles.get(i));
				
				String extension3 = file.getOriginName().substring(file.getOriginName().length()-3);
				String extension4 = file.getOriginName().substring(file.getOriginName().length()-4);
				
				// 파일 하나일때
				if(i == originFiles.size() - 1 && (extension3.equals("jpg") || extension3.equals("JPG") 
						|| extension4.equals("jpeg") || extension4.equals("JPEG") || extension3.equals("png") 
						|| extension3.equals("PNG") || extension3.equals("gif") || extension3.equals("GIF")
						|| extension3.equals("bmp") || extension3.equals("BMP"))) {
					file.setFileLevel(0);
					System.out.println("file.getOriginName : "+file.getOriginName());
					System.out.println("file.getOriginName().substring(file.getOriginName().length()-3) : "+file.getOriginName().substring(file.getOriginName().length()-3));
				
				// 파일 여러개 일때
				}else if(extension3.equals("jpg") || extension3.equals("JPG") || extension4.equals("jpeg") 
						|| extension4.equals("JPEG") || extension3.equals("png") || extension3.equals("PNG") 
						|| extension3.equals("gif") || extension3.equals("GIF") || extension3.equals("bmp") 
						|| extension3.equals("BMP")) {
					file.setFileLevel(0);
				}else {
					file.setFileLevel(1);
					System.out.println("file.getOriginName : "+file.getOriginName());
					System.out.println("file.getOriginName().substring(file.getOriginName().length()-3) : "+file.getOriginName().substring(file.getOriginName().length()-3));
				}
				
				fileList.add(file);
			}
			
			int result = new QuestionService().insertBoardAndFiles(b, fileList);
			
			if(result > 0) {
				response.sendRedirect("main.qa");
			} else {
				for(int i = 0; i<saveFiles.size(); i++) {
					File failedFile = new File(savePath + saveFiles.get(i));
					failedFile.delete();
				}
				
				request.setAttribute("msg", "Q/A 등록에 실패하였습니다.");
				RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/Common/errorPage.jsp");
				view.forward(request, response);
			}
		}	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
