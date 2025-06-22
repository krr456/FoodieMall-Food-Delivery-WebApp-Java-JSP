package com.mall.daoImpl;

import com.mall.dao.OrderHistoryDao;
import com.mall.model.OrderHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDaoI implements OrderHistoryDao {

	private Connection con;

	public OrderHistoryDaoI() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodiemall", "root", "226D5a0501@");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO orderhistory(orderId, userId) VALUES (?, ?)";

	@Override
	public void addOrderHistory(OrderHistory h) {
		try (PreparedStatement ps = con.prepareStatement(INSERT)) {
			ps.setInt(1, h.getOrderId());
			ps.setInt(2, h.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_BY_ID = "SELECT * FROM orderhistory WHERE orderHistoryId = ?";

	@Override
	public OrderHistory getOrderHistoryById(int id) {
		try (PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new OrderHistory(rs.getInt("orderHistoryId"), rs.getInt("orderId"), rs.getInt("userId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static final String SELECT_ALL = "SELECT * FROM orderhistory";

	@Override
	public List<OrderHistory> getAllOrderHistories() {
		List<OrderHistory> list = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(SELECT_ALL); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(new OrderHistory(rs.getInt("orderHistoryId"), rs.getInt("orderId"), rs.getInt("userId")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static final String UPDATE = "UPDATE orderhistory SET orderId=?, userId=? WHERE orderHistoryId=?";

	@Override
	public void updateOrderHistory(OrderHistory h) {
		try (PreparedStatement ps = con.prepareStatement(UPDATE)) {
			ps.setInt(1, h.getOrderId());
			ps.setInt(2, h.getUserId());
			ps.setInt(3, h.getOrderHistoryId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static final String DELETE = "DELETE FROM orderhistory WHERE orderHistoryId=?";

	@Override
	public void deleteOrderHistory(int id) {
		try (PreparedStatement ps = con.prepareStatement(DELETE)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private final static String INSERT_ORDER_HISTORY = "INSERT INTO orderhistory (orderId, userId) VALUES (?, ?)";

	public void addToOrderHistory(int orderId, int userId) {
		try (PreparedStatement ps = con.prepareStatement(INSERT_ORDER_HISTORY)) {
			ps.setInt(1, orderId);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
