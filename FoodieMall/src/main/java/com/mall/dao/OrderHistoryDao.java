package com.mall.dao;

import com.mall.model.OrderHistory;
import java.util.List;

public interface OrderHistoryDao {
	
	void addOrderHistory(OrderHistory history);

	OrderHistory getOrderHistoryById(int id);

	List<OrderHistory> getAllOrderHistories();

	void updateOrderHistory(OrderHistory history);

	void deleteOrderHistory(int id);
}
