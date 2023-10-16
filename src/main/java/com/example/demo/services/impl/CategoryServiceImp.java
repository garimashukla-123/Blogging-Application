package com.example.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Categories;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.CategoryDto;
import com.example.demo.repositiories.CategoryRepo;
import com.example.demo.services.categoryService;
@Service
public class CategoryServiceImp implements categoryService{
  private CategoryRepo categoryRepo;
  private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Categories cat = this.modelMapper.map(categoryDto,Categories.class);
		Categories addedCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat,  CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Categories cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category" , "Category Id",categoryId ));
	cat.setCategoryTitle(categoryDto.getCategoryTitle());
	cat.setCategoryDiscription(categoryDto.getCategoryDescription());
	Categories updatedcat = this.categoryRepo.save(cat);
	return this.modelMapper.map(updatedcat, CategoryDto.class);
		
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Categories cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","category id",categoryId));
		this.categoryRepo.delete(cat);
		//return null;
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Categories cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","category id",categoryId));
		//this.categoryRepo.delete(cat);
		return this.modelMapper.map(cat,  CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		// TODO Auto-generated method stub
		List<Categories> category = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = category.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return null;
	}

}
