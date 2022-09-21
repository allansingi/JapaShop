<%@page import="dao.SupportDao"%>
<%@ page import="dao.OrderDao"%>
<%@ page import="dao.ProductDao"%>
<%@ page import="dao.UserDao"%>
<%@ page import="db.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Home - Japa Shop</title>
<%@ include file="../component/allcss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@ include file="admin_navbar.jsp"%>

	<c:if test="${empty adminObj}">
		<c:redirect url="../login_index.jsp"></c:redirect>
	</c:if>

	<div class="container p-5">
		<p class="text-center fs-3">Admin Dashboard</p>

		<div class="row">
			<div class="col-md-4">
				<div class="card paint-card">
				<%
					UserDao userDao = new UserDao(DBConnection.getConn());
					ProductDao productDao = new ProductDao(DBConnection.getConn());
					OrderDao orderDao = new OrderDao(DBConnection.getConn());
					SupportDao supportDao = new SupportDao(DBConnection.getConn());
				%>
					<div class="card-body text-center text-secondary">
						<i class="fas fa-user-circle fa-3x"></i><br>
						<p class="fs-4 text-center">Total Users<br><%=userDao.countUsers()%></p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-secondary">
						<i class="fab fa-product-hunt fa-3x"></i><br>
						<p class="fs-4 text-center">Total Products<br><%=productDao.countProducts()%></p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card paint-card">
					<div class="card-body text-center text-secondary">
						<i class="fa-solid fa-cash-register fa-3x"></i><br>
						<p class="fs-4 text-center">New Orders<br><%=orderDao.getAdminNewOrdersTotal()%></p>
					</div>
				</div>
			</div>
			<div class="col-md-4 mt-2">
				<div class="card paint-card">
					<div class="card-body text-center text-secondary">
						<i class="fa-regular fa-envelope fa-3x"></i><br>
						<p class="fs-4 text-center">Messages<br><%=supportDao.countMessages()%></p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>