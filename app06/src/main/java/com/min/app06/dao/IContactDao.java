package com.min.app06.dao;

import java.util.List;

import com.min.app06.dto.ContactDto;
// DAO : Database  Access Object
public interface IContactDao {               // 행(Row)은 ContactDto 객체에 저장합니다.
  List<ContactDto> getContactList();         // 모든 행(Row)을 반환합니다.
  ContactDto getContactById(int contact_id); // PK인 contact_id을 쿼리에 전달해 해당 행(Row)을 반환한다.
  int getContactCount();                     // 모든 행(Row)의 개수를 반환합니다.
  int register(ContactDto contactDto);       // 등록할 정보를 ContactDto 객체로 만들어서 쿼리에 전달하고 등록된 행(Row)의 개수를 반환합니다.
  int modify(ContactDto contactDto);         // 수정할 정보를 ContactDto 객체로 만들어서 쿼리에 전달하고 수정된 행(Row)의 개수를 반환합니다.
  int remove(int contact_id);                // 삭제할 행의 PK인 contact_id를 쿼리에 전달하고 삭제된 행(Row)의 개수를 반환합니다.
  
  //같은 패키지 안에 ContactDaoImpl.class를 생성할 때 참조를 눌러서 'IcontactDao.java'라고 써져있는 인터페이스를 참조해서 만들어야 한다.
}
