package com.p.project.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.p.project.DTO.ProductDTO;
import com.p.project.DTO.SearchCriteria;
import com.p.project.FileUpload.FileUpload;
import com.p.project.FileUpload.MediaUtils;
import com.p.project.Service.BoardService;
import com.p.project.Service.MemberService;
import com.p.project.Service.ProductService;
import com.p.project.DTO.Criteria;
import com.p.project.DTO.MemberDTO;
import com.p.project.DTO.PageMaker;

//��ǰ ���� ������ ����
@Controller
@RequestMapping("/shop/product/*")
public class ProductController {
	
	private static final Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	//xml�� ������ ���ҽ� ����
	//bean�� id�� uploadPath�� �±׸� ����
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@Inject
	ProductService productService;

	//1. ��ǰ ��� ������ ����
	@RequestMapping(value="product_regist")
	public String product_regist() {

		return "/product/product_regist";
	}
	
	//��ǰ ��� ó�� ����
	@RequestMapping(value="insertProduct", method=RequestMethod.POST)
	public String insertProduct(ProductDTO dto, ProductDTO product_id) throws Exception{
/*		String filename="";
		//÷������(��ǰ����)�� ������
		if(!dto.getProduct_Photo().isEmpty()) {
			filename=dto.getProduct_Photo().getOriginalFilename();
			//���� ���丮 - ���� ���ε� ���
			//���� ���丮 - ���� ���ε� ���
			String path="c:\\";
			try {
				new File(path).mkdirs(); //�̹��� ������ ������ ���丮 ����
				//�ӽ� ���丮(����)�� ������ ������ ������ ���丮�� ����.
				//path ���丮, filename ���� �̹��� ���ϸ�, ������ �ӽ� ����� ������ ���� ���丮�� ����
				dto.getProduct_Photo().transferTo(new File(path+filename));
			}catch(Exception e) {
				e.printStackTrace();
			}
			dto.setProduct_url(filename);
		}*/
		
		/*if(product_id==null) {
			return null;
		}else {
			productService.getAttach(product_id);
		}*/
		
		logger.info("insert Product : " + dto.toString());
		productService.insertProduct(dto);
		
		/*String[] files=(String[])dto.getFiles();
		logger.info("getFiles() : " + files);*/
		
		return "redirect:/";
	}
	
	//1. ��ǰ ��ü ��� ������ ����. service���� ������ ����Ʈ ��ü ����
/*	@RequestMapping(value="/", method=RequestMethod.GET)
	public String listProduct(Model model) {
		List<ProductDTO> list=productService.listProduct();
		model.addAttribute("list", list);
		System.out.println("list.toString()");
		return "product/product_list";
	}*/
	
	//2. ��ǰ �󼼺��� ������ ����. �ϳ��� URL�� �ϳ��� ������ ���ҽ��� ��ǥ(Rest)�� �� �ֵ��� ó��
	@RequestMapping("/detail/{product_id}")
	public String detailProduct(Model model, @PathVariable("product_id")int product_id) throws Exception{
		
		model.addAttribute("vo", productService.detailProduct(product_id));
		logger.info("Ŭ���� product_id : " + product_id);
		
		return "/product/product_detail";
	}
	
	@ResponseBody
	@RequestMapping("/getAttach/{product_id}")
	public List<String> getAttach(@PathVariable("product_id")int product_id) throws Exception{
		logger.info("getAttach : " + productService.getAttach(product_id).toString());
		return productService.getAttach(product_id);
	}
	
	//�ѱ� ��ȭ ī�װ�
	@RequestMapping(value="product_list_korean")
	public String product_list_korean(Model model, Criteria cri) throws Exception{
	
		System.out.println("product_list_korean");

		List<ProductDTO> list=productService.listProduct();
		//List<ProductDTO> list=productService.criteriaList(cri);
		model.addAttribute("vo", list);
		
		/*PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(productService.listCountCriteria(cri));
		model.addAttribute("pageMaker", pageMaker);*/
		
		return "/product/product_list_korean";
	}
	
