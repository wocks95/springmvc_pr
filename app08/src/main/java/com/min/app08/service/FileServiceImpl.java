package com.min.app08.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import com.min.app08.dao.IFileDao;
import com.min.app08.dto.FileDto;
import com.min.app08.util.FileUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements IFileService {

  private final IFileDao fileDao;
  private final FileUtil fileUtil;
  
  @Override
  public List<FileDto> getFileList() {
    
    return fileDao.selectFileList();
  }

  @Override
  public String uploadFile(MultipartHttpServletRequest multipartRequest) {
    
    // 일반 요청 파라미터
    String writer = multipartRequest.getParameter("writer");
    
    // DB로 보낼 FileDto 객체
    FileDto fileDto = new FileDto();
    fileDto.setWriter(writer);
    
    // 첨부 파일 파라미터
    MultipartFile multipartFile = multipartRequest.getFile("file");
    
    // 첨부 파일이 존재하는지 확인
    if(!multipartFile.isEmpty()) {
      
      // 첨부 파일의 원래 이름
      String originalFilename = multipartFile.getOriginalFilename();
      
      // 첨부 파일의 저장 이름
      String filesystemName = fileUtil.getFilesystemName(originalFilename);
      
      // 첨부 파일의 저장 경로
      String filePath = fileUtil.getFilePath();
      File dir = new File(filePath);
      if(!dir.exists())
        dir.mkdirs();
      
      // 첨부 파일을 HDD에 저장하기
      try {
        multipartFile.transferTo(new File(dir, filesystemName)); // (저장할 경로 , 저장할 이름)
      } catch (Exception e) {
        e.printStackTrace();
      }
      // DB로 보낼 FileDto에 파일 정보 추가하기
      fileDto.setFilePath(filePath);
      fileDto.setOriginalFilename(originalFilename);
      fileDto.setFilesystemName(filesystemName);
    }
    
    // DB로 FileDto 보내서 저장한 뒤, 결과 메시지 반환하기
    return fileDao.insertFile(fileDto) == 1 ? "삽입 성공" : "삽입 실패";
    
  }

}
