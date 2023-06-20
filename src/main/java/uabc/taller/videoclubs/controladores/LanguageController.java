package uabc.taller.videoclubs.controladores;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uabc.taller.videoclubs.dto.Select2.Select2Result;
import uabc.taller.videoclubs.servicios.ILanguageService;
import uabc.taller.videoclubs.servicios.StoreService;

@Controller
@RequestMapping("language/select")
public class LanguageController {

	@Autowired
	private ILanguageService languageService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@GetMapping("select")
	public ResponseEntity<Select2Result> listar(@Param("search") String search){
		
		try {
			Select2Result select = languageService.select2();
			return ResponseEntity.ok(select);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
