package com.min.app11;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.min.app11.dao.IBoardDao;
import com.min.app11.service.IBoardService;

@SpringJUnitConfig(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                               "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
class BoardServiceTxTest {

  @Autowired
  private IBoardService boardService;
  
  @Autowired
  private IBoardDao boardDao;
  
  @Test
  void 트랜잭션_동작테스트() {
    boardService.txTest();
    
  }
  
  @Test
  void 트랜잭션_확인테스트() {
    assertEquals(3, boardDao.selectBoardCount());
  
  }
  
}
