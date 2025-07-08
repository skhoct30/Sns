package com.skhoct30.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skhoct30.sns.post.service.PostService;

import jakarta.servlet.http.HttpSession;

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
			, @RequestParam MultipartFile imageFile
			, HttpSession session) {
		
		Long userId = (Long)session.getAttribute("userId");
		
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(postService.addPost(userId, contents, imageFile)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
		
		
	}
	
	// 삭제기능
	
	@DeleteMapping("/delete")
	public Map<String, String> deletePost(
			@RequestParam long id) {
		
		Map<String, String> resultMap = new HashMap<>();
		if(postService.deletetPost(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
		
	}
	
	
	
	
	
}
