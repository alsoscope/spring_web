package com.p.project.FileUpload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

//파일 업로드용 클래스
//자동적인 폴더 생성, 파일 저장은 UUID로 처리, 썸네일 이미지 생성하는 기능이 처리돼야 한다
public class FileUpload {

	private static final Logger logger = LoggerFactory.getLogger(FileUpload.class);
	
	//최종적으로 파일을 업로드 처리하는 코드
	//파일 업로드하기 위해 필요한 3개의 데이터(파일의 저장 경로(uploadPath), 원본 파일의 이름(originalName), 파일 데이터(byte[]))를 파라미터로 전송받는 함수
	//별도의 데이터가 보관될 필요가 없기 때문에 static으로 설계.
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData)throws Exception {
		
		UUID uid=UUID.randomUUID();
		
		String savedName=uid.toString() + "_" + originalName;
		
		String savedPath=calcPath(uploadPath);
		
		File target=new File(uploadPath + savedPath, savedName);
		
		FileCopyUtils.copy(fileData, target);
		
		String formatName=originalName.substring(originalName.lastIndexOf(".")+1);
		
		String uploadedFileName=null;
		
		if(MediaUtils.getMediaType(formatName)!=null) {
			uploadedFileName=makeThumbnail(uploadPath, savedPath, savedName);	
		}else{
			uploadedFileName=makeIcon(uploadPath, savedPath, savedName);
		}
		
		return uploadedFileName;
	}
	
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		
		String iconName=uploadPath+path+File.separator+fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	//업로드 폴더의 생성 처리
	private static String calcPath(String uplaodPath) {
		
		Calendar cal=Calendar.getInstance();
		
		String yearPath=File.separator+cal.get(Calendar.YEAR);
		
		String monthPath=yearPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		
		String datePath=monthPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uplaodPath, yearPath, monthPath, datePath);
		
		logger.info(datePath);
		
		return datePath;
	}
	
	private static void makeDir(String uploadPath, String... paths) {
		if(new File(paths[paths.length-1]).exists()){
			return;
		}
		
		for(String path : paths) {
			File dirPath=new File(uploadPath + path);
			
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
	
	//썸네일 이미지 생성
	private static String makeThumbnail(String uploadPath, String path, String fileName)throws Exception{
		
		BufferedImage sourceImg=ImageIO.read(new File(uploadPath + path, fileName));
		
		BufferedImage destImg=Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		
		String thumbnailName=uploadPath + path + File.separator + "s_" + fileName;
		
		File newFile=new File(thumbnailName);
		
		String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

}
