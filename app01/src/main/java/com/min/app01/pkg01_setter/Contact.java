package com.min.app01.pkg01_setter;

import lombok.Getter;
import lombok.Setter;

 @Getter
 @Setter
public class Contact {
  private String mobile;
  private String email;
  
  
  /* singleton pattern : Contact 인스턴스를 하나만 사용하는 패턴 */
  
  /*
  private static Contact contact = new Contact();
  // private은 내부에서만 호출가능하고 외부에서는 호출이 안됨.
  private Contact() { 
  }
  public static Contact getInstance() {
    return contact;
  }
  */
  
  
  
}
