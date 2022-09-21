<%@ page import="dao.OrderDao"%>
<%@ page import="java.util.List"%>
<%@ page import="db.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Orders - JapaShop</title>
<%@ include file="../component/allcss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<c:if test="${empty userObj}">
		<c:redirect url="login_index.jsp"></c:redirect>
	</c:if>
	
	<%@ include file="../user_navbar.jsp"%>
	<div class="container-fluid p-3">
		<div class="row">
			<div class="col-md-12">
				<div class="card paint-card"><br>
					<span class="text-center fs-3">Your Orders</span>
					<div class="card-body">
						<table class="table table-striped table-hover">
							<thead class="table-dark">
								<tr>
									<th scope="col" class="text-center">#</th>
									<th scope="col" class="text-center">Name</th>
									<th scope="col" class="text-center">Category</th>
									<th scope="col" class="text-center">Price</th>
									<th scope="col" class="text-center">Quantity</th>
									<th scope="col" class="text-center">Sub-Total</th>
									<th scope="col" class="text-center">Order Date</th>
									<th scope="col" class="text-center">Expected Delivery Date</th>
									<th scope="col" class="text-center">Payment Method</th>
									<th scope="col" class="text-center">Status</th>
								</tr>
							</thead>
							<tbody>
								<%
									int serial = 0;
									String email = request.getSession().getAttribute("email").toString();
									OrderDao dao = new OrderDao(DBConnection.getConn());
									List<List<String>> list = dao.getUserOrdersList(email);
									for(List<String> orders: list) {
								%>
								<tr>
									<td class="text-center"><%out.println(serial = serial + 1);%></td>
									<td class="text-center"><%out.println(orders.get(0));%></td>
									<td class="text-center"><%out.println(orders.get(1));%></td>
									<td class="text-center"><%out.println(orders.get(2));%></td>
									<td class="text-center"><%out.println(orders.get(3));%></td>
									<td class="text-center"><%out.println(orders.get(4));%></td>
									<td class="text-center"><%out.println(orders.get(5));%></td>
									<td class="text-center"><%out.println(orders.get(6));%></td>
									<td class="text-center"><%out.println(orders.get(7));%></td>
									<td class="text-center"><%out.println(orders.get(8));%></td>
								</tr>
								<% } %>
							</tbody>
						</table>		
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>