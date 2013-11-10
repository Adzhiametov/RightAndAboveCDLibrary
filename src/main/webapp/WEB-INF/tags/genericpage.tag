<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="headerTitle" fragment="true" %>

<html>
<head>
    <title>CDLib</title>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet" type="text/css"/>
    <script src="<c:url value="/resources/js/bootstrap.js"/>"type="text/javascript"></script>
    <link href="<c:url value='/resources/css/style.css' />" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container">

    <!--Header section-->
    <div class="header">
        <ul class="nav nav-tabs" id="myTab">
            <li><a href="<c:url value="/"/>" data-toggle="tab">Upload</a></li>
            <li><a href="<c:url value="/show/first"/>" data-toggle="tab">Library</a></li>
            <li><a href="<c:url value="/gotodownload"/>" data-toggle="tab">Download</a></li>
        </ul>
    </div>

    <!--Content-->
    <div class="content">
        <jsp:doBody/>
    </div>

    <!--Footer section-->
    <div class="footer">
        <h5>&copy;2013 Arsen Adzhiametov</h5>
    </div>

</div>
</body>
</html>