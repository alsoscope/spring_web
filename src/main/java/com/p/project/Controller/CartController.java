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

//��ٱ��� ���� controller
@Controller
@RequestMapping("/shop/cart/*")
public class CartController {
	@Inject
	CartService cartService;
	MemberService memberService;
	
	private static final Logger logger=LoggerFactory.getLogger(CartController.class);
	
	//1. ��ٱ��� �߰� ó�� ����. ��ٱ��Ͽ� �߰��Ϸ��� ��ǰ�� ��Ͽ� �ִ��� �˻�
	@RequestMapping("insertCart")
	public String insertCart(@ModelAttribute CartDTO vo, HttpSession session) {
		
		//���� ó���� ������ ��� �޼��忡�� session�� id���� ����
		String userId=(String)session.getAttribute("userId");

		vo.setUserId(userId);
		logger.info("userId : " + userId);
		
		//��ٱ��Ͽ� ���� ��ǰ�� �ִ��� �˻�
		int count=cartService.countCart(vo.getProduct_id(), userId);
		
		//count = 0 ? cartService.updateCart(vo) : cartService.insertCart(vo);
		if(count==0) {
			//������ insertCart
			cartService.insertCart(vo);
		} else {
			//������ updateCart
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

	//2. ��ٱ��� ���
	@RequestMapping("listCart")
	public String listCart(HttpSession session, Model model, ProductDTO dto) {
		
		String userId=(String)session.getAttribute("userId");//session�� ����� userId
		
		int count=cartService.countCart(dto.getProduct_id(), userId);
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		//���� list�� ��ٱ��� ����Ʈ ��ü ����
		List<CartDTO> list=cartService.selectCart(userId); //��ٱ��� ����
		
		//���� sumMoney�� ��ٱ��Ͽ� ��� ��ü ��ǰ�� �ݾ� ����
		int sumMoney=cartService.sumMoney(userId);//��ٱ��� ��ü �ݾ� ȣ��			

		//��ٱ��� ��ü �ݾ׿� ���� ��ۺ� ����
		//��۷� (10���� �̻� ����, �̸� 2500�ΰ�)
		int fee = sumMoney >= 100000 ? 0 : 500;//���׿����� (���ǽ�) ? (true) : (false)
		
		map.put("list", list);//��ٱ��� ���� map�� ����
		map.put("count", list.size()); //��ٱ��� ��ǰ�� ����
		map.put("sumMoney", sumMoney); //��ٱ��� ��ü �ݾ�
		map.put("fee", fee);//������
		map.put("allSum", sumMoney+fee);//�ֹ� ��ǰ ��ü �ݾ�(+��ۺ�)
		model.addAttribute("map", map);
		
		//---------------------------------------------------------------------
		/*List<CartDTO> list_ab=cartService.selectCart_ab(userId); //��ٱ��� ����
		
		int sumMoney_ab=cartService.sumMoney_ab(userId);//��ٱ��� ��ü �ݾ� ȣ��			

		//��ٱ��� ��ü �ݾ׿� ���� ��ۺ� ����
		//��۷� (10���� �̻� ����, �̸� 2500�ΰ�)
		int fee_ab = sumMoney_ab >= 100000 ? 0 : 500;//���׿����� (���ǽ�) ? (true) : (false)
		
		map.put("list", list_ab);//��ٱ��� ���� map�� ����
		map.put("count", list.size()); //��ٱ��� ��ǰ�� ����
		map.put("sumMoney", sumMoney_ab); //��ٱ��� ��ü �ݾ�
		map.put("fee", fee_ab);//������
		map.put("allSum", sumMoney_ab+fee_ab);//�ֹ� ��ǰ ��ü �ݾ�(+��ۺ�)
		model.addAttribute("map", map);*/
		//---------------------------------------------------------------------
		
		return "product/cart_list";
	}//listCart--------------------

	//���� ���� Ȯ��
	@RequestMapping("cart_Success")
	public String cartSuccess(HttpSession session, Model model, OrderDTO dto, CartDTO vo) {
		String userId=(String)session.getAttribute("userId");	
		dto.setUserId(userId);
		logger.info("userId : " + userId);
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		//���� list�� ���� ���� session���� �ҷ��� userId�� �����ִ� ��ٱ��� ��ü�� �����Ѵ�.
		List<CartDTO> list=cartService.selectCart(userId);
		
		//���� ���� userId�� ������ �ִ� sumMoney ��ٱ��� ����(�հ�)�� ���� sumMoney�� ����
		int sumMoney=cartService.sumMoney(userId);
		int fee=sumMoney >= 100000 ? 0 : 500;
		
		map.put("list", list);
		map.put("allSum", sumMoney + fee);
		model.addAttribute("map", map);
		
		// insert�� ��¥�� amount��ŭ ���� ��¥ exprDate ���ϱ�
		//SimpleDateFormat �ν��Ͻ��� ��¥�� ���ϴ� �������� ����ϱ�
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		
		//---------------------------------------����ð� Ȯ��
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		System.out.println("insertDate : " + f.format(cal.getTime()));	
		//---------------------------------------------------------------
		
		int amount=vo.getAmount();
		System.out.println("insert amount : " + amount);
		
		//Calendar �ν��Ͻ� ����
		Calendar expr=Calendar.getInstance();
		
		for(int i=1; i<=10; i++) {
			if(amount==i) {
				//Date �ν��Ͻ��� format �޼ҵ忡 ���� �� �ִ�. Calendar �ν��Ͻ��� Date �ν��Ͻ��� ��ȯ�ϱ�.
				expr.setTime(new Date());//new Date�� Date ��ü ����
				expr.add(expr.DATE, amount);
				
				/*System.out.println(f.format(expr.getTime()));*/
				String exprDate=f.format(expr.getTime());//��¿����� Date Ŭ������ ��´�.
				System.out.println("exprDate : " + exprDate);
				dto.setExprDate(exprDate);
			}
		}

		cartService.insertOrder(dto);
		
		cartService.orderDelete(userId);
		
		return "product/cart_success";
	}
	
	//3.��ٱ��� ����
	@RequestMapping("update")
	public String updateCart(@RequestParam int[] amount, @RequestParam int[] product_id, HttpSession session) {
		//session�� id
		String userId=(String)session.getAttribute("userId");
		
		//��ٱ��� ����� ���ڵ� ���̸�ŭ �ݺ��� ����
		for(int i=0; i<product_id.length; i++) {
			CartDTO vo = new CartDTO();
			vo.setUserId(userId);
			vo.setAmount(amount[i]);
			vo.setProduct_id(product_id[i]);
			cartService.updateCart(vo);
		}
		return "redirect:/shop/cart/listCart";
	}
	
	//4.��ٱ��� ����
	@RequestMapping("delete")
	public String deleteCart(@RequestParam("cartId")int cartId) {
		cartService.deleteCart(cartId);
		return "redirect:/shop/cart/listCart";
	}
	
	//5.��ǰ ����(����) ������ ����
/*		@RequestMapping("edit/{product_id}")
		public String edit(@PathVariable("product_id")int product_id, Model model) {
			model.addAttribute("vo", productService.detailProduct(product_id));
			return "/product/product_edit";
		}
		
		//6. ��ǰ ����(����) ó�� ����
		@RequestMapping("update")
		public String update(ProductDTO vo) {
			String filename="";
			//÷������(��ǰ����)�� ������ ���, ��ǰ ��Ͻ� �̹��� ������ ���ε� �� ���� �����ϰ� ó��
			if(!vo.getProduct_Photo().isEmpty()) {
				filename=vo.getProduct_Photo().getOriginalFilename();
			//���� ���丮-���� ���ε� ���
			//���� ���丮-���� ���ε� ���
			String path="c:\\";
			
				try {
					new File(path).mkdirs(); //���丮 ����
					//�ӽ� ���丮(����)�� ����� ������ ������ ���丮�� ����
					vo.getProduct_Photo().transferTo(new File(path+filename));
				}catch(Exception e) {
					e.printStackTrace();
				}
				vo.setProduct_url(filename);
			} else {
				//��ǰ �̹��� ������ �������� ���� ���, ������ ���ϸ��� �����ͼ� ó��
				//������ ���ϸ�
				ProductDTO vo2=productService.detailProduct(vo.getProduct_id());
				vo.setProduct_url(vo2.getProduct_url());
			}
			productService.updateProduct(vo);
			
			return "redirect:/";
		}
		
		//7.��ǰ ���� ó�� ����
		@RequestMapping("delete")
		public String delete(@RequestParam int product_id) {
			//��ǰ �̹��� ����
			String filename=productService.fileInfo(product_id);
			String path="c:\\";
			//��ǰ �̹��� ����
			if(filename!=null) {
				File file=new File(path+filename);
				//������ �����ϸ�
				if(file.exists()) {
					file.delete(); //���� ����
				}
			}
			//���ڵ� ����
			productService.deleteProduct(product_id);
			
			return "redirect:/";
		}*/
		
}//CartController
