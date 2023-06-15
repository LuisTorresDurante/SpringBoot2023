package uabc.taller.videoclubs.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Film> findAllFirst10() {
		// TODO Auto-generated method stub
		return filmRepository.findFirst10ByOrderByFilmId();
	}
	
	
	
}
