package com.min.app04.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.min.app04.vo.Person;

@Controller
public class MvcController {
  /*
   * MvcController1에서 요청주소를 만드는 규칙
   * 1. Forward 는 중간 주소 /forward 를 사용합니다.
   * 2. 모든 요청 주소는 suffix=".do"값을 가집니다.
   */
  
  /*
   * Forward(전달)
   * 1. 요청을 그대로 전달하는 방식입니다.
   * 2. 요청에 포함된 요청 파라미터들도 함께 전달됩니다.
   * 3. Spring MVC Project의 기본 이동 방식입니다.
   * 4. SELECT(조회) 문의 결과를 전달할 때 사용합니다.
   */
  @RequestMapping(value="/forward/main.do")
  public String main() {
    return "webdir1/main"; //ViewResolver에 의해서 "WEB-INF/webdir1/main.jsp" 경로로 이동합니다.(forward 합니다.)
  
  }
  @RequestMapping(value="/forward/link.do")
  public String forward(
      HttpServletRequest request // 모든 요청을 처리하는 HtppServletRequest 인터페이스
     ,Model model                // Spring 에서 사용하는 속성(Attribute) 저장용 인터페이스 ★★★★★
  ) {
    
    // HttpServletRequest request 에 전달할 데이터를 속성(Attribute)의 형식으로 저장할 수 있습니다.
    request.setAttribute("email", "user@naver.com");  // (속성이름, 속성값)
    
    // Model model 에 전달할 데이터를 속성(Attribute)의 형식으로 저장할 수 있습니다. Spring은 Model 사용을 권장합니다.
    model.addAttribute("address", "서울특별시 강남구 강남대로");
    
    // ★★★Map 을 전달합니다.
    model.addAttribute("contact", Map.of("tel", "02-123-1234", "mobile", "010-1234-1234"));
    
    
    // ★★★Person 인스턴스를 전달합니다.
    Person person = new Person();
    person.setName("홍길동");
    person.setAge(30);
    model.addAttribute("person", person);
    
    // List<String> 를 전달합니다.
    model.addAttribute("hobbies", Arrays.asList("청소", "요리", "원예", "책"));
    
    // ★★★List<Person> 를 전달합니다.
    Person p1 = new Person(); p1.setName("유저1"); p1.setAge(20);
    Person p2 = new Person(); p2.setName("유저2"); p2.setAge(40);
    model.addAttribute("people", Arrays.asList(p1, p2));
    
    return "webdir1/forward"; // "/WEB-INF/webdir1/forward.jsp" 경로로 요청이 전달됩니다.
    
  }

}
