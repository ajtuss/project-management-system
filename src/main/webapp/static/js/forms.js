$(function () {

    var errors = $(".error");
    errors.each(function () {
        $(this).siblings('div').addClass('has-error has-feedback');
    })


});