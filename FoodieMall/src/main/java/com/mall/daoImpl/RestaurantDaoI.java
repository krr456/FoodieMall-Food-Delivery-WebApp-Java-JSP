package com.mall.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mall.dao.RestaurantDao;
import com.mall.model.Restaurant;

public class RestaurantDaoI implements RestaurantDao {

	final static String INSERT_QUERY = "INSERT INTO "
			+ "restaurant(name, imagePath, ratings, eta, cuisineType, address, restaurantOwnerId, isActive) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	final static String SELECT_QUERY = "SELECT * FROM `restaurant` WHERE `restaurantId` = ? ";
	final static String DELETE_QUERY = "DELETE FROM `restaurant` WHERE `restaurantId` = ? ";
	final static String SELECTALL_QUERY = "SELECT * FROM `restaurant`";
	final static String UPDATE_QUERY = "UPDATE restaurant SET name=?, imagePath=?, ratings=?, eta=?, cuisineType=?, address=?, restaurantOwnerId=?, isActive=? WHERE restaurantId=?";

	private Connection con;

	public RestaurantDaoI() {
		String url = "jdbc:mysql://localhost:3306/foodiemall";
		String username = "root";
		String password = "226D5a0501@";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_QUERY)) {
			preparedStatement.setString(1, restaurant.getName());
			preparedStatement.setString(2, restaurant.getImagePath());
			preparedStatement.setFloat(3, restaurant.getRatings());
			preparedStatement.setString(4, restaurant.getEta());
			preparedStatement.setString(5, restaurant.getCuisineType());
			preparedStatement.setString(6, restaurant.getAddress());
			preparedStatement.setInt(7, restaurant.getRestaurantOwnerId());
			preparedStatement.setBoolean(8, restaurant.getIsActive());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		try (PreparedStatement ps = con.prepareStatement(SELECT_QUERY)) {
			ps.setInt(1, restaurantId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Restaurant(rs.getInt("restaurantId"), rs.getString("name"), rs.getString("imagePath"),
						rs.getFloat("ratings"), rs.getString("eta"), rs.getString("cuisineType"),
						rs.getString("address"), rs.getInt("restaurantOwnerId"), rs.getBoolean("isActive"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		try (PreparedStatement ps = con.prepareStatement(UPDATE_QUERY)) {
			ps.setString(1, restaurant.getName());
			ps.setString(2, restaurant.getImagePath());
			ps.setFloat(3, restaurant.getRatings());
			ps.setString(4, restaurant.getEta());
			ps.setString(5, restaurant.getCuisineType());
			ps.setString(6, restaurant.getAddress());
			ps.setInt(7, restaurant.getRestaurantOwnerId());
			ps.setBoolean(8, restaurant.getIsActive());
			ps.setInt(9, restaurant.getRestaurantId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		try (PreparedStatement ps = con.prepareStatement(DELETE_QUERY)) {
			ps.setInt(1, restaurantId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		List<Restaurant> list = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(SELECTALL_QUERY); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(new Restaurant(rs.getInt("restaurantId"), rs.getString("name"), rs.getString("imagePath"),
						rs.getFloat("ratings"), rs.getString("eta"), rs.getString("cuisineType"),
						rs.getString("address"), rs.getInt("restaurantOwnerId"), rs.getBoolean("isActive")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

//+-------------------+-------------+------+-----+---------+----------------+
//| Field             | Type        | Null | Key | Default | Extra          |
//+-------------------+-------------+------+-----+---------+----------------+
//| restaurantId      | int         | NO   | PRI | NULL    | auto_increment |
//| name              | varchar(45) | YES  |     | NULL    |                |
//| imagePath         | varchar(45) | YES  |     | NULL    |                |
//| ratings           | float       | YES  |     | NULL    |                |
//| eta               | varchar(45) | YES  |     | NULL    |                |
//| cuisineType       | varchar(45) | YES  |     | NULL    |                |
//| address           | varchar(45) | YES  |     | NULL    |                |
//| restaurantOwnerId | int         | YES  | MUL | NULL    |                |
//| isActive          | tinyint(1)  | YES  |     | 1       |                |
//+-------------------+-------------+------+-----+---------+----------------+