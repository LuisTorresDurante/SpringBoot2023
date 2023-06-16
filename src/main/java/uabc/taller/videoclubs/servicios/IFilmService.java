package uabc.taller.videoclubs.servicios;
import java.util.List;
import java.util.Optional;

import uabc.taller.videoclubs.dto.FilmPaginationDTO;
import uabc.taller.videoclubs.entidades.Film;

public interface IFilmService{
	List<Film> findAll();
	FilmPaginationDTO buscadorPeliculas(String text, Integer page);
	Optional<byte[]> findImageById(Integer id);
	String obtenerFormatoImagen(byte[] imagenData);
	
}
