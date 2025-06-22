package com.mall.model;

public class Restaurant {

	private int restaurantId;
	private String name;
	private String imagePath;
	private float ratings;
	private String eta;
	private String cuisineType;
	private String address;
	private int restaurantOwnerId;
	private Boolean isActive;

	public Restaurant() {
		// TODO Auto-generated constructor stub
	}

	public Restaurant(int restaurantId, String name, String imagePath, float ratings, String eta, String cuisineType,
			String address, int restaurantOwnerId, Boolean isActive) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.imagePath = imagePath;
		this.ratings = ratings;
		this.eta = eta;
		this.cuisineType = cuisineType;
		this.address = address;
		this.restaurantOwnerId = restaurantOwnerId;
		this.isActive = isActive;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public float getRatings() {
		return ratings;
	}

	public void setRatings(float ratings) {
		this.ratings = ratings;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRestaurantOwnerId() {
		return restaurantOwnerId;
	}

	public void setRestaurantOwnerId(int restaurantOwnerId) {
		this.restaurantOwnerId = restaurantOwnerId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
//9 rows in set (0.01 sec)
