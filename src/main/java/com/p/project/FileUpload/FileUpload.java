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
		
		//UUID 발급. 파일 이름 중복을 위한 유니크한 값 생성
		UUID uid=UUID.randomUUID();
		
		//원본파일 이름과 UUID 결합
		String savedName=uid.toString() + "_" + originalName;
		
		//파일을 저장할 폴더 생성(년/월/일 기준)을 위한 계산
		String savedPath=calcPath(uploadPath);
		
		//파일 경로(기존 업로드 경로+날짜별 경로), 파일명을 받아 파일 객체 생성. 저장할 파일 준비.
		File target=new File(uploadPath + savedPath, savedName);
		
		//임시 디렉토리에 업로드된 파일을 지정된 디렉토리로 복사. 원본 파일 저장.
		FileCopyUtils.copy(fileData, target);
		
		//원본 파일의 확장자. 이를 이용해서 MediaUtils 클래스의 getMediaType()을 이용해 이미지 파일/아닌 경우를 나누어 처리
		String formatName=originalName.substring(originalName.lastIndexOf(".")+1);
		
		String uploadedFileName=null;
		
		if(MediaUtils.getMediaType(formatName)!=null) {
			uploadedFileName=makeThumbnail(uploadPath, savedPath, savedName);	
		}else{
			uploadedFileName=makeIcon(uploadPath, savedPath, savedName);
		}		
		
		logger.info("uploadFile : " + uploadedFileName);

		return uploadedFileName;
	}
	
	//@SuppressWarnings : 컴파일 경고를 사용하지 않도록 설정
	//("unused") : 사용하지 않은 코드 및 불필요한 코드와 관련된 경고 억제
	//업로드 폴더의 생성 처리
	@SuppressWarnings("unused")
	private static String calcPath(String uplaodPath) {
		
		Calendar cal=Calendar.getInstance();
		
		//File.separator : 디렉토리 구분자(\\)
		String yearPath=File.separator+cal.get(Calendar.YEAR);
		
		String monthPath=yearPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		
		String datePath=monthPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		//디렉토리 생성 메소드 호출
		makeDir(uplaodPath, yearPath, monthPath, datePath);
		
		logger.info("datePath : " + datePath);
		
		return datePath;
	}
	
	//디렉토리 생성
	private static void makeDir(String uploadPath, String... paths) {
		
		//디렉토리가 존재하면
		if(new File(paths[paths.length-1]).exists()){
			return;
		}
		
		//디렉토리 존재하지 않으면
		for(String path : paths) {
			File dirPath=new File(uploadPath + path);
			
			//디렉토리 존재하지 않으면
			if(!dirPath.exists()) {
				dirPath.mkdir();//디렉토리 생성
			}
		}
	}
	
	//썸네일 이미지 생성
	private static String makeThumbnail(String uploadPath, String path, String fileName)throws Exception{
		
		//이미지를 읽기 위한 버퍼
		BufferedImage sourceImg=ImageIO.read(new File(uploadPath + path, fileName));
		
		//100px 단위의 썸네일 생성
		BufferedImage destImg=Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		
		String thumbnailName=uploadPath + path + File.separator + "s_" + fileName;
		
		File newFile=new File(thumbnailName);
		
		String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
		
		//썸네일 생성
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		//썸네일의 이름을 리턴함
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	//아이콘 생성
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		
		String iconName=uploadPath+path+File.separator+fileName;
		
		//아이콘 이름 리턴. File.separatorChar : 디렉토리 구분자
		//윈도우 : \ , 유닉스(리눅스) : /
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

}
