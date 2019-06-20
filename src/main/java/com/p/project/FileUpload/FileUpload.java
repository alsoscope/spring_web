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

//���� ���ε�� Ŭ����
//�ڵ����� ���� ����, ���� ������ UUID�� ó��, ����� �̹��� �����ϴ� ����� ó���ž� �Ѵ�
public class FileUpload {

	private static final Logger logger = LoggerFactory.getLogger(FileUpload.class);
	
	//���������� ������ ���ε� ó���ϴ� �ڵ�
	//���� ���ε��ϱ� ���� �ʿ��� 3���� ������(������ ���� ���(uploadPath), ���� ������ �̸�(originalName), ���� ������(byte[]))�� �Ķ���ͷ� ���۹޴� �Լ�
	//������ �����Ͱ� ������ �ʿ䰡 ���� ������ static���� ����.
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData)throws Exception {
		
		//UUID �߱�. ���� �̸� �ߺ��� ���� ����ũ�� �� ����
		UUID uid=UUID.randomUUID();
		
		//�������� �̸��� UUID ����
		String savedName=uid.toString() + "_" + originalName;
		
		//������ ������ ���� ����(��/��/�� ����)�� ���� ���
		String savedPath=calcPath(uploadPath);
		
		//���� ���(���� ���ε� ���+��¥�� ���), ���ϸ��� �޾� ���� ��ü ����. ������ ���� �غ�.
		File target=new File(uploadPath + savedPath, savedName);
		
		//�ӽ� ���丮�� ���ε�� ������ ������ ���丮�� ����. ���� ���� ����.
		FileCopyUtils.copy(fileData, target);
		
		//���� ������ Ȯ����. �̸� �̿��ؼ� MediaUtils Ŭ������ getMediaType()�� �̿��� �̹��� ����/�ƴ� ��츦 ������ ó��
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
	
	//@SuppressWarnings : ������ ��� ������� �ʵ��� ����
	//("unused") : ������� ���� �ڵ� �� ���ʿ��� �ڵ�� ���õ� ��� ����
	//���ε� ������ ���� ó��
	@SuppressWarnings("unused")
	private static String calcPath(String uplaodPath) {
		
		Calendar cal=Calendar.getInstance();
		
		//File.separator : ���丮 ������(\\)
		String yearPath=File.separator+cal.get(Calendar.YEAR);
		
		String monthPath=yearPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		
		String datePath=monthPath+File.separator+new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		//���丮 ���� �޼ҵ� ȣ��
		makeDir(uplaodPath, yearPath, monthPath, datePath);
		
		logger.info("datePath : " + datePath);
		
		return datePath;
	}
	
	//���丮 ����
	private static void makeDir(String uploadPath, String... paths) {
		
		//���丮�� �����ϸ�
		if(new File(paths[paths.length-1]).exists()){
			return;
		}
		
		//���丮 �������� ������
		for(String path : paths) {
			File dirPath=new File(uploadPath + path);
			
			//���丮 �������� ������
			if(!dirPath.exists()) {
				dirPath.mkdir();//���丮 ����
			}
		}
	}
	
	//����� �̹��� ����
	private static String makeThumbnail(String uploadPath, String path, String fileName)throws Exception{
		
		//�̹����� �б� ���� ����
		BufferedImage sourceImg=ImageIO.read(new File(uploadPath + path, fileName));
		
		//100px ������ ����� ����
		BufferedImage destImg=Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		
		String thumbnailName=uploadPath + path + File.separator + "s_" + fileName;
		
		File newFile=new File(thumbnailName);
		
		String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
		
		//����� ����
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		//������� �̸��� ������
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	//������ ����
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		
		String iconName=uploadPath+path+File.separator+fileName;
		
		//������ �̸� ����. File.separatorChar : ���丮 ������
		//������ : \ , ���н�(������) : /
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

}
