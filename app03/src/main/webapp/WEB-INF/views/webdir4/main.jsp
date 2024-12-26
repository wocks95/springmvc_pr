<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewpoint" content="Width=device-width, initial-scale=1.0">
<title>form submit</title>
</head>
<body>
 
  <%-- 요청 만들기 2 : <form> 태그의 submit (GET, POST 방식을 선택할 수 있습니다. --%>
  <%-- 
    action :  요청 주소 작성합니다.(서버 주소)
    method
      1) 요청 메소드 작성합니다.
      2) get, post 방식을 사용합니다.
      3) 디톨프는 get방식입니다.
      4) get 방식으로 처리하면 Query String 방식의 요청으로 처리됩니다.
      5) get 방식으로 처리하면 요청 주소에 요청 파라미터가 노출됩니다. (빠르게 동작합니다.)
      6) post 방식으로 처리하면 요청 주소에 요청 파라미터가 노출되지 않습니다.
         (보안에 좋습니다. 대신 약간 느립니다.)
    <%=자바 변수를 불러내는 표현법%>
   --%>
    <form action="<%=request.getContextPath()%>/webdir4/req1" method="get">
        <%-- 입력 상자(text, number, email, url, password, date) --%>
        <%-- name :  백엔드에서 사용, id : 프론트엔드에서 사용 --%>
      <input type="text" name="a"><br/>
      <input type="number" name="b"><br/>
      <input type="email" name="c"><br/>
      <input type="url" name="d"><br/>
      <input type="password" name="e"><br/>
      <input type="date" name="f"><br/>
    
      <%-- 서브밋(action에 작성된 주소로 모든 입력 요소들을 보냅니다.) --%>
      <button type="submit">submit</button>
    </form>
    
    <hr/>
    
    <form action="<%=request.getContextPath()%>/webdir4/req2" method="get">
    
      <%-- 모든 선택 상자는 선택했을경우 디폴트 value = "on"을 가집니다. --%>
    
    
      <%-- 선택 상자 : 다중 선택이 가능한 checkbox (같은 name 으로 보내기) -디폴트 value = "on"을 사용할 수 없습니다. --%>
      <input type="checkbox" name="flowers" id="rose" value="rose">
      <label for="rose">장미</label>
      <input type="checkbox" name="flowers" id="tulip" value="tulip">
      <label for="tulip">튤립</label>
      <br/>
      
      <%-- 선택 상자 : 다중 선택이 가능한 checkbox (다른 name 으로 보내기) -디폴트 value="on"을 사용할 수 있습니다. --%>
       <input type="checkbox" name="kbs" id="kbs">
       <label for="kbs">kbs</label>
       <input type="checkbox" name="mbc" id="mbc">
       <label for="mbc">mbc</label>
       <input type="checkbox" name="sbs" id="sbs">
       <label for="sbs">sbs</label>
       <br/>
       
      <%-- 선택 상자 : 단일 선택만 가능한 radio -디폴트 value="on"을 사용할 수 없습니다. --%>
        <input type="radio" name="choice" id="yes" value="y">
        <label for="yes">yes</label>
        <input type="radio" name="choice" id="no" value="n">
        <label for="no">no</label>
        <br/>
        
       <%-- 서브밋 (action에 작성된 주소로 모든 입력 요소들을 보냅니다.) --%>
       <button type="submit">submit</button>
    
    </form>
  
    <form action="<%=request.getContextPath()%>/webdir4/req3" method="get">
      
      <%-- 모든 목록 요소는 <option> 태그의 내부 텍스트(textContent)가 value 로 전달됩니다. --%>
      <%-- <option> 태그에 value 속성을 추가하면 value 속성 값이 전달됩니다. --%>
      
      <%-- 목록 요소 : <select> 태그와 <option> 태그 --%>
      <select name="city">
        <option>seoul</option>
        <option>jeju</option>
      </select>
      <br/>
      
      <%-- 콤보 요소 : <input> 태그와 <datalist> 태그 --%>
      <input type="text" name="domain" list="domain-list">
      <datalist id="domain-list">
        <option value="naver">naver.com</option>
        <option value="kakao">kakao.com</option>
      </datalist>
      <br/>
      
      <%-- 다중 라인 입력 요소 : <textarea> 태그 --%>
      <textarea name="content"></textarea>
      <br/>
      
      <%-- 서브밋 (action에 작성된 주소로 모든 입력 요소들을 보냅니다.) --%>
      <button type="submit">submit</button>
    </form>
  




</body>
</html>