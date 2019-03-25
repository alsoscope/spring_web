package com.p.project.Service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.p.project.DAO.ProductDAO;
import com.p.project.DTO.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService{

	@Inject
	ProductDAO productDao;
	
	//1. ��ǰ ��� : listProduct() ��ǰ���� ProductDAO�� ��� �޼��� ȣ��
	@Override
	public List<ProductDTO> listProduct() {
		return productDao.listProduct();
	}

	//2. ��ǰ �� : detailProduct(int product_id) ��ǰ���� ProductDAO�� �󼼺��� �޼��� ȣ��
	@Override
	public ProductDTO detailProduct(int product_id) {
		return productDao.detailProduct(product_id);
	}
	
	//3. ��ǰ ����
	@Override
	public void updateProduct(ProductDTO vo) {
	}

	//4. ��ǰ ����
	@Override
	public void deleteProduct(int product_id) {
	}

	//5. ��ǰ ���
	@Override
	public void insertProduct(ProductDTO vo) {
		productDao.insertProduct(vo);
	}

	//6. ��ǰ �̹��� ���� ���� �̹��� ���� ����
	@Override
	public String fileInfo(int product_id) {
		return productDao.fileInfo(product_id);
	}

}