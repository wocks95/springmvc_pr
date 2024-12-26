package com.min.app01.ex03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
  
  public static void main(String[] args) {
    // GenericXmlApplicationContext
    
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext("com.min.app01.ex03");
    
    Calculator calculator1 = ctx.getBean("calculator", Calculator.class);
    System.out.println(calculator1.getBrand());
    System.out.println(calculator1.getModule1().add(1,2,3,4,5));
    System.out.println(calculator1.getModule2().subtract(5, 10));
    System.out.println(calculator1.getModule3().multiplier(1,2,3,4,5));
    System.out.println(calculator1.getModule4().divide(5, 2));
   
    ctx.close();
  }
}
