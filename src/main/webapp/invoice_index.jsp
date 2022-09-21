<%@ page import="dao.OrderDao"%>
<%@ page import="java.util.List"%>
<%@ page import="db.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../component/allcss.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Invoice Details - Japa Shop</title>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
.themed-grid-col {
  padding-top: .75rem;
  padding-bottom: .75rem;
  background-color: rgba(86, 61, 124, .15);
  border: 1px solid rgba(86, 61, 124, .2);
}
</style>
</head>
<body>
	<c:if test="${empty userObj}">
		<c:redirect url="login_index.jsp"></c:redirect>
	</c:if>
	
	<div class="container-fluid p-3">
		<div class="row">
		<%
			String email = request.getSession().getAttribute("email").toString();
			OrderDao dao = new OrderDao(DBConnection.getConn());
			List<String> list = dao.getUserDetailsWithInvoiceStatus(email);
		%>
			<div class="col-md-12">
				<div class="mb-4">
					<h2 class="mt-4 text-center mb-4">Your Order Invoice</h2>
    				<div class="row text-center">
				    	<div class="col-6 col-sm-4 themed-grid-col">Full Name</div>
				    	<div class="col-6 col-sm-4 themed-grid-col">Email</div>
				    	<div class="col-6 col-sm-4 themed-grid-col">Mobile Number</div>
				    </div>
				    <div class="row mb-3 text-center">
				    	<div class="col-6 col-sm-4 themed-grid-col"><%out.println(list.get(0));%></div>
				    	<div class="col-6 col-sm-4 themed-grid-col"><%out.println(list.get(1));%></div>
				    	<div class="col-6 col-sm-4 themed-grid-col"><%out.println(list.get(2));%></div>
				    </div>
				    <div class="row text-center">
				    	<div class="col-6 col-sm-4 themed-grid-col">Order Date</div>
				    	<div class="col-6 col-sm-4 themed-grid-col">Payment Method</div>
				    	<div class="col-6 col-sm-4 themed-grid-col">Expected Delivery</div>
				    </div>
				    <div class="row mb-3 text-center">
				    	<div class="col-6 col-sm-4 themed-grid-col"><%out.println(list.get(3));%></div>
				    	<div class="col-6 col-sm-4 themed-grid-col"><%out.println(list.get(4));%></div>
				    	<div class="col-6 col-sm-4 themed-grid-col"><%out.println(list.get(5));%></div>
				    </div>
				    <div class="row text-center">
				    	<div class="col-6 col-sm-4 themed-grid-col">Transaction Id</div>
				    	<div class="col-6 col-sm-4 themed-grid-col">City</div>
				    	<div class="col-6 col-sm-4 themed-grid-col">Full Address</div>
				    </div>
				    <div class="row mb-3 text-center">
				    	<div class="col-6 col-sm-4 themed-grid-col"><%out.println(list.get(6));%></div>
				    	<div class="col-6 col-sm-4 themed-grid-col"><%out.println(list.get(7));%></div>
				    	<div class="col-6 col-sm-4 themed-grid-col"><%out.println(list.get(8));%></div>
				    </div>
				    <div class="row text-center">
					    <div class="col-6 themed-grid-col">State</div>
					    <div class="col-6 themed-grid-col">Country</div>
				    </div>
				    <div class="row mb-3 text-center">
					    <div class="col-6 themed-grid-col"><%out.println(list.get(9));%></div>
					    <div class="col-6 themed-grid-col"><%out.println(list.get(10));%></div>
				    </div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="card paint-card mb-4">
					<div class="card-body">
						<%
							OrderDao dao1 = new OrderDao(DBConnection.getConn());
							int total = dao1.getOrderedCartTotalAmount(email);
							int serial = 0;
						%>
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
									OrderDao dao2 = new OrderDao(DBConnection.getConn());
									List<List<String>> list1 = dao2.getOrderListWithInvoiceStatus(email);
									for(List<String> cartList: list1) {
								%>
								<tr>
									<td class="text-center"><%out.println(serial = serial + 1);%></td>
									<td class="text-center"><%out.println(cartList.get(0));%></td>
									<td class="text-center"><%out.println(cartList.get(1));%></td>
									<td class="text-center"><%out.println(cartList.get(2));%></td>
									<td class="text-center"><%out.println(cartList.get(3));%></td>
									<td class="text-center"><%out.println(cartList.get(4));%></td>
								</tr>
								<% } %>
							</tbody>
						</table>
					</div>
					<h1 class="fw-light text-center mb-3">TOTAL $ <%=total%> </h1>
					<div class="btn-group btn-block">
						<a class="btn btn-dark btn-lg mb-4 mr-2 rounded" href="continueShoppingServlet">Continue Shopping</a>
						<a class="btn btn-dark btn-lg mb-4 ml-2 rounded" onclick="window.print();">Print</a>
					</div>	
				</div>
			</div>
		</div>
	</div>
</body>
</html>