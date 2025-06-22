//package com.mall.daoImpl;
//
//import com.mall.dao.OrderDao;
//import com.mall.model.CartItem;
//import com.mall.model.Order;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderDaoI implements OrderDao {
//
//    private Connection con;
//
//    public OrderDaoI() {
//        try {
//            con = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/foodiemall", "root", "226D5a0501@");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private final static String INSERT_QUERY =
//        "INSERT INTO `order`(restaurantId, userId, totalAmount, modeOfPayment, status) VALUES (?, ?, ?, ?, ?)";
//
//    public void addOrder(Order order) {
//        try (PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) {
//            ps.setInt(1, order.getRestaurantId());
//            ps.setInt(2, order.getUserId());
//            ps.setInt(3, order.getTotalAmount());
//            ps.setString(4, order.getModeOfPayment());
//            ps.setString(5, order.getStatus());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private final static String SELECT_BY_ID = "SELECT * FROM `order` WHERE orderId = ?";
//
//    public Order getOrderById(int id) {
//        try (PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return new Order(
//                    rs.getInt("orderId"),
//                    rs.getInt("restaurantId"),
//                    rs.getInt("userId"),
//                    rs.getInt("totalAmount"),
//                    rs.getString("modeOfPayment"),
//                    rs.getString("status")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private final static String SELECT_ALL = "SELECT * FROM `order`";
//
//    public List<Order> getAllOrders() {
//        List<Order> list = new ArrayList<>();
//        try (PreparedStatement ps = con.prepareStatement(SELECT_ALL);
//             ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                list.add(new Order(
//                    rs.getInt("orderId"),
//                    rs.getInt("restaurantId"),
//                    rs.getInt("userId"),
//                    rs.getInt("totalAmount"),
//                    rs.getString("modeOfPayment"),
//                    rs.getString("status")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    private final static String UPDATE_QUERY =
//        "UPDATE `order` SET restaurantId=?, userId=?, totalAmount=?, modeOfPayment=?, status=? WHERE orderId=?";
//
//    public void updateOrder(Order order) {
//        try (PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)) {
//            ps.setInt(1, order.getRestaurantId());
//            ps.setInt(2, order.getUserId());
//            ps.setInt(3, order.getTotalAmount());
//            ps.setString(4, order.getModeOfPayment());
//            ps.setString(5, order.getStatus());
//            ps.setInt(6, order.getOrderId());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private final static String DELETE_QUERY = "DELETE FROM `order` WHERE orderId=?";
//
//    public void deleteOrder(int orderId) {
//        try (PreparedStatement ps = con.prepareStatement(DELETE_QUERY)) {
//            ps.setInt(1, orderId);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    private final static String INSERT_ORDER_ITEMS =
//    	    "INSERT INTO orderitems (orderItemsId, userId, menuId, name, quantity, ratings, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
//
//    	public void addOrderItems(int orderId, int userId, List<CartItem> cartItems) {
//    	    try (PreparedStatement ps = con.prepareStatement(INSERT_ORDER_ITEMS)) {
//    	        for (CartItem item : cartItems) {
//    	            ps.setInt(1, orderId);
//    	            ps.setInt(2, userId);
//    	            ps.setInt(3, item.getItemId());
//    	            ps.setString(4, item.getName());
//    	            ps.setInt(5, item.getQuantity());
//    	            ps.setInt(7, item.getPrice() * item.getQuantity()); // ✅ Multiply quantity for correct pricing
//    	            ps.addBatch();
//    	        }
//    	        ps.executeBatch();
//    	    } catch (SQLException e) {
//    	        e.printStackTrace();
//    	    }
//    	}
//
//}

package com.mall.daoImpl;

import com.mall.dao.OrderDao;
import com.mall.model.CartItem;
import com.mall.model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoI implements OrderDao {
	private Connection con;

	public OrderDaoI() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodiemall", "root", "226D5a0501@");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final static String INSERT_QUERY = "INSERT INTO `order` (restaurantId, userId, totalAmount, modeOfPayment, status) VALUES (?, ?, ?, ?, ?)";

	public int addOrder(Order order) {
		int orderId = -1;
		try (PreparedStatement ps = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, order.getRestaurantId());
			ps.setInt(2, order.getUserId());
			ps.setInt(3, order.getTotalAmount());
			ps.setString(4, order.getModeOfPayment());
			ps.setString(5, order.getStatus());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				orderId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderId;
	}

	private final static String INSERT_ORDER_ITEMS = "INSERT INTO orderitems (orderItemsId, userId, menuId, name, quantity, price) VALUES (?, ?, ?, ?, ?, ?)";

	public void addOrderItems(int orderId, int userId, List<CartItem> cartItems) {
		try (PreparedStatement ps = con.prepareStatement(INSERT_ORDER_ITEMS)) {
			for (CartItem item : cartItems) {
				ps.setInt(1, orderId);
				ps.setInt(2, userId);
				ps.setInt(3, item.getItemId());
				ps.setString(4, item.getName());
				ps.setInt(5, item.getQuantity());
				ps.setInt(6, item.getPrice() * item.getQuantity());
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private final static String SELECT_ORDER_BY_ID = "SELECT * FROM `order` WHERE orderId = ?";

	public Order getOrderById(int orderId) {
		try (PreparedStatement ps = con.prepareStatement(SELECT_ORDER_BY_ID)) {
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Order(rs.getInt("orderId"), rs.getInt("restaurantId"), rs.getInt("userId"),
						rs.getInt("totalAmount"), rs.getString("modeOfPayment"), rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		return null;
	}

	@Override
	public void updateOrder(Order order) {
	}

	@Override
	public void deleteOrder(int orderId) {
	}
}
