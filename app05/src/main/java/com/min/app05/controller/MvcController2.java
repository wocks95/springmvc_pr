package com.min.app05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.min.app05.vo.UserVo;

@SessionAttributes(names ={"loginUser"}) // Model에 loginUser 속성이 저장되면, HttpSession 에 loginUser 속성으로 함께 저장하세요.
                                         // @SessionAttributes Annotation으로 저장된 속성은 SessionStatus 인터페이스의 setComplete() 메소드를 호출해서 삭제할 수 있습니다.
                                         // HttpSession 인터페이스의 invalidate() 메소드는 동작하지 않습니다.(삭제할때)
@Controller
public class MvcController2 {
  
  @RequestMapping(value="/")
  public String main() {
    return "main2";
  }
  @RequestMapping(value="/user/login.do", method=RequestMethod.POST)
  public String login(
      
      Model model,  // 로그인이 성공하면 Model에 loginUser 속성을 저장하기 위해 사용합니다.
      
      @ModelAttribute(name="user") // Model에 저장할 속성의 이름으로 user로 바꾸기 위해서 사용합니다.
      
      UserVo user // 커맨드 객체는 자동으로 Model에 속성으로 저장됩니다.
                  // 클래스명을 CamelCase로 바꾼 이름으로 저장됩니다.
                  // model.addAttribute("userVo", user);
      ) {
    
    // 로그인 처리하기(id와 pw가 동일하면 로그인 성공으로 처리합니다.)
    if(user.getId().equals(user.getPw())) {
      model.addAttribute("loginUser", user);
    }
    
    return"redirect:/";
  }
  
  @RequestMapping(value="/user/logout.do")
  public String logout(
      
      SessionStatus sessionStatus // @SessionAttributes로 저장한 속성을 삭제하기 위해서 사용합니다.
  ) {
    
    sessionStatus.setComplete(); // 세션을 완료 처리합니다. (세션을 지운다는 의미입니다.)
    
    return "redirect:/";
  }
  
}
