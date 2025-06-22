package com.mall.daoImpl;

import com.mall.model.Restaurant;

import java.util.List;

public class RestaurantDAOTest {

	public static void main(String[] args) {
		RestaurantDaoI dao = new RestaurantDaoI();

		// CREATE
		Restaurant newRestaurant = new Restaurant();
		newRestaurant.setName("Hyd Spice Kitchen");
		newRestaurant.setImagePath("images/spice.jpg");
		newRestaurant.setRatings(4.2f);
		newRestaurant.setEta("30 mins");
		newRestaurant.setCuisineType("Hyderabadi");
		newRestaurant.setAddress("Gachibowli");
		newRestaurant.setRestaurantOwnerId(12);
		newRestaurant.setIsActive(true);

		dao.addRestaurant(newRestaurant);
		System.out.println("1️ Restaurant added.");

		// READ ALL

		List<Restaurant> allRestaurants = dao.getAllRestaurant();
		System.out.println("2 All Restaurants in Database:");
		if (allRestaurants.isEmpty()) {
			System.out.println("No restaurants found.");
		} else {
			for (Restaurant r : allRestaurants) {
				System.out.println("ID: " + r.getRestaurantId());
				System.out.println("Name: " + r.getName());
				System.out.println("Cuisine: " + r.getCuisineType());
				System.out.println("ETA: " + r.getEta());
				System.out.println("Ratings: " + r.getRatings());
				System.out.println("Owner ID: " + r.getRestaurantOwnerId());
				System.out.println("Active: " + r.getIsActive());
				System.out.println("---------------------------");
			}
		}

		// READ BY ID
		int testId = allRestaurants.get(allRestaurants.size() - 1).getRestaurantId();
		Restaurant found = dao.getRestaurant(testId);
		if (found != null) {
			System.out.println("3 Retrieved restaurant: " + found.getName());
		} else {
			System.out.println("❌ No restaurant found with that ID.");
		}

		// UPDATE
		found.setName("Updated Spice Kitchen");
		found.setRatings(4.6f);
		dao.updateRestaurant(found);
		System.out.println("4️ Restaurant updated.");

		// READ AFTER UPDATE
		Restaurant updated = dao.getRestaurant(testId);
		System.out.println("5️ Updated name: " + updated.getName());

		// DELETE
		dao.deleteRestaurant(testId);
		System.out.println("6️ Restaurant deleted.");

		// READ AFTER DELETE
		Restaurant afterDelete = dao.getRestaurant(testId);
		if (afterDelete == null) {
			System.out.println("7️ Restaurant no longer exists.");
		} else {
			System.out.println("⚠️ Delete failed.");
		}
	}
}
