package com.min.app01.ex02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  //5개 bean 생성
  @Bean
  public Adder adder() {
    Adder adder = new Adder();
    return adder;
  }
  @Bean
  public Subtractor subtractor() {
    Subtractor subtractor = new Subtractor();
    return subtractor;
  }
  @Bean
  public Multiplier multiplier() {
    Multiplier multiplier = new Multiplier();
    return multiplier;
  }
  @Bean
  public Divider divider() {
    Divider divider = new Divider();
    return divider;
  }
  @Bean
  public Calculator calculator() {
    Calculator calculator = new Calculator();
    calculator.setBrand("샤프");
    calculator.setModule1(adder());
    calculator.setModule2(subtractor());
    calculator.setModule3(multiplier());
    calculator.setModule4(divider());
    return calculator;
  }
  
  
  
}
