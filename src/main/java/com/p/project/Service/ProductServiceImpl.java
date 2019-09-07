package com.p.project.Service;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
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
	
	//3. ��ǰ ���� : �� ���� �۾��� �̷�����⿡ Ʈ����� ó���Ѵ�
	@Transactional
	@Override
	public void updateProduct(ProductDTO dto) throws Exception {
		
		//����
		productDao.updateProduct(dto);
		logger.info("updateProduct" + dto.toString());
		
		//������ ÷������ ����
		int product_id=dto.getProduct_id();		
		productDao.deleteAttach(product_id);
		logger.info("deleteAttach product_id : " + product_id);
		
		//���ο� ÷������ ���� �Է�
		String[] files=dto.getFiles();
		if(files == null) {
			return;
		}
		
		logger.info("���ο� ���� : " + files);
		
		for(String fileName : files) {
			productDao.replaceAttach(fileName, product_id);
			logger.info("���� �����Ǵ� ���� : " + fileName);
		}
	}

	//4. ��ǰ �Խñ� ���� : tbl_attach�� tbl_product�� �����ϱ⿡ �ݵ�� ÷�����ϰ� ���õ� �������� ���� ��, ��ǰ �Խñ��� �����Ѵ�. 
	@Transactional
	@Override
	public void deleteProduct(int product_id) throws Exception{
		productDao.deleteAttach(product_id);
		productDao.deleteProduct(product_id);
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
		logger.info("Arrays.toString(files) : " + Arrays.toString(files));		
		logger.info("ProductServiceImpl files : " + files);
		
		try {
			if(files==null) {
				System.out.println("files null");
				return;
			}			
			
				//������ not null�� ��
				logger.info("files not null : " + files);
				
				//���� for��. for each���� ���� �� for(����Ÿ�� �����̸� : �迭 �̸�)
				for(String fileName : files) {	
					int product_id=dto.getProduct_id();
					
					if(files.length > 1) {
						logger.info("files.length : " + files.length);
						return;
					}
					
					productDao.addAttach(fileName, product_id);
					logger.info("getProduct_id : " + product_id);
					logger.info("addAttach �Ϸ� fileName : " + fileName);
					logger.info("addAttach �Ϸ� files : " + files);
				}				
		} catch (DataIntegrityViolationException ex) { 
			logger.info("DataIntegrityViolationException : " + ex);
		}				
	}
	
	//÷������ ��ȸ
		@Override
		public List<String> getAttach(int product_id) throws Exception {
			return productDao.getAttach(product_id);
		};

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
}
