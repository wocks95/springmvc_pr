package com.min.app01.pkg01_setter;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    
    //GenericXmApplicationContext 클래스
    // XML로 생성된 bean을 관리하는 클래스

    AbstractApplicationContext ctx = new GenericXmlApplicationContext("pkg01_setter/app-context.xml");
    
    Contact c = ctx.getBean("contactBean", Contact.class); // Contact c = (Contact) ctx.getBean("contactBean(Bean의 id)");
    System.out.println(c.getMobile());
    System.out.println(c.getEmail());
    
    Person p = ctx.getBean("personBean", Person.class);
    System.out.println(p.getName());
    System.out.println(p.getContact().getMobile());
    System.out.println(p.getContact().getEmail());
    
    // scope 동작 확인
    Person p2 = ctx.getBean("personBean", Person.class);
    System.out.println(p == p2);
    // xml에서 singleton으로 scope를 작성하게 되면 같은 bean이 나오기 때문에 동일함 true
    // xml에서 prototype으로 scope를 작성하게 되면 생성할때마다 새로운 bean나오기 때문에 false 
    ctx.close();
    
    //Singleton pattern 확인 (클래스.메소드())
    
    /*
    Contact c1 = Contact.getInstance();
    Contact c2 = Contact.getInstance();
    System.out.println(c1);
    System.out.println(c2);
    System.out.println(c1 == c2);
    */
     
  }

}
