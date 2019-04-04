package com.p.project.Controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.p.project.DTO.ProductDTO;
import com.p.project.Service.ProductService;

//상품 관련 페이지 매핑
@Controller
@RequestMapping("/shop/product/*")
public class ProductController {
	
	private static final Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	ProductService productService;
	
	//1. 상품 전체 목록 페이지 매핑. service에서 가져온 리스트 객체 리턴
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String listProduct(Model model) {
		List<ProductDTO> list=productService.listProduct();
		model.addAttribute("list", list);
		System.out.println("list.toString()");
		return "/product/productList";
	}
	
	//2. 상품 상세보기 페이지 매핑. 하나의 URL이 하나의 고유한 리소스를 대표(Rest)할 수 있도록 처리
	//@ResponseBody
	@RequestMapping("/detail/{product_id}")
	public String detailProduct(Model model, @PathVariable("product_id")int product_id) {
		model.addAttribute("vo", productService.detailProduct(product_id));
		logger.info("클릭한 product_id : " + product_id);
		
		return "/product/productDetail";
	}
	
	//3. 상품 등록 페이지 매핑
	@RequestMapping("write.do")
	public String write() {
		return "/product/productWrite";
	}
	
	//4. 상품 등록 처리 매핑
	@RequestMapping("insert.do")
	public String insert(ProductDTO vo) {
		String filename="";
		//첨부파일(상품사진)이 있으면
		if(!vo.getProduct_Photo().isEmpty()) {
			filename=vo.getProduct_Photo().getOriginalFilename();
			//개발 디렉토리 - 파일 업로드 경로
			//배포 디렉토리 - 파일 업로드 경로
			String path="c:\\";
			try {
				new File(path).mkdirs(); //이미지 파일을 저장할 디렉토리 생성
				//임시 디렉토리(서버)에 지정된 파일을 지정된 디렉토리로 전송.
				//path 디렉토리, filename 원본 이미지 파일명, 서버에 임시 저장된 파일을 저장 디렉토리로 전송
				vo.getProduct_Photo().transferTo(new File(path+filename));
			}catch(Exception e) {
				e.printStackTrace();
			}
			vo.setProduct_url(filename);
			productService.insertProduct(vo);
		}
		
		return "redirect:/shop/product/list.do";
	}
	
	//5.상품 수정(편집) 페이지 매핑
	@RequestMapping("edit/{product_id}")
	public String edit(@PathVariable("product_id")int product_id, Model model) {
		model.addAttribute("vo", productService.detailProduct(product_id));
		return "/product/productEdit";
	}
	
	//6. 상품 수정(편집) 처리 매핑
	@RequestMapping("update.do")
	public String update(ProductDTO vo) {
		String filename="";
		//첨부파일(상품사진)이 변경할 경우, 상품 등록시 이미지 파일을 업로드 한 경우와 동일하게 처리
		if(!vo.getProduct_Photo().isEmpty()) {
			filename=vo.getProduct_Photo().getOriginalFilename();
		//개발 디렉토리-파일 업로드 경로
		//배포 디렉토리-파일 업로드 경로
		String path="c:\\";
		
			try {
				new File(path).mkdirs(); //디렉토리 생성
				//임시 디렉토리(서버)에 저장된 파일을 지정된 디렉토리로 전송
				vo.getProduct_Photo().transferTo(new File(path+filename));
			}catch(Exception e) {
				e.printStackTrace();
			}
			vo.setProduct_url(filename);
		} else {
			//상품 이미지 파일을 변경하지 않을 경우, 기존의 파일명을 가져와서 처리
			//기존의 파일명
			ProductDTO vo2=productService.detailProduct(vo.getProduct_id());
			vo.setProduct_url(vo2.getProduct_url());
		}
		productService.updateProduct(vo);
		
		return "redirect:/shop/product/list.do";
	}
	
	//7.상품 삭제 처리 매핑
	@RequestMapping("delete.do")
	public String delete(@RequestParam int product_id) {
		//상품 이미지 정보
		String filename=productService.fileInfo(product_id);
		String path="c:\\";
		//상품 이미지 삭제
		if(filename!=null) {
			File file=new File(path+filename);
			//파일이 존재하면
			if(file.exists()) {
				file.delete(); //파일 삭제
			}
		}
		//레코드 삭제
		productService.deleteProduct(product_id);
		
		return "redirect:/shop/product/list.do";
	}
	
}//ProductController
