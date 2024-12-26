package com.min.app02.ex;

import org.springframework.stereotype.Component;

@Component // Spring Container 에 Adder 타입의 bean 을 만들어둡니다.
public class Adder {
  public int add(int... args) {
    int total = 0;
    for(int i = 0; i <args.length; i++)
      total += args[i];
    return total;
  }
}
