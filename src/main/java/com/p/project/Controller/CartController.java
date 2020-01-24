package com.p.project.Controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
import com.p.project.DTO.OrderDTO;
import com.p.project.DTO.ProductDTO;
import com.p.project.Service.CartService;
import com.p.project.Service.MemberService;
import com.p.project.Service.ProductService;
import com.p.project.VO.MemberVO;

//장바구니 관련 controller
@Controller
@RequestMapping("/shop/cart/*")
public class CartController {
	@Inject
	CartService cartService;
	MemberService memberService;
	
	private static final Logger logger=LoggerFactory.getLogger(CartController.class);
	
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
		
		return "redirect:/shop/cart/listCart";
	}//insertCart-------------------
	@RequestMapping("insertCart_ab")
	public String insertCart_ab(@ModelAttribute CartDTO vo, HttpSession session) {
		String userId=(String)session.getAttribute("userId");
		vo.setUserId(userId);

		int count=cartService.countCart(vo.getProduct_id(), userId);
		
		if(count==0) {
			cartService.insertCart(vo);
		} else {
			cartService.updateCart(vo);
		}
		return "redirect:/shop/cart/listCart";
	}//insertCart-------------------

	//2. 장바구니 목록
	@RequestMapping("listCart")
	public String listCart(HttpSession session, Model model, ProductDTO dto) {
		
		String userId=(String)session.getAttribute("userId");//session에 저장된 userId
		
		int count=cartService.countCart(dto.getProduct_id(), userId);
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		//변수 list에 장바구니 리스트 객체 저장
		List<CartDTO> list=cartService.selectCart(userId); //장바구니 정보
		
		//변수 sumMoney에 장바구니에 담긴 전체 상품의 금액 저장
		int sumMoney=cartService.sumMoney(userId);//장바구니 전체 금액 호출			

		//장바구니 전체 금액에 따라 배송비 구분
		//배송료 (10만원 이상 무료, 미만 2500부과)
		int fee = sumMoney >= 100000 ? 0 : 500;//삼항연산자 (조건식) ? (true) : (false)
		
		map.put("list", list);//장바구니 정보 map에 저장
		map.put("count", list.size()); //장바구니 상품의 유무
		map.put("sumMoney", sumMoney); //장바구니 전체 금액
		map.put("fee", fee);//수수료
		map.put("allSum", sumMoney+fee);//주문 상품 전체 금액(+배송비)
		model.addAttribute("map", map);
		
		//---------------------------------------------------------------------
		/*List<CartDTO> list_ab=cartService.selectCart_ab(userId); //장바구니 정보
		
		int sumMoney_ab=cartService.sumMoney_ab(userId);//장바구니 전체 금액 호출			

		//장바구니 전체 금액에 따라 배송비 구분
		//배송료 (10만원 이상 무료, 미만 2500부과)
		int fee_ab = sumMoney_ab >= 100000 ? 0 : 500;//삼항연산자 (조건식) ? (true) : (false)
		
		map.put("list", list_ab);//장바구니 정보 map에 저장
		map.put("count", list.size()); //장바구니 상품의 유무
		map.put("sumMoney", sumMoney_ab); //장바구니 전체 금액
		map.put("fee", fee_ab);//수수료
		map.put("allSum", sumMoney_ab+fee_ab);//주문 상품 전체 금액(+배송비)
		model.addAttribute("map", map);*/
		//---------------------------------------------------------------------
		
		return "product/cart_list";
	}//listCart--------------------

	//결제 성공 확인
	@RequestMapping("cart_Success")
	public String cartSuccess(HttpSession session, Model model, OrderDTO dto, CartDTO vo) {
		String userId=(String)session.getAttribute("userId");	
		dto.setUserId(userId);
		logger.info("userId : " + userId);
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		//변수 list에 접속 중인 session으로 불러온 userId가 갖고있는 장바구니 객체를 저장한다.
		List<CartDTO> list=cartService.selectCart(userId);
		
		//현재 세션 userId가 가지고 있는 sumMoney 장바구니 정보(합계)를 변수 sumMoney에 저장
		int sumMoney=cartService.sumMoney(userId);
		int fee=sumMoney >= 100000 ? 0 : 500;
		
		map.put("list", list);
		map.put("allSum", sumMoney + fee);
		model.addAttribute("map", map);
		
		// insert한 날짜에 amount만큼 더한 날짜 exprDate 구하기
		//SimpleDateFormat 인스턴스로 날짜를 원하는 패턴으로 출력하기
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		
		//---------------------------------------현재시간 확인
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		System.out.println("insertDate : " + f.format(cal.getTime()));	
		//---------------------------------------------------------------
		
		int amount=vo.getAmount();
		System.out.println("insert amount : " + amount);
		
		//Calendar 인스턴스 생성
		Calendar expr=Calendar.getInstance();
		
		for(int i=1; i<=10; i++) {
			if(amount==i) {
				//Date 인스턴스만 format 메소드에 사용될 수 있다. Calendar 인스턴스를 Date 인스턴스로 변환하기.
				expr.setTime(new Date());//new Date로 Date 객체 생성
				expr.add(expr.DATE, amount);
				
				/*System.out.println(f.format(expr.getTime()));*/
				String exprDate=f.format(expr.getTime());//출력용으로 Date 클래스를 얻는다.
				System.out.println("exprDate : " + exprDate);
				dto.setExprDate(exprDate);
			}
		}

		cartService.insertOrder(dto);
		
		cartService.orderDelete(userId);
		
		return "product/cart_success";
	}
	
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
		return "redirect:/shop/cart/listCart";
	}
	
	//4.장바구니 삭제
	@RequestMapping("delete")
	public String deleteCart(@RequestParam("cartId")int cartId) {
		cartService.deleteCart(cartId);
		return "redirect:/shop/cart/listCart";
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
