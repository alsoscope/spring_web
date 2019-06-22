package com.p.project.DAO;

import java.util.List;

import com.p.project.DTO.ProductDTO;
import com.p.project.DTO.Criteria;

//����Ͻ����� ,DB���� �۾�ó��
public interface ProductDAO {
	//1. ��ǰ���
	public List<ProductDTO> listProduct();
	
	//2.��ǰ ��
	public ProductDTO detailProduct(int product_id);
	
	//3.��ǰ ����
	public void updateProduct(ProductDTO vo);
	
	//4.��ǰ ����
	public void deleteProduct(int Product_id);
	
	//5. ��ǰ�߰�
	public void insertProduct(ProductDTO dto);
	
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
	
	//÷������
	public void addAttach(String fullName)throws Exception;
	
	//÷������ �ִ� �Խù� ��ȸ
	public List<String> getAttach(Integer product_id) throws Exception;
}
