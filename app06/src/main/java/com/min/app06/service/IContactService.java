package com.min.app06.service;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.min.app06.dto.ContactDto;

public interface IContactService {
  //목록 서비스
  Map<String, Object> getAllContact(); 
  
  //상세 조회 서비스
  ContactDto getContact(int contact_id);
  
  //등록,수정,삭제 서비스 (request 데이터를 받고 String 타입으로 반환 하기)
  String register(HttpServletRequest request);
  String modify(HttpServletRequest request);
  String remove(HttpServletRequest request);
}
