<%--
    Document   : head_feedback
    Created on : Jun 28, 2016, 7:14:46 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - farmer feedback </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/feedback.jsp"/>

    </jsp:attribute>
</kcep:head>