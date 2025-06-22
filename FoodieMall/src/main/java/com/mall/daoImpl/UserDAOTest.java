package com.mall.daoImpl;

import java.time.LocalDateTime;
import java.util.List;

import com.mall.model.User;

public class UserDAOTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDaoI userDAO = new UserDaoI();

		// Test addUser method
		User user = new User();
		user.setUserId(1);
		user.setName("Jeevan");
		user.setUserName("JohnDoe");
		user.setPassword("password123");
		user.setEmail("johndoe@example.com");
		user.setAddress("123 Main St");
		user.setPhoneNo(1234567890);
		user.setRole("customer");
		user.setCreatedDate(LocalDateTime.now());
		user.setLastlogin(LocalDateTime.now());
		userDAO.addUser(user);
		System.out.println("User added successfully");

		// Test getUser method
		User retrievedUser = userDAO.getUser(1);
		if (retrievedUser != null) {
			System.out.println("Retrieved User: " + retrievedUser.getUserName());
		} else {
			System.out.println("User not found");
		}

		// Test updateUser method
		user.setUserName("JaneDoe");
		userDAO.updateUser(user);
		System.out.println("User updated successfully");

		// Test getAllUsers method
		List<User> users = userDAO.getAllUsers();
		System.out.println("All Users:");
		if (users != null) {
			for (User u : users) {
				System.out.println("------------------------");
				System.out.println("User ID: " + u.getUserId());
				System.out.println("Name: " + u.getName());
				System.out.println("Email: " + u.getEmail());
				System.out.println("Phone No: " + u.getPhoneNo());
				System.out.println("Address: " + u.getAddress());
				System.out.println("Username: " + u.getUserName());
				System.out.println("Password: " + u.getPassword());
				System.out.println("Role: " + u.getRole());
				System.out.println("Created Date: " + u.getCreatedDate());
				System.out.println("Last Login: " + u.getLastlogin());
				System.out.println("------------------------");
			}
		} else {
			System.out.println("No users found");
		}

		// Test deleteUser method
		userDAO.deleteUser(1);
		System.out.println("User deleted successfully");

	}

}
