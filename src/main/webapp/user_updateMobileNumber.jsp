<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../component/allcss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Mobile Number - JapaShop</title>

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
					<p class="text-center fs-3">Update Mobile Number</p>
					
					<c:if test="${not empty updateMobNumErrorMsg}">
						<p class="text-center text-danger fs-5">${updateMobNumErrorMsg}</p>
						<c:remove var="updateMobNumErrorMsg" scope="session" />
					</c:if>
					<c:if test="${not empty incorrectOldMobNumMsg}">
						<p class="text-center text-danger fs-5">${incorrectOldMobNumMsg}</p>
						<c:remove var="incorrectOldMobNumMsg" scope="session" />
					</c:if>
					
					<div class="card-body">
						<form action="./updateMobileNumberServlet" method="post">
							<div class="mb-3">
								<label>Enter Old Mobile Number</label>
								<input class="form-control" type="number" name="oldMobileNumber" required>
							</div>
							<div class="mb-3">
								<label>Enter New Mobile Number</label>
								<input class="form-control" type="number" name="newMobileNumber" required>
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