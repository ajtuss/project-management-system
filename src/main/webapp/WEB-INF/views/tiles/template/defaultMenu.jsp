<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Project Management System</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/agreements/active">Aktywne umowy</a></li>
            <li><a href="${pageContext.request.contextPath}/agreements">Wszystkie umowy</a></li>
            <li><a href="${pageContext.request.contextPath}/systems">Systemy</a></li>
            <li><a href="${pageContext.request.contextPath}/contactus">O aplikacji</a></li>
        </ul>
    </div>
</nav>