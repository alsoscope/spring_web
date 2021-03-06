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
	
	//1. 상품 목록 : listProduct() 상품관련 ProductDAO의 목록 메서드 호출
	@Override
	public List<ProductDTO> listProduct() {
		return productDao.listProduct();
	}

	//2. 상품 상세 : detailProduct(int product_id) 상품관련 ProductDAO의 상세보기 메서드 호출
	@Override
	public ProductDTO detailProduct(int product_id) {
		return productDao.detailProduct(product_id);
	}
	
	@Override
	public ProductDTO detailAbroad(int product_id) {
		return productDao.detailAbroad(product_id);
	}
	
	@Override
	public ProductDTO detailEtcetera(int product_id) {
		return productDao.detailEtcetera(product_id);
	}
	
	//3. 상품 수정 : 세 가지 작업이 이루어지기에 트랜잭션 처리한다
	@Transactional
	@Override
	public void updateProduct(ProductDTO dto) throws Exception {
		
		//수정
		productDao.updateProduct(dto);
		logger.info("updateProduct" + dto.toString());
		
		//기존의 첨부파일 삭제
		int product_id=dto.getProduct_id();		
		productDao.deleteAttach(product_id);
		logger.info("deleteAttach product_id : " + product_id);
		
		//새로운 첨부파일 정보 입력
		String[] files=dto.getFiles();
		if(files == null) {
			return;
		}
		
		logger.info("새로운 파일 : " + files);
		
		for(String fileName : files) {
			productDao.replaceAttach(fileName, product_id);
			logger.info("새로 수정되는 파일 : " + fileName);
		}
	}
	@Transactional
	@Override
	public void updateAbroad(ProductDTO dto) throws Exception {
		
		//수정
		productDao.updateAbroad(dto);
		logger.info("updateAbroad" + dto.toString());
		
		//기존의 첨부파일 삭제
		int product_id=dto.getProduct_id();		
		productDao.deleteAttach_ab(product_id);
		logger.info("deleteAttach_ab product_id : " + product_id);
		
		//새로운 첨부파일 정보 입력
		String[] files=dto.getFiles();
		if(files == null) {
			return;
		}
		
		logger.info("새로운 파일 : " + files);
		
		for(String fileName : files) {
			productDao.replaceAttach_ab(fileName, product_id);
			logger.info("새로 수정되는 파일 : " + fileName);
		}
	}
	@Transactional
	@Override
	public void updateEtcetera(ProductDTO dto) throws Exception {
		
		//수정
		productDao.updateEtcetera(dto);
		logger.info("updateEtcetera" + dto.toString());
		
		//기존의 첨부파일 삭제
		int product_id=dto.getProduct_id();		
		productDao.deleteAttach_etc(product_id);
		logger.info("deleteAttach_etc product_id : " + product_id);
		
		//새로운 첨부파일 정보 입력
		String[] files=dto.getFiles();
		if(files == null) {
			return;
		}
		
		logger.info("새로운 파일 : " + files);
		
		for(String fileName : files) {
			productDao.replaceAttach_etc(fileName, product_id);
			logger.info("새로 수정되는 파일 : " + fileName);
		}
	}
	//4. 상품 게시글 삭제 : tbl_attach가 tbl_product를 참조하기에 반드시 첨부파일과 관련된 정보부터 삭제 후, 상품 게시글을 삭제한다. 
	@Transactional
	@Override
	public void deleteProduct(int product_id) throws Exception{
		productDao.deleteAttach(product_id);
		productDao.deleteProduct(product_id);
	}
	@Override
	public void deleteAbroad(int product_id) throws Exception {
		productDao.deleteAttach_ab(product_id);
		productDao.deleteAbroad(product_id);
	}
	@Override
	public void deleteEtcetera(int product_id) throws Exception {
		productDao.deleteAttach_etc(product_id);
		productDao.deleteEtcetera(product_id);
	}
	//5. 상품 등록
	//첨부파일 기능 추가, @Transactional : 선언적 트랜잭션 처리. insertProduct의 쿼리문이 처리 도중 에러가 났을 때 처리한 쿼리를 자동으로 rollback해주기 위해 사용
	//트랜잭션 처리 하지 않으면, 정상적으로 완료가 되었다는 처리가 나기에 데이터를 복구 시켜놔야함.
	//인터페이스를 구현한 클래스로 선언된 빈은 인터페이스 메소드에 한해서 트랜잭션 적용/인터페이스에 선언한 것은 인터페이스 내 모든 메소드에 적용됨
	@Transactional
	@Override
	public void insertProduct(ProductDTO dto) throws Exception {
		logger.info("insertProduct 직전 시퀀스값 : " + dto.getProduct_id());
		productDao.insertProduct(dto);
		logger.info("insertProduct dto : " + dto);
		logger.info("insertProduct 직후 시퀀스값 : " + dto.getProduct_id());
		
		String[] files=dto.getFiles();
		logger.info("Arrays.toString(files) : " + Arrays.toString(files));		
		logger.info("ProductServiceImpl files : " + files);
		
		try {
			if(files==null) {
				System.out.println("files null");
				return;
			}			
			
				//파일이 not null일 때
				logger.info("files not null : " + files);
				
				//향상된 for문. for each문의 형식 → for(변수타입 변수이름 : 배열 이름)
				for(String fileName : files) {	
					int product_id=dto.getProduct_id();
					
					/*if(files.length > 1) {
						logger.info("files.length : " + files.length);
						return;
					}*/
					
					productDao.addAttach(fileName, product_id);
					logger.info("getProduct_id : " + product_id);
					logger.info("insertProduct addAttach 완료 fileName : " + fileName);
					logger.info("insertProduct addAttach 완료 files : " + files);
				}				
		} catch (DataIntegrityViolationException ex) { 
			logger.info("DataIntegrityViolationException : " + ex);
		}				
	}//insertProduct
	
	@Transactional
	@Override
	public void insertAbroad(ProductDTO dto) throws Exception {
	
		logger.info("insertAbroad 직전 시퀀스값 : " + dto.getProduct_id());
		productDao.insertAbroad(dto);
		logger.info("insertAbroad dto : " + dto);
		logger.info("insertAbroad 직후 시퀀스값 : " + dto.getProduct_id());
		
		String[] files=dto.getFiles();
		logger.info("Arrays.toString(files) : " + Arrays.toString(files));		
		logger.info("ProductServiceImpl files : " + files);
		
		try {
			if(files==null) {
				System.out.println("files null");
				return;
			}			
				logger.info("files not null : " + files);			

				for(String fileName : files) {	
					int product_id=dto.getProduct_id();
					
					productDao.addAttach_ab(fileName, product_id);
					logger.info("getProduct_id : " + product_id);
					logger.info("insertAbroad addAttach 완료 fileName : " + fileName);
					logger.info("insertAbroad addAttach 완료 files : " + files);
				}				
		} catch (DataIntegrityViolationException ex) { 
			logger.info("DataIntegrityViolationException : " + ex);
		}				
	}//insertAbroad
	
	@Transactional
	@Override
	public void insertEtcetera(ProductDTO dto) throws Exception {
	
		logger.info("insertEtcetera 직전 시퀀스값 : " + dto.getProduct_id());
		productDao.insertEtcetera(dto);
		logger.info("insertEtcetera dto : " + dto);
		logger.info("insertEtcetera 직후 시퀀스값 : " + dto.getProduct_id());
		
		String[] files=dto.getFiles();
		logger.info("Arrays.toString(files) : " + Arrays.toString(files));		
		logger.info("ProductServiceImpl files : " + files);
		
		try {
			if(files==null) {
				System.out.println("files null");
				return;
			}			
				logger.info("files not null : " + files);
				
				for(String fileName : files) {	
					int product_id=dto.getProduct_id();
					
					productDao.addAttach_etc(fileName, product_id);
					logger.info("getProduct_id : " + product_id);
					logger.info("insertEtcetera addAttach_etc 완료 fileName : " + fileName);
					logger.info("insertEtcetera addAttach_etc 완료 files : " + files);
				}				
		} catch (DataIntegrityViolationException ex) { 
			logger.info("DataIntegrityViolationException : " + ex);
		}				
	}//insertEtcetera
	
	//첨부파일 조회
	@Override
	public List<String> getAttach(int product_id) throws Exception {
		return productDao.getAttach(product_id);
	};

	@Override
	public List<String> getAttach_ab(int product_id) throws Exception {
		return productDao.getAttach_ab(product_id);
	};
	
	@Override
	public List<String> getAttach_etc(int product_id) throws Exception {
		return productDao.getAttach_etc(product_id);
	};
			
	//-----------------paging-----------------
	public List<ProductDTO> criteriaList(Criteria cri){
		return productDao.criteriaList(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) {
		return productDao.countPaging(cri);
	}
	//-----------------paging-----------------
	
	@Override
	public List<ProductDTO> listAbroad() {
		return productDao.listAbroad();
	}

	@Override
	public List<ProductDTO> listEtcetera() {
		return productDao.listEtcetera();
	}

	//6. 상품 이미지 삭제 위한 이미지 파일 정보
/*	@Override
	public String fileInfo(int product_id) {
		return productDao.fileInfo(product_id);
	}
	
	//++++++++++++++++++스크롤링 Test++++++++++++++++++
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
	}*/
	//++++++++++++++++++스크롤링 Test++++++++++++++++++
}
