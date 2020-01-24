package com.p.project.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.p.project.DTO.CartDTO;
import com.p.project.DTO.OrderDTO;

public interface CartService {
	
	//1. 장바구니 추가
	public void insertCart(CartDTO vo);
	
	//2. 장바구니 수정
	public List<CartDTO> selectCart(String userId);
	public List<CartDTO> selectCart_ab(String userId);
	public List<CartDTO> selectCart_etc(String userId);
	
	//3. 장바구니 수정
	public void updateCart(CartDTO vo);
	
	//4. 상품 삭제
	public void deleteCart(int cartId);

	//5. 장바구니 금액 합계. 상품 전체 금액 select조회한 결과 리턴
	public int sumMoney(String userId);
	public int sumMoney_ab(String userId);
	
	//6. 장바구니 동일한 상품 확인
	public int countCart(int product_id, String userId);

	//7. 장바구니 수량 수정
	public void updateCartNum(CartDTO vo);
	
	//주문 테이블에 추가
	public void insertOrder(OrderDTO vo);
	
	public void orderDelete(String userId);
	
	
}
