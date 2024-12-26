package com.min.app11.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.min.app11.dao.IBoardDao;
import com.min.app11.dto.BoardDto;
import com.min.app11.dto.UserDto;

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
    
    // 삽입한 뒤 삽입 결과를 텍스트로 반환합니다.
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
  
  @Override
  public void txTest() {
    /*
     * 트랜잭션 처리가 필요한 경우
     * 
     * INSERT/UPDATE/DELETE 처리가 2개 이상으로 이루어지는 서비스입니다.
     * 모든 INSERT/UPDATE/DELETE를 성공시키거나, 모두 실패시킵니다.
     */
    
    // boardDto1 : 등록가능한 게시글 정보를 가지고 있습니다.
    BoardDto boardDto1 = BoardDto.builder()
                         .title("테스트제목1")
                         .contents("테스트콘텐츠1")
                         .userDto(UserDto.builder().usrId(1).build())
                         .build();
    
    // boardDto2 : 등록할 수 없는 게시글 정보를 가지고 있습니다.
    BoardDto boardDto2 = BoardDto.builder()
                         .title("테스트제목2")
                         .contents("테스트콘텐츠2")
                         .userDto(UserDto.builder().usrId(10).build())
                         .build();
    
    // DB에 등록하기
    boardDao.insertBoard(boardDto1); // 등록 성공
    boardDao.insertBoard(boardDto2); // 등록 실패
    
    // 확인해야 하는 정보
    // 둘 중 하나라도 실패하면 직전에 성공한것도 취소시켜야 정상이다.
    // boardDto1과 boardDto2가 모두 등록 실패하면 트랜잭션 매니저가 정상적으로 동작한 것입니다.
    
  }
  
}
