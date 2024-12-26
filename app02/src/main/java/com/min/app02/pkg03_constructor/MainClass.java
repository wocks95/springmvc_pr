package com.min.app02.pkg03_constructor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext("com.min.app02.pkg03_constructor", "com.min.app02.domain"); 
    
    Person person = ctx.getBean("person", Person.class);
    System.out.println(person);
    ctx.close();
    
    
  }

}
