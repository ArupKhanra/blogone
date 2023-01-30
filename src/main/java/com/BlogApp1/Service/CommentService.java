package com.BlogApp1.Service;

import java.util.List;

import com.BlogApp1.PayLoad.CommentDto;

public interface CommentService {

	CommentDto createComment(long postId,CommentDto commentDto);
	
	List<CommentDto>getCommentByPostId(long postId);

	CommentDto updateComment(long postId, long id, CommentDto commentDto);

	void deleteComment(long postId, long commentId);
}
