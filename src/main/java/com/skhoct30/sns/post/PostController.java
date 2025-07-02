package com.skhoct30.sns.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {
	
	@GetMapping("/timeline-view")
	public String postList() {
		return "post/timeline";
	}
	
	
	
	@GetMapping("/create-view")
	public String inputPost() {
		return "post/input";
	}
	
}
