package uabc.taller.videoclubs.servicios;

import java.util.List;

import uabc.taller.videoclubs.dto.RentalDTO;

public interface IReturnService {

	List<RentalDTO> obtenerPeliculasParaDevolver(Integer parametro);

	Integer obtenerDuracionRentaParaDevolver(Integer rentaId);

	List<RentalDTO> obtenerPeliculasDevueltas(Integer parametro);



}
