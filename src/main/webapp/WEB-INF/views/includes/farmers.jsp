<%--
    Document   : farmers
    Created on : Oct 5, 2016, 11:23:05 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Farmers
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addFarmer</label>
                    <table id="farmers-table" class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>Name</th>
                                <th>Gender, Age</th>
                                <th>National id</th>
                                <!--                                <th>Farmer group</th>
                                                                <th>Farmer sub-group</th>-->
                                <th>County</th>
                                <th>Sub-county</th>
                                <th>Ward</th>
                                <th>Division</th>
                                <th>Village</th>
                                <th>Latitude,Longitude</th>
                                <th>Phone number</th>
                                <th>Plot size</th>
                                <th>Total inputs collected</th>
                                <th>Account number</th>
                                <th>Amount of savings</th>
                                <th>Loan amount</th>
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="17">List of farmers</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="person" items="${sessionScope.farmers}" varStatus="index">
                                <tr class="farmer-row">
                                    <td onclick="loadFarmWindow(${person.id})">${index.count}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.sex.sex}, ${person.age}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.nationalId}</td>
<!--                                    <td onclick="loadFarmWindow(${person.id})">${person.farmerGroup.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.farmerSubGroup.name}</td>-->
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.county.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.subCounty.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.ward.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.divisionalLocation.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.village.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.latitude},${person.location.longitude}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.contact.phone}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.plotSize}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.totalInputsCollected}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.account.accountNumber}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.account.savings}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.account.totalLoanAmount}</td>
                                    <td><button onclick="editPerson('${person.id}', '${person.name}', '${person.sex.id}', '${person.nationalId}', '${person.personRoleId}',
                                                    '${person.yearOfBirth}', '${person.businessName}', '${person.farmerGroup.id}', '${person.farmerSubGroup.id}',
                                                    '${person.location.id}', '${person.location.county.id}', '${person.location.subCounty.id}', '${person.location.ward.id}', '${person.contact.id}',
                                                    '${person.contact.phone}', '${person.contact.email}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                    <td><button onclick="deletePerson(${person.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <jsp:include page="people_count.jsp"/>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Farmers locations on map
            </div>
            <div class="panel-body">
                <input type="hidden" id="person-type" value="Farmer">
                <div id="map"></div>
            </div>
        </div>
    </div>
</div>

<div class="dialog" id="person-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Name
                        <input id="person-name" name="person-name" class="form-control">
                    </div>
                    <div class="form-group">
                        Gender
                        <select id="sex" name="sex" class="form-control">
                            <c:forEach var="sex" items="${applicationScope.sexes}" varStatus="index">
                                <option value="${sex.id}">${sex.sex}</option>
                            </c:forEach>
                        </select>
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
                        National ID
                        <input id="national-id" name="national-id" class="form-control">
                    </div>
                    <div class="form-group">
                        Date Of Birth
                        <input id="year-of-birth" name="year-of-birth" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Business Name
                        <input id="business-name" name="person-business-name" class="form-control">
                    </div>
                    <div class="form-group">
                        Farmer Group
                        <input id="farmer-group" name="person-framer-group" class="form-control">
                    </div>
                    <div class="form-group">
                        Farmer SubGroup
                        <input id="farmer-sub-group" name="person-farmer-sub-group" class="form-control">
                    </div>
                    <div class="form-group">
                        County
                        <select id="county" name="person-county" class="form-control" onchange="updateSubCounties()">
                            <c:forEach var="county" items="${applicationScope.counties}" varStatus="index">
                                <option value="${county.id}">${county.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Sub-county
                        <select id="sub-county" name="person-sub-county" class="form-control" onchange="updateWards()">
                            <c:forEach var="subCounty" items="${applicationScope.subCounties}" varStatus="index">
                                <option value="${subCounty.id}">${subCounty.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Ward
                        <select id="ward" name="ward" class="form-control">
                            <c:forEach var="subCounty" items="${applicationScope.subCounties}" varStatus="index">
                                <option value="${subCounty.id}">${subCounty.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Phone
                        <input id="phone" name="person-phone" class="form-control">
                    </div>
                    <div class="form-group">
                        Email
                        <input id="email" name="person-email" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="evoucher-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Amount
                        <input id="e-voucher-amount" name="amount" class="form-control">
                    </div>
                    <div class="form-group">
                        Input type
                        <select id="e-voucher-input-type" name="input-type" class="form-control">
                            <c:forEach var="inputType" items="${sessionScope.inputTypes}" varStatus="index">
                                <option value="${inputType.id}">${inputType.type}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Person
                        <select id="e-voucher-person" name="person" class="form-control">
                            <c:forEach var="person" items="${sessionScope.eVoucherPeople}" varStatus="index">
                                <option value="${person.id}">${person.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Date redeemed
                        <input id="date-redeemed" name="date-redeemed" class="form-control datefield" type="date">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="search-person-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        National id
                        <input id="search-national-id" name="search-national-id" class="form-control">
                    </div>
                    <div class="form-group">
                        Name
                        <input id="search-name" name="search-name" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="static/js/maps.js" type="text/javascript"></script>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAosZcLbpuT4q2Mrl96oMfgtsC2etLRvLw&callback=getLocations"></script>