	/*@RequestMapping(value="product_list_korean")
	public String product_list_korean(Model model, Integer product_id) throws Exception{
	
		logger.info("product_list_korean2..............");

		//List<ProductDTO> list=productService.listTest();
		model.addAttribute("list", productService.getAttach(product_id));		
		
		return "/product/product_list_korean";
	}*/
	
	/*���� ��ũ��. ���������� ��û���� �� JSON�����͸� ��ü�� �ڵ����� ���ε� �����ִ� @RequestBody�� �̿��Ͽ� product_id�� ����� ���� �ִ�
	ProductDTO ��ü�� ���� product_id ���� ���ε� ��Ű��, �ڵ����� ���ϰ��� JSON���·� ������ִ� @ResponseBody �̿�*/
	/*@RequestMapping(value="infiniteScrollDown", method=RequestMethod.POST)
	public @ResponseBody List<ProductDTO> infiniteScrollDown(@RequestBody ProductDTO productDTO){
		
		logger.info("infiniteScrollDown called...............");
		logger.info(productDTO.toString());
		
		Integer bnoStart = productDTO.getProduct_id()-1;
		
		return productService.infiniteScrollDown(bnoStart);
	}
	
	@RequestMapping(value="infiniteScrollUp", method=RequestMethod.POST)
	public @ResponseBody List<ProductDTO> infiniteScrollUp(@RequestBody ProductDTO productDTO){
		
		logger.info("infiniteScrollUp called...............");
		logger.info(productDTO.toString());
		
		Integer bnoStart = productDTO.getProduct_id()+1;
		
		return productService.infiniteScrollUp(bnoStart);
	}*/
	
	//�ؿ� ��ȭ ī�װ�
	@RequestMapping("product_list_abroad")
	public String product_list_aborad() {
		
		return "/product/product_list_abroad";
	}
	
	//��Ÿ ���� ī�װ�
	@RequestMapping("product_list_etcetera")
	public String product_list_etcetera() {
		
		return "/product/product_list_etcetera";
	}

	//Ajax�� ���� ���ε� ----------------------------------------------------------------------------------------
	//���� ���ε� �帧 : ���ε� ��ư Ŭ�� �� �ӽ� ���丮�� ���ε� �� ������ ���丮�� ���� �� ���������� file�� ����
	//Ajax�� ȣ��Ǵ� Ư�� �Խù��� ÷�������� ó���ϴ� �޼ҵ�
	@ResponseBody //view�� �ƴ� data����. produces= : ������ �ѱ�ó��
	@RequestMapping(value="/uploadAjax", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception{
		
		logger.info("uploadFile originalName : " + file.getOriginalFilename());
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
		
		//������ ������ �ٿ�ε��ϱ� ���� ��Ʈ��
		InputStream in=null; //java.io
		
		//ResponseBody�� �̿��ؼ� byte[] �����Ͱ� �״�� ���۵� ������ ���.
		ResponseEntity<byte[]> entity=null;
		
		logger.info("displayFile FILE NAME : " + fileName);

		try {
			//���� ���� �ϴ� �۾�. ���� �̸����� Ȯ���� ����,formatName�� ����. �̹��� Ÿ���� ������ ���� ������ MIME Ÿ���� ����.
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			
			//������ Ȯ���ڸ� MediaUtilsŬ�������� �̹��� ���� ���θ� �˻��ϰ� ���Ϲ޾� mType�� ����
			MediaType mType=MediaUtils.getMediaType(formatName);
			
			//��� ���� ��ü(�ܺο��� �����͸� �ְ���� ������ header�� body�� �����ؾ��ϱ� ������)
			HttpHeaders headers=new HttpHeaders();
			
			//InputStream ����
			in = new FileInputStream(uploadPath+fileName);
			
				//�̹��� �������� Ȯ��
				if(mType != null) {
					headers.setContentType(mType);
				}else {
					fileName=fileName.substring(fileName.indexOf("_")+1);
					
					//�̹����� �ƴ� ��� MIME Ÿ���� �ٿ�ε� ������ ���ǰ� �Ѵ�. ����ڿ��� �ڵ����� �ٿ�ε� â�� ������.
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					//�ѱ�ó��. new String : ����Ʈ �迭�� ��Ʈ������. 
					headers.add("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
				}

				//������ �����͸� �д� �κ�. commons ���̺귯���� ����� Ȱ���� ��� ���Ͽ��� �����͸� �о�� IOUtils.toByteArray()
				//����Ʈ �п�, ���, HTTP �����ڵ�
				entity=new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);		
				
			}catch(Exception e) {
				e.printStackTrace();
				entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);//HTTP���� �ڵ�()
			}finally {
				in.close();//��Ʈ�� �ݱ�
			}
			return entity;
	}//displayFile
	
