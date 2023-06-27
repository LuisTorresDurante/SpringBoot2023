var tabla = null;
$(document).ready(function(){
    $('#buscarCliente').on("click", function(){
        if($.isNumeric($('#cliente').val())){
            $('#tabla-container').show();
            if (tabla==null){
                crearTabla();
            }else{
                tabla.ajax.reload();
            }

        }
    })
})

function crearTabla(){
    tabla = $('#tablaMultas').DataTable({
      ..._tableConfig,
      ajax:{
        "type":"GET",
        "url":"tickets/obtener",
        data: function(d){
            d.customerId = $('#cliente').val();
        }
      },

      columns: [
        {"data":"ticketId"},
        {"data":"rentalId"},
        {"data":"filmId"},
        {"data":null},
        {"data":null},
        {"data":null}
        ],

      "columnDefs":[
            {
            "render": function(data,type,json){
                return "$" + json.amount
            },
            "targets" : 3


            },
            {
            "render": function(data,type,json){
                if(json.active){
                    return '<span class = "red-btn" style="pointer-events: none;">Pendientes</span>';
                }else{
                    return '<span class = "green-btn" style="pointer-events: none;">Pagados</span>';
                }
            },
            "targets" : 4
            }
        ]   
    })
}