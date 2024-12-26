package com.min.app11.dao;

import java.util.List;
import java.util.Map;

import com.min.app11.dto.BoardDto;

public interface IBoardDao {
  List<BoardDto> selectBoardList(String sort);
  int selectBoardCount();
  BoardDto selectBoardById(int boardId);
  List<BoardDto> selectBoardSearchList(Map<String, Object> map);
  List<BoardDto> selectBoardPeriodList(Map<String, Object> map);
  List<BoardDto> selectBoardIntegeratedSearch(Map<String, Object> map);
  int selectBoardIntegeratedSearchCount(Map<String, Object> map);
  int insertBoard(BoardDto boardDto);
  int updateBoard(BoardDto boardDto);
  int deleteBoard(int boardId);
  int deleteSelectedBoard(String[] numbers); 
}
