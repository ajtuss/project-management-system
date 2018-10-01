<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><tiles:getAsString name="title"/></title>
    <link href="/webjars/bootstrap/3.3.7-1/css/bootstrap.css" rel="stylesheet">

</head>

<body>


<section id="topmenu">
    <tiles:insertAttribute name="menu"/>
</section>

<div class="container">
    <section id="site-content">
        <tiles:insertAttribute name="body"/>
    </section>
</div>

<footer id="footer">
    <tiles:insertAttribute name="footer"/>
</footer>


</body>
<script src="/webjars/jquery/3.1.1/jquery.js"></script>
<script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.js"></script>
</html>