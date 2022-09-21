<%@ page import="dao.OrderDao"%>
<%@ page import="java.util.List"%>
<%@ page import="db.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../component/allcss.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delivered Orders - Japa Shop</title>
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
		
		<div class="container-fluid p-3">
			<div class="row">
				<div class="col-md-12">
					<div class="card paint-card">
						<div class="card-body">
							<p class="fs-3 text-center">Delivered Orders Details</p>
							<table class="table table-striped table-hover">
								<thead class="table-dark">
									<tr>
										<th scope="col" class="text-center">Mobile Number</th>
										<th scope="col" class="text-center">Product Name</th>
										<th scope="col" class="text-center">Quantity</th>
										<th scope="col" class="text-center">Sub-Total</th>
										<th scope="col" class="text-center">Address</th>
										<th scope="col" class="text-center">City</th>
										<th scope="col" class="text-center">State</th>
										<th scope="col" class="text-center">Country</th>
										<th scope="col" class="text-center">Order Date</th>
										<th scope="col" class="text-center">Expected Delivery Date</th>
										<th scope="col" class="text-center">Payment Method</th>
										<th scope="col" class="text-center">Transaction ID</th>
										<th scope="col" class="text-center">Status</th>
									</tr>
								</thead>
								<tbody>
									<%
										OrderDao dao = new OrderDao(DBConnection.getConn());
										List<List<String>> list = dao.getAdminOrderListWithDeliveredStatus();
										for(List<String> orders: list) {
									%>
									<tr>
										<td class="text-center"><%=orders.get(0)%></td>
										<td class="text-center"><%=orders.get(1)%></td>
										<td class="text-center"><%=orders.get(2)%></td>
										<td class="text-center"><%=orders.get(3)%></td>
										<td class="text-center"><%=orders.get(4)%></td>
										<td class="text-center"><%=orders.get(5)%></td>
										<td class="text-center"><%=orders.get(6)%></td>
										<td class="text-center"><%=orders.get(7)%></td>
										<td class="text-center"><%=orders.get(8)%></td>
										<td class="text-center"><%=orders.get(9)%></td>
										<td class="text-center"><%=orders.get(10)%></td>
										<td class="text-center"><%=orders.get(11)%></td>
										<td class="text-center"><%=orders.get(12)%></td>
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