package com.min.semi.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.semi.dto.NoticeDto;

public interface INoticeService {
  List<NoticeDto> getNoticeList();
  String registNotice(MultipartHttpServletRequest mutlipartRequest);
  Map<String, Object> getNoticeById(int noticeId);
  String removeNotice(int noticeId);
  ResponseEntity<Resource> download(int attachId, String userAgent);
  Map<String, Object> getSearchList(HttpServletRequest request);
}
