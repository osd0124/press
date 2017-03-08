package com.pressfit.board.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pressfit.board.service.BoardService;
import com.pressfit.common.CommandMap;

@Controller
public class I_BoardController {

Logger log = Logger.getLogger(this.getClass());

	@Resource(name="i_boardService")
	private BoardService boardService;

	@RequestMapping(value="/i_board/openBoardList.do")
    public ModelAndView openBoardList(CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/i_board/i_boardList");
   	System.out.println(commandMap.getMap());
    	return mv;
    }
	 
	@RequestMapping(value="/i_board/selectBoardList.do")
	public ModelAndView selectBoardList(CommandMap commandMap) throws Exception{
	    ModelAndView mv = new ModelAndView("jsonView");
	    System.out.println(commandMap.getMap());
	    List<Map<String,Object>> list = boardService.selectBoardList(commandMap.getMap());
	    mv.addObject("list", list);
	    if(list.size() > 0){
	        mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
	    }
	    else{
	        mv.addObject("TOTAL", 0);
	    }
	     
	    return mv;
	}
	
	@RequestMapping(value="/i_board/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/i_board/i_boardWrite");
		System.out.println(commandMap.getMap());
		return mv;
	}

	@RequestMapping(value="/i_board/openBoardReply.do")
	public ModelAndView openBoardReply(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/i_board/i_boardReply");
		
		Map<String,Object> map = boardService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		
		return mv;
	}
	
	@RequestMapping(value="/i_board/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/i_board/openBoardList.do");

		boardService.insertBoard(commandMap.getMap(), request);
		
		return mv;
	}

	@RequestMapping(value="/i_board/replyinsertBoard.do")
	public ModelAndView replyBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/i_board/openBoardList.do");

		boardService.replyprocessBoard(commandMap.getMap(), request);
		
		return mv;
	}
	
	@RequestMapping(value="/i_board/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/i_board/i_boardDetail");
		System.out.println(commandMap.getMap());
		Map<String,Object> map = boardService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/i_board/openBoardUpdate.do")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("/i_board/i_boardUpdate");
		
		Map<String,Object> map = boardService.selectBoardDetail(commandMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/i_board/updateBoard.do")
	public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/i_board/openBoardDetail.do");
		
		boardService.updateBoard(commandMap.getMap(), request);
		
		mv.addObject("IDX", commandMap.get("IDX"));
		return mv;
	}
	
	@RequestMapping(value="/i_board/deleteBoard.do")
	public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/i_board/openBoardList.do");
		
		boardService.deleteBoard(commandMap.getMap());
		
		return mv;
	}

}