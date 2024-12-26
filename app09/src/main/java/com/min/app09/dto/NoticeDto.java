package com.min.app09.dto;

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
public class NoticeDto {
  private int noticeId;
  private String noticeTitle;
  private String noticeContents;
  private Timestamp createdAt;
  private int attachCount;
}
