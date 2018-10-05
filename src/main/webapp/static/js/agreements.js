$(function () {

    var agreementsTable = $("#agreement");

    var contextPath = $('body').data('path');


    agreementsTable.DataTable({
        initComplete: function () {

        },

        dom: 'Bfrtip',
        buttons: [
            {
                text: 'Dodaj',
                action: function (e, dt, button, config) {
                    window.location.href = contextPath + '/agreements/add';
                }
            },
            {
                extend: 'selectedSingle',
                text: 'Edytuj',
                action: function (e, dt, button, config) {
                    window.location.href = contextPath +'agreements/edit?id=' + dt.row({selected: true}).data()[0];
                }
            },
            {
                text: 'Importuj',
                action: function (e, dt, button, config) {
                    window.location.href = contextPath + '/import';
                }
            }
        ],
        paging: false,
        select: true,
        columnDefs: [
            {
                targets: [0],
                visible: false
            },
            {
                targets: [6],
                render: function (data, type, row) {
                    switch (data){
                        case 'month':
                            return 'miesięcznie';
                        case 'quarter':
                            return 'kawartalnie';
                        case 'year':
                            return 'rocznie';
                        default:
                            return data;
                    }
                }
            },
            {
                targets: [7],
                render: function (data, type, row) {
                    return (data === 'true') ? '<span class="glyphicon glyphicon-ok"></span>' :
                        '<span class="glyphicon glyphicon-remove"></span>';
                }
            }]
    });

});