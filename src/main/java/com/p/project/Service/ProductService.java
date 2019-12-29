package com.p.project.Service;

import java.util.List;

import com.p.project.DTO.Criteria;
import com.p.project.DTO.ProductDTO;

public interface ProductService {

	//1. ��ǰ ���
	public List<ProductDTO> listProduct();
	public List<ProductDTO> listAbroad();
	public List<ProductDTO> listEtcetera();
	
	//2. ��ǰ ��
	public ProductDTO detailProduct(int product_id);
	public ProductDTO detailAbroad(int product_id);	
	public ProductDTO detailEtcetera(int product_id);
	
	//3. ��ǰ ����
	public void updateProduct(ProductDTO vo) throws Exception;
	public void updateAbroad(ProductDTO dto) throws Exception;;
	public void updateEtcetera(ProductDTO dto) throws Exception;;
	
	//4. ��ǰ ����
	public void deleteProduct(int product_id) throws Exception;
	public void deleteAbroad(int product_id) throws Exception;;
	public void deleteEtcetera(int product_id) throws Exception;;
	
	//5. ��ǰ ���
	public void insertProduct(ProductDTO dto) throws Exception;
	public void insertAbroad(ProductDTO dto) throws Exception;
	public void insertEtcetera(ProductDTO dto) throws Exception;

	//paging	
	public List<ProductDTO> criteriaList(Criteria cri);
	
	public int listCountCriteria(Criteria cri);
	
	//÷������ �Խù� ��ȸ
	public List<String> getAttach(int product_id) throws Exception;
	public List<String> getAttach_ab(int product_id) throws Exception;	
	public List<String> getAttach_etc(int product_id) throws Exception;
	
	//test
	/*public List<ProductDTO> listTest();
	
	//6. ��ǰ �̹��� ���� ���� �̹��� ���� ����
	public String fileInfo(int product_id);
		
	//scroll
	public List<ProductDTO> infiniteScrollDown(Integer product_id);
	public List<ProductDTO> infiniteScrollUp(Integer product_id);	*/

}
