<%@ page import="dao.ProductDao"%>
<%@ page import="java.util.List"%>
<%@ page import="db.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../component/allcss.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Details - JapaShop</title>
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
	
	<div class="container-fluid p-3 themed-container">
		<div class="row">
			<div class="col-md-12">
				<div class="card paint-card mb-4">
					<div class="card-body">
						<%
							String email = request.getSession().getAttribute("email").toString();
							ProductDao dao = new ProductDao(DBConnection.getConn());
							int total = dao.getCartTotalAmount(email);
							int serial = 0;
						%>
						<div class="container">
							<a class="btn btn-sm btn-dark mb-2 fs-5" href="product_cart.jsp"><i class="fa-sharp fa-solid fa-circle-arrow-left"></i> Back</a>
							<span class="text-dark fs-4">TOTAL $ <%=total%> </span>
						</div>
						
						<table class="table table-striped table-hover">
							<thead class="table-dark">
								<tr>
									<th scope="col" class="text-center">#</th>
									<th scope="col" class="text-center">Product Name</th>
									<th scope="col" class="text-center">Category</th>
									<th scope="col" class="text-center">Price</th>
									<th scope="col" class="text-center">Quantity</th>
									<th scope="col" class="text-center">Sub Total</th>
								</tr>
							</thead>
							<tbody>
								<%
									ProductDao dao2 = new ProductDao(DBConnection.getConn());
									List<List<String>> list = dao2.getCartList(email);
									for(List<String> cartList: list) {
								%>
								<tr>
									<td class="text-center"><%out.println(serial = serial + 1);%></td>
									<td class="text-center"><%out.println(cartList.get(0));%></td>
									<td class="text-center"><%out.println(cartList.get(1));%></td>
									<td class="text-center"><%out.println(cartList.get(2));%></td>
									<td class="text-center"><%out.println(cartList.get(4));%></td>
									<td class="text-center"><%out.println(cartList.get(5));%></td>
								</tr>
								<% } %>
							</tbody>
						</table>		
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-5 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Your Order Details</p>
						<form action="submitOrderServlet" method="post">
							<div class="mb-3">
								<label class="form-label">Address</label>
								<input class="form-control" type="text" name="address" value="${userObj.address}" placeholder="Type the shipping address here" required>
							</div>
							<div class="mb-3">
								<label class="form-label">City</label>
								<input class="form-control" type="text"  name="city" value="${userObj.city}" placeholder="Type the shipping City here" required>
							</div>
							<div class="mb-3">
								<label class="form-label">State</label>
								<input class="form-control" type="text" name="state" value="${userObj.state}" placeholder="Type the shipping State here" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Country</label>
								<input class="form-control" type="text" name="country" value="${userObj.country}" placeholder="Type the shipping Country here" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Payment Method</label>
								<select class="form-select" name="payment_method" aria-label="Default select example">
									<option value="Full Payment on Delivery (Cash or Card)" selected>Full Payment on Delivery (Cash or Card)</option>
									<option value="Online">Online</option>
								</select>
							</div>
							<div class="mb-3">
								<label class="form-label">Transaction Id</label>
								<input class="form-control" type="text" name="transaction_id" placeholder="Enter the transaction Id here" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Mobile Number</label>
								<input class="form-control" type="number" name="mobileNumber" value="${userObj.mobileNumber}" placeholder="Type a mobile contact for delivery status updates" required>
							</div>
							<input type="hidden" name="email" value="${userObj.email}"/>
							<button type="submit" class="btn btn-dark col-md-12">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>