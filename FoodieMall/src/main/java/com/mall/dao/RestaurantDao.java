package com.mall.dao;

import java.util.List;

import com.mall.model.Restaurant;

public interface RestaurantDao {

	void addRestaurant(Restaurant RestaurantId);

	Restaurant getRestaurant(int RestaurantId);

	void updateRestaurant(Restaurant RestaurantId);

	void deleteRestaurant(int RestaurantId);

	List<Restaurant> getAllRestaurant();

}
