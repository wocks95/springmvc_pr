package com.min.app05.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.app05.vo.UserVo;

// @Controller
public class MvcController1 {
  
  @RequestMapping(value="/")
  public String main() {
    return "main";
  }
  
  @RequestMapping(value="/user/login.do", method=RequestMethod.POST)
  public String login(
      HttpServletRequest request //모든 요청 정보(요청 파라미터, 세션)를 관리하는 인터페이스
      ) { // Session 영역은 request 영역에서 추출하게 된다!! 따라서 먼저 request를 주입 받고 session을 추출한다.
     // 요청 파라미터 받습니다. (서버 측에 저장함)
     String id = request.getParameter("id");
     String pw = request.getParameter("pw");
     
     // 세션 꺼냅니다. (세션은 서버 측에 존재하는 저장소입니다.)
     HttpSession session = request.getSession();
     
     // 세션 아이디를 확인해 봅니다. (브라우저의 쿠키 : F12-Application-Cookies 에서 JSESSIONID 쿠키로 사용자에게 전달되어 저장됩니다.)
     System.out.println(session.getId());
     
     // 세션 유효시간을 설정해 봅니다. (초 단위로 설정합니다. 시간이 지나면 새로운 세션을 발급 받습니다.)
     // 세션의 기본 유효시간은 1800초 입니다. (1800초 == 30분)
     
     // session.setMaxInactiveInterval(5); // 5초 후에 소멸되는 세션
     
     // id 와 pw 를 이용해서 Person 인스턴스를 만듭니다.
     UserVo userVo = new UserVo();
     userVo.setId(id);
     userVo.setPw(pw);
     
     // 로그인 처리 시나리오
     // id 와 pw 가 동일하면 로그인 시켜주기
     // 로그인 할 때 사용자 정보가 담긴 UserVo 인스턴스를 세션에 저장합니다.
     if(id.equals(pw)) {
       
       // 세션에 저장할 때도 속성(Attribute)으로 저장합니다.
       session.setAttribute("userVo", userVo);
     }
     
     // 로그인이 완료된 이후 다시 main.jsp로 이동합니다.
     return "redirect:/";    // return "main"; 도 가능함   
  }
  
  
  @RequestMapping(value="/user/logout.do")
  public String logout(
      HttpSession session // 스프링에서는 세션이 필요하면 매개변수로 선언하고 사용하면 됩니다.
      ) {
    
    // 방법1. 세션에 저장된 userVo 속성 제거하기
     session.removeAttribute("userVo");
    
    // 방법2. 세션을 초기화하기 (이 방법을 추천!)
    session.invalidate();
    
    // welcome 화면으로 이동합니다.
    return "redirect:/";
  }
  
  
}
