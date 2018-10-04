<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<h2><tiles:getAsString name="title"/>:</h2>

<form:form class="form-horizontal col-sm-8" action="" method="post" modelAttribute="agreement">

    <%--System Id--%>
    <div class="form-group">
        <form:label class="control-label col-sm-3" for="systemName" path="systemId">Nazwa systemu:</form:label>
        <div class="col-sm-6">
            <form:select class="form-control" id="systemName" path="systemId">
                <c:forEach var="system" items="${systems}">
                    <option value="${system.id}">${system.name}</option>
                </c:forEach>
            </form:select>
        </div>
    </div>

    <%--Order Number--%>
    <div class="form-group">
        <form:label class="control-label col-sm-3" for="orderNumber" path="orderNumber">Nr zamówienia:</form:label>
        <div class="col-sm-6">
            <form:input type="text" class="form-control" id="orderNumber" placeholder="Wpisz nr zamówienia..."
                        path="orderNumber"/>
        </div>
        <form:errors path="orderNumber" cssClass="error col-sm-3" element="div"/>
    </div>

    <%--Start Date--%>
    <div class="form-group">
        <form:label class="control-label col-sm-3" for="startDate" path="startDate">Od:</form:label>
        <div class="col-sm-6">
            <form:input type="date" class="form-control" id="startDate" path="startDate"/>
        </div>
        <form:errors path="startDate" cssClass="error col-sm-3" element="div"/>
    </div>

    <%--End Date--%>
    <div class="form-group">
        <form:label class="control-label col-sm-3" for="endDate" path="endDate">Do:</form:label>
        <div class="col-sm-6">
            <form:input type="date" class="form-control" id="endDate" path="endDate"/>
        </div>
        <form:errors path="endDate" cssClass="error col-sm-3" element="div"/>
    </div>

    <%--Amount--%>
    <div class="form-group">
        <form:label class="control-label col-sm-3" for="amount" path="amount">Wpływy:</form:label>
        <div class="col-sm-4">
            <form:input type="number" class="form-control" id="amount" placeholder="Wpisz wpływy ..." path="amount"
                        min="0" step="0.01"/>
        </div>
        <form:errors path="amount" cssClass="error col-sm-3" element="div"/>

            <%--Amount Type--%>
        <div class="col-sm-2">
            <form:select class="form-control" path="amountType">
                <option value="NETTO">Netto</option>
                <option value="BRUTTO">Brutto</option>
            </form:select>
        </div>
    </div>

    <%--Amount Period--%>
    <div class="form-group">
        <form:label class="control-label col-sm-3" for="amountPeriod" path="amountPeriod">W skali:</form:label>
        <div class="col-sm-6">
            <form:select class="form-control" id="amountPeriod" path="amountPeriod">
                <option value="MONTH">Miesięcznie</option>
                <option value="QUARTER">Kwartalnie</option>
                <option value="YEAR">Rocznie</option>
            </form:select>
        </div>
    </div>

    <%--Active--%>
    <div class="form-group">
        <form:label class="control-label col-sm-3" for="active" path="active">Aktywność:</form:label>
        <div class="col-sm-4">
            <form:checkbox id="active" path="active"/>
        </div>
    </div>

    <%--Submit--%>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">Zapisz</button>
        </div>
    </div>

</form:form>
