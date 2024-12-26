package com.min.app12.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.min.app12.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository
public class UserDaoImpl implements IUserDao {

  private final SqlSessionTemplate template;
  
  // 아래코드는 lombok에서 "@Slf4j" Annotation으로 지원합니다.
  // private final static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
  
  @Override
  public int insertUser(UserDto userDto) {
    log.debug(userDto.toString()); // 인자 값은 %msg로 표시됩니다.
    // userMapper.xml에서 "namespace + insertUser"
    return template.insert("mybatis.mappers.userMapper.insertUser", userDto);
  }

  @Override
  public UserDto selectUserByMap(Map<String, Object> map) {
    log.debug(map.toString());
    return template.selectOne("mybatis.mappers.userMapper.selectUserByMap", map);
  }
  
}
