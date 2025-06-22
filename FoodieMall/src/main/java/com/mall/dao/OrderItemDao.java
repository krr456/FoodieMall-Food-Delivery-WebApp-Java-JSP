
package com.mall.dao;

import com.mall.model.OrderItem;
import java.util.List;

public interface OrderItemDao {

	void addOrderItem(OrderItem orderItem);

	OrderItem getOrderItemById(int id);

	List<OrderItem> getAllOrderItems();

	void updateOrderItem(OrderItem orderItem);

	void deleteOrderItem(int id);
}
