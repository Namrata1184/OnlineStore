package com.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.app.dao.ICartDao;
import com.app.pojos.Cart;

@Service
@Transactional
public class CartServiceImpl implements ICartService {
	
	@Autowired
	ICartDao dao;

	@Override
	public String addToCart(Cart item) {
		System.out.println("in add to cart "+item);
		
		dao.save(item);
		return "added to cart";
	}

	@Override
	public String removeFromCart(int id) {
		dao.deleteById(id);
		return "book deleted from cart";
	}

	@Override
	public List<Cart> displayCart() {
		
		return dao.findAll();
	}

}
