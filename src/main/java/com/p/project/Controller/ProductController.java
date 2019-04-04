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

//��ǰ ���� ������ ����
@Controller
@RequestMapping("/shop/product/*")
public class ProductController {
	
	private static final Logger logger=LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	ProductService productService;
	
	//1. ��ǰ ��ü ��� ������ ����. service���� ������ ����Ʈ ��ü ����
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String listProduct(Model model) {
		List<ProductDTO> list=productService.listProduct();
		model.addAttribute("list", list);
		System.out.println("list.toString()");
		return "/product/productList";
	}
	
	//2. ��ǰ �󼼺��� ������ ����. �ϳ��� URL�� �ϳ��� ������ ���ҽ��� ��ǥ(Rest)�� �� �ֵ��� ó��
	//@ResponseBody
	@RequestMapping("/detail/{product_id}")
	public String detailProduct(Model model, @PathVariable("product_id")int product_id) {
		model.addAttribute("vo", productService.detailProduct(product_id));
		logger.info("Ŭ���� product_id : " + product_id);
		
		return "/product/productDetail";
	}
	
	//3. ��ǰ ��� ������ ����
	@RequestMapping("write.do")
	public String write() {
		return "/product/productWrite";
	}
	
	//4. ��ǰ ��� ó�� ����
	@RequestMapping("insert.do")
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
			productService.insertProduct(vo);
		}
		
		return "redirect:/shop/product/list.do";
	}
	
	//5.��ǰ ����(����) ������ ����
	@RequestMapping("edit/{product_id}")
	public String edit(@PathVariable("product_id")int product_id, Model model) {
		model.addAttribute("vo", productService.detailProduct(product_id));
		return "/product/productEdit";
	}
	
	//6. ��ǰ ����(����) ó�� ����
	@RequestMapping("update.do")
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
		
		return "redirect:/shop/product/list.do";
	}
	
	//7.��ǰ ���� ó�� ����
	@RequestMapping("delete.do")
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
		
		return "redirect:/shop/product/list.do";
	}
	
}//ProductController
