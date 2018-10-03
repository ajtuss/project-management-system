<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h2>Import pliku:</h2>

<c:if test="${message == null}">

    <form method="POST" action="/upload" enctype="multipart/form-data" class="col-md-7">
        <!-- COMPONENT START -->
        <div class="form-group">
            <input type="file" class="control-label" name="file"
                   accept=".xlsx" required>
        </div>
            <%--Submit--%>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">Wyślij</button>
            </div>
        </div>
    </form>

</c:if>

