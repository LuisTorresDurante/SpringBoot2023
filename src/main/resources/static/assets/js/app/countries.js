var tabla = null;
$(document).ready(function() {


    var countryIdsArray = [];

    tabla = $('#tableCountries').DataTable({
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
                countryIdsArray.push(row.countryName);
                 return	'<button class="btn blue-btn buscar-pais-row"  countryId="'+row.countryId +'" onClick="BuscarPais('+row.countryId+')"><i class="fa fa-search"  ></i></button>'
                	
                	
                }
            }
        ]
    });


    // $('.buscar-pais-row').on('click', function() {
        
    //   });

    $('#mostrarModalRegistrar').on('click', function () {
		$('#modal-registar-pais').modal(
			{
				backdrop: "static",
				keyboard: true,
				show: true,
			}
		);
        $("#inputCountryName").val("");

    });

    $('#btnAgregarPais').on('click', function(){
        let countryName = $("#inputCountryName").val();
        
        if(countryName == null || countryName == ""){
            Lobibox.notify("warning", {
                msg: "Ingrese un nombre valido",
                icon: false,
                position: 'top right'
                
            })
        }else{
            if(verificarDuplicidad(countryIdsArray, countryName)){
                notificaError("Pais repetido");
            }else{
                console.log(countryName);
                $.ajax({    
                        type:'post', 
                        url:'countries/register', 
                        data: {'countryName': countryName},
                        success:function(data){
                        console.log(data)
                        if(data==="ok") {
                            $("#inputCountryName").val("");
                            tabla.ajax.reload();
                            $('#modal-registar-pais').modal("hide");
                            notificaExito("Se ha registrado el pais");
                        }else{
                            notificaError("No se ha registrado el pais");
                        }
                    }
                });
                
            }
        }
     });
});

function verificarDuplicidad(countryIdsArray, countryName) {
    console.log(countryIdsArray);
    for (const element of countryIdsArray) {
      if (element.toLowerCase() === countryName.toLowerCase()) {
        console.log(element.toLowerCase() + " is equal to " + countryName.toLowerCase());
        return true; // Break the function and return true
      }
    }
    return false; // No duplicate found, return false
  }

function BuscarPais(countryId){
    const modal = $('#modal-detalles-pais');
    $.ajax({type:'get', 
    url:"../countries/detallesCountry/"+countryId, 
    success:function(data2){
        data = data2.data;
        console.log(data);
        console.log(data2)
        modal.find(".modal-header span#exampleModalLabel").html(`Detalles del pais`)
       document.getElementById("inputCountryId").value = data.countryId !="null" ? data.countryId : "";
       document.getElementById("inputCountryNameViewOnly").value = data.countryName !="null" ? data.countryName : "";
       document.getElementById("inputCountryLastUpdated").value = data.lastUpdate!="null" ? data.lastUpdate : "";
       
       modal.modal("show");
    }
});
}
