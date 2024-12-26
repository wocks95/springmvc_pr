package com.min.app04.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.app04.vo.Person;

@Controller
public class MvcController2 {

  private List<Person> people = new ArrayList<Person>();
  
  @RequestMapping(value="/redirect/main.do")
  public String main() {
    return "webdir2/main";
  }
  // Insert, UpDate, Delete 로 DB가 수정되었을때 redirect 를 사용함
  @RequestMapping(value="/redirect/insert.do", method=RequestMethod.POST)
  public String insert(
      Person person // 사용자가 입력한 정보가 커맨드 객체 Person person 에 저장됩니다.
     ,Model model                           // Model에 저장된 속성(Attribute)은 forward 할 때만 전달됩니다. redirect할 때는 전달되지 않습니다.
     ,RedirectAttributes redirectAttributes // RedirectAttrivutes에 저장된 플래시 속성(Flash Attribute)은 redirect 할 때 전달됩니다.
      ) {
    
    //사용자가 입력한 정보를 List에 저장합니다.
    boolean result = people.add(person);
    
    // 입력 성공 유무를 Model에 속성(Attribute)으로 저장해봅니다. (사실 쓸데없는 일입니다.)
    model.addAttribute("msg1", result ? "등록 성공" : "등록 실패");
    
    // 입력 성공 유무를 RedirectAttribute에 플래시 속성(Flash Attribute)으로 저장해 봅니다. (이게 동작합니다.)
    redirectAttributes.addFlashAttribute("msg2", result ? "등록 성공" : "등록 실패");
    
    return "redirect:/redirect/list.do"; //people 목록을 보여주는 요청을 수행합니다.(주소로 이동합니다.)
  }
  
  @RequestMapping(value="/redirect/list.do")
  public String list(Model model) {
    model.addAttribute("people", people);
    return "webdir2/list"; //forward (Model에 저장한 people 속성을 list.jsp로 전달합니다.)
  }
}
