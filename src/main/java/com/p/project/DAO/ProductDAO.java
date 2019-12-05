package com.p.project.DAO;

import java.util.List;

import com.p.project.DTO.ProductDTO;
import com.p.project.DTO.Criteria;

//비즈니스로직 ,DB연동 작업처리
public interface ProductDAO {
	//1. 상품목록
	public List<ProductDTO> listProduct();
	//1. 상품목록
	public List<ProductDTO> listAbroad();
	//1. 상품목록
	public List<ProductDTO> listEtcetera();
	
	//2.상품 상세
	public ProductDTO detailProduct(int product_id);
	
	public ProductDTO detailAbroad(int product_id);
	
	public ProductDTO detailEtcetera(int product_id);
	
	//3.상품 수정
	public void updateProduct(ProductDTO dto);
	
	//4.상품 삭제
	public void deleteProduct(int product_id);
	
	//5. 상품추가
	public void insertProduct(ProductDTO dto) throws Exception;

	public void insertAbroad(ProductDTO dto) throws Exception;

	public void insertEtcetera(ProductDTO dto) throws Exception;
	
	//6. 상품 이미지 삭제 위한 이미지 파일 정보
	public String fileInfo(int product_id);
	
	//paging
	public List<ProductDTO> pageList(int page);
	
	public List<ProductDTO> criteriaList(Criteria cri);
	
	public int countPaging(Criteria cri);
	
	//test
	public List<ProductDTO> listTest();
	
	//scroll
	public List<ProductDTO> infiniteScrollDown(Integer product_id);
	public List<ProductDTO> infiniteScrollUp(Integer product_id);
	
	//첨부파일 등록
	public void addAttach(String fullName, int product_id)throws Exception;
	
	public void addAttach_ab(String fullName, int product_id)throws Exception;

	public void addAttach_etc(String fullName, int product_id)throws Exception;
	
	//첨부파일 있는 게시물 조회
	public List<String> getAttach(int product_id) throws Exception;
	
	//첨부파일 있는 게시물 조회
	public List<String> getAttach_ab(int product_id) throws Exception;
	
	public List<String> getAttach_etc(int product_id) throws Exception;
	
	//첨부파일 수정 : 기존의 첨부파일을 삭제하고 새롭게 추가한다
	public void replaceAttach(String fullName, int product_id) throws Exception;
	
	//첨부파일 삭제
	public void deleteAttach(int product_id) throws Exception;
}
