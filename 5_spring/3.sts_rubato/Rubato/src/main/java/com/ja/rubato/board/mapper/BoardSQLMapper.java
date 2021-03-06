package com.ja.rubato.board.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.ja.rubato.vo.BoardCommentVo;
import com.ja.rubato.vo.BoardImageVo;
import com.ja.rubato.vo.BoardVo;

public interface BoardSQLMapper {

	
	//create primary key
	public int createBoardPK();
	
	//insert
	public void writeContent(BoardVo vo);
	
	public void writeComment(BoardCommentVo vo);
	
	//*** 2개 이상의 매개변수를 넘길 때, 
	//매개변수명 매핑시켜야 myBatis 동적쿼리 조건문에서 사용 가능
	
	//select
	//글 목록 조건에 따른 분기처리(전체 글 or 검색 조건에 따른 글)
	public ArrayList<BoardVo> getContents(
			@Param("search_type") String search_type,
			@Param("search_word") String search_word,
			@Param("page_num") int page_num
			);
	
	//select
	//페이징하기위해 가져올 글 카운트 
	public int getContentCount(
			@Param("search_type") String search_type,
			@Param("search_word") String search_word,
			@Param("page_num") int page_num	
			);
	
	//select
	public BoardVo getContentByNo(int board_no);
	
	//delete
	public void deleteContent(int board_no);
	
	//update
	public void updateContent(BoardVo vo);
	
	//조회수 증가
	public void increaseReadCount(int board_no);
	
	//Image 관련, 등록
	public void registerImage(BoardImageVo vo);
	
	public ArrayList<BoardImageVo> getImageInfoByBoardNo(int board_no);
	
	//comment 관련 
	public ArrayList<BoardCommentVo> getCommentInfoByBoardNo(int board_no);
	
	public void deleteComment(int comment_no);
	
	public void updateComment(
			@Param("comment_content") String comment_content, 
			@Param("comment_no")int comment_no
			);
	
	//좋아요 관련
	public int getRecommendNo(int board_no);
	
	public int checkRecommend(
			@Param("board_no") int board_no, 
			@Param("member_no") int member_no
			);
	
	public void upRecommend(
			@Param("board_no") int board_no, 
			@Param("member_no") int member_no
			);
	
	public void downRecommend(
			@Param("board_no") int board_no, 
			@Param("member_no") int member_no
			);
	
}
