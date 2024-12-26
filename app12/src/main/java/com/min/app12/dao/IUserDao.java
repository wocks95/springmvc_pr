package com.min.app12.dao;

import java.util.Map;

import com.min.app12.dto.UserDto;

public interface IUserDao {

  int insertUser(UserDto userDto);
  
  UserDto selectUserByMap(Map<String, Object> map);  
  
}
