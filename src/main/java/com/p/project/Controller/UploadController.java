package com.p.project.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.p.project.FileUpload.FileUpload;
import com.p.project.FileUpload.MediaUtils;

@Controller
public class UploadController {

	private static final Logger logger=LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	//ajax로  파일 업로드
	@ResponseBody
	@RequestMapping(value="/uploadAjax", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		
		logger.info("uploadFile originalName : " + file.getOriginalFilename());
		/*logger.info("size : " + file.getSize());
		logger.info("contentType : " + file.getContentType());*/
		
		return new ResponseEntity<>(FileUpload.uploadFile(uploadPath,
				file.getOriginalFilename(), 
				file.getBytes()),
				HttpStatus.CREATED);//Http상태코드 CREATED 대신 OK라 해도 됨
	}
	//HttpStatus.CREATED : RESTFul 응답결과 상태.The resource was created successfully.
	
	//displayFile()은 파라미터로 브라우저에서 전송받기를 원하는 파일의 이름을 받는다
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName)throws Exception{
		
		InputStream in=null;
		
		//ResponseBody를 이용해서 byte[] 데이터가 그대로 전송될 것임을 명시.
		ResponseEntity<byte[]> entity=null;
		
		logger.info("displayFile FILE NAME : " + fileName);

		try {
			//가장 먼저 하는 작업. 파일 이름에서 확장자 추출, 이미지 타입의 파일인 경우는 적절한 MIME 타입을 지정.
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			
			MediaType mType=MediaUtils.getMediaType(formatName);
			
			HttpHeaders headers=new HttpHeaders();
			
			in = new FileInputStream(uploadPath+fileName);
			
				if(mType != null) {
					headers.setContentType(mType);
				}else {
					fileName=fileName.substring(fileName.indexOf("_")+1);
					
					//이미지가 아닌 경우 MIME 타입을 다운로드 용으로 사용되게 한다. 사용자에게 자동으로 다운로드 창을 열어줌.
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					//한글처리
					headers.add("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
				}

				//실제로 데이터를 읽는 부분. commons 라이브러리의 기능을 활용해 대상 파일에서 데이터를 읽어내는 IOUtils.toByteArray()
				entity=new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);		
				
			}catch(Exception e) {
				e.printStackTrace();
				entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			}finally {
				in.close();
			}
			return entity;
	}
	
	//삭제처리
	//파라미터로 삭제할 파일의 이름을 받는다. 이미지의 경우 썸네일 이름, 일반 파일은 실제 이름이 된다
	@ResponseBody
	@RequestMapping(value="/deleteFile", method=RequestMethod.POST)
		public ResponseEntity<String> deleteFile(String fileName){
			
			logger.info("deleteFile : " + fileName);
			
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			
			MediaType mType=MediaUtils.getMediaType(formatName);
			
			//이미지 파일이 확인되면 원본 파일 먼저 삭제, 이후에 파일을 삭제한다
			if(mType!=null) {
				String front=fileName.substring(0, 12);
				String end=fileName.substring(14);
				
				new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
			}
			new File(uploadPath+fileName.replace('/', File.separatorChar)).delete();
			
			return new ResponseEntity<String>("delete", HttpStatus.OK);
		}

}//UploadController