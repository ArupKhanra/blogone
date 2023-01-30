package com.BlogApp1.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.BlogApp1.Entity.Comment;
import com.BlogApp1.Entity.Post;
import com.BlogApp1.Exception.ResourceNotFoundException;
import com.BlogApp1.PayLoad.CommentDto;
import com.BlogApp1.Repository.CommentRepository;
import com.BlogApp1.Repository.PostRepository;
import com.BlogApp1.Service.CommentService;


@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private ModelMapper mapper;
	
	
	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.mapper=mapper;
	}


	@Override
	public CommentDto createComment(long postId,CommentDto commentDto) {
		Post post = postRepository.findById(postId).orElseThrow(
				()->new ResourceNotFoundException("post","id",postId)
				);
		Comment comment = mapToComment(commentDto);
		comment.setPost(post);
		Comment newComment = commentRepository.save(comment);
		CommentDto dto = mapToDto(newComment);
		return dto;
	}
	Comment mapToComment(CommentDto commentDto) {
		Comment comment = mapper.map(commentDto, Comment.class);
		
//		Comment comment =new Comment();
//		comment.setName(commentDto.getName());
//		comment.setEmail(commentDto.getEmail());
//		comment.setBody(commentDto.getBody());
		return comment;
		
	}
	CommentDto mapToDto(Comment comment) {
		CommentDto commentDto = mapper.map(comment, CommentDto.class);
		
//		CommentDto commentDto=new  CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setBody(comment.getBody());
//		commentDto.setEmail(comment.getEmail());
		return commentDto;
	}


	@Override
	public List<CommentDto> getCommentByPostId(long postId) {
		List<Comment>comments = commentRepository.findByPostId(postId);
		return comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());
		 
	}


	@Override
	public CommentDto updateComment(long postId, long id, CommentDto commentDto) {
		
		Post post = postRepository.findById(postId).orElseThrow(
				()->new ResourceNotFoundException("post","id",postId)
				);
		Comment comment=commentRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("comment","id",id)
				);
		
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		
		Comment updateComment = commentRepository.save(comment);
		
		return mapToDto(updateComment);
	}


	@Override
	public void deleteComment(long postId, long commentId) {
		
		Post post = postRepository.findById(postId).orElseThrow(
				()->new ResourceNotFoundException("post","id",postId)
				);
		Comment comment=commentRepository.findById(commentId).orElseThrow(
				()->new ResourceNotFoundException("comment","id",commentId)
				);
		commentRepository.deleteById(commentId);
	}

}
