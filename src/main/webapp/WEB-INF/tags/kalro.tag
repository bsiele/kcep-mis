<%--
    Document   : kalro
    Created on : Jun 22, 2016, 3:37:40 PM
    Author     : siech
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<%-- The list of fragments:--%>
<%@attribute name="pagecontent" fragment="true"%>
<%@attribute name="pagetitle" required="true" %>

<kcep:genericpage>

    <jsp:attribute name="title"> ${pagetitle} </jsp:attribute>

    <jsp:attribute name="menuitems">
        <jsp:include page="../../tags/includes/people_item.jsp"/>
        <jsp:include page="../../tags/includes/training_item.jsp"/>
        <jsp:include page="../../tags/includes/e_vouchers_item.jsp"/>
        <jsp:include page="../../tags/includes/success_stories_item.jsp"/>
        <jsp:include page="../../tags/includes/surveys_item.jsp"/>
        <jsp:include page="../../tags/includes/documents_item.jsp"/>
    </jsp:attribute>

    <jsp:attribute name="content">
        <div id="page-wrapper">
            <div>
                <jsp:invoke fragment="pagecontent" />
            </div>
        </div>
    </jsp:attribute>

</kcep:genericpage>

