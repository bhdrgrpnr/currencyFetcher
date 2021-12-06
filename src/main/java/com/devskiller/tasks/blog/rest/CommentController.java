package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.service.CommentService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/posts")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping(value = "/{postId}/comments")
	@ResponseStatus(HttpStatus.OK)
	public List<CommentDto> getCommentForPost(@PathVariable Long postId) {
		return commentService.getCommentsForPost(postId);
	}

	@PostMapping(value = "/{postId}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public void createComment(@RequestBody NewCommentDto newCommentDto) {
		 commentService.addComment(newCommentDto);
	}


}
