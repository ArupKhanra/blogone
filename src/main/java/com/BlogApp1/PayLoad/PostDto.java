package com.BlogApp1.PayLoad;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class PostDto {

	

	private Long id;
	@NotNull
	@Size(min=2,message="Post title shoud have at least 2 charecters")
	private String title;
	@NotNull
	@Size(min=10,message="Post description shoud have at least 10 charecters or more")
	private String description;
	@NotNull
	private String content;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
