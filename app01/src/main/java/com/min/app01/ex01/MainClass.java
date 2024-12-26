package com.min.app01.ex01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
  
  public static void main(String[] args) {
    // GenericXmlApplicationContext
    
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("ex01/appContact.xml");
    
    Calculator calculator1 = ctx.getBean("Calc1", Calculator.class);
    System.out.println(calculator1.getBrand());
    System.out.println(calculator1.getModule1().add(1,2,3,4,5));
    System.out.println(calculator1.getModule2().subtract(5, 10));
    System.out.println(calculator1.getModule3().multiplier(1,2,3,4,5));
    System.out.println(calculator1.getModule4().divide(5, 2));
   
    Calculator calculator2 = ctx.getBean("Calc2", Calculator.class);
    System.out.println(calculator2.getBrand());
    System.out.println(calculator2.getModule1().add(1,2,3,4,5));
    System.out.println(calculator2.getModule2().subtract(5, 10));
    System.out.println(calculator2.getModule3().multiplier(1,2,3,4,5));
    System.out.println(calculator2.getModule4().divide(5, 2));
    
    ctx.close();
  }
}
