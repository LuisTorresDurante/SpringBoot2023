package uabc.taller.videoclubs.controladores;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uabc.taller.videoclubs.dto.FilmPaginationDTO;
import uabc.taller.videoclubs.servicios.FilmService;

@Controller
@RequestMapping("films")
public class FilmController {
					//org.slf4j
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private FilmService filmService;
	
	@GetMapping("buscar")
	@ResponseBody
	public ResponseEntity<FilmPaginationDTO> buscadorPeliculas(
			@RequestParam("texto") String texto,
			@RequestParam(value = "page", defaultValue = "0") Integer page){
		
		try {
			FilmPaginationDTO data = filmService.buscadorPeliculas(texto, page);
			return ResponseEntity.ok(data);
		}catch(Exception e){
			logger.error(e.getMessage());
			
		}
		
		return ResponseEntity.internalServerError().build();
	}
	
    @GetMapping("/poster/{id}")
    public ResponseEntity<Resource> obtenerPoster(@PathVariable("id") Integer id) {
        Optional<byte[]> imagenBytes = filmService.findImageById(id);
        if (imagenBytes.isPresent()) {
            Resource imagenResource = new ByteArrayResource(imagenBytes.get());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf("image/" + filmService.obtenerFormatoImagen(imagenBytes.get())));
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(imagenResource);
        }
        return ResponseEntity.notFound().build();
    }

	
	
	
}
