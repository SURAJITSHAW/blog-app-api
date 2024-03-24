package com.shaw.payloads;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private Integer id;

    private String title;

    private String content;
    private String imageName;

    private UserDto user;

    private CategoryDto category;

    private LocalDateTime createdAt;
}
