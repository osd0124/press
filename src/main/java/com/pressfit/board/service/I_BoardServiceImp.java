package com.pressfit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pressfit.board.dao.I_BoardDAO;
import com.pressfit.common.util.FileUtils;

@Service("i_boardService")
public class I_BoardServiceImp implements BoardService{
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Autowired
	private I_BoardDAO i_boardDAO;
	
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		return i_boardDAO.selectBoardList(map);
		
	}

	@Override
	public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		i_boardDAO.insertBoard(map);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for(int i=0, size=list.size(); i<size; i++){
			i_boardDAO.insertFile(list.get(i));
		}
	}
	
	@Override
	public void replyprocessBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
		i_boardDAO.replyprocessBoard(map);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request);
		for(int i=0, size=list.size(); i<size; i++){
			i_boardDAO.insertFile(list.get(i));
		}
	}

	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		i_boardDAO.updateHitCnt(map);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = i_boardDAO.selectBoardDetail(map);
		resultMap.put("map", tempMap);
		
		List<Map<String,Object>> list = i_boardDAO.selectFileList(map);
		resultMap.put("list", list);
		
		return resultMap;
	}

	@Override
	public void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception{
		i_boardDAO.updateBoard(map);
		
		i_boardDAO.deleteFileList(map);
		List<Map<String,Object>> list = fileUtils.parseUpdateFileInfo(map, request);
		Map<String,Object> tempMap = null;
		for(int i=0, size=list.size(); i<size; i++){
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")){
				i_boardDAO.insertFile(tempMap);
			}
			else{
				i_boardDAO.updateFile(tempMap);
			}
		}
	}

	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		i_boardDAO.deleteBoard(map);
	}

}

	
	

