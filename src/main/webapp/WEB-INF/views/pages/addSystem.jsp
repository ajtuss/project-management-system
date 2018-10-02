<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<h2><tiles:getAsString name="title"/>:</h2>

<form:form class="form-horizontal" action="" method="post" modelAttribute="system">
    <form:input path="id" hidden="true"/>
    <div class="form-group">
        <form:label class="control-label col-sm-2" path="name">Nazwa systemu:</form:label>
        <div class="col-sm-4">
            <form:input type="text" class="form-control" id="name" placeholder="Wpisz nazwę systemu" path="name"/>
        </div>
        <form:errors path="name" cssClass="error col-sm-4" element="div"/>
    </div>
    <div class="form-group">
        <form:label class="control-label col-sm-2" path="description">Opis systemu:</form:label>
        <div class="col-sm-4">
            <form:input type="text" class="form-control" id="desc" placeholder="Wpisz opis..." path="description"/>
        </div>
        <form:errors path="description" cssClass="error col-sm-4" element="div"/>
    </div>
    <div class="form-group">
        <form:label class="control-label col-sm-2" path="techDescription">Opis technologii:</form:label>
        <div class="col-sm-4">
            <form:input type="text" class="form-control" id="techDesc" placeholder="Wpisz technologie..."
                        path="techDescription"/>
        </div>
        <form:errors path="techDescription" cssClass="error col-sm-4" element="div"/>
    </div>
    <div class="form-group">
        <form:label class="control-label col-sm-2" path="owner">Właściciel systemu:</form:label>
        <div class="col-sm-4">
            <form:input type="text" class="form-control" id="owner" placeholder="Wpisz właściciela..." path="owner"
                        min="3" max="25"/>
        </div>
        <form:errors path="owner" cssClass="error col-sm-4" element="div"/>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <form:button type="submit" class="btn btn-primary">Zapisz</form:button>
        </div>
    </div>
</form:form>