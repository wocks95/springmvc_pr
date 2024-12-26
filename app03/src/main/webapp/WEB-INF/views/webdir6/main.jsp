<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewpoint" content="Width=device-width, initial-scale=1.0">
<title>path Variable</title>
</head>
<body>
  <a href="<%=request.getContextPath()%>/webdir6/123">경로변수1</a>
  <br/>
  <a href="<%=request.getContextPath()%>/webdir6/sort/ASC/page/1">경로변수2</a>
  <br/>
  
</body>
</html>