package com.mall.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mall.dao.UserDao;
import com.mall.model.User;

public class UserDaoI implements UserDao {
	final static String INSERT_QUERY = "INSERT into `user` "
			+ "(`userId`,`name`,`email`,`phoneNo`,`address`,`username`,`password`,`Role`,`createdDate`,`lastlogin`) "
			+ "values (?,?,?,?,?,?,?,?,?,?)"; // whenever we use final to variable assign in all capitals
	final static String SELECT_QUERY = "SELECT * from `user` WHERE `userId` = ?";
	final static String DELETE_QUERY = "DELETE from `user` WHERE `userId` = ?";
	final static String SELECT_ALL_QUERY = "SELECT * from `user`";
	final static String UPDATE_QUERY = "UPDATE `user` " + "SET `username` = ?, " + "`password` = ?, " + "`email` = ?, "
			+ "`address` = ?, " + "`role` = ?, " + "`name` = ?, " + "`phoneNo` = ?, " + "`createdDate` = ?, "
			+ "`lastlogin` = ? " + "WHERE `userId` = ?";

	private Connection con;

	public UserDaoI() {
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
	public void addUser(User user) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = con.prepareStatement(INSERT_QUERY);
			preparedStatement.setInt(1, user.getUserId());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setInt(4, user.getPhoneNo());
			preparedStatement.setString(5, user.getAddress());
			preparedStatement.setString(6, user.getUserName());
			preparedStatement.setString(7, encrypt(user.getPassword()));
			preparedStatement.setString(8, user.getRole());
			preparedStatement.setObject(9, user.getCreatedDate());
			preparedStatement.setObject(10, user.getLastlogin());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(int userId) {
		PreparedStatement preparedStatement2 = null;
		ResultSet res = null;
		User user = null;
		try {
			preparedStatement2 = con.prepareStatement(SELECT_QUERY);
			preparedStatement2.setInt(1, userId);
			res = preparedStatement2.executeQuery();
			if (res.next()) {
				String name = res.getString("name");
				String email = res.getString("email");
				int phoneNo = res.getInt("phoneNo");
				String address = res.getString("address");
				String username = res.getString("username");
				String password = decrypt(res.getString("password"));
				String Role = res.getString("role");
				LocalDateTime createdDate = res.getTimestamp("createdDate").toLocalDateTime();
				LocalDateTime lastlogin = res.getTimestamp("lastlogin").toLocalDateTime();
				user = new User(userId, name, email, phoneNo, address, username, password, Role, createdDate,
						lastlogin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = con.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, encrypt(user.getPassword()));
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getAddress());
			preparedStatement.setString(5, user.getRole());
			preparedStatement.setString(6, user.getName());
			preparedStatement.setInt(7, user.getPhoneNo());
			preparedStatement.setObject(8, user.getCreatedDate());
			preparedStatement.setObject(9, user.getLastlogin());
			preparedStatement.setInt(10, user.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userId) {
		PreparedStatement preparedStatement4 = null;
		try {
			preparedStatement4 = con.prepareStatement(DELETE_QUERY);
			preparedStatement4.setInt(1, userId);
			preparedStatement4.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUsers() {
		Statement stmt = null;
		ResultSet res = null;
		ArrayList<User> userlist = new ArrayList<User>();
		try {
			stmt = con.createStatement();
			res = stmt.executeQuery(SELECT_ALL_QUERY);
			while (res.next()) {
				int userId = res.getInt("userId");
				String name = res.getString("name");
				String email = res.getString("email");
				int phoneNo = res.getInt("phoneNo");
				String address = res.getString("address");
				String username = res.getString("username");
				String password = decrypt(res.getString("password"));
				String Role = res.getString("role");
				LocalDateTime createdDate = res.getTimestamp("createdDate").toLocalDateTime();
				LocalDateTime lastlogin = res.getTimestamp("lastlogin").toLocalDateTime();
				User user = new User(userId, name, email, phoneNo, address, username, password, Role, createdDate,
						lastlogin);
				userlist.add(user);
			}
		} catch (SQLException | NullPointerException e) {
			// e.printStackTrace();
			System.out.println("Check later...");
		}
		return userlist;
	}

	@Override
	public List<User> getVerifyByUserName(String username) {
		List<User> users = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE username = ?")) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String encryptedPassword = rs.getString("password");
				String decryptedPassword = decrypt(encryptedPassword);

				users.add(new User(rs.getInt("userId"), rs.getString("name"), rs.getString("email"),
						rs.getInt("phoneNo"), rs.getString("address"), rs.getString("username"), decryptedPassword,
						rs.getString("role"), rs.getTimestamp("createdDate").toLocalDateTime(),
						rs.getTimestamp("lastlogin").toLocalDateTime()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public void updateUserContactInfo(User user) {
		String updateContactQuery = "UPDATE `user` SET `phoneNo` = ?, `address` = ?, `lastlogin` = ? WHERE `userId` = ?";
		try (PreparedStatement ps = con.prepareStatement(updateContactQuery)) {
			ps.setInt(1, user.getPhoneNo());
			ps.setString(2, user.getAddress());
			ps.setObject(3, user.getLastlogin());
			ps.setInt(4, user.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Encryption and Decryption for Password
	private String encrypt(String input) {
		StringBuilder encrypted = new StringBuilder();
		for (char c : input.toCharArray()) {
			encrypted.append((char) (c + 1));
		}
		return encrypted.toString();
	}

	private String decrypt(String input) {
		StringBuilder decrypted = new StringBuilder();
		for (char c : input.toCharArray()) {
			decrypted.append((char) (c - 1));
		}
		return decrypted.toString();
	}

}
