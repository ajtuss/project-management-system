<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:importAttribute name="stylesheets"/>
<tiles:importAttribute name="javascripts"/>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:getAsString name="title"/></title>

    <%--stylesheets--%>
    <c:forEach var="css" items="${stylesheets}">
        <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
    </c:forEach>
    <%--end stylesheets--%>
</head>

<body data-path="${pageContext.request.contextPath}">

<section id="topmenu">
    <tiles:insertAttribute name="menu"/>
</section>

<div class="container">
    <section id="site-content">
        <tiles:insertAttribute name="body"/>
    </section>
</div>

<footer id="footer" class="footer">
    <tiles:insertAttribute name="footer"/>
</footer>

<!-- scripts -->
<c:forEach var="script" items="${javascripts}">
    <script src="<c:url value="${script}"/>"></script>
</c:forEach>
<!-- end scripts -->

</body>

</html>