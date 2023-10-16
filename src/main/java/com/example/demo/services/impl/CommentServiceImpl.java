package com.example.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.CommentDto;
import com.example.demo.repositiories.CommentRepo;
import com.example.demo.repositiories.PostRepo;
import com.example.demo.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
    private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
	Comment comment = 	this.modelMapper.map(commentDto, Comment.class);
	comment.setPost(post);
Comment savedComment =	((CrudRepository<Comment, Integer>) this.commentRepo).save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
		
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment com = ((CrudRepository<Comment, Integer>) this.commentRepo).findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","CommentId",commentId));
    ((CrudRepository<Comment, Integer>) this.commentRepo).delete(com);
	}

}
