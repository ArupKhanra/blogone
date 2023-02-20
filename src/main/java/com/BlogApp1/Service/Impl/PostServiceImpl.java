
package com.BlogApp1.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.BlogApp1.Entity.Post;
import com.BlogApp1.Exception.ResourceNotFoundException;
import com.BlogApp1.PayLoad.PostDto;
import com.BlogApp1.PayLoad.PostResponse;
import com.BlogApp1.Repository.PostRepository;
import com.BlogApp1.Service.PostService;

@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;
    private ModelMapper mapper;


    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }


    @Override
    public PostDto createPost(PostDto postDto) {

//		//create data  in database
//		Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
//        Post postEntity=postRepository.save(post);

//        PostDto dto=new PostDto();
//        dto.setId(postEntity.getId());
//        dto.setTitle(postEntity.getTitle());
//        dto.setContent(postEntity.getContent());
//        dto.setDescription(postEntity.getDescription());

        //uporer ta na likhe  ai vabe krechi ,kintu oi code niche ache ble  tai ai vabe likte parchi.
        Post post = mapToEntity(postDto);
        Post postEntity = postRepository.save(post);
        PostDto dto = mapToDto(postEntity);

        return dto;
    }

    public Post mapToEntity(PostDto postDto) {
        Post post = mapper.map(postDto, Post.class);

//		Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return post;

    }

    public PostDto mapToDto(Post post) {
        PostDto dto = mapper.map(post, PostDto.class);

//		 PostDto dto=new PostDto();
//	        dto.setId(post.getId());
//	        dto.setTitle(post.getTitle());
//	        dto.setContent(post.getContent());
//	        dto.setDescription(post.getDescription());

        return dto;
    }

//Get data

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        PostResponse postResponse = null;
        try{
            postResponse = new PostResponse();
            if(sortBy == null && sortDir == null){
                List<Post> posts = postRepository.findAll();
                List<PostDto> allp = posts.stream().map(this::mapToDto).collect(Collectors.toList());
                postResponse.setContent(allp);
            }else{
                //Ascending and descending order data
                Sort sort = Sort.by(sortBy).ascending();
                //sort data
                Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
                Page<Post> posts = postRepository.findAll(pageable);
                List<Post> content = posts.getContent();
                List<PostDto> contents = content.stream().map(this::mapToDto).collect(Collectors.toList());

                postResponse.setContent(contents);
                postResponse.setPageNo(posts.getNumber());
                postResponse.setPageSize(posts.getSize());
                postResponse.setTotalPage(posts.getTotalPages());
                postResponse.setTotalElements(posts.getTotalElements());
                postResponse.setLast(posts.isLast());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return postResponse;
    }


    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));

        return mapToDto(post);
    }

    //update the record
    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post newPost = postRepository.save(post);
        return mapToDto(newPost);
    }


    @Override
    public void deletePost(long id) {

        //get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", id)
        );
        Optional<Post> findById = postRepository.findById(id);

        postRepository.delete(post);

    }


}
