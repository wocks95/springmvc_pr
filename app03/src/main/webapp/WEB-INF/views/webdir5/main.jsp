<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewpoint" content="Width=device-width, initial-scale=1.0">
<title>Location</title>
  <script>
    window.onload = () => { //window 객체는 항상 생략할 수 있습니다. onload = () => {}로 작성해도 됩니다.
      const btn1 = document.getElementById("btn1"); // 나를 보내기 연습
      
      btn1.addEventListener('click', (event) => {
        //location 객체의 href 속성을 이용해서 요청할 수 있습니다. 
        
        // Java 변수는 JavaScript 내부에서 사용할 수 있습니다. (하지만 반대는 안 됩니다.)
        const contextpath = '<%=request.getContextPath()%>';
        //클릭한 요소(envent.currentTarget)의 내부 텍스트(textContent)
         location.href = contextpath + '/webdir5/req1?param=' + event.currentTarget.textContent; 
        
        //JSP 에서는 템플릿 문자열(Template Strings)을 사용할 때 \${}를 사용합니다.
        // location.href = '\${contextpath}/webdir5/req1?param=\${event.currentTarget.textContent}';
      });
      
      const btn2 = document.getElementById('btn2'); // 나를 보내기 연습
      
      btn2.addEventListener('click', (event)=> {
        
        // data- 속성(Attribute)의 값은 dataset 속성(Property)에 저장됩니다.
        const contextPath = '<%=request.getContextPath()%>';
        location.href = `\${contextPath}/webdir5/req2?param=\${event.currentTarget.dataset.id}`;   
      }); 
       
      const btnDo = document.getElementsByClassName('btn-do'); //형제 보내기 연습
      for(const btn of btnDo) {
        btn.addEventListener('click', (event) => {
          //Node : Element, Text 등을 지칭하는 상위 개념의 DOM 객체입니다.
          //Element : 한 마디로 태그(HTMLElement)를 의미하는 Node 의 하위 개념 DOM 객체입니다.
          
          // 같은 레벨의 요소를 '형제(Sibling)관계'라고 합니다.
          // 이전 형제 요소(Element)를 previousElementSibling 이라고 하고, 다음 형제 요소를 nextSibling 이라고 합니다.
          
          //value 속성(Attribute=태그)은 value 속성(Property=자바 변수)과 같습니다.
          
          const contextPath = '<%=request.getContextPath()%>';
          // console.log(event.currentTarget.nextElementSibling);
          location.href = `\${contextPath}/webdir5/req3?param=\${event.currentTarget.nextSibling.value}`; 
        });
      }
      
      const btns = document.getElementsByClassName('btns');
      for(const btn of btns) {
        
        btn.addEventListener('click', (event) => {
                    
          //부모 노드(Node)    : parentNode
          //부모 요소(Element) : parentElement
          
          const contextPath = '<%=request.getContextPath()%>';
          location.href = `\${contextPath}/webdir5/req4?param=\${event.currentTarget.parentElement.dataset.no}`;
          
        });
      }
     
  } // end onload
  
    function fnMove(anchor) { // anchor 는 클릭한 <a> 태그 요소를 의미합니다.
      alert('이동합니다.')
      location.href = anchor.dataset.url;
    }    
  </script>
  
</head>
<body>
  
  <%-- 요청 만들기 3 : JavaScript 의 Location 객체 활용하기(<a> 태그와 동일한 요청입니다. --%>  
    
  <button type="button" id="btn1">요청1</button>  
  <br/>
  
  <button type="button" id="btn2" data-id="1">요청2</button>
  <br/>
  
  <%-- 형제 보내기 --%>
  <button type="button" class="btn-do">요청Do1</button>
  <input type="hidden" value="1"><br/>
  <button type="button" class="btn-do">요청Do2</button>
  <input type="hidden" value="2"><br/>
  
  
  <%-- 클릭한 버튼의 데이터가 가지고 있는 부모의 값 --%>
  <div data-no="1">
    <button type="button" class="btns">요청하기1</button>
  </div>
  <div data-no="2">
    <button type="button" class="btns">요청하기2</button>
  </div>
  
  <%-- <a> 태그를 클릭하면 JavaScript 의 fnMove()함수가 요청됩니다. --%>
  <%-- this : 클릭한 요소를 의미합니다. 어떤 <a> 태그를 클릭했는지 함수에 인자로 전달합니다.--%>
  <a onclick="fnMove(this)" data-url="https://www.naver.com">네이버</a><br/>
  <a onclick="fnMove(this)" data-url="https://www.kakao.com">카카오</a>
  
    
</body>
</html>