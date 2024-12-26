package com.min.app11.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BoardDto {
  private int boardId;
  private String title;
  private String contents;
  private Timestamp createDt;
  // private int usrId;
  private UserDto userDto; 
  
  
}
