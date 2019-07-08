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

//상품 관련 페이지 매핑
@Controller
@RequestMapping("/shop/product/*")
public class ProductController {
	
	private static final Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	ProductService productService;

	//1. 상품 등록 페이지 매핑
	@RequestMapping(value="product_regist")
	public String product_regist() {

		return "/product/product_regist";
	}
	
	//상품 등록 처리 매핑
	@RequestMapping(value="insertProduct", method=RequestMethod.POST)
	public String insertProduct(ProductDTO dto, MultipartFile file, @PathVariable("product_id")Integer product_id) throws Exception{
/*		String filename="";
		//첨부파일(상품사진)이 있으면
		if(!dto.getProduct_Photo().isEmpty()) {
			filename=dto.getProduct_Photo().getOriginalFilename();
			//개발 디렉토리 - 파일 업로드 경로
			//배포 디렉토리 - 파일 업로드 경로
			String path="c:\\";
			try {
				new File(path).mkdirs(); //이미지 파일을 저장할 디렉토리 생성
				//임시 디렉토리(서버)에 지정된 파일을 지정된 디렉토리로 전송.
				//path 디렉토리, filename 원본 이미지 파일명, 서버에 임시 저장된 파일을 저장 디렉토리로 전송
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
	
	//1. 상품 전체 목록 페이지 매핑. service에서 가져온 리스트 객체 리턴
/*	@RequestMapping(value="/", method=RequestMethod.GET)
	public String listProduct(Model model) {
		List<ProductDTO> list=productService.listProduct();
		model.addAttribute("list", list);
		System.out.println("list.toString()");
		return "product/product_list";
	}*/
	
	//2. 상품 상세보기 페이지 매핑. 하나의 URL이 하나의 고유한 리소스를 대표(Rest)할 수 있도록 처리
	//@ResponseBody
	@RequestMapping("/detail/{product_id}")
	public String detailProduct(Model model, @PathVariable("product_id")int product_id) throws Exception{
		model.addAttribute("vo", productService.detailProduct(product_id));
		logger.info("클릭한 product_id : " + product_id);
		return "/product/product_detail";
	}
	
	//한국 영화 카테고리
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

	/*무한 스크롤. 브라우저에서 요청으로 온 JSON데이터를 객체로 자동으로 바인딩 시켜주는 @RequestBody를 이용하여 product_id를 멤버로 갖고 있는
	ProductDTO 객체를 통해 product_id 값을 바인딩 시키고, 자동으로 리턴값을 JSON형태로 만들어주는 @ResponseBody 이용*/
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
	
	//해외 영화 카테고리
	@RequestMapping("product_list_abroad")
	public String product_list_aborad() {
		
		return "/product/product_list_abroad";
	}
	
	//기타 영상 카테고리
	@RequestMapping("product_list_etcetera")
	public String product_list_etcetera() {
		
		return "/product/product_list_etcetera";
	}

	//Ajax로 호출되는 특정 게시물의 첨부파일을 처리하는 메소드
	@ResponseBody
	@RequestMapping("/getAttach/{product_id}")
	public List<String> getAttach(@PathVariable("product_id")Integer product_id) throws Exception{

		return productService.getAttach(product_id);
	}
	
}//ProductController
