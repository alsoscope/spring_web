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
	
	//1. 장바구니 등록
	@Override
	public void insertCart(CartDTO vo) {
		cartDao.insertCart(vo);
	}

	//2. 장바구니 조회
	@Override
	public List<CartDTO> selectCart(String userId) {
		return cartDao.selectCart(userId);
	}

	//3. 장바구니 화면에서 상품 수량 변경할 때
	@Override
	public void updateCart(CartDTO vo) {
		cartDao.updateCart(vo);
	}

	//4. 장바구니 삭제
	@Override
	public void deleteCart(int userId) {
		cartDao.deleteCart(userId);
	}

	//5. 장바구니 상품 전체 금액 구하는 메서드 호출, 리턴
	@Override
	public int sumMoney(String userId) {
		return cartDao.sumMoney(userId);
	}

	//6. 장바구니에 동일한 상품이 있는지 확인하는 메소드 호출, 리턴
	@Override
	public int countCart(int product_id, String userId) {
		return cartDao.countCart(product_id, userId);
	}

	//7. 장바구니 동일한 상품 장바구니에 담았을 때, 상품수량 합산하는 메서드 호출
	@Override
	public void updateCartNum(CartDTO vo) {
		cartDao.updateCartNum(vo);
	}
	
	//주문 테이블에 추가
	@Override
	public void insertOrder(OrderDTO vo) {
		cartDao.insertOrder(vo);
	}

}
