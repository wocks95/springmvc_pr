package com.min.app09.dao;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.min.app09.dto.AttachDto;
import com.min.app09.dto.NoticeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // Service에서 가져다 쓰라고 붙여둔거다.
public class NoticeDaoImpl implements INoticeDao {

  private final SqlSessionTemplate template;
  
  @Override
  public List<NoticeDto> selectNoticeList() { // 전체보기
    
    return template.selectList("mybatis.mappers.noticeMapper.selectNoticeList");
  }

  @Override
  public NoticeDto selectNoticeById(int noticeId) { // 상세보기
    
    return template.selectOne("mybatis.mappers.noticeMapper.selectNoticeById", noticeId);
  }

  @Override
  public List<AttachDto> selectAttachListByNoticeId(int noticeId) {
    
    return template.selectList("mybatis.mappers.noticeMapper.selectAttachListByNoticeId", noticeId);
  }

  @Override
  public AttachDto selectAttachById(int attachId) {
    
    return template.selectOne("mybatis.mappers.noticeMapper.selectAttachById", attachId);
  }

  
  @Override
  public int insertNotice(NoticeDto noticeDto) {
    
    return template.insert("mybatis.mappers.noticeMapper.insertNotice", noticeDto);
  }

  @Override
  public int insertAttach(AttachDto attachDto) {
   
    return template.insert("mybatis.mappers.noticeMapper.insertAttach", attachDto);
  }

  @Override
  public int deleteNotice(int noticeId) {
    
    return template.delete("mybatis.mappers.noticeMapper.deleteNotice", noticeId);
  }

  @Override
  public int updateAttachDownloadCount(int attachId) {

    return template.update("mybatis.mappers.noticeMapper.updateAttachDownloadCount", attachId);
  }
}
