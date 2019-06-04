package com.p.project.Controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.p.project.DTO.ProductDTO;
import com.p.project.DTO.SearchCriteria;
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

}//ProductController
