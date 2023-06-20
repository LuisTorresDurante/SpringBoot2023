package uabc.taller.videoclubs.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import uabc.taller.videoclubs.servicios.StoreService;

@Controller
@RequestMapping("store")
public class StoreController {

	@Autowired
	private StoreService storeService;
	
//	@GetMapping("select")
//	public Response
}
