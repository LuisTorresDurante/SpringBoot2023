
$(document).ready(function() {
    $('#tableCountries').DataTable({
        ajax: {
            url: '/countries/lista',
            
        },
        columns: [
            { data: 'countryId' },
            { data: 'countryName' },
            { data: 'lastUpdate' },
            { data: null}
        ],columnDefs:[
            {
                targets:3,
                data: null,
                defaultContent: '<button class="btn blue-btn accionesRow"><i class="fa fa-search"></i></button>',
                render: function(data, type, row){
                 return	'<button class="btn red-btn accionesRow"  countryId="'+row.countryId +'" onClick="BorrarPais('+row.countryId+')"><i class="fa fa-trash"  ></i></button>'
                	
                	
                }
            }
        ]
    });

    $('#agregarPais').on('click', function () {
		$('#modal-registar-pais').modal(
				{
					backdrop: "static",
					keyboard: true,
					show: true,
				}
		);
    });

    $('#agregarPais').on('click', function(){
        		console.log($('#countryForm').serialize());
                $.ajax({type:'post', 
                    url:'countries/register', 
                    data:$('#countryForm').serialize(), 
                    success:function(data){
               	console.log(data)
                    if(data==="ok") {
                        $('#modal-registar-pais').modal("hide");
                        notificaExito("Se ha registrado el pais");
                    }
                    
                    }
                });
            });
    
});