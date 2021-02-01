package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ICartDao;
import com.app.pojos.Cart;
import com.app.pojos.User;

@Service
@Transactional
public class CartServiceImpl implements ICartService {

	@Autowired
	ICartDao dao;

	@Override
	public String addToCart(Cart item) {
		System.out.println("in add to cart " + item);

		dao.save(item);
		return "added to cart";
	}

	@Override
	public void removeFromCart(int id) {
		System.out.println("in delete cart items " + id);
		// check if user exists
		Optional<Cart> optional = dao.findById(id);
	
				//Cart c=optional.get();
				//System.out.println("detail"+c);
				System.out.println("in delete cart items collection ");
				User u=optional.get().getUser_id();
				//System.out.println("user"+u.toString());
			
				u.getOrders().remove(optional.get());
			
	}

	@Override
	public List<Cart> displayCart() {

		return dao.findAll();
	}

	@Override
	public Optional<Cart> findById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}
	
	@Override
	public long totalCount() {
		return dao.count();
	}
	

}
