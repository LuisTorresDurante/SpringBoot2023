$(document).ready(function () {
    rentalController().inicializarTabla();
    // console.log('ready');
});

const rentalController = () => {
    let table = null;
    const _tableConfig = {}; // Define _tableConfig object with desired configurations

    const inicializarTabla = () => {
        table = $("#tableRental").DataTable({
            ..._tableConfig,
            ajax: 'rental/pendientes',
            columns: [
                { data: 'rentalId' },
                { data: 'rentalDate' },
                { data: 'inventoryId' },
                { data: 'customerId' },
                { data: 'staffId' },
                { data: 'returnDate' },
            ],
            columnDefs: [
                {
                    targets: 1,
                    data: 'rentalDate',
                    render: function (data, type, row) {
                        return `${row.rentalDate}`;
                    },
                },
                {
                    targets: 2,
                    data: 'inventoryId',
                    render: function (data, type, row) {
                        return `${row.inventoryId}`;
                    },
                },
                {
                    targets: 3,
                    data: 'customerId',
                    render: function (data, type, row) {
                        return `${row.customerId} - ${row.nombreCliente}`;
                    },
                },
                {
                    targets: 5,
                    data: 'staffId',
                    render: function (data, type, row) {
                        return `${row.staffId}`;
                    },
                },
                {
                    targets: 6,
                    data: null,
                    render: function (data, type, row) {
                        return `<div class="btn-group" role="group">
                            <button type="button" class="btn btn-primary">Consultar</button>
                        </div>`;
                    },
                },
            ],
        });

        $(document).on('click', 'button#agregarRenta', function (e) {
            e.preventDefault();
            const btn = $(this);
            btn.prop("disabled", false);
            mostrarModal(btn);
        });
    };

    const mostrarModal = (btn) => {
        const modal = $("div#modalRegistro");
        modal.modal({
            backdrop: false,
            keyboard: false,
            show: true
        });
        modal.find("span#title-modal").html("Registrar Renta");
        modal.on("hidden.bs.modal", function (e) {
            console.log(btn);
            btn.prop("disabled", false);
        }).off("click", "button#buttonSuccess").on("click", "button#buttonSuccess", function (e) {
            e.preventDefault();
            const _btn = $(this);
            _btn.prop("disabled", true);
            console.log("Click en registrar película");
        }).off("shown.bs.modal").on("shown.bs.modal", function () {
            _select2({
                id: "select#customerSearch",
                url: "rental/customerSearch",
                mapResultData: ({ data }) => {
                    return data.map(({ customerId, name, email }) => ({
                        id: customerId,
                        text: `${customerId} - ${name} / ${email}`
                    }));
                },
            });

            _select2({
                id: "select#filmSearch",
                url: "rental/filmSearch",
                mapResultData: ({ data }) => {
                    return data.map(({ inventoryId, title, releaseYear, language }) => ({
                        id: inventoryId,
                        text: `${inventoryId} - ${title} [${releaseYear}][${language}]`
                    }));
                },
            });
        });

        const inputsHtml = () => {
            return `
            ${inputSelect("customerSearch", "customerSearch", [], 0, "Buscar cliente", false, "Ingrese un número de cliente o nombre de cliente")}
            ${inputSelect("filmSearch", "filmSearch", [], 0, "Buscar película", false, "Ingrese un número de película")}
            ${inputText("subtotal", "subtotal", 0, "Subtotal", true, "0,0")}
            ${inputNumber("recibido", "recibido", 0, "Importe Recibido", false, "0,0", "Importe recibido del cliente", "numeric")}
            ${inputText("devolucion", "devolucion", 0, "Cambio", true, "0,0", "Importe a devolver al cliente", "numeric")}
            `;
        };

        modal.find("div.modal-body").html(inputsHtml());
    };

    return {
        inicializarTabla,
    };
};
