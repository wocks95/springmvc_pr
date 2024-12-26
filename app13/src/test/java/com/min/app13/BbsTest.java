package com.min.app13;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.min.app13.dao.IBbsDao;


@SpringJUnitConfig(locations= {
                            "file:src/main/webapp/WEB-INF/spring/root-context.xml"
                          , "file:src/mian/wedapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BbsTest {
  
  @Autowired
  private IBbsDao bbsDao;
  
  @Test
  void 원글_삽입_테스트() {
    bbsDao.insertBbs(null);
  }
  
  
}
