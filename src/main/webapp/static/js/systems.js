$(function () {

    var systemsTable = $("#systems");


    systemsTable.DataTable({
        initComplete: function () {

        },

        dom: 'Bfrtip',
        buttons: [
            {
                text: 'Dodaj',
                action: function (e, dt, button, config) {
                    window.location.href = '/systems/add';
                }
            },
            {
                extend: 'selectedSingle',
                text: 'Edytuj',
                action: function (e, dt, button, config) {
                    window.location.href = '/systems/edit?id='+dt.row({selected: true}).data().id;
                }
            }
        ],
        paging: false,
        select: true
    });

});