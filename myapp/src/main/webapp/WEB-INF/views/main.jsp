<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<jsp:include page="./layout/header.jsp">
  <jsp:param name="title" value="Welcome"/>
</jsp:include>
  
  <img src="${contextPath}/assets/images/animal2.jpg">

</div>

</body>
</html>