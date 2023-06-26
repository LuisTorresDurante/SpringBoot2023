package uabc.taller.videoclubs.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import uabc.taller.videoclubs.dto.RentalDTO;
import uabc.taller.videoclubs.entidades.Rental;
import uabc.taller.videoclubs.repositorios.RentalRepository;

@Service
public class ReturnService implements IReturnService {

	@Autowired
	private RentalRepository rentalRepository;
	
	private RentalDTO toRentalDTO(Rental r) {
		return RentalDTO.builder()
				.rentalId(r.getRentalId())
						.rentalDate(r.getRentalDate())
						.customerId(r.getCustomer().getCustomerId())
						.nombreCliente(String.format("%1$s %2$s", r.getCustomer().getFirstName(), r.getCustomer().getLastName()))
						.inventoryId(r.getInventory().getInventoryId())
						.tituloPelicula(r.getInventory().getFilm().getTitle())
						.returnDate(r.getReturnDate())
						.staffId(r.getStaff().getStaffId())
						.nombreStaff(String.format("%1$s %2$s", r.getStaff().getFirstName(), r.getStaff().getLastName()))
						.lastUpdate(r.getLastUpdate())
				.build();
	}
	
	@Override
	public List<RentalDTO> obtenerPeliculasParaDevolver(Integer parametro){
		
		return rentalRepository.obtenerPeliculasParaDevolver(parametro).stream().map(r->toRentalDTO(r)).collect(Collectors.toList());
	}
	
	@Override
	public Integer obtenerDuracionRentaParaDevolver(Integer rentaId) {
		return rentalRepository.obtenerDuracionRentaParaDevolver(rentaId);
	}
	
	@Override
	public List<RentalDTO> obtenerPeliculasDevueltas(Integer parametro){
		
		return rentalRepository.obtenerPeliculasDevueltas(parametro).stream().map(r->toRentalDTO(r)).collect(Collectors.toList());
	}
	
	
}
