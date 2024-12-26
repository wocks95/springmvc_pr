package com.min.app06.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//DTO : Data Transfer Object. 데이터 전송 객체.
// 주로 Java 와 DB 간 데이터 교환을 위해서 사용하는 객체를 의미합니다.



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ContactDto {
  private int contact_id;
  private String last_name;
  private String first_name;
  private String email;
  private String mobile;
  private Date create_dt;
}
