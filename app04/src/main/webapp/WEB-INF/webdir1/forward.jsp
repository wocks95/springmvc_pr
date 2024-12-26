<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSTL 의 사용은 JSTL 라이브러리를 프로젝트에 포함해야만 가능합니다. 이 작업은 pom.xml 파일을 통해 이미 처리했습니다. --%>
<%-- 사용할 JSTL 라이브러리의 taglib directive 작업이 필요합니다. --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%-- Core  라이브러리에 속한 태그들은 c: 으로 시작합니다. --%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewpoint" content="Width=device-width, initial-scale=1.0">
<title>Forward 결과 확인</title>
</head>
<body>

  <%-- 요청 파라미터가 전달되었는지 확인하기 --%>
  <%-- 요청 파라미터는 id는 ${param.id} 방식으로 확인할 수 있습니다. --%>
  <div>${param.id}</div>
  
  <%-- 요청에 저장된 속성(Attribute) 확인하기 --%>
  <%-- 요청에 포함된 속성 email은 ${email} 방식으로 확인할 수 있습니다. --%>
  <div>${email}</div>
  <div>${address}</div>
  
  <%-- Map 확인하기 --%>
  <%-- Map 은 ${Map명.Key} 방식으로 확인할 수 있습니다. --%>
  <div>${contact.tel}</div>
  <div>${contact.mobile}</div>
  
  <%-- 인스턴스 확인하기 --%>
  <%-- 인스턴스는 ${인스턴스.필드}방식으로 확인할 수 있습니다. --%>
  <div>${person.name}</div> <%-- 실제로는 person.getName() 메소드를 호출합니다. --%>
  <div>${person.age}</div>  <%-- 실제로는 person.getAge() 메소드를 호출합니다. --%>
  
  <%-- List 확인하기 --%>
  <%-- 반복태그를 이용해서 확인 할 수 있습니다. 반복 태그는 JSTL 의 Core 라이브러리에 저장되어 있습니다.(화면 가장 위로 올라가 봅니다.) --%>
  <%-- items 에 List 가 ${list} 형태로 입력됩니다. --%>
  <%-- var 속성에는 List 의 각 요소를 저장할 속성(Attribute)을 입력합니다. --%>
  <c:forEach var="hobby" items="${hobbies}">
    <div>${hobby}</div>
  </c:forEach>
  
  <c:forEach var="person" items="${people}">
    <div>${person.name}</div>
    <div>${person.age}</div>
  </c:forEach>
  
  
</body>
</html>