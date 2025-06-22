package com.mall.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.model.CartCreator;
import com.mall.model.CartItem;
import com.mall.model.User;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		// It is used to if the user isn't logged in, store the added item and redirect
		// to login
		if (user == null) {
			session.setAttribute("pendingMenuId", request.getParameter("menuId"));
			session.setAttribute("pendingQuantity", request.getParameter("quantity"));
			session.setAttribute("pendingPrice", request.getParameter("price"));
			session.setAttribute("pendingName", request.getParameter("name"));
			session.setAttribute("pendingRestaurantId", request.getParameter("restaurantId"));

			response.sendRedirect("Login.jsp");
			return;
		}

		// Retrieve cart from session (ensure it exists)
		CartCreator cart = (CartCreator) session.getAttribute("cart");
		if (cart == null) {
			cart = new CartCreator();
			session.setAttribute("cart", cart);
		}

		// Fetch action type (add, update, delete)
		String action = request.getParameter("action");
		if (action == null || action.isEmpty()) {
			response.sendRedirect("CartViewServlet?error=Missing action type");
			return;
		}

		// Validate `menuId` before parsing
		String menuIdStr = request.getParameter("menuId");
		if (menuIdStr == null || menuIdStr.isEmpty()) {
			response.sendRedirect("CartViewServlet?error=Missing menuId");
			return;
		}

		int menuId;
		try {
			menuId = Integer.parseInt(menuIdStr);
		} catch (NumberFormatException e) {
			response.sendRedirect("CartViewServlet?error=Invalid menuId format");
			return;
		}

		if ("add".equals(action)) {
			String quantityStr = request.getParameter("quantity");
			String priceStr = request.getParameter("price");
			String nameStr = request.getParameter("name");
			String restaurantIdStr = request.getParameter("restaurantId");

			if (quantityStr == null || priceStr == null || nameStr == null || restaurantIdStr == null
					|| quantityStr.isEmpty() || priceStr.isEmpty() || nameStr.isEmpty() || restaurantIdStr.isEmpty()) {
				response.sendRedirect("CartViewServlet?error=Missing item details");
				return;
			}

			int quantity = Integer.parseInt(quantityStr);
			int price = Integer.parseInt(priceStr);
			int restaurantId = Integer.parseInt(restaurantIdStr);

			CartItem item = new CartItem(menuId, restaurantId, nameStr, price, quantity);
			cart.addCartItem(item);
			session.setAttribute("cart", cart);
		}

		else if ("update".equals(action)) {
			String quantityStr = request.getParameter("quantity");

			if (quantityStr == null || quantityStr.isEmpty()) {
				response.sendRedirect("CartViewServlet?error=Missing quantity");
				return;
			}

			int quantity;
			try {
				quantity = Integer.parseInt(quantityStr);
			} catch (NumberFormatException e) {
				response.sendRedirect("CartViewServlet?error=Invalid quantity format");
				return;
			}

			// Ensure item exists before updating
			if (cart.getAllItems().containsKey(menuId)) {
				cart.updateCartItem(menuId, quantity);
			} else {
				response.sendRedirect("CartViewServlet?error=Item not found in cart");
				return;
			}
		}
		// Handle "delete" operation: Ensure item exists before deleting
		else if ("delete".equals(action)) {
			if (cart.getAllItems().containsKey(menuId)) {
				cart.deleteCartItem(menuId);
			} else {
				response.sendRedirect("CartViewServlet?error=Item not found in cart");
				return;
			}
		}

		// ✅ Redirect to cart view
		response.sendRedirect("CartViewServlet");
	}

}
