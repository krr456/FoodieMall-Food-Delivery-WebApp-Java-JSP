package com.mall.daoImpl;

import com.mall.model.OrderItem;

import java.util.List;

public class OrderItemDaoTest {

	public static void main(String[] args) {
		OrderItemDaoI dao = new OrderItemDaoI();

		// CREATE
		OrderItem item = new OrderItem();
		item.setUserId(13);
		item.setMenuId(2);
		item.setName("Butter Naan");
		item.setQuantity(3);
		item.setRatings(4.8f);
		item.setPrice(60);
		dao.addOrderItem(item);
		System.out.println("1️⃣ Order item added.");

		// READ ALL
		List<OrderItem> all = dao.getAllOrderItems();
		int lastId = all.get(all.size() - 1).getOrderItemsId();
		System.out.println("2️⃣ All Items:");
		all.forEach(i -> System.out.println(i));

		// READ BY ID
		OrderItem found = dao.getOrderItemById(lastId);
		System.out.println("3️⃣ Found item: " + found.getName());

		// UPDATE
		found.setQuantity(5);
		found.setPrice(100);
		dao.updateOrderItem(found);
		System.out.println("4️⃣ Updated item.");

		// DELETE
		dao.deleteOrderItem(lastId);
		System.out.println("5️⃣ Order item deleted.");
	}
}
