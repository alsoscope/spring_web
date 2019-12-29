package com.p.project.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.p.project.DTO.Criteria;
import com.p.project.DTO.ProductDTO;
import com.p.project.Service.ProductServiceImpl;

@Repository
public class ProductDAOImpl implements ProductDAO {

	private static final Logger logger=LoggerFactory.getLogger(ProductDAOImpl.class);
	
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
	public void updateProduct(ProductDTO dto) {
		sqlSession.update(namespace + ".updateProduct", dto);
	}
	@Override
	public void updateAbroad(ProductDTO dto) {
		sqlSession.update("product.updateAbroad", dto);
	}
	@Override
	public void updateEtcetera(ProductDTO dto) {
		sqlSession.update("product.updateEtcetera", dto);
	}
	
	//04. ��ǰ ����
	@Override
	public void deleteProduct(int product_id) {
		sqlSession.delete(namespace + ".deleteProduct", product_id);
	}
	@Override
	public void deleteAbroad(int product_id) {
		sqlSession.delete(namespace + ".deleteAbroad", product_id);
	}
	@Override
	public void deleteEtcetera(int product_id) {
		sqlSession.delete(namespace + ".deleteEtcetera", product_id);
	}
	
	//5. ��ǰ �߰�
	@Override
	public void insertProduct(ProductDTO dto) throws Exception{
		sqlSession.insert("product.insertProduct", dto);
	}	
	@Override
	public void insertAbroad(ProductDTO dto) throws Exception{
		sqlSession.insert("product.insertAbroad", dto);
	}	
	@Override
	public void insertEtcetera(ProductDTO dto) throws Exception{
		sqlSession.insert("product.insertEtcetera", dto);
	}

	//÷������ ���
	@Override
	public void addAttach(String fullName, int product_id) throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("fullName", fullName);
		map.put("product_id", product_id);
		
		logger.info("ProductDAOImpl fullName : " + fullName);		
		logger.info("ProductDAOImpl product_id : " + product_id);
		sqlSession.insert(namespace + ".addAttach", map);
	}
	
	@Override
	public void addAttach_ab(String fullName, int product_id) throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("fullName", fullName);
		map.put("product_id", product_id);
		
		logger.info("ProductDAOImpl fullName : " + fullName);		
		logger.info("ProductDAOImpl attach_id : " + product_id);
		sqlSession.insert(namespace + ".addAttach_ab", map);
	}

	@Override
	public void addAttach_etc(String fullName, int product_id) throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("fullName", fullName);
		map.put("product_id", product_id);
		
		logger.info("ProductDAOImpl fullName : " + fullName);		
		logger.info("ProductDAOImpl product_id : " + product_id);
		sqlSession.insert(namespace + ".addAttach_etc", map);
	}
	
	//÷������ �ִ� �Խù� ��ȸ
	@Override
	public List<String> getAttach(int product_id) throws Exception {
		return sqlSession.selectList(namespace + ".getAttach", product_id);
	}
	@Override
	public List<String> getAttach_ab(int product_id) throws Exception {
		return sqlSession.selectList(namespace + ".getAttach_ab", product_id);
	}	
	@Override
	public List<String> getAttach_etc(int product_id) throws Exception {
		return sqlSession.selectList(namespace + ".getAttach_etc", product_id);
	}
	
	//÷������ ���� : ������ ÷�������� �����ϰ� ���Ӱ� �߰��Ѵ�
	@Override
	public void replaceAttach(String fullName, int product_id) throws Exception {	
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("fullName", fullName);
		paramMap.put("product_id", product_id);
		
		sqlSession.insert(namespace + ".replaceAttach", paramMap);
	}
	@Override
	public void replaceAttach_ab(String fullName, int product_id) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		map.put("fullName", fullName);
		map.put("product_id", product_id);
		
		sqlSession.insert("product.replaceAttach_ab", map);
	}
	@Override
	public void replaceAttach_etc(String fullName, int product_id) throws Exception {
		Map<String, Object> map=new HashMap<>();
		
		map.put("fullName", fullName);
		map.put("product_id", product_id);
		
		sqlSession.insert(namespace + ".replaceAttach_etc", map); 
	}
	
	//÷������ ����
	@Override
	public void deleteAttach(int product_id) throws Exception {
		sqlSession.delete(namespace + ".deleteAttach", product_id);
	}
	@Override
	public void deleteAttach_ab(int product_id) throws Exception {
		sqlSession.delete(namespace + ".deleteAttach_ab", product_id);
	}
	@Override
	public void deleteAttach_etc(int product_id) throws Exception {
		sqlSession.delete("product.deleteAttach_etc", product_id);
	}
	
	@Override
	public List<ProductDTO> listAbroad() {
		return sqlSession.selectList("product.listAbroad");
	}

	@Override
	public List<ProductDTO> listEtcetera() {
		return sqlSession.selectList("product.listEtcetera");
	}

	@Override
	public ProductDTO detailAbroad(int product_id) {
		return sqlSession.selectOne(namespace + ".detailAbroad", product_id);
	}

	@Override
	public ProductDTO detailEtcetera(int product_id) {
		return sqlSession.selectOne(namespace + ".detailEtcetera", product_id);
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
	
	//6. ��ǰ �̹��� ���� ���� �̹��� ���� ����
	/*@Override
	public String fileInfo(int product_id) {
		return sqlSession.selectOne("product.fileInfo", product_id);
	}
	
	//++++++++++++++++++��ũ�Ѹ� Test++++++++++++++++++
	@Override
	public List<ProductDTO> listTest() {
		return sqlSession.selectList(namespace + ".listTest");
	}

	@Override
	public List<ProductDTO> infiniteScrollDown(Integer product_id) {
		return sqlSession.selectList(namespace + ".infiniteScrollDown", product_id);
	}
	
	@Override
	public List<ProductDTO> infiniteScrollUp(Integer product_id) {
		return sqlSession.selectList(namespace + ".infiniteScrollUp", product_id);
	}
	//++++++++++++++++++��ũ�Ѹ� Test++++++++++++++++++
*/}
