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
	
	// 01. ��ǰ ��� : ��ü ��ǰ ����� select ��ȸ�� ����� ����
	@Override
	public List<ProductDTO> listProduct() {
		return sqlSession.selectList("product.listProduct");
	}

	//02. ��ǰ �� : ��ǰ �� ������ select��ȸ�� ����� ����
	@Override
	public ProductDTO detailProduct(int product_id) {
		return sqlSession.selectOne("product.detailProduct", product_id);
	}

	//03. ��ǰ ����
	@Override
	public void updateProduct(ProductDTO vo) {
	}

	//04. ��ǰ ����
	@Override
	public void deleteProduct(int Product_id) {
	}

	//5. ��ǰ �߰�
	@Override
	public void insertProduct(ProductDTO vo) {
		sqlSession.insert("product.insertProduct", vo);
	}

	//6. ��ǰ �̹��� ���� ���� �̹��� ���� ����
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
	
	//++++++++++++++++++��ũ�Ѹ� Test++++++++++++++++++
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
	//++++++++++++++++++��ũ�Ѹ� Test++++++++++++++++++

	
}
