package com.min.app02.pkg02_setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.min.app02.domain.Address;
import com.min.app02.domain.Contact;
import lombok.ToString;

/*
 * DI 방식 2 - Setter(형식의 메소드) 주입
 * Spring Container 에 있는 bean 을 Setter 형식의 메소드의 매개변수에 주입(전달)하는 방식입니다. 
 * Setter 형식의 메소드에 @Autowired 를 한 번만 추가하면 됩니다.
 */
@Component // Spring Container 에 Person 타입의 이름이 person인 bean 을 만들어둡니다.
@ToString
public class Person {

  //field
  private Address address;
  private Contact contact;
  
  //setter 형식의 메소드
  
  @Autowired // 매개변수에 선언된 Address 타입과 Contact 타입의 bean 이 매개변수에 자동으로 주입됩니다.
  public void setBeans(Address address, Contact contact) {
    this.address = address;
    this.contact = contact;
  }
  
  
  /* 실제 setter 에 작업 가능하지만 이렇게 작업할 필요는 없습니다.
  @Autowired
  public void setAddress(Address address) {
    this.address = address;
  }
  
  @Autowired
  public void setContact(Contact contact) {
    this.contact = contact;
  }
  */
  
  
  
}
