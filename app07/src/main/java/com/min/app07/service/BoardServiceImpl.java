package com.min.app07.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.min.app07.dao.IBoardDao;
import com.min.app07.dto.BoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 필드로 선언된 IBoardDao boardDao에 자동으로 bean을 주입하기 위한 생성자입니다.
@Service // 서비스에서 사용하는 @Component입니다. Spring Container에 IBoardService 타입의 bean이 생성됩니다.
public class BoardServiceImpl implements IBoardService {

  private final IBoardDao boardDao;
  

  @Override
  public Map<String, Object> getBoardList(HttpServletRequest request) {
    // 요청 파라미터(sort가 전달되지 않으면 DESC를 사용합니다.)
    Optional<String> opt = Optional.ofNullable(request.getParameter("sort"));
    String sort = opt.orElse("DESC");
    
    // 목록 가져오기
    List<BoardDto> boardList = boardDao.selectBoardList(sort);
    
    // 전체 목록의 개수 가져오기
    int boardCount = boardDao.selectBoardCount();
    
    // 목록과 전체 목록의 개수를 Map으로 반환하기
    return Map.of("boardList", boardList, "boardCount", boardCount);
  }
  
  @Override
  public BoardDto getBoardById(int boardId) {
    
    // 상세정보를 받아와서 컨트롤러로 반환합니다.
    return boardDao.selectBoardById(boardId);
  }

  @Override
  public String modifyBoard(BoardDto boardDto) {
    
    // 수정한 뒤 수정 결과를 텍스트로 반환합니다.
    
    return boardDao.updateBoard(boardDto) == 1 ? "수정 성공" : "수정 실패";
  }
  
  @Override
  public String removeBoard(int boardId) {
    // 삭제한 뒤 삭제 결과를 텍스트로 반환합니다.  
    
    return boardDao.deleteBoard(boardId) == 1 ? "삭제 성공" : "삭제 실패";
  }
 
  @Override
  public String removeBoardList(String[] numbers) {
    
    // 삭제한 뒤 삭제 결과를 텍스트로 반환합니다.
    return boardDao.deleteSelectedBoard(numbers) == numbers.length ? "선택 삭제 성공" : "선택 삭제 실패";
  }
  
  @Override
  public String registerBoard(BoardDto boardDto) {
    
    // 삽입한 뒤 삭제 결과를 텍스트로 반환합니다.
    return boardDao.insertBoard(boardDto) == 1 ? "삽입 성공" : "삽입 실패";
  }
  
  @Override
  public Map<String, Object> getSearchList(HttpServletRequest request) {
    
    // 요청 파라미터를 Map으로 만듭니다.
    Map<String, Object> param = Map.of("title", request.getParameter("title")
                                     , "usrEmail", request.getParameter("usrEmail")
                                     , "usrName", request.getParameter("usrName")
                                     , "beginDt", request.getParameter("beginDt")
                                     , "endDt", request.getParameter("endDt"));
    // 검색 결과 목록을 가져옵니다.
    List<BoardDto> boardList = boardDao.selectBoardIntegeratedSearch(param);
    
    // 검색 결과 개수를 가져옵니다.
    int boardCount = boardDao.selectBoardIntegeratedSearchCount(param);
    
    // 검색 결과 목록과 검색 결과 개수를 Map으로 반환합니다.
    return Map.of("boardList", boardList, "boardCount", boardCount);
  }
  
}
