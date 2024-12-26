package com.min.app08.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.app08.dto.FileDto;


public interface IFileService {
  List<FileDto> getFileList();
  String uploadFile(MultipartHttpServletRequest multipartHttpServletRequest);
}
