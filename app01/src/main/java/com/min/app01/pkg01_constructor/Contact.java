package com.min.app01.pkg01_constructor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor // 모든 매개변수를 받아서 생성자를 만들어줌
@Getter
public class Contact {
  private String moblie;
  private String email;
  
}
