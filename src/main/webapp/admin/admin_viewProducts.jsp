<%@ page import="entity.Product"%>
<%@ page import="dao.ProductDao"%>
<%@ page import="java.util.List"%>
<%@ page import="db.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../component/allcss.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View / Edit Products - Japa Shop</title>
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
						<p class="fs-3 text-center">Products Details</p>
							
							<c:if test="${not empty editProdSuccessMsg}">
								<div class="fs-3 text-center text-success" role="alert">${editProdSuccessMsg}</div>
								<c:remove var="editProdSuccessMsg" scope="session"/>
							</c:if>
							<c:if test="${not empty editProdErrorMsg}">
								<p class="fs-3 text-center text-danger">${editProdErrorMsg}</p>
								<c:remove var="editProdErrorMsg" scope="session"/>
							</c:if>
							
						<table class="table table-striped table-hover">
							<thead class="table-dark">
								<tr>
									<th scope="col" class="text-center">Id</th>
									<th scope="col" class="text-center">Name</th>
									<th scope="col" class="text-center">Category</th>
									<th scope="col" class="text-center">Price</th>
									<th scope="col" class="text-center">Active</th>
									<th scope="col" class="text-center"></th>
								</tr>
							</thead>
							<tbody>
								<%
									ProductDao dao = new ProductDao(DBConnection.getConn());
									List<Product> list = dao.getAllProducts();
									for(Product product: list) {
								%>
								<tr>
									<td class="text-center"><%=product.getId() %></td>
									<td class="text-center"><%=product.getName() %></td>
									<td class="text-center"><%=product.getCategory() %></td>
									<td class="text-center"><%=product.getPrice() %></td>
									<td class="text-center"><%=product.getActive() %></td>
									<td class="text-center">
										<a class="btn btn-dark btn-sm" href="admin_editProduct.jsp?id=<%=product.getId()%>">Edit</a>
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