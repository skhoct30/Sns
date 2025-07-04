package com.skhoct30.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skhoct30.sns.like.service.LikeService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class likeRestController {

		private LikeService likeService;
		
		public likeRestController(LikeService likeService) {
			this.likeService = likeService;
		}
	
	
		@PostMapping("/like")
		public Map<String, String> like(
				@RequestParam long postId
				, HttpSession session){
			
			long userId = (Long)session.getAttribute("userId");
			Map<String, String> resultMap = new HashMap<>();
			
			if(likeService.addLike(userId, postId)) {
				resultMap.put("result", "success");
			} else {
				resultMap.put("result", "fail");
			}
			return resultMap;
			
		}
		
		
}
