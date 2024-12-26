package com.min.app01.pkg03_component;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component // 이 Contact 클래스는 이름이 contact인  bean 으로 만들어집니다.
           // 디폴트형식의 생성자를 사용함
           // @AllArgsConstructor 사용하게 되면 디폴트형식의 생성자가 없게됩니다.
           // 그래서 @NoArgsConstructor 의 디폴트형식의 생성자를 만들면 됩니다.
@NoArgsConstructor  // Contact()
@AllArgsConstructor // Contact(String, String)
@Getter
public class Contact {
  private String mobile = "010-1111-1111";
  private String email = "user@main.com";
  
}
