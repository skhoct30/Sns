package com.skhoct30.sns.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skhoct30.sns.comment.service.CommentService;

import jakarta.servlet.http.HttpSession;


@RequestMapping("/post/comment")
@RestController
public class CommentRestController {
	
	private CommentService commentService;
	
	public CommentRestController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	
	
	
	@PostMapping("/create")
	public Map<String, String> createComment(
			@RequestParam long postId
			, @RequestParam String contents
			, HttpSession session) {
		
		
		Map<String, String> resultMap = new HashMap<>();
		
		// userId 로 외래키로 가져오는거임.
		long userId = (Long)session.getAttribute("userId");
		
		if(commentService.addComment(userId, postId, contents)) {
			resultMap.put("result", "success"); 
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
		
		
		
	}
	
}
