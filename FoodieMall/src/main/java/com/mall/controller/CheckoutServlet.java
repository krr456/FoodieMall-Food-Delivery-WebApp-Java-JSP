package com.mall.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mall.model.CartCreator;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String address = request.getParameter("address");
		String paymentMode = request.getParameter("paymentMode");

		if (address == null || paymentMode == null || address.isEmpty() || paymentMode.isEmpty()) {
			response.sendRedirect("Checkout.jsp?error=Missing details");
			return;
		}

		// Retrieve cart items and store them for confirmation display
		CartCreator cart = (CartCreator) session.getAttribute("cart");
		if (cart == null || cart.getAllItems().isEmpty()) {
			response.sendRedirect("CartViewServlet?error=Cart is empty");
			return;
		}

		session.removeAttribute("cart");
		session.setAttribute("cartItems", cart.getAllItems()); // Passing cart items for display

		request.getRequestDispatcher("OrderConfirmation.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("Checkout.jsp").forward(request, response);
	}
}
