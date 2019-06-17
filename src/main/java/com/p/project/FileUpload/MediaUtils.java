package com.p.project.FileUpload;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

//jpg, gif, png 이미지 타입을 식별할 수 있는 기능의 클래스
public class MediaUtils {

	private static Map<String, MediaType> mediaMap;
	
	static {
		mediaMap=new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	public static MediaType getMediaType(String type) {
		return mediaMap.get(type.toUpperCase());
	}
}
