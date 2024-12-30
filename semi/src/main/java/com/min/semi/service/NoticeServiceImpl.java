package com.min.semi.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.semi.dao.INoticeDao;
import com.min.semi.dto.AttachDto;
import com.min.semi.dto.NoticeDto;
import com.min.semi.util.FileUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements INoticeService {

  private final INoticeDao noticeDao;
  private final FileUtil fileUtil;
  
  
  @Override
  public List<NoticeDto> getNoticeList() {
    return noticeDao.selectNoticeList();
  }

  @Override
  public String registNotice(MultipartHttpServletRequest mutlipartRequest) {
    String noticeTitle = mutlipartRequest.getParameter("noticeTitle");
    String noticeContents = mutlipartRequest.getParameter("noticeContents");
    
    NoticeDto noticeDto = NoticeDto.builder()
                              .noticeTitle(noticeTitle)
                              .noticeContents(noticeContents)
                              .build();
    
    int result = noticeDao.insertNotice(noticeDto);
    if(result == 0)
       return "공지사항 등록 실패";
    // ------여기까지 왔다는건 공지사항 등록 성공했다는 의미이다.------
    
    List<MultipartFile> files = mutlipartRequest.getFiles("files");
    for(MultipartFile multipartFile : files) {
      // 첨부 파일 존재 여부 확인
      if(!multipartFile.isEmpty()) { 
        String originalFilename = multipartFile.getOriginalFilename(); // 첨부 파일의 원래 이름
        String filesystemName = fileUtil.getFilesystemName(originalFilename); // 첨부 파일의 저장 이름
        
        String filePath = fileUtil.getFilePath(); // 첨부 파일의 저장 경로
        File dir = new File(filePath);
        if(!dir.exists())
          dir.mkdirs();
        
        try {
          multipartFile.transferTo(new File(dir, filesystemName));
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        AttachDto attachDto = AttachDto.builder()
                                  .noticeId(noticeDto.getNoticeId())
                                  .filePath(filePath)
                                  .originalFilename(originalFilename)
                                  .filesystemName(filesystemName)
                                  .build();
        int attachResult = noticeDao.insertAttach(attachDto);
        if(attachResult == 0)
          return "첨부 파일 등록 실패";
      }
    }
    return "공지사항 등록 성공";
  }

  @Override
  public Map<String, Object> getNoticeById(int noticeId) {
    return Map.of("n", noticeDao.selectNoticeById(noticeId)
                , "attachList"  , noticeDao.selectAttachListByNoticeId(noticeId));
  }

  @Override
  public String removeNotice(int noticeId) {
    for(AttachDto attachDto : noticeDao.selectAttachListByNoticeId(noticeId)) {
      File file = new File(attachDto.getFilePath(), attachDto.getFilesystemName());
      if(file.exists())
        file.delete(); 
    }
    int result = noticeDao.deleteNotice(noticeId);
    return result == 1 ? "공지사항 삭제 성공" : "공지사항 삭제 실패";
  }

  @Override
  public ResponseEntity<Resource> download(int attachId, String userAgent) {
    AttachDto attachDto = noticeDao.selectAttachById(attachId);
    
    Resource resource = new FileSystemResource(new File(attachDto.getFilePath(), attachDto.getFilesystemName()));
    
    if(! resource.exists())
      return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
    
    String originalFilename = attachDto.getOriginalFilename();
    try {
      if(userAgent.contains("Edg")) {
        originalFilename  = URLEncoder.encode(originalFilename, "UTF-8");
      }
      else {
        originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
      } 
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    HttpHeaders responseHeader = new HttpHeaders();
    
    try {
      responseHeader.add("content-Disposition", "attachment; filename=" + originalFilename);
      responseHeader.add("Content_Type", "application/octet-stream");
      responseHeader.add("Content-Length", resource.contentLength() + "");  
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    noticeDao.updateAttachDownloadCount(attachId);
    
    
    return new ResponseEntity<Resource>(resource, responseHeader, HttpStatus.OK);
  }

}
