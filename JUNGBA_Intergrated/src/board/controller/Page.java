package board.controller;

import board.model.service.BoardService;
import board.model.vo.PageInfo;

public class Page {

	public static PageInfo PageInfo(String strName, int iCurrentPage, String strPageName) {
		BoardService bServuce = new BoardService();
		
		//페이징을 위한 변수들
		int listCount;		// 총 게시글 개수
		int currentPage;	// 현재 페이지
		int pageLimit;		// 한 페이지에서 표시되는 페이징 
		int boardLimit;		// 한 페이지에서 보일 게시글 최대 개수
		int maxPage;		// 최대 페이지 중 가장 마지막 페이지
		int startPage;		// 페이징된 페이지 중 시작 페이지
		int endPage;		// 페이징된 페이지 중 마지막 페이지
		String pageName;	// 페이지 이름
		
		listCount = bServuce.getListCount(strName);
		System.out.println(strName + "리스트 : " +listCount);
		currentPage = iCurrentPage;
		
		pageLimit = 10;
		boardLimit = 10;
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		startPage = ( currentPage -1)/ pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		if(maxPage < endPage) { //페이지 수가 10개가 안될때
			endPage = maxPage;
		}
		
		pageName = strPageName;
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage,pageName);
		
		return pi;
				
	}
	
	
	// 검색 페이징 처리
	public static PageInfo searchPageInfo(int listCount, int iCurrentPage, String strPageName) {
		
		//페이징을 위한 변수들
		int currentPage;	// 현재 페이지
		int pageLimit;		// 한 페이지에서 표시되는 페이징 
		int boardLimit;		// 한 페이지에서 보일 게시글 최대 개수
		int maxPage;		// 최대 페이지 중 가장 마지막 페이지
		int startPage;		// 페이징된 페이지 중 시작 페이지
		int endPage;		// 페이징된 페이지 중 마지막 페이지
		String pageName;	// 페이지 이름
		
		currentPage = iCurrentPage;
		
		pageLimit = 10;
		boardLimit = 10;
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		startPage = ( currentPage -1)/ pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		if(maxPage < endPage) { //페이지 수가 10개가 안될때
			endPage = maxPage;
		}
		
		pageName = strPageName;
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage,pageName);
		
		return pi;
				
	}
	
	
	public static PageInfo pageInfo2(int listCount, int iCurrentPage, String strPageName) {
		
		//페이징을 위한 변수들
		int currentPage;	// 현재 페이지
		int pageLimit;		// 한 페이지에서 표시되는 페이징 
		int boardLimit;		// 한 페이지에서 보일 게시글 최대 개수
		int maxPage;		// 최대 페이지 중 가장 마지막 페이지
		int startPage;		// 페이징된 페이지 중 시작 페이지
		int endPage;		// 페이징된 페이지 중 마지막 페이지
		String pageName;	// 페이지 이름
		
		System.out.println("listCount : " +listCount);
		currentPage = iCurrentPage;
		
		pageLimit = 10;
		boardLimit = 10;
		
		maxPage = (int)Math.ceil((double)listCount/boardLimit);
		
		startPage = ( currentPage -1)/ pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		if(maxPage < endPage) { //페이지 수가 10개가 안될때
			endPage = maxPage;
		}
		
		pageName = strPageName;
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage,pageName);
		
		return pi;
				
	}
	
}
