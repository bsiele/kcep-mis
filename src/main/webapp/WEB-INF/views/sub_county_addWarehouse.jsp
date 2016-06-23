<%-- 
    Document   : sub_county_addWarehouse
    Created on : Jun 23, 2016, 7:47:53 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:sub_county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add warehouse</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Sub-county Desk Officer</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Warehouse details
                        </div>
                        <div class="panel-body">
                            <form role="form">
                                <div class="form-group">
                                    Name
                                    <input id="warehouse-name" class="form-control">
                                </div>
                                <div class="form-group">
                                    Warehouse operator
                                    <select id="warehouse-operator" class="form-control">
                                        <c:forEach var="person" items="${sessionScope.people}" varStatus="index"> 
                                            <option value="${person.id}">${person.name}</option>
                                        </c:forEach>
                                    </select>    
                                </div>
                                <div class="form-group">
                                    Warehouse type
                                    <select id="warehouse-type" class="form-control">
                                        <c:forEach var="warehouseType" items="${applicationScope.warehouseTypes}" varStatus="index"> 
                                            <option value="${warehouseType.id}">${warehouseType.type}</option>
                                        </c:forEach>
                                    </select> 
                                </div>
                                <div class="form-group">
                                    Capacity
                                    <input id="capacity" class="form-control">
                                </div>
                                <div class="form-group">
                                    Units
                                    <select id="capacity-units" class="form-control">
                                        <c:forEach var="measurementUnit" items="${applicationScope.measurementUnits}" varStatus="index"> 
                                            <option value="${measurementUnit.id}">${measurementUnit.unit}</option>
                                        </c:forEach>
                                    </select> 
                                </div>
                                <div class="form-group">
                                    Offers WRS services?
                                    <select id="offers-wrs" class="form-control">
                                        <option value="true">Yes</option>
                                        <option value="false">No</option>
                                    </select> 
                                </div>
                                <div class="form-group">
                                    Certified?
                                    <select id="certified" class="form-control">
                                        <option value="true">Yes</option>
                                        <option value="false">No</option>
                                    </select> 
                                </div>
                                <div class="form-group">
                                    Latitude
                                    <input id="warehouse-latitude" class="form-control">
                                </div>
                                <div class="form-group">
                                    Longitude
                                    <input id="warehouse-longitude" class="form-control">
                                </div>
                                <div class="form-group">
                                    County
                                    <select id="warehouse-county" class="form-control">
                                        <c:forEach var="county" items="${applicationScope.counties}" varStatus="index"> 
                                            <option value="${county.id}">${county.name}</option>
                                        </c:forEach>
                                    </select>  
                                </div>
                                <div class="form-group">
                                    Sub-county
                                    <select id="warehouse-sub-county" class="form-control">
                                        <c:forEach var="subCounty" items="${applicationScope.subCounties}" varStatus="index"> 
                                            <option value="${subCounty.id}">${subCounty.name}</option>
                                        </c:forEach>
                                    </select>  
                                </div>
                                <div class="form-group">
                                    Ward
                                    <select id="warehouse-ward" class="form-control">
                                        <c:forEach var="ward" items="${applicationScope.wards}" varStatus="index"> 
                                            <option value="${ward.id}">${ward.name}</option>
                                        </c:forEach>
                                    </select>  
                                </div>
                                <button type="button" class="btn btn-outline btn-primary" onclick="addWarehouse()">Save warehouse</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:sub_county>