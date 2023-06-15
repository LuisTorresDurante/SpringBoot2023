package uabc.taller.videoclubs.servicios;
import java.util.List;
import uabc.taller.videoclubs.entidades.Film;

public interface IFilmService{
	List<Film> findAll();
	List<Film> findAllFirst10();
}
