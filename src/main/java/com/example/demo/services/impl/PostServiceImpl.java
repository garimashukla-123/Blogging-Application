package com.example.demo.services.impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Categories;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;
import com.example.demo.repositiories.CategoryRepo;
import com.example.demo.repositiories.PostRepo;
import com.example.demo.repositiories.UserRepo;
import com.example.demo.services.PostService;
@Service
public class PostServiceImpl implements PostService {
@Autowired
private UserRepo userRepo;
@Autowired
private CategoryRepo categoryRepo;
@Autowired
private ModelMapper modelMapper;
@Autowired
private PostRepo postRepo;
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
		Categories category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Categories" ,"category id",categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newpost = this.postRepo.save(post);
		
		return this.modelMapper.map( newpost ,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImagename());
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}
     // delete post
	
	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
		this.postRepo.delete(post);
		
		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
	List<PostDto> postDtos = 	allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	PostResponse postResponse = new PostResponse();
	postResponse.setContent(postDtos);
	postResponse.setPageNumber(pagePost.getNumber());
	postResponse.setPageSize(pagePost.getSize());
	postResponse.setTotalElements(pagePost.getTotalElements());
	postResponse.setTotalPages(pagePost.getTotalPages());
	postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub

		Post  post = 	this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}
	
	//get by category
	

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
Categories cat =this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Categories","category id",categoryId));
	List<Post> posts = this.postRepo.findByCategory(cat);
	List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
	return postDtos;
	}
	
	
	// get by user
	

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
	User user = 	this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
	List<Post> posts = this.postRepo.findByUser(user);
	List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(posts,  PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto>searchPosts(String keyword) {
		// TODO Auto-generated method stub
	List<Post> posts = 	 this.postRepo.searchByTitle(keyword);
	List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(posts,  PostDto.class)).collect(Collectors.toList());
	return postDtos;
		
	}

	

	

}
