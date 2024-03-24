package com.shaw.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
//    page related fields
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalpages;
	private boolean lastPage;
	
	private List<PostDto> content;
}
