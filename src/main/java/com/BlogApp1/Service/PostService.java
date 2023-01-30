package com.BlogApp1.Service;

import java.util.List;

import com.BlogApp1.PayLoad.PostDto;
import com.BlogApp1.PayLoad.PostResponse;

public interface PostService {

	
	    PostDto createPost(PostDto postDto);
	 
	   PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir);
	    
		PostDto getPostById(long id);
		
		PostDto updatePost(PostDto postDto, long id);
		
		void deletePost(long id);
	    
}
