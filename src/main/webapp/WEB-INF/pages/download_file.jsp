<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>

    <jsp:attribute name="headerTitle">
            <h1>Download</h1>
    </jsp:attribute>

    <jsp:body>
        <div class=text-center>
            <h2><a href="<c:url value="/download"/>">Click here to download file</a></h2>
        </div>
    </jsp:body>
</t:genericpage>