package com.skhoct30.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skhoct30.sns.user.service.UserService;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	
	private UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	
	
	@PostMapping("/join")
	public Map<String, String> join (
			@RequestParam String loginId
			, @RequestParam String password
			, @RequestParam String name
			, @RequestParam String nickname){
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(userService.addUser(loginId, password, name, nickname)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}

		return resultMap;
	}
	
	
	// 아이디 중복확인
	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicateId(@RequestParam String loginId) {
		
		Map<String, Boolean> resultMap = new HashMap<>();
		
		if(userService.isDuplicateId(loginId)) {
			resultMap.put("isDuplicate", true);
		} else {
			resultMap.put("isDuplicate", false);
		}
		
		return resultMap;
	}
	
	
	// 로그인을 위한 api 작성
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam String loginId
			, @RequestParam String password){
		
		return;
	}
	
	

}
