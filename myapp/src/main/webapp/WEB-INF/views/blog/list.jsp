<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="블로그"/>
</jsp:include>

<style>
  .blogs:hover {
    cursor: pointer;
    background-color: lightblue;
  }
</style>

  <h1>Blog List</h1>

  <div>
    <button type="button" id="btn-write">새 블로그 작성하기</button>
  </div>

  <div>
    <a href="${contextPath}/blog/list.do?page=1&sort=DESC">최신순</a> | 
    <a href="${contextPath}/blog/list.do?page=1&sort=ASC">과거순</a>
  </div>

  <div>전체 블로그 ${total}개</div>

  <table border="1">
    <thead>
      <tr>
        <td>번호</td>
        <td>제목</td>
        <td>조회수</td>
        <td>작성일자</td>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="blog" items="${blogList}" varStatus="vs">
        <tr class="blogs" data-user-email="${blog.user_email}" data-blog-id="${blog.blog_id}">
          <td>${offset + vs.count}</td>
          <td>${blog.title}</td>
          <td>${blog.hit}</td>
          <td><fmt:formatDate pattern="yyyy-MM-dd" value="${blog.create_dt}"/></td>
        </tr>
      </c:forEach>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="4">${paging}</td>
      </tr>
    </tfoot>
  </table>
  
  <script>
    function toBlogWrite() {      
      document.getElementById('btn-write').addEventListener('click', (event) => {
        location.href = '${contextPath}/blog/write.do';
      })
    }
    function toBlogDetail() {
      const blogs = document.getElementsByClassName('blogs');
      for(const blog of blogs) {
        blog.addEventListener('click', (event) => {
          if('${sessionScope.loginUser.userEmail}' === event.currentTarget.dataset.userEmail) {            
            location.href = '${contextPath}/blog/detail.do?blog_id=' + event.currentTarget.dataset.blogId;  // data- 뒷 부분을 저장할 때 대시(-)는 camel case 로 변환되어 저장됩니다. 
          } else {            
            location.href = '${contextPath}/blog/increseHit.do?blog_id=' + event.currentTarget.dataset.blogId;  // data- 뒷 부분을 저장할 때 대시(-)는 camel case 로 변환되어 저장됩니다. 
          }
        })
      }
    }
    toBlogWrite();
    toBlogDetail();
  </script>

</div>

</body>
</html>