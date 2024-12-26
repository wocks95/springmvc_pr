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

  
  <%-- 세션에 저장된 userVo 확인하기 --%>
  <%-- 세션에 저장된 속성(Attribute)은 sessionScope 라는 표현 언어(El, ${})의 내장 객체를 함께 사용할 수 있습니다. (사용하기를 추천) --%>
  <div>${sessionScope.userVo.id}</div>
  <hr/>
  
  
  <%-- 세션에 저장된 userVo 인스턴스가 있으면 로그인이 성공한 화면을 보여주고, 없으면 로그인 폼을 보여 줍니다. --%>
  
  <c:if test="${not empty sessionScope.userVo}">
    <%-- 로그인이 성공한 화면 --%>
    <%-- 세션에 저장된 사용자 정보를 보여주고, 로그아웃 버튼을 보여줍니다. --%>
    <div>
      ${sessionScope.userVo.id}님 환영합니다.
      <button type="button">로그아웃</button>  
    </div>
  </c:if>
  
  <c:if test="${empty sessionScope.userVo}">
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
      
      
    });
  </script>
  

</body>
</html>