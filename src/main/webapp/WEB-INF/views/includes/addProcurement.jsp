<%--
    Document   : addProcurements
    Created on : Sep 15, 2016, 11:51:47 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                Equipment procurement details
            </div>
            <div class="panel-body">
                <form id="procurement-form" role="form" action="doAddProcurement" method="POST" enctype="multipart/form-data">
                    <!--                    <div class="form-group">
                                            Item
                                            <select id="item" name="item" class="form-control">
                    <c:forEach var="item" items="${sessionScope.items}" varStatus="index">
                        <option value="${item.id}">${item.category.name} - ${item.category.child.name}</option>
                    </c:forEach>
                </select>
            </div>-->
                    <div class="form-group">
                        Item
                        <input id="item" name="item" class="form-control">
                    </div>
                    <div class="form-group">
                        Cost[KES]
                        <input id="cost" type="number" step="0.01" name="cost" class="form-control">
                    </div>
                    <div class="form-group">
                        Date purchased
                        <input type="date" id="date-purchased" name="date-purchased" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Serial number
                        <input id="serial-number" name="serial-number" class="form-control">
                    </div>
                    <div class="form-group">
                        Item description / particulars
                        <input id="description" name="description" class="form-control">
                    </div>
                    <div class="form-group">
                        Target office
                        <input id="target-office" name="target-office" class="form-control">
                    </div>
                    <div class="form-group">
                        County
                        <select id="county" name="county" class="form-control" onchange="updateSubCounties()">
                            <c:forEach var="county" items="${applicationScope.counties}" varStatus="index">
                                <option value="${county.id}">${county.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Sub-county
                        <select id="sub-county" name="sub-county" class="form-control" onchange="updateWards()">
                            <c:forEach var="subCounty" items="${applicationScope.subCounties}" varStatus="index">
                                <option value="${subCounty.id}">${subCounty.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        LPO number
                        <input id="lpo-number" name="lpo-number" class="form-control">
                    </div>
                    <div class="form-group">
                        Attachments (e.g. invoice or receipt)
                        <input type="file" id="invoice-or-receipt" name="invoice-or-receipt" class="form-control" multiple>
                    </div>
                    <input type="submit" class="btn btn-outline btn-primary" value="Save procurement">
                </form>
            </div>
        </div>
    </div>
</div>

