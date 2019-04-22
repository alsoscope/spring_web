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
	public void insertProduct(ProductDTO vo);
	
	//6. ��ǰ �̹��� ���� ���� �̹��� ���� ����
	public String fileInfo(int product_id);
	
	//paging
	public List<ProductDTO> pageList(int page);
	
	public List<ProductDTO> criteriaList(Criteria cri);
	
	public int countPaging(Criteria cri);
}
