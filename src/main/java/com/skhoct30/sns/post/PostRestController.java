package com.skhoct30.sns.post;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skhoct30.sns.post.service.PostService;
@RequestMapping("/post")
@RestController
public class PostRestController {
	
	
	private final PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}

	
	
	
	
	@PostMapping("/create")
	public Map<String, String> createPost(
			@RequestParam String contents
			, RequestParam MultipartFile imageFile
			, HttpSession session) {
		
		long userId = (long)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		if(postService.addPost(userId, contents, imageFile)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
		
		
		
	}
	
}
