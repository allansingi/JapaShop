<%@ page import="entity.Product"%>
<%@ page import="dao.ProductDao"%>
<%@ page import="java.util.List"%>
<%@ page import="db.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Cart - Japa Shop</title>
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
					<span class="text-center fs-3">Your Shopping Cart</span>
					<div class="card-body">
						
						<c:if test="${not empty incMsg}">
							<p class="fs-3 text-center text-success" role="alert">${incMsg}</p>
							<c:remove var="incMsg" scope="session"/>
						</c:if>
						<c:if test="${not empty decMsg}">
							<div class="fs-3 text-center text-success" role="alert">${decMsg}</div>
							<c:remove var="decMsg" scope="session"/>
						</c:if>
						<c:if test="${not empty cantMsg}">
							<div class="fs-3 text-center text-info" role="alert">${cantMsg}</div>
							<c:remove var="cantMsg" scope="session"/>
						</c:if>
						<c:if test="${not empty errorIncDecMsg}">
							<div class="fs-3 text-center text-danger" role="alert">${errorIncDecMsg}</div>
							<c:remove var="errorIncDecMsg" scope="session"/>
						</c:if>
						<c:if test="${not empty removeSuccessMsg}">
							<div class="fs-3 text-center text-success" role="alert">${removeSuccessMsg}</div>
							<c:remove var="removeSuccessMsg" scope="session"/>
						</c:if>
						<c:if test="${not empty removeErrorMsg}">
							<div class="fs-3 text-center text-danger" role="alert">${removeErrorMsg}</div>
							<c:remove var="removeErrorMsg" scope="session"/>
						</c:if>
						
						<%
							String email = request.getSession().getAttribute("email").toString();
							ProductDao dao = new ProductDao(DBConnection.getConn());
							int total = dao.getCartTotalAmount(email);
							int serial = 0;
						%>
						<div class="container">
							<span class="text-dark fs-4">TOTAL $ <%=total%> </span>
							<c:if test="<%=total>0 %>">
								<a class="btn btn-sm btn-dark mb-2 fs-5" href="order_index.jsp">Proceed to Checkout</a>
							</c:if>
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
									<th scope="col" class="text-center"></th>
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
									<td class="text-center">
										<a href="incDecQuantityServlet?id=<%=cartList.get(3)%>&qty=dec"><i class="fa-solid fa-circle-minus text-dark fs-5"></i></a>
										<%out.println(cartList.get(4));%>
										<a href="incDecQuantityServlet?id=<%=cartList.get(3)%>&qty=inc"><i class="fa-solid fa-circle-plus text-dark fs-5"></i></a>
									</td>
									<td class="text-center"><%out.println(cartList.get(5));%></td>
									<td class="text-center">
										<a class="btn btn-sm btn-dark" href="removeItemFromCartServlet?id=<%=cartList.get(3)%>">Remove</a>
									</td>
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