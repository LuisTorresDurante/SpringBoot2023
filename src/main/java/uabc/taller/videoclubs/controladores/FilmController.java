package uabc.taller.videoclubs.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import uabc.taller.videoclubs.dto.FilmDetails;
import uabc.taller.videoclubs.dto.FilmPaginationDTO;
import uabc.taller.videoclubs.dto.FilmRegisterDTO;
import uabc.taller.videoclubs.dto.Paginacion;
import uabc.taller.videoclubs.servicios.IFilmService;
import uabc.taller.videoclubs.util.CheckAvailability;

@Controller
@RequestMapping("films")
public class FilmController {

	@Autowired
	private IFilmService filmService;

	Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping(value = "{filmId}")
	@ResponseBody
	public ResponseEntity<FilmDetails> cargarDetalles(@PathVariable Integer filmId) {
		Optional<FilmDetails> film = filmService.findById(filmId);
		return film.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/poster/{id}")
	public ResponseEntity<Resource> obtenerPoster(@PathVariable("id") Integer id) {
		Optional<byte[]> imagenBytes = filmService.findImageById(id);
		if (imagenBytes.isPresent()) {
			Resource imagenResource = new ByteArrayResource(imagenBytes.get());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.valueOf("image/" + filmService.obtenerFormatoImagen(imagenBytes.get())));
			return ResponseEntity.ok().headers(headers).body(imagenResource);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("buscar")
	@ResponseBody
	public ResponseEntity<FilmPaginationDTO> buscadorPeliculas(@RequestParam("texto") String texto,
			@RequestParam(value = "page", defaultValue = "0") Integer page) {
		try {
			FilmPaginationDTO data = filmService.buscadorPeliculas(texto, page);
			return ResponseEntity.ok(data);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}
		return ResponseEntity.internalServerError().build();
	}
	
	@GetMapping()
	public String films(Model modelo) {
		return "/views/film/films";
	}
	
	@GetMapping("list")
	public ResponseEntity<Paginacion> lista(){
		
		List<List<String>> result = filmService.findByOrder().stream()
				.map(i-> List.of(CheckAvailability.check(i.getTitle()),
						CheckAvailability.check(i.getCategory()),
						CheckAvailability.check(i.getCopies()),
						CheckAvailability.check(i.getFilmId()))).collect(Collectors.toList());
		
		Paginacion p = new Paginacion();
		p.setData(result);
		return ResponseEntity.ok(p);
						
	}
	
	@PostMapping()
	public ResponseEntity<Boolean> agregarDatos(@RequestBody FilmRegisterDTO film){
		boolean resultado = false;
		resultado = filmService.save(film);
		return ResponseEntity.ok(resultado);
	}
	
	
}
