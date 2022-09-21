<%@ page import="java.util.List"%>
<%@ page import="db.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../component/allcss.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product - Japa Shop</title>
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
			<div class="col-md-5 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Add Product</p>
						
							<c:if test="${not empty addProdSuccessMsg}">
								<div class="fs-3 text-center text-success" role="alert">${addProdSuccessMsg}</div>
								<c:remove var="addProdSuccessMsg" scope="session" />
							</c:if>
							<c:if test="${not empty addProdErrorMsg}">
								<p class="fs-3 text-center text-danger">${addProdErrorMsg}</p>
								<c:remove var="addProdErrorMsg" scope="session" />
							</c:if>
							
						<form action="../addProductServlet" method="post">
							<div class="mb-3">
								<label class="form-label">Product Name</label>
								<input class="form-control" type="text" name="name" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Category</label>
								<input class="form-control" type="text"  name="category" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Price</label>
								<input class="form-control" type="text" name="price" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Active</label>
								<select class="form-select" name="active" aria-label="Default select example">
									<option value="Yes" selected>Yes</option>
									<option value="No">No</option>
								</select>
							</div>
							<button type="submit" class="btn btn-dark col-md-12">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>