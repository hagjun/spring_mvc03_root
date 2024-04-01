package com.ict.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.shop.dao.CartVO;
import com.ict.shop.dao.ShopDAO;
import com.ict.shop.dao.ShopVO;

@Service
public class ShopServiceImpl  implements ShopService{
	@Autowired
	private ShopDAO shopdao;
	
	
	@Override
	public List<ShopVO> getShopList(String category) throws Exception {
		return shopdao.getShopList(category);
	}

	@Override
	public ShopVO getShopDetail(String shop_idx) throws Exception {
		return shopdao.getShopDetail(shop_idx);
	}

	@Override
	public CartVO getCartChk(String m_id, String p_num) {
		return shopdao.getCartChk(m_id, p_num);
	}

	@Override
	public int getCartInsert(CartVO cartVO) throws Exception {
		return shopdao.getCartInsert(cartVO);
	}

	@Override
	public int getCartUpdate(CartVO cartVO) throws Exception {
		return shopdao.getCartUpdate(cartVO);
	}
	
	@Override
	public List<CartVO> getCartList(String m_id) throws Exception {
		return shopdao.getCartList(m_id);
	}
	@Override
	public int getCartEdit(CartVO cavo) throws Exception {
		return shopdao.getCartEdit(cavo);
	}
	
	@Override
	public int getCartDelete(String cart_idx) throws Exception {
		return shopdao.getCartDelete(cart_idx);
	}
	@Override
	public int getProductInsert(ShopVO svo) throws Exception {
		return shopdao.getProductInsert(svo);
	}
	
	
}
