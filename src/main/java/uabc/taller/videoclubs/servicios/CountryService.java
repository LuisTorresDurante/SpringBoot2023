package uabc.taller.videoclubs.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.taller.videoclubs.entidades.Country;
import uabc.taller.videoclubs.repositorios.CountryRepository;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> findAll(){
        return countryRepository.findAll();
    }
    
}
