<%--
    Document   : agmark_addTraining
    Created on : Oct 9, 2016, 5:39:32 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agmark>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add training </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addTraining.jsp"/>

    </jsp:attribute>
</kcep:agmark>