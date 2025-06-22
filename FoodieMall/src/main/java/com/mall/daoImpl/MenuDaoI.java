package com.mall.daoImpl;

import com.mall.dao.MenuDao;
import com.mall.model.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoI implements MenuDao {

	private final static String INSERT_QUERY = "INSERT INTO menu(name, price, description, imagePath, isAvailable, restaurantId, ratings) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final static String SELECT_BY_ID = "SELECT * FROM menu WHERE menuId = ?";
	private final static String SELECT_ALL = "SELECT * FROM menu";
	private final static String UPDATE_QUERY = "UPDATE menu SET name=?, price=?, description=?, imagePath=?, isAvailable=?, restaurantId=?, ratings=? WHERE menuId=?";
	private final static String DELETE_QUERY = "DELETE FROM menu WHERE menuId = ?";

	private Connection con;

	public MenuDaoI() {
		String url = "jdbc:mysql://localhost:3306/foodiemall";
		String username = "root";
		String password = "226D5a0501@";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addMenu(Menu menu) {
		try (PreparedStatement ps = con.prepareStatement(INSERT_QUERY)) {
			ps.setString(1, menu.getName());
			ps.setInt(2, menu.getPrice());
			ps.setString(3, menu.getDescription());
			ps.setString(4, menu.getImagePath());
			ps.setBoolean(5, menu.isAvailable());
			ps.setInt(6, menu.getRestaurantId());
			ps.setFloat(7, menu.getRatings());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Menu getMenuById(int menuId) {
		try (PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
			ps.setInt(1, menuId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Menu(rs.getInt("menuId"), rs.getString("name"), rs.getInt("price"),
						rs.getString("description"), rs.getString("imagePath"), rs.getBoolean("isAvailable"),
						rs.getInt("restaurantId"), rs.getFloat("ratings"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Menu> getAllMenus() {
		List<Menu> list = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(SELECT_ALL); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(new Menu(rs.getInt("menuId"), rs.getString("name"), rs.getInt("price"),
						rs.getString("description"), rs.getString("imagePath"), rs.getBoolean("isAvailable"),
						rs.getInt("restaurantId"), rs.getFloat("ratings")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateMenu(Menu menu) {
		try (PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)) {
			ps.setString(1, menu.getName());
			ps.setInt(2, menu.getPrice());
			ps.setString(3, menu.getDescription());
			ps.setString(4, menu.getImagePath());
			ps.setBoolean(5, menu.isAvailable());
			ps.setInt(6, menu.getRestaurantId());
			ps.setFloat(7, menu.getRatings());
			ps.setInt(8, menu.getMenuId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMenu(int menuId) {
		try (PreparedStatement ps = con.prepareStatement(DELETE_QUERY)) {
			ps.setInt(1, menuId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Menu> getMenusByRestaurant(int restaurantId) {
		List<Menu> menuList = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM menu WHERE restaurantId = ?")) {
			ps.setInt(1, restaurantId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				menuList.add(new Menu(rs.getInt("menuId"), rs.getString("name"), rs.getInt("price"),
						rs.getString("description"), rs.getString("imagePath"), rs.getBoolean("isAvailable"),
						rs.getInt("restaurantId"), rs.getFloat("ratings")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}

}
