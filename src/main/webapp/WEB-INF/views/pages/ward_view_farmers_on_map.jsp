<%--
    Document   : ward_view_farmers_on_map
    Created on : Jan 25, 2017, 8:02:37 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:ward>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view agro-dealers on map</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/view_farmers_on_map.jsp"/>

    </jsp:attribute>
</kcep:ward>