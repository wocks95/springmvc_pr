<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewpoint" content="width=device-width, initial-scale=1.0">
<title>Notice Write</title>
</head>
<body>

  <h1>Notice Write</h1>
  
  <div><!-- <form> 태그의 method와 enctype은 필수 작성이다. -->
    <form action="${contextPath}/notice/regist.do" method="post" enctype="multipart/form-data">
      <input type="text" name="noticeTitle" placeholder="제목"><br/>
      <textarea rows="5" cols="30" name="noticeContents" placeholder="내용"></textarea><br/>
      <input type="file" name="files" id="files" multiple><br/>
      <button type="submit">작성완료</button>
    </form>
  </div>
  
  <script>
    function attachCheck() {
      const files = document.getElementById('files'); // document.getElementById('files') >> 배열, 정확히는 files의 property가 배열이다.
      
      // 개별 파일 크기
      const limitPerSize = 1024 * 1024 * 10;
      
      // 전체 파일 크기
      const limitTotalSize = 1024 * 1024 * 100;
      
      // 전체 파일 크기를 저장할 변수
      let totalSize = 0;
      
      // 첨부 이벤트
      files.addEventListener('change', (event) => {
        
        for(const file of event.currentTarget.files) { // files 객체(event.currentTarget)의 files 프로퍼티
          
          if(file.size > limitPersize) {
            alert('각 첨부 파일의 크기는 최대 10MB입니다.');
            event.currentTarget.value = '';
            return; // 이벤트 핸들러 취소
          }
            
          totalSize += file.size;
          
          if(totalSize > limitTotalSize) {
            alert('전체 첨부 파일의 크기는 최대 100MB입니다.');
            event.currentTarget.value = '';
            RETURN;
          }
          
        }
      })
    }
    attachCheck();
  </script>
 
</body>
</html>