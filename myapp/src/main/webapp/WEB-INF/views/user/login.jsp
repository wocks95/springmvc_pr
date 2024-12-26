<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewpoint" content="width=device-width, initial-scale=1.0">
<title>Login</title>
</head>
<body>
  <h1>로그인 화면</h1>
  
  <form action="${contextPath}/user/login.do" method="post">
    <input type="hidden" name="url" value="${url}">
    <input type="text" name="userEmail" placeholder="이메일"><br/>
    <input type="password" name="userPw" placeholder="비밀번호"><br/>
    <button type="submit">로그인</button>
  </form>
  
  <script>
    function displayMsg() {
      const msg ='${msg}';
      if(msg !=='')
        alert(msg);
    }
    
    displayMsg();
 
  </script>
  
</body>
</html>