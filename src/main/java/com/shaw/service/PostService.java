package com.shaw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shaw.entity.Category;
import com.shaw.payloads.PostDto;
import com.shaw.payloads.PostResponse;
import com.shaw.entity.User;
import com.shaw.exception.ResourceNotFound;


public interface PostService {

	// Create a new post
    PostDto createPost(PostDto post, Integer userId, Integer categoryId) throws ResourceNotFound;

    // Retrieve a post by its ID
    PostDto getPostById(Integer postId) throws ResourceNotFound;

    // Update an existing post
    PostDto updatePost(PostDto post) throws ResourceNotFound;

    // Delete a post
    void deletePost(Integer postId) throws ResourceNotFound;

    // Retrieve all posts
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    // Find posts by user
    List<PostDto> findPostsByUser(Integer userId) throws ResourceNotFound;

    // Find posts by category
    List<PostDto> findPostsByCategory(Integer categoryId)  throws ResourceNotFound;
    
    // Find posts by search
//    List<PostDto> findPostBySearch(String keyword);
}
