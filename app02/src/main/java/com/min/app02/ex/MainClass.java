package com.min.app02.ex;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


public class MainClass {
  
  public static void main(String[] args) {
    // GenericXmlApplicationContext
    
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext("com.min.app02.ex");

    Calculator calculator = ctx.getBean("calculator", Calculator.class);
    System.out.println(calculator.getModule1().add(1, 2, 3, 4, 5));
    System.out.println(calculator.getModule2().subtract(5, 10));
    System.out.println(calculator.getModule3().multiplier(1, 2, 3, 4, 5));
    System.out.println(calculator.getModule4().divide(5, 2));
    ctx.close();
  }
}
