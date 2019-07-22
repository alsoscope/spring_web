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
	
	//3. 상품 수정
	@Override
	public void updateProduct(ProductDTO vo) {
	}

	//4. 상품 삭제
	@Override
	public void deleteProduct(int product_id) {
	}
	
	//5. 상품 등록
	//첨부파일 기능 추가, @Transactional : 선언적 트랜잭션 처리. insertProduct의 쿼리문이 처리 도중 에러가 났을 때 처리한 쿼리를 자동으로 rollback해주기 위해 사용
	//트랜잭션 처리 하지 않으면, 정상적으로 완료가 되었다는 처리가 나기에 데이터를 복구 시켜놔야함.
	//인터페이스를 구현한 클래스로 선언된 빈은 인터페이스 메소드에 한해서 트랜잭션 적용/인터페이스에 선언한 것은 인터페이스 내 모든 메소드에 적용됨
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

	//6. 상품 이미지 삭제 위한 이미지 파일 정보
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
	}

	//++++++++++++++++++스크롤링 Test++++++++++++++++++

	//첨부파일 조회
	@Override
	public List<String> getAttach(Integer product_id) throws Exception {
		return productDao.getAttach(product_id);
	};
}
