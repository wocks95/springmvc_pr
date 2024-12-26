package com.min.app13.dao;

import java.util.List;
import java.util.Map;

import com.min.app13.dto.BbsDto;

public interface IBbsDao {
  int insertBbs(BbsDto bbsDto);
  List<BbsDto> selectBbsList(Map<String, Object> map);
  int selectBbsCount();
  int updateGroupOrder(BbsDto bbsDto);
  int insertBbsReply(BbsDto bbsDto);
  int deleteBbs(int bbsId);
}
