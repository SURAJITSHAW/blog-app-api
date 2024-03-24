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
import org.springframework.web.bind.annotation.RestController;

import com.shaw.exception.ResourceNotFound;
import com.shaw.payloads.PostDto;
import com.shaw.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId) throws ResourceNotFound{
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED); 
	}
	
	@GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) throws ResourceNotFound {
        PostDto postDto = postService.getPostById(postId);
        return ResponseEntity.ok(postDto);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId, @RequestBody PostDto postDto) throws ResourceNotFound {
        postDto.setId(postId);
        PostDto updatedPostDto = postService.updatePost(postDto);
        return ResponseEntity.ok(updatedPostDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer postId) throws ResourceNotFound {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> findPostsByUser(@PathVariable Integer userId) throws ResourceNotFound {
        List<PostDto> posts = postService.findPostsByUser(userId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> findPostsByCategory(@PathVariable Integer categoryId) throws ResourceNotFound {
        List<PostDto> posts = postService.findPostsByCategory(categoryId);
        return ResponseEntity.ok(posts);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<PostDto>> findPostBySearch(@RequestParam String keyword) {
//        List<PostDto> posts = postService.findPostBySearch(keyword);
//        return ResponseEntity.ok(posts);
//    }
}
