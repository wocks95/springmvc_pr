<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewpoint" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${contextPath}/resources/css/list.css?dt=<%=System.currentTimeMillis()%>"> 
<!-- 주소의 특징인 캐싱은 기억력은 좋으나 새로 업데이트할때 과거의 기록만 기억하기에 새로고침하기 어려움 
      그래서 ?dt=<'%=System.currentTimeMillis()%'> 를 입력하여 캐싱을 방지함-->
<title>Single File Upload</title>
</head>
<body>

  <img src="${contextPath}/resources/images/logo_coupang.png" width="200px">
  
  <hr/>

  <div class="form-wrap">
    <h1>File Upload</h1>
    <!-- 파일 업로드를 위한 form 태그는 method 속성과 enctype 속성이 정해져 있습니다. -->
    <form action="${contextPath}/single/upload.do" method="post" enctype="multipart/form-data">
      <input type="text" name="writer" placeholder="작성자"><br/>
      <input type="file" name="file" id="file" accept="image/*"><br/>
      <button type="submit">제출</button>
    </form>
  </div>
  
  <hr/>
  
  <div>
    <h1>Upload File List</h1>
    <c:forEach items="${fileList}" var="f">
      <div class="file">
      <img src="${contextPath}${f.filePath}/${f.filesystemName}" width= "50px">
          작성자 : ${f.writer} | 파일명 : ${f.originalFilename} | 저장명 : ${f.filePath}/${f.filesystemName}
      </div>
    </c:forEach>
  </div>
  
  <script>
    
    const msg = '${msg}';
    if(msg !== '')
      alert(msg);
    
    document.getElementById('file').addEventListener('change', (event) => {
      const limit = 1024 * 1024 * 10;
      const size = event.currentTarget.files[0].size;
      if(size > limit) {
        alert('첨부 파일의 크기는 최대 10MB까지만 가능합니다.');
        event.currentTarget.value = '';
      }
    })  
    
  </script>
  

</body>
</html>