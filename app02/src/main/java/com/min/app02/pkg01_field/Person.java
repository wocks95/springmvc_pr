package com.min.app02.pkg01_field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.min.app02.domain.Address;
import com.min.app02.domain.Contact;
import lombok.ToString;

/*
 * DI 방식 1 - 필드 주입
 * Spring Container 에 있는 bean 을 필드에 주입(전달)하는 방식입니다. 
 * 필드 마다 @Autowired 를 추가하면 됩니다.
 */
@Component //Spring Container 에 Person 이라는 이름의 bean 이 만들어집니다.

@ToString // person 타입의  bean 을 System.out.println()으로 곧바로 확인할 수 있습니다.
public class Person {
  
  //field
  @Autowired // Spring Container 에서 타입이 Address 인 bean 을 가져옵니다.
  private Address address;
  
  @Autowired // Spring Container 에서 타입이 Contact 인 bean 을 가져옵니다.
  private Contact contact;
  
}
