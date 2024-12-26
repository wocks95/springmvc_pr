package com.min.app02.ex;

import org.springframework.stereotype.Component;

@Component // Spring Container 에 Subtractor 타입의 bean 을 만들어둡니다.
public class Subtractor {
  public int subtract(int a, int b) {
    return a >= b ? a - b : b - a;
  }
}
