<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:forEach items="${listHolder.pageList}" var="cd">
<c:out value="${cd}"/><p>
    </c:forEach>

    ${message}

    <form:form method="get" action="/show/next">
    <input type="submit" value="next" <c:if test="${listHolder.isLastPage()}"><c:out value="disabled='disabled'"/></c:if>/>
    </form:form>

    <form:form method="get" action="/show/prev">
    <input type="submit" value="prev" <c:if test="${listHolder.isFirstPage()}"><c:out value="disabled='disabled'"/></c:if>/>
    </form:form>

    <a href="<c:url value="/gotodownload"/>">Download catalog</a>
    <a href="<c:url value="/"/>">Upload catalog</a>
</body>
</html>