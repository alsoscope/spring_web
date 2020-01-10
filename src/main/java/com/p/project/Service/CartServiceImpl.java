package com.p.project.Service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.p.project.DAO.CartDAO;
import com.p.project.DTO.CartDTO;
import com.p.project.DTO.OrderDTO;

@Service
public class CartServiceImpl implements CartService{

	@Inject
	CartDAO cartDao;
	
	//1. ��ٱ��� ���
	@Override
	public void insertCart(CartDTO vo) {
		cartDao.insertCart(vo);
	}

	//2. ��ٱ��� ��ȸ
	@Override
	public List<CartDTO> selectCart(String userId) {
		return cartDao.selectCart(userId);
	}

	//3. ��ٱ��� ȭ�鿡�� ��ǰ ���� ������ ��
	@Override
	public void updateCart(CartDTO vo) {
		cartDao.updateCart(vo);
	}

	//4. ��ٱ��� ����
	@Override
	public void deleteCart(int userId) {
		cartDao.deleteCart(userId);
	}

	//5. ��ٱ��� ��ǰ ��ü �ݾ� ���ϴ� �޼��� ȣ��, ����
	@Override
	public int sumMoney(String userId) {
		return cartDao.sumMoney(userId);
	}

	//6. ��ٱ��Ͽ� ������ ��ǰ�� �ִ��� Ȯ���ϴ� �޼ҵ� ȣ��, ����
	@Override
	public int countCart(int product_id, String userId) {
		return cartDao.countCart(product_id, userId);
	}

	//7. ��ٱ��� ������ ��ǰ ��ٱ��Ͽ� ����� ��, ��ǰ���� �ջ��ϴ� �޼��� ȣ��
	@Override
	public void updateCartNum(CartDTO vo) {
		cartDao.updateCartNum(vo);
	}
	
	//�ֹ� ���̺� �߰�
	@Override
	public void insertOrder(OrderDTO vo) {
		cartDao.insertOrder(vo);
	}

}
