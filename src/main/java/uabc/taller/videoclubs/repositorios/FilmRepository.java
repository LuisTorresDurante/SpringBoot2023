package uabc.taller.videoclubs.repositorios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import uabc.taller.videoclubs.entidades.Film;


public interface FilmRepository extends JpaRepository<Film, Integer>{
	List<Film> findFirst10ByOrderByFilmId();
}
