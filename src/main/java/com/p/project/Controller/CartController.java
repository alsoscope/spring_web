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

//��ٱ��� ���� controller
@Controller
@RequestMapping("/shop/cart/*")
public class CartController {
	@Inject
	CartService cartService;
	
	//1. ��ٱ��� �߰�
	//��ٱ��Ͽ� ��ǰ �Է�ó�� ����. ��ٱ��Ͽ� �߰��Ϸ��� ��ǰ�� ��Ͽ� �ִ��� �˻�
	@RequestMapping("insert.do")
	public String insertCart(@ModelAttribute CartDTO vo, HttpSession session) {
		//���� ó���� ������ ��� �޼��忡�� session�� id���� ����
		String user_id=(String)session.getAttribute("user_id");
		
		vo.setUser_id(user_id);
		
		//��ٱ��Ͽ� ���� ��ǰ�� �ִ��� �˻�
		int count=cartService.countCart(vo.getProduct_id(), user_id);
		
		//count == 0 ? cartService.updateCart(vo) : cartService.insertCart(vo);
		if(count==0) {
			//������ insertCart
			cartService.insertCart(vo);
		} else {
			//������ updateCart
			cartService.updateCart(vo);
		}
		return "redirect:/shop/cart/list.do";
	}//insertCart-------------------
	
	//2. ��ٱ��� ���
	@RequestMapping("cartlist.do")
	public String selectCart(HttpSession session, Model model) {
		String user_id=(String)session.getAttribute("user_id");//session�� ����� user_id

		Map<String, Object> map=new HashMap<String, Object>();
		
		//���� list�� ��ٱ��� ����Ʈ ��ü ����
		List<CartDTO> list=cartService.selectCart(user_id); //��ٱ��� ����
		
		//���� sumMoney�� ��ٱ��Ͽ� ��� ��ü ��ǰ�� �ݾ� ����
		int sumMoney=cartService.sumMoney(user_id);//��ٱ��� ��ü �ݾ� ȣ��
		
		//��ٱ��� ��ü �ݾ׿� ���� ��ۺ� ����
		//��۷� (10���� �̻� ����, �̸� 2500�ΰ�)
		int fee=sumMoney>=100000 ? 0 : 2500;//���׿����� (���ǽ�) ? (true) : (false)
		
		map.put("list", list);//��ٱ��� ���� map�� ����
		map.put("count", list.size()); //��ٱ��� ��ǰ�� ����
		map.put("sumMoney", sumMoney); //��ٱ��� ��ü �ݾ�
		map.put("fee", fee);//��۱ݾ�
		map.put("allSum", sumMoney+fee);//�ֹ� ��ǰ ��ü �ݾ�(+��ۺ�)
		
		model.addAttribute("cartService.selectCart", map);
		return "cartList";
	}//selectCart--------------------
	
	//3.��ٱ��� ����
	@RequestMapping("update.do")
	public String updateCart(@RequestParam int[] amount, @RequestParam int[] product_id, HttpSession session) {
		//session�� id
		String user_id=(String)session.getAttribute("user_id");
		
		//��ٱ��� ����� ���ڵ� ���̸�ŭ �ݺ��� ����
		for(int i=0; i<product_id.length; i++) {
			CartDTO vo = new CartDTO();
			vo.setUser_id(user_id);
			vo.setAmount(amount[i]);
			vo.setProduct_id(product_id[i]);
			cartService.updateCart(vo);
		}
		return "redirect:/shop/cart/list.do";
	}
	
	//4.��ٱ��� ����
	@RequestMapping("delete.do")
	public String deleteCart(@RequestParam("cart_id")int cart_id) {
		cartService.deleteCart(cart_id);
		return "redirect:/shop/cart/list.do";
	}
}//CartController
