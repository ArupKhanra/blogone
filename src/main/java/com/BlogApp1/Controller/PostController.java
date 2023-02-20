package com.BlogApp1.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp1.PayLoad.PostDto;
import com.BlogApp1.PayLoad.PostResponse;
import com.BlogApp1.Service.PostService;
import com.BlogApp1.utils.AppConstants;


@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

           return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
           // pre check krte hbe// return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage());
        }
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //get data

//    @GetMapping
//    public PostResponse getAllPosts(
//            //localhost:8080/api/posts?pageNo=2&pageSize=5 ai url dia pagination check postman
//            ///pagination table used
//            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,
//                    required = false) int pageNo,
//            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE,
//                    required = false) int pageSize,
//
//            ///sort data
//            ///	localhost:8080/api/posts?pageNo=2&pageSize=5&sortBy=title ai vabe check krte hbe
//            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY,
//                    required = false) String sortBy,
//
//            ////Ascending and descending order data
//            /// localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=desc
//            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR,
//                    required = false) String sortDir
//
//
//    ) {
//        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
//   }

    @GetMapping
    public PostResponse getAllPosts() {
		return postService.getAllPosts(0, 0, null, null);

    }
 


    //http//localhost:8080/api/posts/1000   with test postman
    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {

        return ResponseEntity.ok(postService.getPostById(id));
    }

    //http//localhost:8080/api/posts/1000   update  the record
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id) {
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //delete the record rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePost(id);

        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }

}

