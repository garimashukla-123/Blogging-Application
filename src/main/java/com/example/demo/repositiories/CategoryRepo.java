package com.example.demo.repositiories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Categories;
//import com.example.demo.payloads.CategoryDto;

public interface CategoryRepo extends JpaRepository<Categories,Integer>{

	//void delete(Categories cat);
	

}
