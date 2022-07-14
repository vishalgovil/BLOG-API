package blogappapis.services;

import java.util.List;

import blogappapis.payloads.CategoryDto;

public interface CategoryService {

	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	public void deleteCategory(Integer categoryId);
	
	public CategoryDto getCategory(Integer categoryId);
	
	List<CategoryDto> getCategories();
	
}
