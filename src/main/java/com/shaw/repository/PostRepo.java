package com.shaw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shaw.entity.Category;
import com.shaw.entity.Post;
import com.shaw.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	// Custom finder method to find posts by user
    List<Post> findByUser(User user);
    
    // Custom finder method to find posts by category
    List<Post> findByCategory(Category category);
}
