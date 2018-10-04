$(function () {

    var startDateInput = $('#startDate');
    var endDateInput = $('#endDate');

    startDateInput.change(function () {
        endDateInput.prop('min', startDateInput.val());
    });
    startDateInput.trigger('change');

});