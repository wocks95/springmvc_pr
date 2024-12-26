package com.min.myapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations= {
    "file:src/main/webapp/WEB-INF/spring/root-context.xml"
  , "file:src/mian/wedapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class MyAppDaoTests {

  @Autowired
  private SqlSessionTemplate template;
  
  @Test
  void 빈_생성_테스트() {
    Assertions.assertNotNull(template);
  }
  
}
