package com.p.project.Service;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.p.project.Controller.ProductController;
import com.p.project.DAO.ProductDAO;
import com.p.project.DTO.Criteria;
import com.p.project.DTO.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService{

	private static final Logger logger=LoggerFactory.getLogger(ProductServiceImpl.class);
	
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
	
		logger.info("INSERT ���� �������� : " + dto.getProduct_id());
		productDao.insertProduct(dto);
		logger.info("dto : " + dto);
		logger.info("INSERT ���� �������� : " + dto.getProduct_id());
		
		String[] files=dto.getFiles();
		System.out.println("Arrays.toString(files) : " + Arrays.toString(files));
		
		logger.info("ProductServiceImpl files : " + files);
		
		try {			
			if(files==null) {
				System.out.println("files null");
				return;
			} else {
				logger.info("files not null : " + files);
				//System.out.println("files null");
				
				//���� for��. for each���� ���� �� for(����Ÿ�� �����̸� : �迭 �̸�)
				for(String fileName : files) {
					productDao.addAttach(fileName);
					System.out.println("fileName : " + fileName);
					logger.info("ProductServiceImpl fileName : " + files);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("e" + e);
			logger.debug("debug : " + e);
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
