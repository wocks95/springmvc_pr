<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<style> 


</style>
<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="공지사항"/>
</jsp:include>

  <h1>Notice List</h1>
  
  <div>
    <button type="button" id="btn-notice" > 새 공지사항 작성하기</button>
  </div>

  <div>
    <a href="${contextPath}/notice/search.form">검색</a>
  </div>

  <div>
    <select id="sort-column">
      <option value="notice_id">작성일자</option>
      <option value="notice_title">제목</option>
    </select>
    <button type="button" class="btn-sort" data-sort="ASC">오름차순</button>
    <button type="button" class="btn-sort" data-sort="DESC">내림차순</button>    
  </div>

  <div>전체 공지 ${total}개</div>
  
  <table border="1">
    <thead>
      <tr>
        <td>번호</td>
        <td>제목</td>
        <td>작성일자</td>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="n" items="${noticeList}"  varStatus="vs">
      <tr class="notices" data-notice-id="${n.noticeId}">
        <td>${offset + vs.count}</td>
        <td> ${n.noticeTitle}(${n.attachCount})</td>
        <td><fmt:formatDate value="${n.createdAt}" pattern="yyyy.MM.dd HH:mm:ss"/></td>
      </tr>
      </c:forEach>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="3">${paging}</td>
      </tr>
    </tfoot>
  </table>



  <script>
    function detailHandle() {
      const notices = document.getElementsByClassName('notices');
      for(const notice of notices) {
        notice.addEventListener('click', (event) => {
          location.href = '${contextPath}/notice/detail.do?noticeId=' + event.currentTarget.dataset.noticeId;          
        })
      }
    }
    function sortList() {
      const btnSort = document.getElementsByClassName('btn-sort');
      for(const btn of btnSort) {
        btn.addEventListener('click', (event) => {
          location.href = '${contextPath}/notice/list.do?page=1&sort=' + event.target.dataset.sort + '&column=' + document.getElementById('sort-column').value;
        })
      }
    }
    function selectOption() {
      // <select> 태그에서 선택한 <option> 태그를 그대로 유지시키는 기능
      const paramColumn = '${param.column}';
      if(paramColumn !== '')
        document.getElementById('sort-column').value = paramColumn;
    }
    
    function toNoticeWrite() {
      document.getElementById('btn-notice').addEventListener('click', (event) => {
        location.href = '${contextPath}/notice/write.do';
      })
    }
    
    detailHandle();
    sortList();
    selectOption();
    toNoticeWrite();
  </script>

</div>

</body>
</html>