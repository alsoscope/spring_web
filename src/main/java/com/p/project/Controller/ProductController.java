package com.p.project.Controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
	
	@Inject
	ProductService productService;

	//1. ��ǰ ��� ������ ����
	@RequestMapping(value="product_regist")
	public String product_regist() {

		return "/product/product_regist";
	}
	
	//��ǰ ��� ó�� ����
	@RequestMapping(value="insertProduct", method=RequestMethod.POST)
	public String insertProduct(ProductDTO dto, MultipartFile file, @PathVariable("product_id")Integer product_id) throws Exception{
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
		
		if(product_id==null) {
			return null;
		}else {
			productService.getAttach(product_id);			
		}
		
		logger.info("insert Product : " + dto);
		productService.insertProduct(dto);
		
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
	//@ResponseBody
	@RequestMapping("/detail/{product_id}")
	public String detailProduct(Model model, @PathVariable("product_id")int product_id) throws Exception{
		model.addAttribute("vo", productService.detailProduct(product_id));
		logger.info("Ŭ���� product_id : " + product_id);
		return "/product/product_detail";
	}
	
	//�ѱ� ��ȭ ī�װ�
/*	@RequestMapping(value="product_list_korean")
	public String product_list_korean(Model model, Criteria cri) throws Exception{
	
		System.out.println("product_list_korean");

		List<ProductDTO> list=productService.listProduct();
		List<ProductDTO> list=productService.criteriaList(cri);
		model.addAttribute("list", list);
		
		PageMaker pageMaker=new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(productService.listCountCriteria(cri));
		model.addAttribute("pageMaker", pageMaker);
		
		return "/product/product_list_korean";
	}
	*/
	@RequestMapping(value="product_list_korean")
	public String product_list_korean(Model model) throws Exception{
	
		logger.info("product_list_korean2..............");

		//List<ProductDTO> list=productService.listTest();
		model.addAttribute("list", productService.listTest());
		
		return "/product/product_list_korean";
	}

	/*���� ��ũ��. ���������� ��û���� �� JSON�����͸� ��ü�� �ڵ����� ���ε� �����ִ� @RequestBody�� �̿��Ͽ� product_id�� ����� ���� �ִ�
	ProductDTO ��ü�� ���� product_id ���� ���ε� ��Ű��, �ڵ����� ���ϰ��� JSON���·� ������ִ� @ResponseBody �̿�*/
	@RequestMapping(value="infiniteScrollDown", method=RequestMethod.POST)
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
	}
	
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

	//Ajax�� ȣ��Ǵ� Ư�� �Խù��� ÷�������� ó���ϴ� �޼ҵ�
	@ResponseBody
	@RequestMapping("/getAttach/{product_id}")
	public List<String> getAttach(@PathVariable("product_id")Integer product_id) throws Exception{

		return productService.getAttach(product_id);
	}
	
}//ProductController
