<%-- 
    Document   : ward_addEquipment
    Created on : Jun 22, 2016, 4:02:40 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:ward>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add equipment </jsp:attribute>
    <jsp:attribute name="pagecontent">

      <jsp:include page="../includes/addEquipment.jsp"/>

    </jsp:attribute>
</kcep:ward>