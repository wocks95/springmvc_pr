<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewpoint" content="width=device-width, initial-scale=1.0">
<title>Contact Detail</title>
</head>
<body>

  <h1>${contact.last_name} 연락처</h1>
  
  <form id="form-detail"  method="post">
   <div>
      <label for="contact_id">CONTACT ID</label>
      <input type="text" name="contact_id" id="contact_id" value="${contact.contact_id}" readonly>
    </div>
    <div>
      <label for="last_name">LAST NAME</label>
      <input type="text" name="last_name" id="last_name" value="${contact.last_name}">
    </div>
    <div>
      <label for="first_name">FIRST NAME</label>
      <input type="text" name="first_name" id="first_name" value="${contact.first_name}">
    </div>
    <div>
      <label for="email">EMAIL</label>
      <input type="text" name="email" id="email" value="${contact.email}">
    </div>
    <div>
      <label for="mobile">MOBILE</label>
      <input type="text" name="mobile" id="mobile" value="${contact.mobile}">
    </div>
    <div>
      <button type="button" id="btn-modify">수정 완료</button> <%-- submit은 action에 의해 작동됨 --%>
      <button type="button" id="btn-remove">연락처 삭제</button>
      <button type="reset">수정 취소</button>
      <button type="button" id="btn-confirm">확인 완료</button>
    </div>
  </form>
  
  <script>
  
    // form
    const formDetail = document.getElementById('form-detail');
  
    // 수정 완료 버튼을 클릭하면 수정하기 요청을 합니다.
     document.getElementById('btn-modify').addEventListener('click', (event) => {
      formDetail.action = '${contextPath}/contact/modify.do';
      formDetail.submit();
    })

    // 연락처 삭제 버튼을 클릭하면 삭제하기 요청을 합니다.
    document.getElementById('btn-remove').addEventListener('click', (event) => {
      if(confirm('해당 연락처를 삭제할까요?')) { // confirm : 확인창
      formDetail.action = '${contextPath}/contact/remove.do';
      formDetail.submit();        
      }
    })
    
    // 확인 완료 버튼을 클릭하면 연락처 목록으로 이동합니다.
    document.getElementById('btn-confirm').addEventListener('click', (event) => {
      location.href = '${contextPath}/contact/list.do';
    })
  
  </script>
  
  
  
  
</body>
</html>