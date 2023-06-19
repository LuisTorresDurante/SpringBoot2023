package uabc.taller.videoclubs.servicios;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.taller.videoclubs.dto.CatalogoIndex;
import uabc.taller.videoclubs.dto.FilmDetails;
import uabc.taller.videoclubs.dto.FilmPaginationDTO;
import uabc.taller.videoclubs.dto.film_proyection.FilmP;
import uabc.taller.videoclubs.entidades.Film;
import uabc.taller.videoclubs.repositorios.FilmRepository;
import uabc.taller.videoclubs.repositorios.FilmRepositoryImpl;
import uabc.taller.videoclubs.util.FilmDetailsToFilmPConverter;

@Service
public class FilmService implements IFilmService {

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private FilmRepositoryImpl filmRepositoryImpl;

	@Override
	public Optional<byte[]> findImageById(Integer id) {
		Optional<Film> f = filmRepository.findById(id);
		return f.map(Film::getImage);
	}

	@Override
	public String obtenerFormatoImagen(byte[] imagenData) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(imagenData);
			ImageInputStream iis = ImageIO.createImageInputStream(bais);
			Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
			if (iter.hasNext()) {
				ImageReader reader = iter.next();
				return reader.getFormatName().toLowerCase();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "png";
	}

	@Override
	public FilmPaginationDTO buscadorPeliculas(String texto, Integer page) {
		Integer limit = 5;
		Integer offset = page * limit;

		List<CatalogoIndex> catalogo = filmRepository.buscadorPeliculas(texto.replace(" ", " & "), limit, offset);
		Long total = filmRepository.countBuscadorPeliculas(texto.replace(" ", " & "));
		Integer totalPages = (int) Math.ceil((double) total / (double) limit);
		return FilmPaginationDTO.builder().data(catalogo).page(page).totalPages(totalPages).build();
	}

	@Override
	public Optional<FilmDetails> findById(Integer id) {

		Optional<FilmP> f = filmRepository.findByFilmId(id);
		if (f.isPresent()) {
			FilmDetails fd = new FilmDetailsToFilmPConverter().fromEntity(f.get());
			fd.setSpecialFeatures(filmRepositoryImpl.getFilmSpecialFeatures(id));
			return Optional.of(fd);
		}
		return Optional.empty();
	}
	
	@Override
	public List<CatalogoIndex> findByOrder(){
		return filmRepository.obtenerPeliculas();
	}

}
