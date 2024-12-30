package com.min.semi.dao;

import java.util.List;
import java.util.Map;

import com.min.semi.dto.AttachDto;
import com.min.semi.dto.NoticeDto;

public interface INoticeDao {

  List<NoticeDto> selectNoticeList();
  NoticeDto selectNoticeById(int noticeId); //상세보기
  List<AttachDto>selectAttachListByNoticeId(int noticeId);
  AttachDto selectAttachById(int attachId);
  int insertNotice(NoticeDto noticeDto);
  int insertAttach(AttachDto attachDto);
  int deleteNotice(int noticeId);
  int updateAttachDownloadCount(int attachId);  
  List<NoticeDto> selectSearchList(Map<String, Object> map);
  int selectSearchCount(Map<String, Object> map);
}
