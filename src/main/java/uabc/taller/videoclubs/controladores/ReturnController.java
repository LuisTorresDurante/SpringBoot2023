package uabc.taller.videoclubs.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uabc.taller.videoclubs.dto.RentalDTO;
import uabc.taller.videoclubs.servicios.IReturnService;

@Controller
@RequestMapping(value = {"/return", "return"})
public class ReturnController {
	
	@Autowired
	private IReturnService returnService;
	
	private HashMap<String, Object> response(Boolean res, Object data, String msg){
		HashMap<String, Object > response = new HashMap<>();
		response.put("response", response);
		response.put("Message", response);
		response.put("data", response);
		return response;
	}
	
	@GetMapping("/cargar-tabla")
	@ResponseBody
	public List<RentalDTO> obtenerPeliculasParaDevolver(
			@RequestParam("parametroBusqueda") Integer parametroBusqueda,
			@RequestParam("consulta") Boolean consulta			
			){
		List<RentalDTO> resultado = new ArrayList<>();
		
		if(consulta) {
			resultado = returnService.obtenerPeliculasDevueltas(parametroBusqueda);
		}else {
			resultado = returnService.obtenerPeliculasParaDevolver(parametroBusqueda);
		}
		return resultado;
	}
	
	@GetMapping("/obtener-duracion-renta")
	@ResponseBody
	public Integer obtenerDuracionRentaParaDevolver(@RequestParam("rentalId") Integer rentalId) {
		
		return returnService.obtenerDuracionRentaParaDevolver(rentalId);
	}
	
	
	@PostMapping(value= {"/registrar-devolucion-masivo","registrar-devolucion-masivo"})
	@ResponseBody
	public HashMap<String, Object> registrarDevolucionMasivo(HttpServletRequest request){
		String msg = "";
		boolean resultado = false;
		try {
			JSONArray jsonRentas = new JSONArray(request.getParameter("rental"));
			List<RentalDTO> rentals = new ArrayList<>();
			jsonRentas.forEach(item -> {
				JSONObject itemObjeto = new JSONObject(item.toString());
				rentals.add(RentalDTO.builder()
						.rentalId(itemObjeto.getInt("rentalId"))
						.build());
			String returnDate = request.getParameter("returnDate");
			String multaGenerada = request.getParameter("multaGenerada");
			Integer customerId = Integer.parseInt(request.getParameter("customerId"));
			String resultadoRegistro = returnService.registrarDevolucion(rentals, returnDate, multaGenerada, customerId);
			resultado = resultadoRegistro == "OK";
			msg = resultadoRegistro == "OK" ? "Devoluci&oacute;n registrada" : "Error al procesar los datos"
			
				
			});
		}catch(Exception e) {
			 msg = "Los datos son invalidos";
		}
		return null;
		
		
	}
	
}
