package com.min.myapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component //스프링 컨테이너의 bean으로 만들었습니다.
public class FileUtil {

  // field : 현재 날짜
  private LocalDate today = LocalDate.now();
  
  /**
   * 파일 업로드 경로를 반환하는 메소드
   * @return 현재 날짜를 경로로 사용. 예를 들어 2024-12-12일에 실행하는 경우 반환되는 경로는 "/upload/2024/12/12" 입니다.
   *         경로 구분자는 슬래시(/)를 사용합니다. (linux, mac, windows 모두 사용 가능)
   */
  public String getFilePath() {
    return "/upload" + DateTimeFormatter.ofPattern("/yyyy/MM/dd").format(today);
  }
  
  /**
   * 프로필 이미지 경로를 반환하는 메소드
   * @return 현재 날짜를 경로로 사용. 예를 들어 2024-12-12일에 실행하는 경우 반환되는 경로는 "/upload/2024/12/12" 입니다.
   *         경로 구분자는 슬래시(/)를 사용합니다. (linux, mac, windows 모두 사용 가능)
   */
  public String getProfilePath() {
    return "/profile" + DateTimeFormatter.ofPattern("/yyyy/MM/dd").format(today);    
  }
  
  /**
   * 파일 저장 이름을 반환하는 메소드
   * @param 파일의 원래 이름
   * @return 파일의 저장 이름. 중복 방지를 위해서 난수 처리된 이름을 사용(UUID : 중복이 없는 랜덤 데이터). 파일의 원래 확장자
   자를 그대로 사용.
   */
  public String getFilesystemName(String originalFilename) {
    String extensionName = "";
    if(originalFilename.endsWith(".tar.gz"))
      extensionName = ".tar.gz";
    else
      extensionName = originalFilename.substring(originalFilename.lastIndexOf("."));
    return UUID.randomUUID().toString() + extensionName;
  }
  
}
