package uabc.taller.videoclubs.servicios;

import java.util.List;

import uabc.taller.videoclubs.dto.Select2.Select2Result;
import uabc.taller.videoclubs.entidades.Store;

public interface IStoreService {

	List<Store> findAll();

	Store obtenerTiendaPorId(Integer id);

	Select2Result select2();

}
