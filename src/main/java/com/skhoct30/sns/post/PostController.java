package com.skhoct30.sns.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skhoct30.sns.post.domain.Post;
import com.skhoct30.sns.post.dto.PostDto;
import com.skhoct30.sns.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {
	
	private PostService postService;
	
	PostController(PostService postService) {
		this.postService = postService;
	}
	
	
	
	@GetMapping("/timeline-view")
	public String postList(HttpSession session
			, Model model) {
		
		
		List<PostDto> postList = postService.getPostList();
		
		
		model.addAttribute("postList", postList);
		
		return "post/timeline";
	}
	
	
	
	@GetMapping("/create-view")
	public String inputPost() {
		return "post/input";
	}
	
	
	@GetMapping("/detail-view")
	public String postDetail(
			@RequestParam long id
			, Model model) {
		
		Post post = postService.getPost(id);
		
		model.addAttribute("post", post);
		
		return "post/detail";
	}
	
	
}
