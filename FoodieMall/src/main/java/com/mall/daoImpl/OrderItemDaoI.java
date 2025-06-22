package com.mall.daoImpl;

import com.mall.dao.OrderItemDao;
import com.mall.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDaoI implements OrderItemDao {

	private Connection con;

	private final static String INSERT_QUERY = "INSERT INTO orderitems(userId, menuId, name, quantity, ratings, price) VALUES (?, ?, ?, ?, ?, ?)";
	private final static String SELECT_BY_ID = "SELECT * FROM orderitems WHERE orderItemsId = ?";
	private final static String SELECT_ALL = "SELECT * FROM orderitems";
	private final static String UPDATE_QUERY = "UPDATE orderitems SET userId=?, menuId=?, name=?, quantity=?, ratings=?, price=? WHERE orderItemsId=?";
	private final static String DELETE_QUERY = "DELETE FROM orderitems WHERE orderItemsId = ?";

	public OrderItemDaoI() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodiemall", "root", "226D5a0501@");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addOrderItem(OrderItem o) {
		try (PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) {
			ps.setInt(1, o.getUserId());
			ps.setInt(2, o.getMenuId());
			ps.setString(3, o.getName());
			ps.setInt(4, o.getQuantity());
			ps.setFloat(5, o.getRatings());
			ps.setInt(6, o.getPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderItem getOrderItemById(int id) {
		try (PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new OrderItem(rs.getInt("orderItemsId"), rs.getInt("userId"), rs.getInt("menuId"),
						rs.getString("name"), rs.getInt("quantity"), rs.getFloat("ratings"), rs.getInt("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		List<OrderItem> list = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(SELECT_ALL); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(new OrderItem(rs.getInt("orderItemsId"), rs.getInt("userId"), rs.getInt("menuId"),
						rs.getString("name"), rs.getInt("quantity"), rs.getFloat("ratings"), rs.getInt("price")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateOrderItem(OrderItem o) {
		try (PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)) {
			ps.setInt(1, o.getUserId());
			ps.setInt(2, o.getMenuId());
			ps.setString(3, o.getName());
			ps.setInt(4, o.getQuantity());
			ps.setFloat(5, o.getRatings());
			ps.setInt(6, o.getPrice());
			ps.setInt(7, o.getOrderItemsId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderItem(int id) {
		try (PreparedStatement ps = con.prepareStatement(DELETE_QUERY)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
