package com.ja.finalproject.board.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ja.finalproject.board.service.BoardServiceImpl;
import com.ja.finalproject.vo.BoardVo;
import com.ja.finalproject.vo.MemberVo;

//servlet-context.xml 등록


@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardServiceImpl boardService;
	
	//로그인 성공시 or 로그아웃시 
	@RequestMapping("mainPage.do")
	public String mainPage(
			Model model, 
			String search_type, 
			String search_word,
			@RequestParam(defaultValue = "1") int page_num){
		// 할 것 많음
		
		ArrayList<HashMap<String, Object>> contentList = 
				boardService.getContents(search_type, search_word, page_num);
		model.addAttribute("contentList", contentList); //Q. request, session대신 model쓰는 이유??
		
		return "board/mainPage";
	}

	//mainPage.do에서 글쓰기 클릭 시
	@RequestMapping("writeContentPage.do")
	public String writeContentPage() {

		return "board/writeContentPage";
	}

	//writeContentPage.do에서 submit 시
	@RequestMapping("writeContentProcess.do")
	public String writeContentProcess(BoardVo param, HttpSession session) {

		// 공부해야 할 부분

		MemberVo sessionUser = (MemberVo) session.getAttribute("sessionUser");
		int member_no = sessionUser.getMember_no();

		param.setMember_no(member_no);

		//
		boardService.writeContent(param);

		return "redirect:./mainPage.do";
	}
	
	//게시판 글 목록 클릭 시
	@RequestMapping("readContentPage.do")
	public String readContentPage(int board_no, Model model) {
		
		//조회수 증가
		boardService.increaseReadCount(board_no);
		
		//조회
		HashMap<String, Object> map =boardService.getContent(board_no);
		
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
	
			
}











