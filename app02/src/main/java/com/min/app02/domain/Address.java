package com.min.app02.domain;

import org.springframework.stereotype.Component;

import lombok.ToString;

@Component //Spring Container 에 address 라는 이름의 bean 이 만들어집니다.
@ToString //toString() 메소드 오버라이드
public class Address {
  private String postcode = "14404";
  private String roadAddr = "서울특별시 강남구 강남대로";
}
