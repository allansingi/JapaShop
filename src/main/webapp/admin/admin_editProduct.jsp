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
<title>Edit Products - Japa Shop</title>
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
			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Edit Products</p>
						
						<c:if test="${not empty adminObj}">
							<p class="fs-3 text-center text-success">${editProdSuccessMsg}</p>
							<c:remove var="editProdSuccessMsg" scope="session" />
						</c:if>
						<c:if test="${not empty adminObj}">
							<div class="fs-3 text-center text-danger" role="alert">${editProdErrorMsg}</div>
							<c:remove var="editProdErrorMsg" scope="session" />
						</c:if>

						<%
							ProductDao dao = new ProductDao(DBConnection.getConn());
							Product product = dao.getProductById(Integer.parseInt(request.getParameter("id")));
						%>
						
						<form action="../editProductServlet" method="post">
							<div class="mb-3">
								<label class="form-label">Id</label>
								<input class="form-control" name="id" type="number" value="<%=product.getId()%>" readonly>
							</div>
							<div class="mb-3">
								<label class="form-label">Name</label>
								<input class="form-control" name="name" type="text" value="<%=product.getName()%>" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Category</label>
								<input class="form-control" name="category" type="text" value="<%=product.getCategory()%>" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Price</label>
								<input class="form-control" name="price" type="number" value="<%=product.getPrice()%>" required>
							</div>
							<div class="mb-3">
								<label class="form-label" >Active</label>
								<select class="form-control" name="active" required>
									<option><%=product.getActive()%></option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</select>
							</div>
							<button type="submit" class="btn btn-dark col-md-12">Update</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>