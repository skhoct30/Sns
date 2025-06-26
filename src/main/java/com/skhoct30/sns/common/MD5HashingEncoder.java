package com.skhoct30.sns.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashingEncoder {

	
	public static String encode(String message) {
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			
			byte[] bytes = message.getBytes();
			
			messageDigest.update(bytes);
			
			// 해싱된 결과가 바이트 라는 타입의 배열에 들어가있다.
			// 이 바이트라는 형태는 정수, 이 정수형태를 16진수 숫자로 변경해서 문자열로 구성을 시킬것이다.
			byte[] digest = messageDigest.digest();
			
			
			// 하나씩 바이트 배열에 접근해서 이어붙히는 과정을 시작할것이다.
			String result = "";
			for(int i = 0; i < digest.length; i++) {
				// byte 연산
				result += Integer.toHexString(digest[i] & 0xff);
			}
			
			return result;
			
			
		} catch (NoSuchAlgorithmException e) {
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
}
