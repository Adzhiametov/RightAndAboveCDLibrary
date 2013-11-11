<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>

    <jsp:attribute name="headerTitle">
            <h1>View CD's</h1>
    </jsp:attribute>

    <jsp:body>

        <div>
            <h1>CD Collection</h1> </br>
        </div>

        <div class="row table_height">
            <div class="col-lg-12">
                <table class="table table-striped table-hover table-condensed">
                    <thead>
                    <tr>
                        <td width="5%">#</td>
                        <th width="20%">Title</th>
                        <th width="20%">Artist</th>
                        <th width="20%">Country</th>
                        <th width="20%">Company</th>
                        <th width="7%">Price</th>
                        <th width="8%">Year</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${listHolder.pageList==null||listHolder.pageList.isEmpty()}">
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><h5>Library is empty now</h5></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="cd" items="${listHolder.pageList}">
                                <tr>
                                    <td width="5%">${listHolder.getSource().indexOf(cd)+1}</td>
                                    <td width="20%">${cd.title}</td>
                                    <td width="20%">${cd.artist}</td>
                                    <td width="20%">${cd.country}</td>
                                    <td width="20%">${cd.company}</td>
                                    <td width="7%">${cd.price}</td>
                                    <td width="8%">${cd.year}</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>

        ${message}

        <ul class="pager">
            <li <c:if test="${listHolder.isFirstPage()}"><c:out value="class=disabled"/></c:if>><a
                    href="<c:url value='/show/prev' />">Prev</a></li>
            <li <c:if test="${listHolder.isLastPage()}"><c:out value="class=disabled"/></c:if>><a
                    href="<c:url value='/show/next' />">Next</a></li>
        </ul>

    </jsp:body>
</t:genericpage>