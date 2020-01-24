package com.p.project.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.p.project.DTO.CartDTO;
import com.p.project.DTO.OrderDTO;

@Repository
public class CartDAOImpl implements CartDAO {

	@Inject
	SqlSession sqlSession;
	
	private static final String namespace = "cart";
	
	//1. 장바구니 등록
	@Override
	public void insertCart(CartDTO vo) {
		sqlSession.insert("cart.insertCart", vo);
	}

	//2. 장바구니 목록 조회
	@Override
	public List<CartDTO> selectCart(String userId) {
		return sqlSession.selectList("cart.selectCart", userId);
	}
	@Override
	public List<CartDTO> selectCart_ab(String userId) {
		return sqlSession.selectList("cart.selectCart_ab", userId);
	}
	@Override
	public List<CartDTO> selectCart_etc(String userId) {
		return sqlSession.selectList("cart.selectCart_etc", userId);
	}
	
	//3. 장바구니 수정
	@Override
	public void updateCart(CartDTO vo) {
		sqlSession.update("cart.updateCart", vo);
	}

	//4. 장바구니 삭제
	@Override
	public void deleteCart(int cartId) {
		sqlSession.delete("cart.deleteCart", cartId);
	}

	//5. 장바구니 금액 합계. 상품 전체 금액 select조회한 결과 리턴
	@Override
	public int sumMoney(String userId) {
		sqlSession.selectOne("cart.sumMoney", userId);
		return sqlSession.selectOne("cart.sumMoney", userId);
	}
	@Override
	public int sumMoney_ab(String userId) {
		sqlSession.selectOne("cart.sumMoney_ab", userId);
		return sqlSession.selectOne("cart.sumMoney_ab", userId);
	}
	
	//6. 장바구니 동일한 상품 확인
	@Override
	public int countCart(int product_id, String userId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("product_id", product_id);
		map.put("userId", userId);
		return sqlSession.selectOne("cart.countCart", map);
	}

	//7. 장바구니 수량 수정
	@Override
	public void updateCartNum(CartDTO vo) {
		sqlSession.update("cart.updateCartNum", vo);
	}
	
	//주문 테이블에 추가
	@Override
	public void insertOrder(OrderDTO vo) {
		sqlSession.insert(namespace + ".insertOrder", vo);
	}

	@Override
	public void orderDelete(String userId) {
		sqlSession.delete(namespace + ".orderDelete", userId);
	}



}
