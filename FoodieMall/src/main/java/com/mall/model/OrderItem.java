package com.mall.model;

public class OrderItem {
	private int orderItemsId;
	private int userId;
	private int menuId;
	private String name;
	private int quantity;
	private float ratings;
	private int price;

	public OrderItem() {
	}

	public OrderItem(int orderItemsId, int userId, int menuId, String name, int quantity, float ratings, int price) {
		this.orderItemsId = orderItemsId;
		this.userId = userId;
		this.menuId = menuId;
		this.name = name;
		this.quantity = quantity;
		this.ratings = ratings;
		this.price = price;
	}

	public int getOrderItemsId() {
		return orderItemsId;
	}

	public void setOrderItemsId(int orderItemsId) {
		this.orderItemsId = orderItemsId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getRatings() {
		return ratings;
	}

	public void setRatings(float ratings) {
		this.ratings = ratings;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderItem{" + "orderItemsId=" + orderItemsId + ", userId=" + userId + ", menuId=" + menuId + ", name='"
				+ name + '\'' + ", quantity=" + quantity + ", ratings=" + ratings + ", price=" + price + '}';
	}
}
