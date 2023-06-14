package uabc.taller.videoclubs.controladores;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uabc.taller.videoclubs.entidades.Staff;

@Controller
public class LogicController {
	@GetMapping("/login")
	public String login (@RequestParam(value = "error", required = false) String error
			,Model model
			,Principal principal) {
	
	model.addAttribute("usuario", new Staff());
	
		if(error != null) {
			model.addAttribute("usuario", "Nombre de usuario o contraseña incorrecta");
		}
		return "views/login/login";
	}
}
