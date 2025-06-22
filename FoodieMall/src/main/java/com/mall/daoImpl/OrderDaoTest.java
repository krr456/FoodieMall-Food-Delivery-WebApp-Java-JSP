package com.mall.daoImpl;

import com.mall.model.Order;

import java.util.List;

public class OrderDaoTest {

	public static void main(String[] args) {
		OrderDaoI dao = new OrderDaoI();

		// CREATE
		Order newOrder = new Order();
		newOrder.setRestaurantId(3);
		newOrder.setUserId(13);
		newOrder.setTotalAmount(399);
		newOrder.setModeOfPayment("cash");
		newOrder.setStatus("confirmed");

		dao.addOrder(newOrder);
		System.out.println("1️ Order added.");

		// READ ALL
		List<Order> orders = dao.getAllOrders();
		if (orders.isEmpty()) {
			System.out.println("No orders found.");
			return;
		}

		int lastOrderId = orders.get(orders.size() - 1).getOrderId();
		System.out.println("2️ All Orders:");
		for (Order o : orders) {
			System.out
					.println("Order ID: " + o.getOrderId() + ", ₹" + o.getTotalAmount() + ", Status: " + o.getStatus());
		}

		// READ BY ID
		Order found = dao.getOrderById(lastOrderId);
		if (found != null) {
			System.out.println("3️ Found: Order ID " + found.getOrderId() + ", Status: " + found.getStatus());

			// UPDATE
			found.setStatus("dispatched");
			dao.updateOrder(found);
			System.out.println("4️ Order status updated.");

			// READ AFTER UPDATE
			Order updated = dao.getOrderById(lastOrderId);
			System.out.println("5️ Updated status: " + updated.getStatus());

			// DELETE
			dao.deleteOrder(lastOrderId);
			System.out.println("6️ Order deleted.");

			// VERIFY DELETE
			Order afterDelete = dao.getOrderById(lastOrderId);
			if (afterDelete == null) {
				System.out.println("7️ Order no longer exists.");
			} else {
				System.out.println(" Deletion failed.");
			}
		} else {
			System.out.println(" Order not found by ID.");
		}
	}
}
