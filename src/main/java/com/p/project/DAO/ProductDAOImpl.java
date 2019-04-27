package com.p.project.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.p.project.DTO.Criteria;
import com.p.project.DTO.ProductDTO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject
	SqlSession sqlSession;
	
	private static final String namespace = "product";
	
	// 01. 상품 목록 : 전체 상품 목록을 select 조회한 결과를 리턴
	@Override
	public List<ProductDTO> listProduct() {
		return sqlSession.selectList("product.listProduct");
	}

	//02. 상품 상세 : 상품 상세 정보를 select조회한 결과를 리턴
	@Override
	public ProductDTO detailProduct(int product_id) {
		return sqlSession.selectOne("product.detailProduct", product_id);
	}

	//03. 상품 수정
	@Override
	public void updateProduct(ProductDTO vo) {
	}

	//04. 상품 삭제
	@Override
	public void deleteProduct(int Product_id) {
	}

	//5. 상품 추가
	@Override
	public void insertProduct(ProductDTO vo) {
		sqlSession.insert("product.insertProduct", vo);
	}

	//6. 상품 이미지 삭제 위한 이미지 파일 정보
	@Override
	public String fileInfo(int product_id) {
		return sqlSession.selectOne("product.fileInfo", product_id);
	}

	//-----------------paging-----------------
	@Override
	public List<ProductDTO> pageList(int page) {
		if (page <= 0) {
			page = 1;
		}
		page = (page-1) * 10;
				
		return sqlSession.selectList("product.listProduct", page);
	}
	
	@Override
	public List<ProductDTO> criteriaList(Criteria cri) {
		return sqlSession.selectList("product.listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) {
		return sqlSession.selectOne("product.countPaging", cri);
	}
	//-----------------paging-----------------
	
	//++++++++++++++++++스크롤링 Test++++++++++++++++++
	@Override
	public List<ProductDTO> listTest() {
		return sqlSession.selectList(namespace + ".listTest");
	}

	@Override
	public List<ProductDTO> infiniteScrollDown(int product_id) {
		return sqlSession.selectList(namespace + ".infiniteScrollDown", product_id);
	}
	
	@Override
	public List<ProductDTO> infiniteScrollUp(int product_id) {
		return sqlSession.selectList(namespace + ".infiniteScrollUp", product_id);
	}
	//++++++++++++++++++스크롤링 Test++++++++++++++++++

	
}
