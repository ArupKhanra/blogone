package com.BlogApp1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApp1.Entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
     
	
	List<Comment>findByPostId(long postId);
}
