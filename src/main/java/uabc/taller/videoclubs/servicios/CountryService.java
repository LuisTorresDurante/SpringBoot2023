package uabc.taller.videoclubs.servicios;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.taller.videoclubs.entidades.Country;
import uabc.taller.videoclubs.repositorios.CountryRepository;

@Service
public class CountryService implements ICountryService {	
    private final CountryRepository countryRepository;
    
    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll(){
        return countryRepository.findAll();
    }
    
    @Override
    public Country findCountryById(Integer id){
        return countryRepository.findCountryById(id);
    }

	public Country save(Country country) {
		return countryRepository.save(country);
	}

	
}

