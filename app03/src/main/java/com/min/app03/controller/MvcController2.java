package com.min.app03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// "/standard" 로 시작하는 모든 요청 주소는 이 컨트롤러가 처리합니다.
@RequestMapping(value="/standard") // 대표 주소

@Controller
public class MvcController2 {
  
  @RequestMapping(value="/update") // http://localhost:8080/app03/standard/update 
  public String method1() {
    return "webdir2/update";
  }
  
}
