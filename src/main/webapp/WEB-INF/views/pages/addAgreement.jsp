<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h2>Dodaj umowę:</h2>

<form class="form-horizontal" action="" method="post">
    <div class="form-group">
        <label class="control-label col-sm-2" for="systemName">Nazwa systemu:</label>
        <div class="col-sm-4">
            <select class="form-control" id="systemName" name="systemId">
                <c:forEach var="system" items="${systems}">
                    <option value="${system.id}">${system.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="orderNumber">Nr zamówienia:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="orderNumber" placeholder="Wpisz nr zamówienia..."
                   name="orderNumber">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="startDate">Od:</label>
        <div class="col-sm-4">
            <input type="date" class="form-control" id="startDate" name="startDate">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="endDate">Do:</label>
        <div class="col-sm-4">
            <input type="date" class="form-control" id="endDate" name="endDate">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="amount">Wpływy:</label>
        <div class="col-sm-3">
            <input type="number" class="form-control" id="amount" placeholder="Wpisz wpływy ..." name="amount" value="0"
                   min="0" step="0.01" required>
        </div>
        <div class="col-sm-2">
            <select class="form-control" name="amountType">
                <option value="NETTO">Netto</option>
                <option value="BRUTTO">Brutto</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="amountPeriod">W skali:</label>
        <div class="col-sm-4">
            <select class="form-control" id="amountPeriod" name="amountPeriod">
                <option value="MONTH">Miesięcznie</option>
                <option value="QUARTER">Kwartalnie</option>
                <option value="YEAR">Rocznie</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="active">Aktywność:</label>
        <div class="col-sm-4">
            <input type="checkbox" class="" id="active" name="active">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">Zapisz</button>
        </div>
    </div>

</form>
