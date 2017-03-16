package com.pressfit.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pressfit.board.service.QBoardService;
import com.pressfit.common.CommandMap;

@Controller
public class QBoardController {

	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private QBoardService service;
	
	@RequestMapping(value="/qboard/openBoardList.do")
	public ModelAndView openBoardList(CommandMap commandMap) throws Exception{
		
		ModelAndView mav = new ModelAndView("/qboard/qboardList");
		System.out.println(commandMap.getMap());
		return mav;
	}
	
	@RequestMapping(value="/qboard/selectBoardList.do")
	public ModelAndView selectBoardList(CommandMap commandMap) throws Exception{
		
		ModelAndView mav = new ModelAndView("jsonView");
		System.out.println(commandMap.getMap());
		List<Map<String,Object>> list = service.selectBoardList(commandMap.getMap());
		mav.addObject("list", list);
		if(list.size() > 0){
			mav.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
		}
		else{
			mav.addObject("TOTAL", 0);
		}
		return mav;
	}
	
	@RequestMapping(value="/qboard/openBoardWrite.do")
	public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
	
		ModelAndView mav = new ModelAndView("/qboard/qboardWrite");
		System.out.println(commandMap.getMap());
		return mav;
	}
	
	@RequestMapping(value="/qboard/insertBoard.do")
	public ModelAndView insertBoard(CommandMap commandMap,HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/qboard/openBoardList.do");
		
		service.insertBoard(commandMap.getMap(), request);
		
		return mav;
	}
	
	@RequestMapping(value="/qboard/openBoardDetail.do")
	public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception{
		ModelAndView mav = new ModelAndView("/qboard/qboardDetail");
		
		Map<String,Object> map = service.selectBoardDetail(commandMap.getMap());
		
		mav.addObject("map",map);
		mav.addObject("list",map.get("list"));
		return mav;
	}
	@RequestMapping(value="/qboard/openBoardUpdate.do")
	public ModelAndView openBoardUpdate(CommandMap commandMap) throws Exception{
		ModelAndView mav = new ModelAndView("/qboard/qboardUpdate");
		
		Map<String,Object> map = service.selectBoardDetail(commandMap.getMap());
		mav.addObject("map",map);
		mav.addObject("list", map.get("list"));
		
		return mav;
	}
	
	@RequestMapping(value="/qboard/updateBoard.do")
	public ModelAndView updateBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("redirect:/qboard/openBoardDetail.do");
		
		service.updateBoard(commandMap.getMap(), request);
		
		mav.addObject("IDX", commandMap.get("IDX"));
		return mav;
	}
}
