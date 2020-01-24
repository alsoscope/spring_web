package com.p.project.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		return "redirect:/shop/product/product_list_korean";
	}//insertProduct
	
	@RequestMapping(value="insertAbroad", method=RequestMethod.POST)
	public String insertAbroad(ProductDTO dto, ProductDTO product_id, HttpServletRequest request, HttpServletResponse response) throws Exception{	
		logger.info("insert insertAbroad : " + dto.toString());
		productService.insertAbroad(dto);
		
		//radio button�� value ���� �����´�.
		//String abroad=request.getParameter("abroad");
		//dto.setGroup("abroad");
		
		/*PrintWriter out=response.getWriter();
		out.println("�з� : " + abroad);*/
		//System.out.println("�з� : " + abroad);
		
		return "redirect:/shop/product/product_list_abroad";
	}//insertAbroad
	
	@RequestMapping(value="insertEtcetera", method=RequestMethod.POST)
	public String insertEtcetera(ProductDTO dto, ProductDTO product_id) throws Exception{	
		logger.info("insert insertEtcetera : " + dto.toString());
		productService.insertEtcetera(dto);		
		return "redirect:/shop/product/product_list_etcetera";
	}//insertEtcetera
	
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
	
	@RequestMapping("/detail_ab/{product_id}")
	public String detailAbroad(Model model, @PathVariable("product_id")int product_id,HttpServletRequest request) throws Exception{
		
		model.addAttribute("vo", productService.detailAbroad(product_id));
		logger.info("Ŭ���� product_id : " + product_id);
		
		//radio button�� value ���� �����´�.
		String abroad=request.getParameter("abroad");
		if(abroad == null) {
			abroad = "AAbroad";
		}
		System.out.println("�з� : " + abroad);
		
		return "/product/product_detail_ab";
	}
	
	@RequestMapping("/detail_etc/{product_id}")
	public String detailEtcetera(Model model, @PathVariable("product_id")int product_id) throws Exception{
		
		model.addAttribute("vo", productService.detailEtcetera(product_id));
		logger.info("Ŭ���� product_id : " + product_id);
		
		return "/product/product_detail_etc";
	}
	
	//�Խñ� ���� form
	@RequestMapping(value="/product_update/{product_id}", method=RequestMethod.GET)
	public String product_update(ProductDTO dto, Model model, @PathVariable("product_id")int product_id, RedirectAttributes rttr) throws Exception{
		
/*		if(request.getParameter("abroad") == "AAbroad") {
			model.addAttribute("dto", productService.detailAbroad(product_id));
		}else {
		}
*/					
		model.addAttribute("dto", productService.detailProduct(product_id));
		
		rttr.addFlashAttribute("msg" , "success");
		logger.info("������ product_id : " + product_id);
		logger.info("----------product_update_form----------");
		return "/product/product_update";
	}

	@RequestMapping(value="/product_update_ab/{product_id}", method=RequestMethod.GET)
	public String product_update_ab(ProductDTO dto, Model model, @PathVariable("product_id")int product_id, RedirectAttributes rttr) throws Exception{
		model.addAttribute("dto", productService.detailAbroad(product_id));
		
		rttr.addFlashAttribute("msg" , "success");
		logger.info("������ product_id : " + product_id);
		logger.info("----------Abroad update form----------");
		return "/product/product_update_ab";
	}
	
	@RequestMapping(value="/product_update_etc/{product_id}", method=RequestMethod.GET)
	public String product_update_etc(ProductDTO dto, Model model, @PathVariable("product_id")int product_id, RedirectAttributes rttr) throws Exception{
		model.addAttribute("dto", productService.detailEtcetera(product_id));
		
		rttr.addFlashAttribute("msg" , "success");
		logger.info("������ product_id : " + product_id);
		logger.info("----------Etcetera update form----------");
		return "/product/product_update_etc";
	}
	
	//�Խñ� ���� post
	@RequestMapping(value="/updatePost", method=RequestMethod.POST)
	public String updatePost(@ModelAttribute ProductDTO dto) throws Exception{
		productService.updateProduct(dto);
		logger.info("------- updatePost -------");
		return "redirect:/shop/product/detail/" + dto.getProduct_id();
	}
	@RequestMapping(value="/updatePost_ab", method=RequestMethod.POST)
	public String updatePost_ab(@ModelAttribute ProductDTO dto) throws Exception{
		productService.updateAbroad(dto);
		logger.info("------- updateAbroad -------");
		return "redirect:/shop/product/detail_ab/" + dto.getProduct_id();
	}
	@RequestMapping(value="/updatePost_etc", method=RequestMethod.POST)
	public String updatePost_etc(@ModelAttribute ProductDTO dto) throws Exception{
		productService.updateEtcetera(dto);
		logger.info("------- updateEtcetera -------");
		return "redirect:/shop/product/detail_etc/" + dto.getProduct_id();
	}
	
	@RequestMapping("product_remove")
	public String product_remove(int product_id, RedirectAttributes rttr) throws Exception {
		
		/*if(request.getParameter("abroad") == "AAbroad") {
			productService.deleteAbroad(product_id);
		}*/
		productService.deleteProduct(product_id);		
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/";
	}
	@RequestMapping("product_remove_ab")
	public String product_remove_ab(int product_id, RedirectAttributes rttr) throws Exception {
		productService.deleteAbroad(product_id);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/";
	}
	@RequestMapping("product_remove_etc")
	public String product_remove_etc(int product_id, RedirectAttributes rttr) throws Exception {
		productService.deleteEtcetera(product_id);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/";
	}
	
	//÷������ �̹��� (fullName) �ҷ�����
	@ResponseBody
	@RequestMapping("/getAttach/{product_id}")
	public List<String> getAttach(@PathVariable("product_id")int product_id) throws Exception{
		logger.info("getAttach : " + productService.getAttach(product_id).toString());
		return productService.getAttach(product_id);
	}
	
	@ResponseBody
	@RequestMapping("/getAttach_ab/{product_id}")
	public List<String> getAttach_ab(@PathVariable("product_id")int product_id) throws Exception{
		logger.info("getAttach : " + productService.getAttach_ab(product_id).toString());
		return productService.getAttach_ab(product_id);
	}
	
	@ResponseBody
	@RequestMapping("/getAttach_etc/{product_id}")
	public List<String> getAttach_etc(@PathVariable("product_id")int product_id) throws Exception{
		logger.info("getAttach : " + productService.getAttach_etc(product_id).toString());
		return productService.getAttach_etc(product_id);
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
	
	//�ؿ� ��ȭ ī�װ�
	@RequestMapping("product_list_abroad")
	public String product_list_aborad(Model model, Criteria cri) {
		logger.info("product_list_abroad");
		List<ProductDTO> list=productService.listAbroad();
		model.addAttribute("vo", list);
		return "/product/product_list_abroad";
	}
	
	//��Ÿ ���� ī�װ�
	@RequestMapping("product_list_etcetera")
	public String product_list_etcetera(Model model, Criteria cri) {
		logger.info("product_list_etcetera");
		List<ProductDTO> list=productService.listEtcetera();
		model.addAttribute("vo", list);
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
	
	//÷������ ���ε� (�� ���� ��)
	/*@ResponseBody
	@RequestMapping(value="/uploadUpdate/{product_id}", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadUpdate(MultipartFile file) throws Exception{
		
		logger.info("uploadFile originalName : " + file.getOriginalFilename());
		
		return new ResponseEntity<>(FileUpload.uploadFile(uploadPath,file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);//Http�����ڵ� CREATED ��� OK�� �ص� ��
	}*///uploadUpdate
	
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
	/*@ResponseBody
	@RequestMapping("/displayFile_detail")
	public ResponseEntity<byte[]> displayFile_detail(String fileName)throws Exception{
		
		//������ ������ �ٿ�ε��ϱ� ���� ��Ʈ��
		InputStream in=null; //java.io
		
		//ResponseBody�� �̿��ؼ� byte[] �����Ͱ� �״�� ���۵� ������ ���.
		ResponseEntity<byte[]> entity=null;
		
		logger.info("displayFile_detail : " + fileName);

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
*/	
	//��ǰ��� ������ ���� ���� ����
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
	
	//�Խñ� ������ ������ ÷������ �Բ� ����
	@ResponseBody
	@RequestMapping(value="deleteAllFiles", method=RequestMethod.POST)
	public ResponseEntity<String> deleteAllFiles(@RequestParam("files[]")String[] files){
		logger.info("delete All Files : " + files);
		
		//���� ���� ���� �̸��� ���� �� �ֵ��� String[]�� �ۼ��Ѵ�
		if(files == null || files.length==0) {
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		}
		
		for(String fileName : files) {
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			
			MediaType mType=MediaUtils.getMediaType(formatName);
			
			if(mType != null) {
				String front=fileName.substring(0,12);
				String end=fileName.substring(14);
				new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
			}
			
			new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
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
}//ProductController
