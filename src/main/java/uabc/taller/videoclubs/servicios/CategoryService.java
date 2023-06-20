package uabc.taller.videoclubs.servicios;

import java.util.List;

import uabc.taller.videoclubs.dto.Select2.Select2Data;
import uabc.taller.videoclubs.dto.Select2.Select2Result;
import uabc.taller.videoclubs.entidades.Category;
import uabc.taller.videoclubs.repositorios.CategorytRepository;

public class CategoryService implements ICategoryService {
	
	@Override
	public Select2Result select2(String term) {
		public CategoryRepository categoryRepository;
		List<Category> category = categoryRepository.finAll();
		
//		List<Select2Data> select2Data = 
		
	}

}
