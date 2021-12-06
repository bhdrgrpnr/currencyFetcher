package com.devskiller.tasks.blog.model.dto;

import java.time.LocalDateTime;

public class NewCommentDto {

	private Long postId;

	private String author;

	private String content;

	private LocalDateTime creationDate;

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public NewCommentDto() {
	}

	public NewCommentDto(Long postId, String author, String content, LocalDateTime creationDate) {
		this.postId = postId;
		this.author = author;
		this.content = content;
		this.creationDate = creationDate;
	}
}
