package com.BlogApp1.Controller;

import java.util.List;

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

import com.BlogApp1.PayLoad.CommentDto;
import com.BlogApp1.Service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	private CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService =commentService ;
	}
	//localhost:8080/api/posts/2/comments          :check postman
     @PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto>createComment(@PathVariable("postId")long postId,@RequestBody CommentDto commentDto){
		CommentDto dto=commentService.createComment(postId, commentDto);
    	 return new ResponseEntity<CommentDto>(dto,HttpStatus.CREATED);
		
	}
     
     ///localhost:8080/api/posts/2/comments/1       : check postman
     @GetMapping("/posts/{postId}/comments")
     public List<CommentDto>getAllCommentById(@PathVariable("postId")long postId){
    	 List<CommentDto> dto = commentService.getCommentByPostId(postId);
    	 return dto;
     }
     
     @PutMapping("/posts/{postId}/comments/{id}")
     public ResponseEntity<CommentDto>updateComment(@PathVariable("postId")long postId,
    		 @PathVariable("id")long id,@RequestBody CommentDto commentDto
    		 ){
    	 CommentDto dto = commentService.updateComment(postId,id,commentDto);
    	 
		return new  ResponseEntity<>(dto,HttpStatus.OK);
    	 
     }
     ///localhost:8080/api/posts/2/comments/1  
     @DeleteMapping("/posts/{postId}/comments/{id}")
     public ResponseEntity<String>deleteComment(@PathVariable("postId")long postId ,
    		 @PathVariable("id")long commentId
    		 ){
    	 commentService.deleteComment(postId,commentId);
		return new ResponseEntity<>("Comment is deleted",HttpStatus.OK);
     }
}
