package com.min.app01.ex01;

public class Multiplier {
  public int multiplier(int... args) {
    int product = 1;
    for(int i = 0; i < args.length; i++) {
      product *= args[i];
     
    }
    return product;
  }
}
