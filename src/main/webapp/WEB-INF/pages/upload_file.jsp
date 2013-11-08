<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="jquery-1.2.6.min.js"></script>
    <title>Upload File</title>
</head>
<body>

<h3>Please select a file to upload !</h3>

<form:form method="post" enctype="multipart/form-data"
           modelAttribute="uploadedFile" action="fileUpload">
    <table>
        <tr>
            <td>Upload File: </td>
            <td><input type="file" name="file"/>
            </td>
            <td style="color: red; font-style: italic;">
                <form:errors path="file"/>
            </td>
        </tr>
        <tr>
            <td> </td>
            <td><input type="submit" value="Upload"/>
            </td>
            <td> </td>
        </tr>
    </table>

    <a href="<c:url value="show/first"/>">Show catalog</a>
    <a href="<c:url value="/gotodownload"/>">Download catalog</a>

</form:form>

</body>
</html>