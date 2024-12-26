package com.min.app02.ex;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * DI 방식 3 - Constructor 주입
 * Spring Container 에 있는 bean 을 Constructor 의 매개변수에 주입(전달)하는 방식입니다. 
 * 생성자에 @Autowired 를 한 번만 추가하면 됩니다.
 * Spring Framework 4.3 이후 @Autowired 는 생략할 수 있습니다.
 * 생성자 주입을 이용하면 필드에 final 키워드를 추가하여 좀 더 안전한 코드를 작성할 수 있습니다.
 * final 키워드가 추가된 필드의 초기화를 위한 생성자는 @AllArgsConstructor 가 아닙니다.
 * 필드에 final 키워드를 추가한 뒤 @RequiredArgsConstructor 를 사용합니다.
 */

@RequiredArgsConstructor // Spring Container 의 Adder / Subtractor / Multiplier / Divider 타입의 bean 이 생성자의 매개변수로 자동 주입됩니다.
@Component
@Getter
public class Calculator {
  
  private final Adder module1;
  private final Subtractor module2;
  private final Multiplier module3;
  private final Divider module4;
  
}
