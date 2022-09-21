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
<title>View Products - Japa Shop</title>
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
				<div class="card paint-card">
					<div class="card-body">
						
						<c:if test="${not empty addedMsg}">
							<p class="fs-3 text-center text-success">${addedMsg}</p>
							<c:remove var="addedMsg" scope="session"/>
						</c:if>
						<c:if test="${not empty existMsg}">
							<div class="fs-3 text-center text-info" role="alert">${existMsg}</div>
							<c:remove var="existMsg" scope="session"/>
						</c:if>
						<c:if test="${not empty invalidMsg}">
							<div class="fs-3 text-center text-danger" role="alert">${invalidMsg}</div>
							<c:remove var="invalidMsg" scope="session"/>
						</c:if>
						
						<nav class="navbar navbar-expand-lg bg-light">
							<div class="mx-auto">
								<form class="d-flex" action="productSearchServlet" method="post" role="search">
									<a class="navbar-brand">Products</a>
							        <input class="form-control me-2 text-center" type="text" name="searchString" placeholder="Search for products" aria-label="Search">
							        <button class="btn btn-outline-dark" type="submit"> Search </button>
							    </form>
							</div>
						</nav>
							
						<table class="table table-striped table-hover">
							<thead class="table-dark">
								<tr>
									<th scope="col" class="text-center">Id</th>
									<th scope="col" class="text-center">Name</th>
									<th scope="col" class="text-center">Category</th>
									<th scope="col" class="text-center">Price</th>
									<th scope="col" class="text-center"></th>
								</tr>
							</thead>
							<tbody>
								<%
								ProductDao dao = new ProductDao(DBConnection.getConn());
								List<Product> list = dao.getActiveProducts();
								for(Product product: list) {
								%>
								<tr>
									<td class="text-center"><%=product.getId()%></td>
									<td class="text-center"><%=product.getName()%></td>
									<td class="text-center"><%=product.getCategory()%></td>
									<td class="text-center"><%=product.getPrice()%></td>
									<td class="text-center">
										<a class="btn btn-sm btn-dark" href="addProductToCartFromViewServlet?productId=<%=product.getId()%>">Add to Cart</a>
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