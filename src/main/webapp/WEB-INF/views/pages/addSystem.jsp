<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h2>Dodaj system:</h2>

<form class="form-horizontal" action="" method="post">
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">Nazwa systemu:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="name" placeholder="Wpisz nazwę systemu" name="name" min="3" max="25" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="desc">Opis systemu:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="desc" placeholder="Wpisz opis..." name="description">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="techDesc">Opis technologii:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="techDesc" placeholder="Wpisz technologie..." name="techDescription" >
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="owner">Właściciel systemu:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="owner" placeholder="Wpisz właściciela..." name="owner" min="3" max="25" required>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">Zapisz</button>
        </div>
    </div>
</form>
