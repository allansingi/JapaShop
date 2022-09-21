<%@ page import="db.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../component/allcss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update/Add Address - JapaShop</title>

<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@ include  file="../user_navbar.jsp"%>

	<c:if test="${empty userObj}">
		<c:redirect url="../login_index.jsp"></c:redirect>
	</c:if>

	<div class="container p-4">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<p class="text-center fs-3">Update/Add Address</p>
					
					<c:if test="${not empty addressUpdateErrorMsg}">
						<p class="text-center text-danger fs-5">${addressUpdateErrorMsg}</p>
						<c:remove var="addressUpdateErrorMsg" scope="session" />
					</c:if>
					
					<div class="card-body">
						<form action="./updateAddAddressServlet" method="post">
							<div class="mb-3">
								<label>Enter Address</label>
								<input class="form-control" type="text" name="address" value="${userObj.address}" placeholder="Enter Address" required>
							</div>
							<div class="mb-3">
								<label>Enter City</label>
								<input class="form-control" type="text" name="city" value="${userObj.city}" placeholder="Enter City" required>
							</div>
							<div class="mb-3">
								<label>Enter State</label>
								<input class="form-control" type="text" name="state" value="${userObj.state}" placeholder="Enter State" required>
							</div>
							<div class="mb-3">
								<label>Enter Country</label>
								<input class="form-control" type="text" name="country" value="${userObj.country}" placeholder="Enter Country" required>
							</div>
							<input type="hidden" value="${userObj.email}" name="email">
							<button class="btn btn-dark col-md-12">Update</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>