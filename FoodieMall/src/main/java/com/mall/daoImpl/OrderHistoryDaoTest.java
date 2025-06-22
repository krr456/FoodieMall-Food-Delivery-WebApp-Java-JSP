package com.mall.daoImpl;

import com.mall.model.OrderHistory;

import java.util.List;

public class OrderHistoryDaoTest {

	public static void main(String[] args) {
		OrderHistoryDaoI dao = new OrderHistoryDaoI();

		// CREATE
		OrderHistory h = new OrderHistory();
		h.setOrderId(2);
		h.setUserId(12);
		dao.addOrderHistory(h);
		System.out.println("1️ Order history added.");

		// READ ALL
		List<OrderHistory> list = dao.getAllOrderHistories();
		int lastId = list.get(list.size() - 1).getOrderHistoryId();
		System.out.println("2️ All Order Histories:");
		list.forEach(System.out::println);

		// READ BY ID
		OrderHistory found = dao.getOrderHistoryById(lastId);
		System.out.println("3️ Retrieved: " + found);

		// UPDATE
		found.setOrderId(found.getOrderId());
		dao.updateOrderHistory(found);
		System.out.println("4️ Order history updated.");

		// DELETE
		dao.deleteOrderHistory(lastId);
		System.out.println("5️ Order history deleted.");
	}
}
