<%--
    Document   : addFarmer
    Created on : Oct 25, 2016, 6:46:54 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                Person details
            </div>
            <div class="panel-body">
                <form role="form">
                    <div>
                        <input type="hidden" id="person-role" value="1">
                    </div>
                    <div class="form-group">
                        Name
                        <input id="person-name" class="form-control">
                    </div>
                    <div class="form-group">
                        National id number
                        <input id="national-id" class="form-control">
                    </div>
                    <div class="form-group">
                        Year of birth
                        <select id="year-of-birth" class="form-control">
                            <option disabled>Select year</option>
                        </select>
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
                    <!--                    <div class="form-group">
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
            </div>-->