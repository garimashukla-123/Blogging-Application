package com.example.demo.repositiories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Categories;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.payloads.PostDto;

public interface  PostRepo extends JpaRepository<Post,Integer> {
	
	List<Post> findByCategory(Categories catogry);
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key")String title);
	//List<Post> findByTitleContaining(String keyword);
	List<Post> findByUser(User user);

}
