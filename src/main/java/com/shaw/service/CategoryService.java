package com.shaw.service;

import java.util.List;

import com.shaw.exception.ResourceNotFound;
import com.shaw.payloads.CategoryDto;

public interface CategoryService {
	// get a category
	CategoryDto getCategory(Integer id) throws ResourceNotFound;

	// get all category
	List<CategoryDto> getAllCategory();

	// delete category
	void deleteCategory(Integer id) throws ResourceNotFound;

	// update category
	CategoryDto updateCategory(CategoryDto category, Integer id) throws ResourceNotFound;

	// create category
	CategoryDto createCategory(CategoryDto category);
}
