package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Post;
import com.example.demo.payloads.PostDto;
import com.example.demo.payloads.PostResponse;

public interface PostService {
PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);
PostDto updatePost(PostDto postDto,Integer postId);
void deletePost(Integer postId);
PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
PostDto getPostById(Integer postId);
List<PostDto> getPostsByCategory(Integer categoryId); 
List<PostDto> getPostsByUser(Integer userId);

//search post
List<PostDto> searchPosts(String keyword);

	
}

