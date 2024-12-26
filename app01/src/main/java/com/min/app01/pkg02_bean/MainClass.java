package com.min.app01.pkg02_bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    
    // AnnotationConfigApplicationContext 클래스
    // Java Annotation(@Configuration, @Bean) 을 이용해 생성된 bean 관리
    
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);  
                                  // new AnnotationConfigApplicationContext("com.min.app01.pkg02_bean"); << 데이터가 많을 경우
    
    Contact contact = ctx.getBean("contact", Contact.class);
    System.out.println(contact.getMobile());
    System.out.println(contact.getEmail());
    
    Person person = ctx.getBean("person", Person.class);
    System.out.println(person.getName());
    System.out.println(person.getContact().getMobile());
    System.out.println(person.getContact().getEmail());
    
  }

}
