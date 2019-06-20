package com.p.project.Controller;

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
	
	//ajax��  ���� ���ε�
	@ResponseBody
	@RequestMapping(value="/uploadAjax", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		
		logger.info("originalName : " + file.getOriginalFilename());
		/*logger.info("size : " + file.getSize());
		logger.info("contentType : " + file.getContentType());*/
		
		return new ResponseEntity<>(FileUpload.uploadFile(uploadPath,
				file.getOriginalFilename(), 
				file.getBytes()),
				HttpStatus.CREATED);//Http�����ڵ� CREATED ��� OK�� �ص� ��
	}
	//HttpStatus.CREATED : RESTFul ������ ����.The resource was created successfully.
	
	//displayFile()�� �Ķ���ͷ� ���������� ���۹ޱ⸦ ���ϴ� ������ �̸��� �޴´�
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName)throws Exception{
		
		InputStream in=null;
		
		//ResponseBody�� �̿��ؼ� byte[] �����Ͱ� �״�� ���۵� ������ ���.
		ResponseEntity<byte[]> entity=null;
		
		logger.info("FILE NAME : " + fileName);

		try {
			//���� ���� �ϴ� �۾�. ���� �̸����� Ȯ���� ����, �̹��� Ÿ���� ������ ���� ������ MIME Ÿ���� ����.
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			
			MediaType mType=MediaUtils.getMediaType(formatName);
			
			HttpHeaders headers=new HttpHeaders();
			
			in = new FileInputStream(uploadPath+fileName);
			
				if(mType != null) {
					headers.setContentType(mType);
				}else {
					fileName=fileName.substring(fileName.indexOf("_")+1);
					
					//�̹����� �ƴ� ��� MIME Ÿ���� �ٿ�ε� ������ ���ǰ� �Ѵ�. ����ڿ��� �ڵ����� �ٿ�ε� â�� ������.
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					//�ѱ�ó��
					headers.add("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
				}

				//������ �����͸� �д� �κ�. commons ���̺귯���� ����� Ȱ���� ��� ���Ͽ��� �����͸� �о�� IOUtils.toByteArray()
				entity=new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);		
				
			}catch(Exception e) {
				e.printStackTrace();
				entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			}finally {
				in.close();
			}
			return entity;
	}
}
