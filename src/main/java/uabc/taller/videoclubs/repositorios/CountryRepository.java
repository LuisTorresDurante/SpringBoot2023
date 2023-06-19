package uabc.taller.videoclubs.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import uabc.taller.videoclubs.entidades.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{
    
}
