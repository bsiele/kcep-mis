<%--
    Document   : sub_county_view_agro_dealers_on_map
    Created on : Jan 25, 2017, 7:57:39 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:sub_county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view agro-dealers on map</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/view_agro_dealers_on_map.jsp"/>

    </jsp:attribute>
</kcep:sub_county>