package com.shaw.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	
	private Integer id;
	
	@NotEmpty
	private String catgoryTitle;
	@NotEmpty
	private String catgeDescription;
}
