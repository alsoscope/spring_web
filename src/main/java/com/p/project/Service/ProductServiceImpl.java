package com.p.project.Service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.p.project.DAO.ProductDAO;
import com.p.project.DTO.Criteria;
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
	//÷������ ��� �߰�, @Transactional : ������ Ʈ����� ó��. insertProduct�� �������� ó�� ���� ������ ���� �� ó���� ������ �ڵ����� rollback���ֱ� ���� ���
	//Ʈ����� ó�� ���� ������, ���������� �Ϸᰡ �Ǿ��ٴ� ó���� ���⿡ �����͸� ���� ���ѳ�����.
	//�������̽��� ������ Ŭ������ ����� ���� �������̽� �޼ҵ忡 ���ؼ� Ʈ����� ����/�������̽��� ������ ���� �������̽� �� ��� �޼ҵ忡 �����
	@Transactional
	@Override
	public void insertProduct(ProductDTO dto) throws Exception {
	
		productDao.insertProduct(dto);
		
		String[] files=dto.getFiles();
		
		if(files==null) {
			return;
		}
			for(String fileName : files) {
				productDao.addAttach(fileName);
			}		
	}

	//6. ��ǰ �̹��� ���� ���� �̹��� ���� ����
	@Override
	public String fileInfo(int product_id) {
		return productDao.fileInfo(product_id);
	}
	
	//-----------------paging-----------------
	public List<ProductDTO> criteriaList(Criteria cri){
		return productDao.criteriaList(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) {
		return productDao.countPaging(cri);
	}
	//-----------------paging-----------------
	
	//++++++++++++++++++��ũ�Ѹ� Test++++++++++++++++++
	@Override
	public List<ProductDTO> listTest() {
		return productDao.listTest();
	}

	@Override
	public List<ProductDTO> infiniteScrollDown(Integer product_id) {
		return productDao.infiniteScrollDown(product_id);
	}

	@Override
	public List<ProductDTO> infiniteScrollUp(Integer product_id) {
		return productDao.infiniteScrollUp(product_id);
	}

	//++++++++++++++++++��ũ�Ѹ� Test++++++++++++++++++

	//÷������ ��ȸ
	@Override
	public List<String> getAttach(Integer product_id) throws Exception {
		return productDao.getAttach(product_id);
	};
}
