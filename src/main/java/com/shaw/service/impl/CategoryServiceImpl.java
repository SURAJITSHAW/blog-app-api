package com.shaw.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaw.entity.Category;
import com.shaw.exception.ResourceNotFound;
import com.shaw.payloads.CategoryDto;
import com.shaw.repository.CategoryRepo;
import com.shaw.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
    private CategoryRepo CategoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto getCategory(Integer id) throws ResourceNotFound {
        Category category = CategoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Category", "id", id));
        return categoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = CategoryRepo.findAll();
        return categories.stream()
                .map(this::categoryToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Integer id) throws ResourceNotFound {
        Category category = CategoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Category", "id", id));
        CategoryRepo.delete(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) throws ResourceNotFound {
        Category category = CategoryRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Category", "id", id));

        category.setCatgoryTitle(categoryDto.getCatgoryTitle());
        category.setCatgeDescription(categoryDto.getCatgeDescription());
        // Set other properties if needed

        Category updatedCategory = CategoryRepo.save(category);
        return categoryToDto(updatedCategory);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = dtoToCategory(categoryDto);
        Category savedCategory = CategoryRepo.save(category);
        return categoryToDto(savedCategory);
    }

    private CategoryDto categoryToDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    private Category dtoToCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

}
