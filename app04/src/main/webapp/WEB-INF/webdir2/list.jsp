<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewpoint" content="width=device-width, initial-scale=1.0">
<title>Person List</title>
<script>
  
  // Model에 저장된 속성(Attribute) msg1 확인해 봅니다. 확인된다면 전달이 된 것이고, 아니라면 전달이 안 된 것입니다.
  const msg1 = '${msg1}';
  alert('msg1 : ' + msg1); //실패, 어떠한 메시지도 안왔음
  
  // RedirectAttributes에 저장된 플래쉬 속성(Flash Attribute) msg2 확인해 봅니다.
  const msg2 = '${msg2}';
  alert('msg2 : ' + msg2);
  
</script>
</head>
<body>
  
  <%-- 속성(Attribute)을 만드는 태그입니다. --%>
  <%-- var 속성 : 속성의 이름을 작성합니다. value 속성 : 속성의 값을 작성합니다. --%>
  <c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
  <div>
    <a href="${contextPath}/redirect/main.do"></a>
  </div>
  

  <%-- 속성 people 반복 태그로 출력하기 --%>
  <c:forEach var="person" items="${people}">
    <div>
      <div>${person.name}</div>
      <div>${person.age}</div>
    </div>  
  </c:forEach>
  
  
</body>
</html>