<%-- 
    Document   : agro_dealer_farm
    Created on : Jul 22, 2016, 10:26:05 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agro_dealer>
    <jsp:attribute name="pagetitle"> KCEP-MIS - national officer </jsp:attribute>
    <jsp:attribute name="pagecontent">

           <jsp:include page="../includes/farm.jsp"/>

    </jsp:attribute>
</kcep:agro_dealer>