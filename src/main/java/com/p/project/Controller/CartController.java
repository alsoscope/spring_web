package com.p.project.Controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.p.project.DTO.CartDTO;
import com.p.project.DTO.ProductDTO;
import com.p.project.Service.CartService;

//장바구니 관련 controller
@Controller
@RequestMapping("/shop/cart/*")
public class CartController {
	@Inject
	CartService cartService;
	
	private static final Logger logger=LoggerFactory.getLogger(CartController.class);
	
	//1. 상품 등록 페이지 매핑
	@RequestMapping("product_regist")
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
			//productService.insertProduct(vo);
		}
		return "product/product_regist";
	}
	
	//1. 장바구니 추가 처리 매핑. 장바구니에 추가하려는 상품이 목록에 있는지 검사
	@RequestMapping("insertCart")
	public String insertCart(@ModelAttribute CartDTO vo, HttpSession session) {
		
		//삭제 처리를 제외한 모든 메서드에서 session의 id값을 저장
		String userId=(String)session.getAttribute("userId");
		
		vo.setUserId(userId);
		logger.info("userId : " + userId);
		
		//장바구니에 기존 상품이 있는지 검사
		int count=cartService.countCart(vo.getProduct_id(), userId);
		
		//count = 0 ? cartService.updateCart(vo) : cartService.insertCart(vo);
		if(count==0) {
			//없으면 insertCart
			cartService.insertCart(vo);
		} else {
			//있으면 updateCart
			cartService.updateCart(vo);
		}
		logger.info("insertCart" + vo.toString());
		
		return "redirect:/shop/cart/cart_list";
	}//insertCart-------------------
	
	//2. 장바구니 목록
	@RequestMapping("cart_list")
	public String selectCart(HttpSession session, Model model) {
		String userId=(String)session.getAttribute("userId");//session에 저장된 user_id

		Map<String, Object> map=new HashMap<String, Object>();
		
		//변수 list에 장바구니 리스트 객체 저장
		List<CartDTO> list=cartService.selectCart(userId); //장바구니 정보
		
		//변수 sumMoney에 장바구니에 담긴 전체 상품의 금액 저장
		int sumMoney=cartService.sumMoney(userId);//장바구니 전체 금액 호출
		
		//장바구니 전체 금액에 따라 배송비 구분
		//배송료 (10만원 이상 무료, 미만 2500부과)
		int fee=sumMoney>=100000 ? 0 : 2500;//삼항연산자 (조건식) ? (true) : (false)
		
		map.put("list", list);//장바구니 정보 map에 저장
		map.put("count", list.size()); //장바구니 상품의 유무
		map.put("sumMoney", sumMoney); //장바구니 전체 금액
		map.put("fee", fee);//배송금액
		map.put("allSum", sumMoney+fee);//주문 상품 전체 금액(+배송비)
		model.addAttribute("cartService.selectCart", map);
		
		return "product/cart_list";
	}//selectCart--------------------
	
	//3.장바구니 수정
	@RequestMapping("update")
	public String updateCart(@RequestParam int[] amount, @RequestParam int[] product_id, HttpSession session) {
		//session의 id
		String userId=(String)session.getAttribute("userId");
		
		//장바구니 목록의 레코드 길이만큼 반복문 실행
		for(int i=0; i<product_id.length; i++) {
			CartDTO vo = new CartDTO();
			vo.setUserId(userId);
			vo.setAmount(amount[i]);
			vo.setProduct_id(product_id[i]);
			cartService.updateCart(vo);
		}
		return "redirect:/shop/cart/cart_list";
	}
	
	//4.장바구니 삭제
	@RequestMapping("delete")
	public String deleteCart(@RequestParam("cart_id")int cart_id) {
		cartService.deleteCart(cart_id);
		return "redirect:/shop/cart/cart_list";
	}
	
	//5.상품 수정(편집) 페이지 매핑
/*		@RequestMapping("edit/{product_id}")
		public String edit(@PathVariable("product_id")int product_id, Model model) {
			model.addAttribute("vo", productService.detailProduct(product_id));
			return "/product/product_edit";
		}
		
		//6. 상품 수정(편집) 처리 매핑
		@RequestMapping("update")
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
			
			return "redirect:/";
		}
		
		//7.상품 삭제 처리 매핑
		@RequestMapping("delete")
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
			
			return "redirect:/";
		}*/
		
}//CartController
