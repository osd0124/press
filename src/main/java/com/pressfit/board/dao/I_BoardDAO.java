package com.pressfit.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("i_boardDAO")
public class I_BoardDAO extends AbstractDAO{
			
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectPagingList("i_board.selectBoardList", map);
	}

	public void insertBoard(Map<String, Object> map) throws Exception{
		insert("i_board.insertBoard", map);
	}
	
	public void replyprocessBoard(Map<String, Object> map) throws Exception{
		update("i_board.replyprocess", map);
		insert("i_board.insertBoard", map);
	}
	
	public void updateHitCnt(Map<String, Object> map) throws Exception{
		update("i_board.updateHitCnt", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("i_board.selectBoardDetail", map);
	}

	public void updateBoard(Map<String, Object> map) throws Exception{
		update("i_board.updateBoard", map);
	}

	public void deleteBoard(Map<String, Object> map) throws Exception{
		update("i_board.deleteBoard", map);
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

}
