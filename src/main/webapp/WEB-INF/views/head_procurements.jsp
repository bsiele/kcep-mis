<%-- 
    Document   : head_procurements
    Created on : Jun 22, 2016, 3:34:29 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view procurements </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        List of procurements
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover data-table" id="procurement-table">
                                <thead>
                                    <tr>
                                        <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addProcurement')">Add</button></th>
                                        <th>Item</th>
                                        <th>Cost[KES]</th>
                                        <th>Date purchased</th>
                                        <th>Serial/plate number</th>
                                        <th>Description</th>
                                        <th>Target office</th>
                                        <th>County</th>
                                        <th>Sub-county</th>
                                        <th>LPO number</th>
                                        <th>Attachments (e.g. invoice or receipt)</th>
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <td colspan="8"> List of procurements</td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach var="procurement" items="${sessionScope.procurements}" varStatus="index">
                                        <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                            <td>${index.count}</td>
                                            <td>${procurement.item}</td>
                                            <td>${procurement.cost}</td>
                                            <td>${procurement.datePurchased}</td>
                                            <td>${procurement.serialNumber}</td>
                                            <td>${procurement.description}</td>
                                            <td>${procurement.targetOffice}</td>
                                            <td>${procurement.county.name}</td>
                                            <td>${procurement.subCounty}</td>
                                            <td>${procurement.lpoNumber}</td>
                                            <td><a onclick="loadAjaxWindow('download?filePath=${procurement.invoiceOrReceipt}')" target="_blank">${procurement.fileName}</a></td>
                                            <td><button onclick="editProcurement('${procuremet.id}', '${procurement.item}', '${procurement.cost}', '${procurement.datePurchased}', '${procurement.serialNumber}', '${procurement.description}', '${procurement.targetOffice}', '${procurement.county.name}', '${procurement.subCounty}', '${procurement.lpoNumber}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                            <td><button onclick="deleteProcuremenet(${procuremet.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="dialog" id="procurements-dialog">
            <div class="col-lg-12">
                <div class="panel-default">
                    <div class="panel-body">
                        <form role="form">
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
                                Item description/particulars
                                <input id="description" name="description" class="form-control">
                            </div>
                            <div class="form-group">
                                Target office
                                <input id="target-office" name="target-office" class="form-control">
                            </div>
                            <div class="form-group">
                                County
                                <select id="procurement-county" name="county" class="form-control">
                                    <c:forEach var="county" items="${applicationScope.counties}" varStatus="index"> 
                                        <option value="${county.id}">${county.name}</option>
                                    </c:forEach>
                                </select>  
                            </div>
                            <div class="form-group">
                                Sub-county
                                <select id="procurement-sub-county" name="sub-county" class="form-control">
                                    <c:forEach var="subCounty" items="${applicationScope.subCounties}" varStatus="index"> 
                                        <option value="${subCounty.id}">${subCounty.name}</option>
                                    </c:forEach>
                                </select>  
                            </div>
                            <div class="form-group">
                                LPO number
                                <input id="lpo-number" name="lpo-number" class="form-control">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</kcep:head>