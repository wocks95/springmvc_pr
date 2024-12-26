<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewpoint" content="width=device-width, initial-scale=1.0">
<title>Contact List</title>
</head>
<body>
  
  <h1>Contact List</h1>
  <div>전체 연락처 : ${count}개</div>
  
  <div>
    <button type="button" onclick="fnWriteForm()">등록</button>
  </div>
  
  <%-- c:forEach는 반복문이기때문에 안에 id를 사용하면 안된다. --%>
  <%-- 리스트의 인덱스가 필요한 경우 varStatus 태그 속성을 만들고, index 값을 꺼냅니다. --%>
  <c:forEach var="contact" items="${contacts}">
    <div id= "contact${vs.index}" class="contact">
      <a href="${contextPath}/contact/detail.do?contact_id=${contact.contact_id}">${contact.contact_id} : ${contact.last_name}</a>
    </div>
  </c:forEach>
 
 <script>
 
   function fnWriteForm() {
     location.href = '${contextPath}/contact/write.do';
   } 
 
   const msg = '${msg}';
   if(msg !== '') {
     alert(msg);
   }
   
 
 </script>
 
 
 
  
</body>
</html>