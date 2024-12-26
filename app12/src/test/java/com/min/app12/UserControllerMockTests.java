package com.min.app12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitWebConfig(locations= {
                            "file:src/main/webapp/WEB-INF/spring/root-context.xml"
                          , "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class UserControllerMockTests {
  
  // 통합 테스트를 수행하는 객체
  private MockMvc mockMvc;
  
  @BeforeEach // @Test 이전에 먼저 동작하는 메소드
  void setup(WebApplicationContext webApplicationContext) throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    
  }
  
  @Test
  void MockMvc_객체_생성_테스트() {
    Assertions.assertNotNull(mockMvc); // mockMvc 객체가  Not Null이면 통과
    log.debug("객체생성테스트");
  }
  @Test // 회원가입_테스트는 같은 값으로 여러번 불가함
  void 회원가입_테스트() throws Exception {
    
    // 요청을 생성하고 요청 결과를 받아옵니다.
    MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders
                              .post("/user/signup.do")
                              .param("userEmail", "test@naver.com")
                              .param("userPw", "test")
                              .param("userName", "테스트")
                          ).andReturn(); // 요청하고 결과까지 받아서 가지고 있으라는 뜻 
                              
    // RedirectAttributes의 FlashAttribute를 이용해 결과를 생성하면 getFlashMap() 메소드를 호출해서 확인합니다.
    log.debug(mvcResult.getFlashMap().toString()); // FlashAttribute 확인
    
    // Model의 Attributes를 이용해 결과를 생성하면 getModelAndView() 메소드와 getModelMap() 메소드를 호출해서 확인합니다.
    log.debug(mvcResult.getModelAndView().getModelMap().toString()); // Attribute 확인
                        
  }
  
  @Test
  void 로그인_테스트() throws Exception {
    MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders
                            .get("/user/login.do")
                            .param("userEmail", "test@naver.com")
                            .param("userPw", "test")
                          ).andReturn();
    
    log.debug(mvcResult.getFlashMap().toString());        
  }
  
  
  
  
  
  
  
  
}
