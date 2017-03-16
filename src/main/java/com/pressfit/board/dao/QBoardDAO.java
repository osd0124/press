package com.pressfit.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class QBoardDAO extends AbstractDAO{

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> selectBoardList(Map<String,Object> map) throws Exception{
		return (List<Map<String,Object>>) selectPagingList("qboard.selectBoardList",map);
	}
	
	public void insertBoard(Map<String, Object> map) throws Exception{
		insert("qboard.insertBoard", map);
	}
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception {
		return (Map<String,Object>) selectOne("qboard.selectBoardDetail",map);
	}
	
	public void updateHitCnt(Map<String,Object> map ) throws Exception{
		update("qboard.updateHitCnt", map);
	}
	
	public void insertFile(Map<String, Object> map) throws Exception{
		insert("common.insertFile", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFileList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("common.selectFileList", map);
	}

	public void deleteFileList(Map<String, Object> map) throws Exception{
		update("common.deleteFileList", map);
	}

	public void updateFile(Map<String, Object> map) throws Exception{
		update("common.updateFile", map);
	}
	public void updateBoard(Map<String, Object> map) throws Exception{
		update("qboard.updateBoard", map);
	}
}
