package com.p.project.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.p.project.DTO.CartDTO;
import com.p.project.Service.CartService;

//장바구니 관련 controller
@Controller
@RequestMapping("/shop/cart/*")
public class CartController {
	@Inject
	CartService cartService;
	
	//1. 장바구니 추가
	//장바구니에 상품 입력처리 매핑. 장바구니에 추가하려는 상품이 목록에 있는지 검사
	@RequestMapping("insert.do")
	public String insertCart(@ModelAttribute CartDTO vo, HttpSession session) {
		//삭제 처리를 제외한 모든 메서드에서 session의 id값을 저장
		String user_id=(String)session.getAttribute("user_id");
		
		vo.setUser_id(user_id);
		
		//장바구니에 기존 상품이 있는지 검사
		int count=cartService.countCart(vo.getProduct_id(), user_id);
		
		//count == 0 ? cartService.updateCart(vo) : cartService.insertCart(vo);
		if(count==0) {
			//없으면 insertCart
			cartService.insertCart(vo);
		} else {
			//있으면 updateCart
			cartService.updateCart(vo);
		}
		return "redirect:/shop/cart/list.do";
	}//insertCart-------------------
	
	//2. 장바구니 목록
	@RequestMapping("cartlist.do")
	public String selectCart(HttpSession session, Model model) {
		String user_id=(String)session.getAttribute("user_id");//session에 저장된 user_id

		Map<String, Object> map=new HashMap<String, Object>();
		
		//변수 list에 장바구니 리스트 객체 저장
		List<CartDTO> list=cartService.selectCart(user_id); //장바구니 정보
		
		//변수 sumMoney에 장바구니에 담긴 전체 상품의 금액 저장
		int sumMoney=cartService.sumMoney(user_id);//장바구니 전체 금액 호출
		
		//장바구니 전체 금액에 따라 배송비 구분
		//배송료 (10만원 이상 무료, 미만 2500부과)
		int fee=sumMoney>=100000 ? 0 : 2500;//삼항연산자 (조건식) ? (true) : (false)
		
		map.put("list", list);//장바구니 정보 map에 저장
		map.put("count", list.size()); //장바구니 상품의 유무
		map.put("sumMoney", sumMoney); //장바구니 전체 금액
		map.put("fee", fee);//배송금액
		map.put("allSum", sumMoney+fee);//주문 상품 전체 금액(+배송비)
		
		model.addAttribute("cartService.selectCart", map);
		return "cartList";
	}//selectCart--------------------
	
	//3.장바구니 수정
	@RequestMapping("update.do")
	public String updateCart(@RequestParam int[] amount, @RequestParam int[] product_id, HttpSession session) {
		//session의 id
		String user_id=(String)session.getAttribute("user_id");
		
		//장바구니 목록의 레코드 길이만큼 반복문 실행
		for(int i=0; i<product_id.length; i++) {
			CartDTO vo = new CartDTO();
			vo.setUser_id(user_id);
			vo.setAmount(amount[i]);
			vo.setProduct_id(product_id[i]);
			cartService.updateCart(vo);
		}
		return "redirect:/shop/cart/list.do";
	}
	
	//4.장바구니 삭제
	@RequestMapping("delete.do")
	public String deleteCart(@RequestParam("cart_id")int cart_id) {
		cartService.deleteCart(cart_id);
		return "redirect:/shop/cart/list.do";
	}
}//CartController
