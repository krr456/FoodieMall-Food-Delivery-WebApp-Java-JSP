package com.mall.model;

import java.util.HashMap;
import java.util.Map;

public class CartCreator {

//	Map<Integer,CartItem> cart;
//	
//	public CartCreator() {
//		cart = new HashMap<>();
//	}
//	public void addCartItem(CartItem ci){
//		if (cart.containsKey(ci.getItemId())) {
//			CartItem cr = cart.get(ci.getItemId());
//			int n1 = cr.getQuantity();
//			int n2 = ci.getQuantity();
//			int n3 = n1+n2;
//			cr.setQuantity(n3);
//			cart.put(cr.getItemId(), cr);
//		} else {
//			cart.put(ci.getItemId(), ci);
//		}
//	}
//	
//	
//	
//	public void updateCartItem(int itemId,int quantity) {
//		if (cart.containsKey(itemId)) {
//		    cart.get(itemId).setQuantity(quantity);
//		}
//	}
//	
//	
//	public void deleteCartItem(int itemId) {
//		//remove the item from cart
//		cart.remove(itemId);
//	}
//	
//	
//	public Map<Integer, CartItem> getAllItems() {
//        return cart;
//    }
//	

	private Map<Integer, CartItem> cart;

	public CartCreator() {
		cart = new HashMap<>();
	}

	public void addCartItem(CartItem ci) {
		cart.merge(ci.getItemId(), ci, (existing, newItem) -> {
			existing.setQuantity(existing.getQuantity() + newItem.getQuantity());
			return existing;
		});
	}

	public void updateCartItem(int itemId, int quantity) {
		if (cart.containsKey(itemId)) {
			cart.get(itemId).setQuantity(quantity);
		}
	}

	public void deleteCartItem(int itemId) {
		cart.remove(itemId);
	}

	public Map<Integer, CartItem> getAllItems() {
		return cart;
	}
}
