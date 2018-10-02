<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h2>Wszystkie umowy:</h2>

<table id="agreement" class="display" style="width:100%">
    <thead>
    <tr>
        <th>Id</th>
        <th>System</th>
        <th>Nr umowy</th>
        <th>Od</th>
        <th>Do</th>
        <th>Wpływy</th>
        <th>W skali</th>
        <th>Aktywna</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="agreement" items="${agreements}">
        <tr>
            <td>${agreement.id}</td>
            <td>${agreement.systemName}</td>
            <td>${agreement.orderNumber}</td>
            <td>${agreement.startDate}</td>
            <td>${agreement.endDate}</td>
            <td>${agreement.amount} zł ${fn:toLowerCase(agreement.amountType)}</td>
            <td>${fn:toLowerCase(agreement.amountPeriod)}</td>
            <td>${agreement.active}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>