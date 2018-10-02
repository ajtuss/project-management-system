<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h2>Systemy:</h2>

<table id="systems" class="display" style="width:100%">
    <thead>
    <tr>
        <th>Id</th>
        <th>Nazwa</th>
        <th>Opis</th>
        <th>Specyfikacja Techniczna</th>
        <th>Właściciel</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="system" items="${systems}">
        <tr>
            <td>${system.id}</td>
            <td>${system.name}</td>
            <td>${system.description}</td>
            <td>${system.techDescription}</td>
            <td>${system.owner}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>