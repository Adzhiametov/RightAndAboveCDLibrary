<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>

    <jsp:attribute name="headerTitle">
            <h1>Upload</h1>
    </jsp:attribute>

    <jsp:body>

        <h3>Please select a file to upload !</h3>
        </br>

        <form:form method="post" enctype="multipart/form-data"
                   modelAttribute="uploadedFile" action="fileUpload">
            <table class="center-block">
                <tr>
                    <td width="50%">
                        <input type="file" name="file"/>
                    </td>
                    <td width="50%" style="color: red; font-style: italic; text-align: right">
                        <form:errors path="file"/>
                    </td>
                </tr>

                <tr>
                    <td width="50%"><input type="submit" class="btn btn-primary" value="Upload"/></td>
                    <td width="50%"></td>
                </tr>
            </table>

        </form:form>

    </jsp:body>
</t:genericpage>

