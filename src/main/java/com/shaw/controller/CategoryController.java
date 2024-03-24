package com.shaw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaw.exception.ResourceNotFound;
import com.shaw.payloads.ApiResponse;
import com.shaw.payloads.CategoryDto;
import com.shaw.service.impl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
    private CategoryServiceImpl categoryService;

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> getCategory(@PathVariable("id") Integer id) {
	    try {
	        CategoryDto categoryDto = categoryService.getCategory(id);
	        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Category retrieved successfully", categoryDto, null));
	    } catch (ResourceNotFound ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Category not found", null, null));
	    }
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategory() {
	    List<CategoryDto> categories = categoryService.getAllCategory();
	    return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "All categories retrieved successfully", categories, null));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<CategoryDto>> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
	    CategoryDto createdCategory = categoryService.createCategory(categoryDto);
	    return ResponseEntity.status(HttpStatus.CREATED)
	            .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Category created successfully", createdCategory, null));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(@PathVariable("id") Integer id, @Valid @RequestBody CategoryDto categoryDto) {
	    try {
	        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, id);
	        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Category updated successfully", updatedCategory, null));
	    } catch (ResourceNotFound ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Category not found", null, null));
	    }
	}


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable("id") Integer id) {
        try {
            categoryService.deleteCategory(id);
            // Category deleted successfully, return 200 OK with ApiResponse
            return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "Category deleted successfully", null, null));
        } catch (ResourceNotFound ex) {
            // Category not found, return 404 Not Found with ApiResponse
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Category not found", null, null));
        } catch (Exception e) {
            // Other exceptions, return 500 Internal Server Error with ApiResponse
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null, null));
        }
    }


}
