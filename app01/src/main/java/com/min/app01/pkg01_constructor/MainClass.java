package com.min.app01.pkg01_constructor;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

  public static void main(String[] args) {

    //GenericXmApplicationContext 클래스
    // XML로 생성된 bean을 관리하는 클래스
    
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("pkg01_constructor/appCtx.xml");
    
    Contact c = ctx.getBean("contactBean", Contact.class);
    System.out.println(c.getEmail());
    System.out.println(c.getMoblie());
    
    Person p = ctx.getBean("personBean", Person.class);
    System.out.println(p.getName());
    System.out.println(p.getContact().getMoblie());
    System.out.println(p.getContact().getEmail());
    
    ctx.close();
  }

}