	//displayFile()�� �Ķ���ͷ� ���������� ���۹ޱ⸦ ���ϴ� ������ �̸��� �޴´�
	@ResponseBody
	@RequestMapping("/displayFile_detail")
	public ResponseEntity<byte[]> displayFile_detail(String fileName)throws Exception{
		
		//������ ������ �ٿ�ε��ϱ� ���� ��Ʈ��
		InputStream in=null; //java.io
		
		//ResponseBody�� �̿��ؼ� byte[] �����Ͱ� �״�� ���۵� ������ ���.
		ResponseEntity<byte[]> entity=null;
		
		logger.info("displayFilep_detail : " + fileName);

		try {
			//���� ���� �ϴ� �۾�. ���� �̸����� Ȯ���� ����,formatName�� ����. �̹��� Ÿ���� ������ ���� ������ MIME Ÿ���� ����.
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			
			//������ Ȯ���ڸ� MediaUtilsŬ�������� �̹��� ���� ���θ� �˻��ϰ� ���Ϲ޾� mType�� ����
			MediaType mType=MediaUtils.getMediaType(formatName);
			
			//��� ���� ��ü(�ܺο��� �����͸� �ְ���� ������ header�� body�� �����ؾ��ϱ� ������)
			HttpHeaders headers=new HttpHeaders();
			
			//InputStream ����
			in = new FileInputStream(uploadPath+fileName);
			
				//�̹��� �������� Ȯ��
				if(mType != null) {
					headers.setContentType(mType);
				}else {
					fileName=fileName.substring(fileName.indexOf("_")+1);
					
					//�̹����� �ƴ� ��� MIME Ÿ���� �ٿ�ε� ������ ���ǰ� �Ѵ�. ����ڿ��� �ڵ����� �ٿ�ε� â�� ������.
					headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
					//�ѱ�ó��. new String : ����Ʈ �迭�� ��Ʈ������. 
					headers.add("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
				}

				//������ �����͸� �д� �κ�. commons ���̺귯���� ����� Ȱ���� ��� ���Ͽ��� �����͸� �о�� IOUtils.toByteArray()
				//����Ʈ �п�, ���, HTTP �����ڵ�
				entity=new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);		
				
			}catch(Exception e) {
				e.printStackTrace();
				entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);//HTTP���� �ڵ�()
			}finally {
				in.close();//��Ʈ�� �ݱ�
			}
			return entity;
	}//displayFile_detail
	
	//���� ���� ����
	//�Ķ���ͷ� ������ ������ �̸��� �޴´�. �̹����� ��� ����� �̸�, �Ϲ� ������ ���� �̸��� �ȴ�
	@ResponseBody
	@RequestMapping(value="/deleteFile", method=RequestMethod.POST)
		public ResponseEntity<String> deleteFile(String fileName){
			
			logger.info("deleteFile : " + fileName);
			
			//������ Ȯ���� ����
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			
			//�̹��� ���� ���� �˻�
			MediaType mType=MediaUtils.getMediaType(formatName);
			
			//�̹��� ������ Ȯ�εǸ� ���� ����+����� ���� ����, �̹��� �ƴϸ� �������ϸ� ����
			if(mType!=null) {
				//����� �̹��� ���� ����
				String front=fileName.substring(0, 12);
				String end=fileName.substring(14);
				
				//����� �̹��� ����
				new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
			}
			//���� ���� ����
			new File(uploadPath+fileName.replace('/', File.separatorChar)).delete();
			
			//�����Ϳ� http���� �ڵ� ����
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		}
}//ProductController
