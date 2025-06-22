package com.mall.daoImpl;

import com.mall.model.Menu;

import java.util.List;

public class MenuDaoTest {

	public static void main(String[] args) {
		MenuDaoI dao = new MenuDaoI();

		// CREATE
		Menu newMenu = new Menu();
		newMenu.setName("Chicken Biryani");
		newMenu.setPrice(199);
		newMenu.setDescription("Spicy biryani with raita");
		newMenu.setImagePath("images/biryani.jpg");
		newMenu.setAvailable(true);
		newMenu.setRestaurantId(3);
		newMenu.setRatings(4.5f);

		dao.addMenu(newMenu);
		System.out.println("1️ Menu item added.");

		// READ ALL
		List<Menu> menuList = dao.getAllMenus();
		if (menuList.isEmpty()) {
			System.out.println("No menu items found.");
			return;
		}

		int testMenuId = menuList.get(menuList.size() - 1).getMenuId();
		System.out.println("2️ All Menu Items:");
		for (Menu m : menuList) {
			System.out.println(m.getMenuId() + ": " + m.getName() + " - ₹" + m.getPrice());
		}

		// READ BY ID
		Menu found = dao.getMenuById(testMenuId);
		if (found != null) {
			System.out.println("3️ Found: " + found.getName());

			// UPDATE
			found.setPrice(219);
			found.setRatings(4.7f);
			dao.updateMenu(found);
			System.out.println("4️ Menu item updated.");

			// READ AFTER UPDATE
			Menu updated = dao.getMenuById(testMenuId);
			System.out.println("5️ Updated: ₹" + updated.getPrice() + ", Ratings: " + updated.getRatings());

			// DELETE
			dao.deleteMenu(testMenuId);
			System.out.println("6️ Menu item deleted.");

			// VERIFY DELETE
			Menu afterDelete = dao.getMenuById(testMenuId);
			if (afterDelete == null) {
				System.out.println("7️ Menu item no longer exists.");
			} else {
				System.out.println("⚠️ Deletion failed.");
			}
		} else {
			System.out.println(" Could not find menu item by ID.");
		}
	}
}
