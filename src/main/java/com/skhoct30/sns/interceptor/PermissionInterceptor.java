package com.skhoct30.sns.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws IOException {
		
		HttpSession session = request.getSession();
		
		Long userId = (Long)session.getAttribute("userId");
		
		// 얘를 통해서 어디로 접근하려는지 판단이 됨.
		String uri = request.getRequestURI();
		
		if(userId == null) {
			// 로그인이 안된 경우
			// list-view , create-view, detail-view 접근 막기
			
			// /post 로 시작하는 url
			if(uri.startsWith("/post")) {
				// 로그인 페이지로 다시 보내줌 리다이렉트
				response.sendRedirect("/user/login-view");
				
				// 요청막기
				return false;
			} 

			
		} else {
			// 로그인이 된 경우
			// 회원가입 페이지로 접근하는 걸 막아야한다.
			// /user 로 시작하는 url 
			if(uri.startsWith("/user")) {
				response.sendRedirect("/post/timeline-view");
				return false;
			}
			
		}
		
		return true;
		
		
	}

}
