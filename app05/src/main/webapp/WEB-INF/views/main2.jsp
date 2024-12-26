<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewpoint" content="width=device-width, initial-scale=1.0">
<title>Welcome</title>
</head>
<body>

  <c:if test="${not empty sessionScope.loginUser}">
    <%-- 로그인이 성공한 화면 --%>
    <%-- 세션에 저장된 사용자 정보를 보여주고, 로그아웃 버튼을 보여줍니다. --%>
    <div>
      ${sessionScope.loginUser.id}님 환영합니다.
      <button type="button" id="btn-logout">로그아웃</button>  
    </div>
  </c:if>
  
  <c:if test="${empty sessionScope.loginUser}">
    <%-- 로그인 폼 --%>
    <form action="${contextPath}/user/login.do" method="post">
      <input type="text" name="id"><br/>
      <input type="password" name="pw"><br/>
      <button type="submit">로그인</button>
    </form>
  </c:if> 
  
  <script>
  
    const btnLogout = document.getElementById('btn-logout');
    btnLogout.addEventListener('click', (event) => {
      location.href = '${contextPath}/user/logout.do';
      
      
    })
  </script>
  

</body>
</html>