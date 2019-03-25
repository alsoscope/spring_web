package com.p.project.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.p.project.DTO.CartDTO;

public interface CartService {
	
	//1. ��ٱ��� �߰�
	public void insertCart(CartDTO vo);
	
	//2. ��ٱ��� ����
	public List<CartDTO> selectCart(String user_id);

	//3. ��ٱ��� ����
	public void updateCart(CartDTO vo);
	
	//4. ��ǰ ����
	public void deleteCart(int cart_id);

	//5. ��ٱ��� �ݾ� �հ�. ��ǰ ��ü �ݾ� select��ȸ�� ��� ����
	public int sumMoney(String user_id);
	
	//6. ��ٱ��� ������ ��ǰ Ȯ��
	public int countCart(int product_id, String user_id);

	//7. ��ٱ��� ���� ����
	public void updateCartNum(CartDTO vo);
}
