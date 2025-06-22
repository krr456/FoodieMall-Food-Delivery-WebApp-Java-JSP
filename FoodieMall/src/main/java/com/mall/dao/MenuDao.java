package com.mall.dao;

import java.util.List;
import com.mall.model.Menu;

public interface MenuDao {

	void addMenu(Menu menu);

	Menu getMenuById(int menuId);

	List<Menu> getAllMenus();

	void updateMenu(Menu menu);

	void deleteMenu(int menuId);

	List<Menu> getMenusByRestaurant(int restaurantId);
}
