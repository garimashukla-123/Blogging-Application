package com.example.demo.services;

import java.util.List;

import com.example.demo.payloads.CategoryDto;

public interface categoryService {
	 CategoryDto createCategory(CategoryDto categoryDto);
	 CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	  void deleteCategory(Integer categoryDto);
	 CategoryDto getCategory(Integer categoryId);
	 List<CategoryDto> getCategories();

}
