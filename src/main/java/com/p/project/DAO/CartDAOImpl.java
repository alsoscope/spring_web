package com.p.project.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.p.project.DTO.CartDTO;

@Repository
public class CartDAOImpl implements CartDAO {

	@Inject
	SqlSession sqlSession;
	
	//1. ��ٱ��� ���
	@Override
	public void insertCart(CartDTO vo) {
		sqlSession.insert("cart.insertCart", vo);
	}

	//2. ��ٱ��� ��� ��ȸ
	@Override
	public List<CartDTO> selectCart(String user_id) {
		return sqlSession.selectList("cart.selectCart", user_id);
	}

	//3. ��ٱ��� ����
	@Override
	public void updateCart(CartDTO vo) {
		sqlSession.update("cart.updateCart", vo);
	}

	//4. ��ٱ��� ����
	@Override
	public void deleteCart(int cart_id) {
		sqlSession.delete("cart.deleteCart", cart_id);
	}

	//5. ��ٱ��� �ݾ� �հ�. ��ǰ ��ü �ݾ� select��ȸ�� ��� ����
	@Override
	public int sumMoney(String user_id) {
		sqlSession.selectOne("cart.sumMoney", user_id);
		return sqlSession.selectOne("cart.sumMoney", user_id);
	}

	//6. ��ٱ��� ������ ��ǰ Ȯ��
	@Override
	public int countCart(int product_id, String user_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("product_id", product_id);
		map.put("user_id", user_id);
		return sqlSession.selectOne("cart.countCart", map);
	}

	//7. ��ٱ��� ���� ����
	@Override
	public void updateCartNum(CartDTO vo) {
		sqlSession.update("cart.updateCartNum", vo);
	}

}
