$(function () {

    var systemsTable = $("#systems");

    var contextPath = $('body').data('path');

    systemsTable.DataTable({
        initComplete: function () {

        },

        dom: 'Bfrtip',
        buttons: [
            {
                text: 'Dodaj',
                action: function (e, dt, button, config) {
                    window.location.href = contextPath + '/systems/add';
                }
            },
            {
                extend: 'selectedSingle',
                text: 'Edytuj',
                action: function (e, dt, button, config) {
                    window.location.href = contextPath + '/systems/edit?id=' + dt.row({selected: true}).data()[0];
                }
            }
        ],
        paging: false,
        select: true,
        columnDefs: [
            {
                "targets": [0],
                "visible": false
            }]
    });

});