package com.p.project.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.p.project.DTO.CartDTO;
import com.p.project.DTO.OrderDTO;

public interface CartService {
	
	//1. ��ٱ��� �߰�
	public void insertCart(CartDTO vo);
	
	//2. ��ٱ��� ����
	public List<CartDTO> selectCart(String userId);
	public List<CartDTO> selectCart_ab(String userId);
	public List<CartDTO> selectCart_etc(String userId);
	
	//3. ��ٱ��� ����
	public void updateCart(CartDTO vo);
	
	//4. ��ǰ ����
	public void deleteCart(int cartId);

	//5. ��ٱ��� �ݾ� �հ�. ��ǰ ��ü �ݾ� select��ȸ�� ��� ����
	public int sumMoney(String userId);
	public int sumMoney_ab(String userId);
	
	//6. ��ٱ��� ������ ��ǰ Ȯ��
	public int countCart(int product_id, String userId);

	//7. ��ٱ��� ���� ����
	public void updateCartNum(CartDTO vo);
	
	//�ֹ� ���̺� �߰�
	public void insertOrder(OrderDTO vo);
	
	public void orderDelete(String userId);
	
	
}
