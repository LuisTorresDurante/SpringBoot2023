package uabc.taller.videoclubs.controladores;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import uabc.taller.videoclubs.entidades.Country;
import uabc.taller.videoclubs.servicios.CountryService;

@Controller
@RequestMapping("countries")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping()
	public String vistaPrincipal() {
		return "views/countries/countries";
	}
	
	private HashMap<String, Object> response(Boolean res, Object data, String message) {
        HashMap<String, Object> _response = new HashMap<>();
        _response.put("response", res);
        _response.put("message", message);
        _response.put("data", data);
        return _response;
    }
	
	@GetMapping("/lista")
	@ResponseBody
	public HashMap<String, Object> getCountryList() {
	    List<Country> countries = countryService.findAll();
	    HashMap<String, Object> response = new HashMap<>();
	    response.put("data", countries);
	    
	    return response;
	}
	
	//Request mapping pa recibir los datos serializados
	@PostMapping(value = "/register")
	@ResponseBody
	public String registroCountry(@RequestParam String countryName) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		System.out.print(countryName);
		Country country = new Country();
		country.setCountryName(countryName);
		country.setLastUpdate(timestamp);
		

		try {
			countryService.save(country);
			return "ok";
		} catch (Exception e) {
			return "no OK";
		}
		
		
	}
	
	@RequestMapping("detallesCountry/{id}")
    @ResponseBody
    public HashMap<String, Object> detallesCountry(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) {
        if (id == null) {
            return response(false, null, "Datos invalidos");
        }else {
        	Country country = countryService.findCountryById(id);
        	return response(country != null, country, country == null ? "Registro no encontrado" : "");
        }
    }


	
	
	

}
