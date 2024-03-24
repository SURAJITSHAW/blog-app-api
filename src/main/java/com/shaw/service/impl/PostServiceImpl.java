package com.shaw.service.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shaw.entity.Category;
import com.shaw.entity.Post;
import com.shaw.entity.User;
import com.shaw.exception.ResourceNotFound;
import com.shaw.payloads.PostDto;
import com.shaw.payloads.PostResponse;
import com.shaw.repository.CategoryRepo;
import com.shaw.repository.PostRepo;
import com.shaw.repository.UserRepo;
import com.shaw.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) throws ResourceNotFound {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "id", userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Category", "id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setUser(user);
		post.setCategory(category);
		post.setCreatedAt(LocalDateTime.now());
		post.setImageName("default.png");
		
		Post newPost = this.postRepo.save(post);
		
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
    public PostDto getPostById(Integer postId) throws ResourceNotFound {
        Post post = postRepo.findById(postId)
                            .orElseThrow(() -> new ResourceNotFound("Post", "id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto) throws ResourceNotFound {
        Post post = postRepo.findById(postDto.getId())
                            .orElseThrow(() -> new ResourceNotFound("Post", "id", postDto.getId()));

        // Update post fields
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        // Update other fields as needed

        // Save and return updated post
        return modelMapper.map(postRepo.save(post), PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) throws ResourceNotFound {
        Post post = postRepo.findById(postId)
                            .orElseThrow(() -> new ResourceNotFound("Post", "id", postId));
        postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
    	
    	Sort sort = (sortDir.equalsIgnoreCase("asc") == true ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
    	
    	Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
    	
        Page<Post> all = postRepo.findAll(pageable);
        List<Post> posts = all.getContent();
        
        List<PostDto> postDtos = posts.stream()
                    .map(post -> modelMapper.map(post, PostDto.class))
                    .collect(Collectors.toList());
        
        PostResponse postResponse = new PostResponse();
        
        postResponse.setPageNumber(all.getNumber());
        postResponse.setPageSize(all.getSize());
        postResponse.setLastPage(all.isLast());
        postResponse.setTotalpages(all.getTotalPages());
        postResponse.setTotalElements(all.getTotalElements());
        postResponse.setContent(postDtos);
        
        return postResponse;
    }

    @Override
    public List<PostDto> findPostsByUser(Integer userId) throws ResourceNotFound {
    	User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "id", userId));
		
        List<Post> posts = postRepo.findByUser(user);
        return posts.stream()
                    .map(post -> modelMapper.map(post, PostDto.class))
                    .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostsByCategory(Integer categoryId) throws ResourceNotFound {
    	Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Category", "id", categoryId));
		
        List<Post> posts = postRepo.findByCategory(category);
        return posts.stream()
                    .map(post -> modelMapper.map(post, PostDto.class))
                    .collect(Collectors.toList());
    }

//    @Override
//    public List<PostDto> findPostBySearch(String keyword) {
//        List<Post> posts = postRepo.findByTitleContainingOrContentContaining(keyword, keyword);
//        return posts.stream()
//                    .map(post -> modelMapper.map(post, PostDto.class))
//                    .collect(Collectors.toList());
//    	return null;
//    }

}
