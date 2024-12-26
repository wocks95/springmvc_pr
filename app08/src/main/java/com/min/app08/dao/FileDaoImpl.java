package com.min.app08.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.min.app08.dto.FileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class FileDaoImpl implements IFileDao {

  private final SqlSessionTemplate template;
  
  @Override
  public List<FileDto> selectFileList() {
    return template.selectList("mybatis.mappers.fileMapper.selectFileList");
  }

  @Override
  public int insertFile(FileDto fileDto) {
    return template.insert("mybatis.mappers.fileMapper.insertFile", fileDto);
  }

}
