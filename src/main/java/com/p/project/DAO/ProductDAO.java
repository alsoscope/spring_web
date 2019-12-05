package com.p.project.DAO;

import java.util.List;

import com.p.project.DTO.ProductDTO;
import com.p.project.DTO.Criteria;

//����Ͻ����� ,DB���� �۾�ó��
public interface ProductDAO {
	//1. ��ǰ���
	public List<ProductDTO> listProduct();
	//1. ��ǰ���
	public List<ProductDTO> listAbroad();
	//1. ��ǰ���
	public List<ProductDTO> listEtcetera();
	
	//2.��ǰ ��
	public ProductDTO detailProduct(int product_id);
	
	public ProductDTO detailAbroad(int product_id);
	
	public ProductDTO detailEtcetera(int product_id);
	
	//3.��ǰ ����
	public void updateProduct(ProductDTO dto);
	
	//4.��ǰ ����
	public void deleteProduct(int product_id);
	
	//5. ��ǰ�߰�
	public void insertProduct(ProductDTO dto) throws Exception;

	public void insertAbroad(ProductDTO dto) throws Exception;

	public void insertEtcetera(ProductDTO dto) throws Exception;
	
	//6. ��ǰ �̹��� ���� ���� �̹��� ���� ����
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
	
	//÷������ ���
	public void addAttach(String fullName, int product_id)throws Exception;
	
	public void addAttach_ab(String fullName, int product_id)throws Exception;

	public void addAttach_etc(String fullName, int product_id)throws Exception;
	
	//÷������ �ִ� �Խù� ��ȸ
	public List<String> getAttach(int product_id) throws Exception;
	
	//÷������ �ִ� �Խù� ��ȸ
	public List<String> getAttach_ab(int product_id) throws Exception;
	
	public List<String> getAttach_etc(int product_id) throws Exception;
	
	//÷������ ���� : ������ ÷�������� �����ϰ� ���Ӱ� �߰��Ѵ�
	public void replaceAttach(String fullName, int product_id) throws Exception;
	
	//÷������ ����
	public void deleteAttach(int product_id) throws Exception;
}
