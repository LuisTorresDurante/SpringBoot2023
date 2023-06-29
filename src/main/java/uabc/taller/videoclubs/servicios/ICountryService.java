package uabc.taller.videoclubs.servicios;

import java.util.List;
import uabc.taller.videoclubs.entidades.Country;

public interface ICountryService {

	List<Country> findAll();

	Country findCountryById(Integer id);

}
