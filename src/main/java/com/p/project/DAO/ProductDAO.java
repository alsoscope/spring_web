package com.p.project.DAO;

import java.util.List;

import com.p.project.DTO.ProductDTO;
import com.p.project.DTO.Criteria;

//비즈니스로직 ,DB연동 작업처리
public interface ProductDAO {
	//1. 상품목록
	public List<ProductDTO> listProduct();
	
	//2.상품 상세
	public ProductDTO detailProduct(int product_id);
	
	//3.상품 수정
	public void updateProduct(ProductDTO vo);
	
	//4.상품 삭제
	public void deleteProduct(int Product_id);
	
	//5. 상품추가
	public void insertProduct(ProductDTO dto);
	
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
	
	//첨부파일
	public void addAttach(String fullName)throws Exception;
	
	//첨부파일 있는 게시물 조회
	public List<String> getAttach(Integer product_id) throws Exception;
}
