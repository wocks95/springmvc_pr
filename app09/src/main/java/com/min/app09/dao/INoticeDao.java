package com.min.app09.dao;

import java.util.List;


import com.min.app09.dto.AttachDto;
import com.min.app09.dto.NoticeDto;



public interface INoticeDao {

  List<NoticeDto> selectNoticeList();
  NoticeDto selectNoticeById(int noticeId); // 상세보기에서 쓰임, 나의 서비스에서 두개의 Dao를 호출하게 됨
  List<AttachDto> selectAttachListByNoticeId(int noticeId); //상세보기에서 쓰임, 하나의 서비스에서 두개의 Dao를 호출하게 됨  
  AttachDto selectAttachById(int attachId);
  int insertNotice(NoticeDto noticeDto);
  int insertAttach(AttachDto attachDto);
  int deleteNotice(int noticeId); //삭제할때는 Id를 전달함, 공지에서 삭제되면 첨부도 삭제되는 코드를 mysql에서 작성했기 때문에 Attach는 작성 안함
  int updateAttachDownloadCount(int attachId);

}
