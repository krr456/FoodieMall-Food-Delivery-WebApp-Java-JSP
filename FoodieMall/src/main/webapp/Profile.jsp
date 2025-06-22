<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.mall.model.User"%>
<%
    User user = (User) request.getAttribute("user");
    if (user == null) {
        user = (User) session.getAttribute("user"); // Fallback if forwarded attribute not found
    }

    String successMsg = request.getParameter("successMsg");
    if (successMsg == null) {
        successMsg = (String) request.getAttribute("successMsg");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Profile | FoodieMall</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(145deg, #f3f4f7, #eef1f5);
            margin: 0;
            padding: 0;
        }

        .profile-container {
            max-width: 560px;
            margin: 60px auto;
            background: #fff;
            border-radius: 14px;
            box-shadow: 0 10px 32px rgba(0, 0, 0, 0.08);
            padding: 36px 32px;
        }

        .profile-container h2 {
            margin-top: 0;
            font-size: 24px;
            color: #333;
            text-align: center;
        }

        .success-msg {
            color: green;
            font-weight: bold;
            text-align: center;
            margin-bottom: 20px;
        }

        .profile-data p {
            font-size: 15px;
            margin: 8px 0;
            color: #444;
        }

        .profile-data p strong {
            display: inline-block;
            width: 110px;
            color: #007bff;
        }

        .edit-form {
            margin-top: 30px;
        }

        label {
            display: block;
            margin: 16px 0 6px;
            color: #555;
            font-weight: 600;
        }

        input[type="text"], textarea {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 6px;
            resize: vertical;
        }

        .submit-btn {
            margin-top: 24px;
            width: 100%;
            background-color: #007bff;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 6px;
            font-weight: bold;
            cursor: pointer;
        }

        .submit-btn:hover {
            background-color: #0056b3;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #007bff;
            font-weight: bold;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <% if (successMsg != null) { %>
            <p class="success-msg"><%= successMsg %></p>
        <% } %>

        <% if (user != null) { %>
            <h2>Hello, <%= user.getName() %> 👋</h2>

            <div class="profile-data">
                <p><strong>Username:</strong> <%= user.getUserName() %></p>
                <p><strong>Email:</strong> <%= user.getEmail() %></p>
                <p><strong>Role:</strong> <%= user.getRole() %></p>
                <p><strong>Created:</strong> <%= user.getCreatedDate() %></p>
                <p><strong>Last Login:</strong> <%= user.getLastlogin() %></p>
                <p><strong>Phone:</strong> <%= user.getPhoneNo() == 0 ? "Not set" : user.getPhoneNo() %></p>
                <p><strong>Address:</strong> <%= user.getAddress() == null || user.getAddress().isEmpty() ? "Not set" : user.getAddress() %></p>
            </div>

            <form class="edit-form" action="UpdateProfileServlet" method="post">
                <input type="hidden" name="userId" value="<%= user.getUserId() %>">

                <label for="phoneNo">Phone Number</label>
                <input type="text" id="phoneNo" name="phoneNo"
                       value="<%= user.getPhoneNo() == 0 ? "" : user.getPhoneNo() %>"
                       placeholder="Enter your phone number">

                <label for="address">Address</label>
                <textarea id="address" name="address" rows="3"
                          placeholder="Enter your address"><%= user.getAddress() == null ? "" : user.getAddress() %></textarea>

                <button type="submit" class="submit-btn">Save Changes</button>
            </form>

            <a href="callServlet" class="back-link">← Back to Dashboard</a>
        <% } else { %>
            <p style="color: red; text-align: center;">
                User not found. Please <a href="Login.jsp">log in again</a>.
            </p>
        <% } %>
    </div>
</body>
</html>
