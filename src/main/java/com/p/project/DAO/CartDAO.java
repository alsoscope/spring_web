package com.p.project.DAO;

import java.util.List;

import com.p.project.DTO.CartDTO;

public interface CartDAO {
	//1.장바구니 추가
	public void insertCart(CartDTO vo);
	
	//2.장바구니 목록. select조회한 결과 리턴
	public List<CartDTO> selectCart(String userId);
	
	//3.장바구니 수정. 상품수량update
	public void updateCart(CartDTO vo); 
	
	//4.장바구니 삭제
	public void deleteCart(int cartId);

	//5. 장바구니 금액 합계. 장바구니 상품 전체 금액 select조회한 결과 리턴
	public int sumMoney(String userId);
	
	//6. 장바구니 동일한 상품 레코드 확인. 동일한 상품 있는디 select조회한 결과 리턴
	public int countCart(int product_id, String userId);
	
	//7. 장바구니 상품수량 변경. 동일한 상품일 경우 수량 합산하여 update
	public void updateCartNum(CartDTO vo);
}
