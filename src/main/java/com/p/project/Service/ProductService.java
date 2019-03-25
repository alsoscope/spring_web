package com.p.project.Service;

import java.util.List;

import com.p.project.DTO.ProductDTO;

public interface ProductService {

	//1. ��ǰ ���
	public List<ProductDTO> listProduct();
	
	//2. ��ǰ ��
	public ProductDTO detailProduct(int product_id);
	
	//3. ��ǰ ����
	public void updateProduct(ProductDTO vo);
	
	//4. ��ǰ ����
	public void deleteProduct(int product_id);
	
	//5. ��ǰ ���
	public void insertProduct(ProductDTO vo);
	
	//6. ��ǰ �̹��� ���� ���� �̹��� ���� ����
	public String fileInfo(int product_id);
}