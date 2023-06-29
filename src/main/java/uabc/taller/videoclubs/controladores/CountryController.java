package uabc.taller.videoclubs.controladores;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import uabc.taller.videoclubs.entidades.Country;
import uabc.taller.videoclubs.entidades.Customer;
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
	
	@GetMapping("/lista")
	@ResponseBody
	public HashMap<String, Object> getCountryList() {
	    List<Country> countries = countryService.findAll();
	    HashMap<String, Object> response = new HashMap<>();
	    response.put("data", countries);
	    
	    return response;
	}
	
	//Request mapping pa recibir los datos serializados
	@PostMapping(value = "register")
	@ResponseBody
	public String registroCountry(
			@ModelAttribute(name = "country") @Valid Country country,
			BindingResult bindingResult) {
//
//		Date fecha = new Date();
//		Timestamp fechaStamp = new Timestamp(fecha.getTime());
//		long timeInMilliSeconds = fecha.getTime();
//		java.sql.Date fechaSql = new java.sql.Date(timeInMilliSeconds);

		country.getCountryId();
		country.getCountryName();
		country.getLastUpdate();

		try {
			countryService.save(country);
			return "ok";
		} catch (Exception e) {
			return "no OK";
		}
		
	}


	
	
	

}
