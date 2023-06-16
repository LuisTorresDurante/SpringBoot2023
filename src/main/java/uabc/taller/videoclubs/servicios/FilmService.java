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
import uabc.taller.videoclubs.dto.FilmPaginationDTO;
import uabc.taller.videoclubs.entidades.Film;
import uabc.taller.videoclubs.repositorios.FilmRepository;

@Service
public class FilmService implements IFilmService{
	
	@Autowired
	private FilmRepository filmRepository;

	@Override
	public List<Film> findAll() {
		// TODO Auto-generated method stub
		return filmRepository.findAll();
	}
	
	@Override
	public FilmPaginationDTO buscadorPeliculas(String text, Integer page) {
		Integer limit = 4;
		Integer offset = page * limit;
		
		List<CatalogoIndex> catalogo = filmRepository.buscadorPeliculas(text.replace(" ", " &"), limit, offset);
		Long total = filmRepository.countBuscadorPeliculas(text.replace(" ", " &"));
		Integer totalPages = (int) Math.ceil((double) total / (double) limit);
		return FilmPaginationDTO.builder().data(catalogo).page(page).totalPages(totalPages).build();
				
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
	public Optional<byte[]> findImageById(Integer id){
		
		Optional<Film> f = filmRepository.findById(id);
		return f.map(Film::getImage);
	}
}
