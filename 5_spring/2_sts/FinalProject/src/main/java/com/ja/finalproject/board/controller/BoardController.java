package com.ja.finalproject.board.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ja.finalproject.board.service.BoardServiceImpl;
import com.ja.finalproject.vo.BoardCommentVo;
import com.ja.finalproject.vo.BoardImageVo;
import com.ja.finalproject.vo.BoardVo;
import com.ja.finalproject.vo.MemberVo;

//servlet-context.xml 등록

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardServiceImpl boardService;

	// 로그인 성공시 or 로그아웃시
	// myBatis 쿼리할 때, 목적에 따라 조건문 설정해서 가져올 데이터 나눔
	// 1. 모든글 : 첫화면(DB(serviceIml->mapper,xml) 전체 글 목록 조회, 페이징 위해서 전체글 개수 count 값 쿼리
	// -> (controller)model에 데이터 저장-> jsp에 뿌려주기)
	// 2. 검색 화면 : 검색화면(DB(serviceIml->mapper,xml) 조건에 따른 글 목록 조회, 페이징 위해서 조건에 맞는 글
	// 개수 count 값 쿼리 -> (controller)model에 데이터 저장
	// *** 조건 검색 시, 넘어온 search_type, search_word 값 Model에 저장 -> (jsp)페이징할때 param에
	// 붙여주기
	@RequestMapping("mainPage.do")
	public String mainPage(Model model, String search_type, String search_word,
			@RequestParam(defaultValue = "1") int page_num) {
		// 할 것 많음

		ArrayList<HashMap<String, Object>> contentList = boardService.getContents(search_type, search_word, page_num);

		// 전체 글 수
		int count = boardService.getContentCount(search_type, search_word, page_num);
		// 전체 페이지 수(각 페이지 10개 글)
		int totalPageCount = (int) Math.ceil(count / 10.0);
		// 현재 페이지 번호
		int currentPage = page_num;
		// 보여줄 시작 페이지번호
		int beginPage = ((currentPage - 1) / 5) * 5 + 1;
		// 보여줄 끝 페이지번호
		int endPage = ((currentPage - 1) / 5 + 1) * (5);

		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}

		String addParam = "";

		if (search_type != null && search_word != null) {
			addParam += "&search_type=" + search_type;
			addParam += "&search_word=" + search_word;
		}
		model.addAttribute("addParam", addParam);

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("beginPage", beginPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalPageCount", totalPageCount);

		model.addAttribute("contentList", contentList); // Q. request, session대신 model쓰는 이유??

		return "board/mainPage";
	}

	// mainPage.do에서 글쓰기 클릭 시
	@RequestMapping("writeContentPage.do")
	public String writeContentPage() {

		return "board/writeContentPage";
	}

	// writeContentPage.do에서 submit 시
	@RequestMapping("writeContentProcess.do")
	public String writeContentProcess(BoardVo param, MultipartFile [] board_files ,HttpSession session) {
		
		ArrayList<BoardImageVo> boardImageVoList = 
				new ArrayList<BoardImageVo>();
		
		//"C:/uploadFolder/aaa.jpg"	

		//파일 업로드 
		for(MultipartFile boardFile : board_files) {
			
			//예외처리: 하나도 안 날려도 한바퀴는 돈다.
			if(boardFile.isEmpty()) {
				continue;
			}
			
			String rootFolderName = "/Users/soos/Desktop/uploadFolder/";//"C:/uploadFolder/";
			
			//랜덤 파일 네임 만들기 : 충돌 방지 (시간 + 랜덤 활용)
			String originalFilename = boardFile.getOriginalFilename();
			String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
			String uuidName = UUID.randomUUID().toString();
			long currentTimeMillis  = System.currentTimeMillis();
			String randomFileName = uuidName + "_" + currentTimeMillis + ext;
			
			//오늘 날짜 폴더 만들기 
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String todayFolderName = sdf.format(today);
			String uploadFolderName = rootFolderName + todayFolderName;
			
			File uploadFolder = new File(uploadFolderName); 
			
			if(!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}
			
			String saveFilePathName = uploadFolderName + "/" + randomFileName;
			
			try {
				boardFile.transferTo(new File(saveFilePathName));
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			//데이터 
			BoardImageVo boardImageVo = new BoardImageVo();
			boardImageVo.setImage_ori(originalFilename);
			boardImageVo.setImage_url(todayFolderName + "/" + randomFileName);
		
			boardImageVoList.add(boardImageVo);
		}
		
		//아래 데이터 처리
		
		// 공부해야 할 부분

		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUser");
		int member_no = sessionUser.getMember_no();

		param.setMember_no(member_no);

		//
		boardService.writeContent(param, boardImageVoList);

		return "redirect:./mainPage.do";
	}

	// 게시판 글 목록 클릭 시
	@RequestMapping("readContentPage.do")
	public String readContentPage(int board_no, Model model) {

		// 조회수 증가
		boardService.increaseReadCount(board_no);

		// 조회
		HashMap<String, Object> map = boardService.getContent(board_no);

		model.addAttribute("content", map);

		return "board/readContentPage";
	}

	@RequestMapping("deleteContentProcess.do")
	public String deleteContentProcess(int board_no) {

		boardService.deleteContent(board_no);

		return "redirect:./mainPage.do";
	}

	@RequestMapping("updateContentPage.do")
	public String updateContentPage(int board_no, Model model) {

		HashMap<String, Object> map = boardService.getContent(board_no);

		model.addAttribute("content", map);

		return "board/updateContentPage";
	}

	@RequestMapping("updateContentProcess.do")
	public String updateContentProcess(BoardVo param) {

		boardService.updateContent(param);

		return "redirect:./readContentPage.do?board_no=" + param.getBoard_no();
	}
	
	@RequestMapping("writeComment.do")
	public String writeComment(BoardCommentVo vo, HttpSession session) {

		MemberVo memberVo = (MemberVo)session.getAttribute("sessionUser");
		int memberNo = memberVo.getMember_no();
		vo.setMember_no(memberNo);

		boardService.writeComment(vo);
		
		return "redirect:./readContentPage.do?board_no=" + vo.getBoard_no();
	}

	
	
	
	
}
