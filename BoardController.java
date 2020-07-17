package com.springbook.view.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
public class BoardController {
	
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(BoardVO vo,BoardDAO boardDAO) {
		
		System.out.println("글 등록 처리");

		boardDAO.insertBoard(vo);
		return "getBoardList.do";
	}
	
	@RequestMapping(value="/updateBoard.do")
	public String updateBoard(BoardVO vo,BoardDAO boardDAO) {
		System.out.println("글 수정 처리");
		
		boardDAO.updateBoard(vo);
		return "getBoardList.do";
	}
	
	@RequestMapping(value="/deleteBoard.do")
	public String deleteBoard(BoardVO vo,BoardDAO boardDAO) {
		
		
		System.out.println("글 삭제 처리");
			
		boardDAO.deleteBoard(vo);		
		return "getBoardList.do";
	}
	
	@RequestMapping(value="/getBoard.do")
	public String getBoard(BoardVO vo,BoardDAO boardDAO,Model model) {
		
		System.out.println("글 상세 조회 처리");
	
		model.addAttribute("board",boardDAO.getBoard(vo));
		
		return "getBoard.jsp";
	
	}
	
	// 검색 조건 목록 설정
		@ModelAttribute("conditionMap")
		public Map<String, String> searchConditionMap(){
			Map<String, String> conditionMap = new HashMap<String, String>();
			conditionMap.put("제목", "TITLE");
			conditionMap.put("내용", "CONTENT");
			return conditionMap;
		}
	
	@RequestMapping(value="/getBoardList.do")
	public String getBoardList(@RequestParam(value="srchCondition",defaultValue="TITLE",required=false) String condition,@RequestParam(value="searchKeyword",defaultValue="",required=false) String keyword,BoardDAO boardDAO,Model model) {
		System.out.println("글 목록 검색 처리");
	
		//model.addAttribute("boardList",boardDAO.getBoardList(vo));
		
		return "getBoardList.jsp";
		
	}
}
