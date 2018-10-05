<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h2>Import pliku:</h2>

<div class="row">

    <form method="POST" action="${pageContext.request.contextPath}/import" enctype="multipart/form-data" class="col-md-7">
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

</div>


<c:if test="${message != null}">
    <c:if test="${message.done > 0}">
        <div class="row alert alert-success">
            <strong>Success!</strong> Zaimportowano ${message.done} elementów</a>.
        </div>
    </c:if>
    <c:if test="${message.fail > 0}">
        <div class="row alert alert-warning">
            <strong>Ostrzeżenie!</strong> Nie udało się zaimportować ${message.fail} elementów</a>.
        </div>
    </c:if>
    <c:if test="${message.message != null}">
        <div class="row alert alert-danger">
            <strong>${message.message}!</strong></a>.
        </div>
    </c:if>
</c:if>

