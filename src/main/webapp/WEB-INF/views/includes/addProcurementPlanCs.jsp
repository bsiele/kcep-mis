<%--
    Document   : addProcurementPlanCs
    Created on : Sep 7, 2016, 1:21:40 PM
    Author     : ronne
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                Procurement plan - cs details
            </div>
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Description
                        <input id="description" class="form-control">
                    </div>
                    <div class="form-group">
                        IFAD prior review
                        <select id="ifad-prior-review" class="form-control">
                            <c:forEach var="ifadPriorReview" items="${sessionScope.ifadPriorReviewChoices}">
                                <option value="${ifadPriorReview.id}">${ifadPriorReview.choice}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Plan vs Actual
                        <select id="plan-vs-actual" class="form-control">
                            <c:forEach var="planVsActual" items="${sessionScope.planVsActualChoices}">
                                <option value="${planVsActual.id}">${planVsActual.choice}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Cost [KES]
                        <input id="cost"  type="number" step="0.01"  class="form-control">
                    </div>
                    <div class="form-group">
                        Procurement method
                        <select id="procurement-method" class="form-control">
                            <c:forEach var="procurementMethod" items="${sessionScope.procurementMethods}">
                                <option value="${procurementMethod.id}">${procurementMethod.method}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Submit TOR
                        <input id="submit-tor" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Complete REOI
                        <input id="complete-reoi" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Complete BD
                        <input id="complete-bd" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        *Approval by IFAD
                        <input id="approval-by-ifad1" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        *Approval by SDA
                        <input id="approval-by-sda" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Issue REOI
                        <input id="issue-reoi" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Receive EOIS
                        <input id="receive-eois" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Evaluate bids
                        <input id="evaluate-bids" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Establish short list
                        <input id="establish-short-list" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Complete RFP
                        <input id="complete-rfp" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        *Approval by IFAD
                        <input id="approval-by-ifad2" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        *Approval by SDA
                        <input id="approval-by-sda" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Issue RFP
                        <input id="issue-rfp" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Receive proposals
                        <input id="receive-proposals" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Evaluate technical proposals
                        <input id="evaluate-technical-proposals" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        *Approval by IFAD
                        <input id="approval-by-ifad3" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Negotiate
                        <input id="negotiate" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        *Approval by IFAD
                        <input id="approval-by-ifad4" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Award
                        <input id="award" class="form-control datefield" type="date">
                    </div>

                    <div class="form-group">
                        Approval by SDA/AG
                        <input id="approval-by-sda-or-ag" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Sign contract
                        <input id="sign-contract" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Commence contract
                        <input id="commence-contract" class="form-control datefield" type="date">
                    </div>
                    <button type="button" class="btn btn-outline btn-primary" onclick="addProcurementPlanCs()">Save procurement plan-cs</button>
                </form>
            </div>
        </div>
    </div>
</div>
