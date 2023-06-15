package uabc.taller.videoclubs.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uabc.taller.videoclubs.servicios.IFilmService;

@Controller
public class IndexController {

	@Autowired
	private IFilmService filmService;
	
	
	
	@GetMapping("/")
	public String index(Model modelo) {
		modelo.addAttribute("films", filmService.findAllFirst10());
		return "views/index";
	}
}
