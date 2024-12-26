package com.min.app12.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.min.app12.dto.UserDto;

public interface IUserService {
  String signup(UserDto userDto);
  
  boolean login(HttpServletRequest request);
  
  void logout(HttpSession session);
}
