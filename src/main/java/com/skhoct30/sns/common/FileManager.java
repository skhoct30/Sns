package com.skhoct30.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {

	
	// 경로 잡아준거같음.
	public static final String FILE_UPLOAD_PATH ="D:\\seo_kang_hyun\\springProject\\upload\\sns";
	
	// 파일 저장기능
	public static String saveFile(long userId, MultipartFile file) {
		
		if(file == null) {
			return null;
		}
		
		
		// 경로 만들기
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		// 폴더(디렉토리) 만들기
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			// 디렉토리(파일) 생성 실패
			return null;
		}
		
		
		
		// 파일 저장하기.
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		
		try {
			byte[] bytes = file.getBytes();
			
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			e.printStackTrace();
			
			return null;
		}
		
		// 실제 파일 저장 위치와 url 경로를 매칭하는 규칙
		// 을 정해서 그 규칙대로 url 경로르 만들어서 return 한다.
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();

		
		
		
		
		
	}
	
}
