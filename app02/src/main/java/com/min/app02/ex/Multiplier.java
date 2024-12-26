package com.min.app02.ex;

import org.springframework.stereotype.Component;

@Component // Spring Container 에 Multiplier 타입의 bean 을 만들어둡니다.
public class Multiplier {
  public int multiplier(int... args) {
    int product = 1;
    for(int i = 0; i < args.length; i++) {
      product *= args[i];
     
    }
    return product;
  }
}
