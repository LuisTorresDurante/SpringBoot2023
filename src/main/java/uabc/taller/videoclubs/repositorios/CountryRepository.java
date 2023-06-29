package uabc.taller.videoclubs.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uabc.taller.videoclubs.entidades.Country;


@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>{
    
	
	public List<Country> findAll();
	
	@Query(value="select * from country c where country_id = ?1", nativeQuery = true)
	public Country findCountryById(Integer countryId);
}
