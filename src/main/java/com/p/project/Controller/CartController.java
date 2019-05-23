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

//��ٱ��� ���� controller
@Controller
@RequestMapping("/shop/cart/*")
public class CartController {
	@Inject
	CartService cartService;
	
	private static final Logger logger=LoggerFactory.getLogger(CartController.class);
	
	//1. ��ǰ ��� ������ ����
	@RequestMapping("product_regist")
	public String insert(ProductDTO vo) {
		String filename="";
		//÷������(��ǰ����)�� ������
		if(!vo.getProduct_Photo().isEmpty()) {
			filename=vo.getProduct_Photo().getOriginalFilename();
			//���� ���丮 - ���� ���ε� ���
			//���� ���丮 - ���� ���ε� ���
			String path="c:\\";
			try {
				new File(path).mkdirs(); //�̹��� ������ ������ ���丮 ����
				//�ӽ� ���丮(����)�� ������ ������ ������ ���丮�� ����.
				//path ���丮, filename ���� �̹��� ���ϸ�, ������ �ӽ� ����� ������ ���� ���丮�� ����
				vo.getProduct_Photo().transferTo(new File(path+filename));
			}catch(Exception e) {
				e.printStackTrace();
			}
			vo.setProduct_url(filename);
			//productService.insertProduct(vo);
		}
		return "product/product_regist";
	}
	
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
		
		return "redirect:/shop/cart/cart_list";
	}//insertCart-------------------
	
	//2. ��ٱ��� ���
	@RequestMapping("cart_list")
	public String selectCart(HttpSession session, Model model) {
		String userId=(String)session.getAttribute("userId");//session�� ����� user_id

		Map<String, Object> map=new HashMap<String, Object>();
		
		//���� list�� ��ٱ��� ����Ʈ ��ü ����
		List<CartDTO> list=cartService.selectCart(userId); //��ٱ��� ����
		
		//���� sumMoney�� ��ٱ��Ͽ� ��� ��ü ��ǰ�� �ݾ� ����
		int sumMoney=cartService.sumMoney(userId);//��ٱ��� ��ü �ݾ� ȣ��
		
		//��ٱ��� ��ü �ݾ׿� ���� ��ۺ� ����
		//��۷� (10���� �̻� ����, �̸� 2500�ΰ�)
		int fee=sumMoney>=100000 ? 0 : 2500;//���׿����� (���ǽ�) ? (true) : (false)
		
		map.put("list", list);//��ٱ��� ���� map�� ����
		map.put("count", list.size()); //��ٱ��� ��ǰ�� ����
		map.put("sumMoney", sumMoney); //��ٱ��� ��ü �ݾ�
		map.put("fee", fee);//��۱ݾ�
		map.put("allSum", sumMoney+fee);//�ֹ� ��ǰ ��ü �ݾ�(+��ۺ�)
		model.addAttribute("cartService.selectCart", map);
		
		return "product/cart_list";
	}//selectCart--------------------
	
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
		return "redirect:/shop/cart/cart_list";
	}
	
	//4.��ٱ��� ����
	@RequestMapping("delete")
	public String deleteCart(@RequestParam("cart_id")int cart_id) {
		cartService.deleteCart(cart_id);
		return "redirect:/shop/cart/cart_list";
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
