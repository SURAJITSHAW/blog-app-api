package com.shaw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shaw.exception.ResourceNotFound;
import com.shaw.payloads.ApiResponse;
import com.shaw.payloads.PostDto;
import com.shaw.payloads.PostResponse;
import com.shaw.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@PostMapping("/user/{userId}/category/{categoryId}")
    public ResponseEntity<ApiResponse<PostDto>> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId) throws ResourceNotFound {
        PostDto createdPostDto = postService.createPost(postDto, userId, categoryId);
        ApiResponse<PostDto> response = new ApiResponse<>(
            HttpStatus.CREATED.value(),
            "Post created successfully",
            createdPostDto,
            null
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostDto>> getPostById(@PathVariable Integer postId) throws ResourceNotFound {
        PostDto postDto = postService.getPostById(postId);
        ApiResponse<PostDto> response = new ApiResponse<>(
            HttpStatus.OK.value(),
            "Post retrieved successfully",
            postDto,
            null
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostDto>> updatePost(@PathVariable Integer postId, @RequestBody PostDto postDto) throws ResourceNotFound {
        postDto.setId(postId);
        PostDto updatedPostDto = postService.updatePost(postDto);
        ApiResponse<PostDto> response = new ApiResponse<>(
            HttpStatus.OK.value(),
            "Post updated successfully",
            updatedPostDto,
            null
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Integer postId) throws ResourceNotFound {
        postService.deletePost(postId);
        ApiResponse<Void> response = new ApiResponse<>(
            HttpStatus.OK.value(),
            "Post deleted successfully",
            null,
            null
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PostResponse>> getAllPosts(
    			@RequestParam(defaultValue = "10", required = false) int pageSize,
    			@RequestParam(defaultValue = "0", required = false) int pageNumber, 
    			@RequestParam(defaultValue = "id", required = false) String sortBy,
    			@RequestParam(defaultValue = "asc", required = false) String sortDir
    		) {
    	PostResponse posts = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        ApiResponse<PostResponse> response = new ApiResponse<>(
            HttpStatus.OK.value(),
            "All posts retrieved successfully",
            posts,
            null
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<PostDto>>> findPostsByUser(@PathVariable Integer userId) throws ResourceNotFound {
        List<PostDto> posts = postService.findPostsByUser(userId);
        ApiResponse<List<PostDto>> response = new ApiResponse<>(
            HttpStatus.OK.value(),
            "Posts retrieved successfully by user",
            posts,
            null
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<PostDto>>> findPostsByCategory(@PathVariable Integer categoryId) throws ResourceNotFound {
        List<PostDto> posts = postService.findPostsByCategory(categoryId);
        ApiResponse<List<PostDto>> response = new ApiResponse<>(
            HttpStatus.OK.value(),
            "Posts retrieved successfully by category",
            posts,
            null
        );
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<PostDto>> findPostBySearch(@RequestParam String keyword) {
//        List<PostDto> posts = postService.findPostBySearch(keyword);
//        return ResponseEntity.ok(posts);
//    }
}
