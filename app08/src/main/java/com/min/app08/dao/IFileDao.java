package com.min.app08.dao;

import java.util.List;

import com.min.app08.dto.FileDto;

public interface IFileDao {
  List<FileDto> selectFileList();
  int insertFile(FileDto fileDto); 
}
