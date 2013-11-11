<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:genericpage>

    <jsp:attribute name="headerTitle">
            <h1>Upload</h1>
    </jsp:attribute>

    <jsp:body>

        <script language="JavaScript">
            function Validate() {
                var input = document.getElementById("file");
                var file = input.files[0];
                var fileSize = file.size;
                if (fileSize > 5000000) {
                    document.getElementById("err").innerHTML = <spring:message code='file.size.must.be.less.then.10.mb'/>
                            document.getElementById("file").focus();
                    return false;
                }
                var fileName = document.getElementById("file").value;
                if (fileName != '') {
                    var checkFileExt = fileName.toLowerCase();
                    if (!checkFileExt.match(/(\.xml|\.txt)$/)) {
                        document.getElementById("err").innerHTML = <spring:message code='file.extension.must.be.xml.or.txt'/>;
                        document.getElementById("file").focus();
                        return false;
                    }
                }
                return true;
            }
        </script>

        <h3>Please select a file to upload !</h3>
        </br>

        <form:form method="post" enctype="multipart/form-data"
                   modelAttribute="uploadedFile" action="fileUpload" onsubmit="return Validate();">
            <table class="center-block">
                <tr>
                    <td width="50%">
                        <input type="file" name="file" id="file"/>
                    </td>
                    <td width="50%" style="color: red; font-style: italic; text-align: right">
                        <form:errors path="file"/>
                        <div id="err">${error}</div>
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

