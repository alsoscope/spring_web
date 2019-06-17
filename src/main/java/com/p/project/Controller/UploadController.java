package com.p.project.Controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.p.project.FileUpload.FileUpload;

@Controller
public class UploadController {

	private static final Logger logger=LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	//Dropzone.js 를 이용한 파일 업로드
	@ResponseBody
	@RequestMapping(value="/uploadAjax", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		
		logger.info("originalName : " + file.getOriginalFilename());
		/*logger.info("size : " + file.getSize());
		logger.info("contentType : " + file.getContentType());*/
		
		return new ResponseEntity<>(FileUpload.uploadFile(uploadPath,
				file.getOriginalFilename(), 
				file.getBytes()),
				HttpStatus.CREATED);
	}
	
}
