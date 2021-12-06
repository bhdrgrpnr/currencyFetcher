package com.devskiller.tasks.blog.service;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.repository.CommentRepository;
import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;

@Service
public class CommentService {

	private final CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(Long postId) {
		return commentRepository.findByPostId(postId).stream().map(comment -> new CommentDto(comment.getId(), comment.getComment(), comment.getAuthor(), comment.getCreationDate())).collect(Collectors.toList());
	}

	/**
	 * Creates a new comment
	 *
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if there is no blog post for passed newCommentDto.postId
	 */
	public Long addComment(NewCommentDto newCommentDto) {
		return commentRepository.save(new Comment(newCommentDto.getContent(), newCommentDto.getAuthor(), LocalDateTime.now(), newCommentDto.getPostId())).getId();
	}
}
