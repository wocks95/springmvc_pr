package com.min.app02.pkg03_constructor;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.min.app02.domain.Address;
import com.min.app02.domain.Contact;

// import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/*
 * DI 방식 3 - Constructor 주입
 * Spring Container 에 있는 bean 을 Constructor 의 매개변수에 주입(전달)하는 방식입니다. 
 * 생성자에 @Autowired 를 한 번만 추가하면 됩니다.
 * Spring Framework 4.3 이후 @Autowired 는 생략할 수 있습니다.
 * 생성자 주입을 이용하면 필드에 final 키워드를 추가하여 좀 더 안전한 코드를 작성할 수 있습니다.
 * final 키워드가 추가된 필드의 초기화를 위한 생성자는 @AllArgsConstructor 가 아닙니다.
 * 필드에 final 키워드를 추가한 뒤 @RequiredArgsConstructor 를 사용합니다.
 */
@Component

// @AllArgsConstructor // Person (Address, Contact)
@RequiredArgsConstructor //Person(Address, Contact) + @NonNull(값이 왔는지 안왔는지 체크)

@ToString
public class Person {

  // field
  private final Address address;
  private final Contact contact;
  
  // constructor 
  // ★★★@Autowired : 생성자의 @Autowired 생략이 가능하다.
  /*
    public Person(Address address, Contact contact) {
    this.address = address;
    this.contact = contact;
  } */
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
