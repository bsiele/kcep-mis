<%-- 
    Document   : sub_county_addPerson
    Created on : Jun 25, 2016, 1:47:18 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:sub_county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add person </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Person details
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <div class="form-group">
                                Name
                                <input id="person-name" class="form-control">
                            </div>
                            <div class="form-group">
                                Person role
                                <select id="person-role" class="form-control">
                                    <c:forEach var="personRole" items="${applicationScope.personRoles}" varStatus="index">
                                        <option value="${personRole.id}">${personRole.personRole}</option>
                                    </c:forEach>
                                </select>    
                            </div>
                            <div class="form-group">
                                National id number
                                <input id="national-id" class="form-control">
                            </div>
                            <div class="form-group">
                                Date of birth
                                <input id="date-of-birth" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                Business name
                                <input id="business-name" class="form-control">
                            </div>
                            <div class="form-group">
                                Gender
                                <select id="sex" class="form-control">
                                    <c:forEach var="sex" items="${applicationScope.sexes}" varStatus="index"> 
                                        <option value="${sex.id}">${sex.sex}</option>
                                    </c:forEach>
                                </select> 
                            </div>
                            <div class="form-group">
                                Phone number
                                <input id="phone" type="phone" class="form-control">
                            </div>
                            <div class="form-group">
                                Postal address
                                <input id="postal-address" class="form-control">
                            </div>
                            <div class="form-group">
                                Email address
                                <input id="email" type="email" class="form-control">
                            </div>
                            <div class="form-group">
                                Farmer group
                                <select id="farmer-group" class="form-control">
                                    <c:forEach var="farmerGroup" items="${applicationScope.farmerGroups}" varStatus="index"> 
                                        <option value="${farmerGroup.id}">${farmerGroup.name}</option>
                                    </c:forEach>
                                </select>    
                            </div>
                            <div class="form-group">
                                Farmer sub-group
                                <select id="farmer-sub-group" class="form-control">
                                    <c:forEach var="farmerSubGroup" items="${applicationScope.farmerSubGroups}" varStatus="index"> 
                                        <option value="${farmerSubGroup.id}">${farmerSubGroup.name}</option>
                                    </c:forEach>
                                </select>    
                            </div>
                            <div class="form-group">
                                <input type="hidden" id="person-county" value="${sessionScope.person.location.county.id}">
                            </div>
                            <div class="form-group">
                                <input type="hidden" id="person-sub-county" value="${sessionScope.person.location.subCounty.id}">
                            </div>
                            <div class="form-group">
                                Ward
                                <select id="person-ward" class="form-control">
                                    <c:forEach var="ward" items="${sessionScope.wards}" varStatus="index"> 
                                        <option value="${ward.id}">${ward.name}</option>
                                    </c:forEach>
                                </select>  
                            </div>
                            <button type="button" class="btn btn-outline btn-primary" onclick="addPerson()">Save person</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:sub_county>