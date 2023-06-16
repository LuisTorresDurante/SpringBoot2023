package uabc.taller.videoclubs.dto;

import java.util.List;

import uabc.taller.videoclubs.entidades.Film;

public interface CatalogoIndex {
    Integer getFilmId();

    String getTitle();

    String getCategory();

    String getActor();

    Integer getCopies();

	static List<Film> buscadorPeliculas(String string, int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
}
