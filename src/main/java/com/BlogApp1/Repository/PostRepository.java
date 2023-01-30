package com.BlogApp1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BlogApp1.Entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
