package com.min.app02.ex;

import org.springframework.stereotype.Component;

@Component // Spring Container 에 Divider 타입의 bean 을 만들어둡니다.
public class Divider {
  
 public double divide(int a, int b) {
    return a >= b ? (double)a / b : (double)b / a;
  }

}
