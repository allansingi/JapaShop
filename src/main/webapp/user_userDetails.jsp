<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../component/allcss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Details - JapaShop</title>

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
					<p class="text-center fs-3">User Details</p>
					
					<c:if test="${not empty updateMobNumSuccessMsg}">
						<p class="text-center text-success fs-3">${updateMobNumSuccessMsg}</p>
						<c:remove var="updateMobNumSuccessMsg" scope="session" />
					</c:if>
					<c:if test="${not empty secQuesSuccessMsg}">
						<p class="text-center text-success fs-3">${secQuesSuccessMsg}</p>
						<c:remove var="secQuesSuccessMsg" scope="session" />
					</c:if>
					<c:if test="${not empty addressUpdateSuccessMsg}">
						<p class="text-center text-success fs-3">${addressUpdateSuccessMsg}</p>
						<c:remove var="addressUpdateSuccessMsg" scope="session" />
					</c:if>
					
					<div class="card-body">
						<form action="#" method="post">
							<div class="mb-3">
								<label>Name</label>
								<input class="form-control" type="text" name="name" value="${userObj.name}" readonly>
							</div>
							<div class="mb-3">
								<label>Email</label>
								<input class="form-control" type="text" name="email" value="${userObj.email}" readonly>
							</div>
							<div class="mb-3">
								<label>Mobile Number</label>
								<input class="form-control mb-2" type="number" name="mobile_number" value="${userObj.mobileNumber}" readonly>
								<a class="btn btn-dark col-md-12" href="user_updateMobileNumber.jsp">Update</a>
							</div>
							<div class="mb-3">
								<label>Security Question</label>
								<input class="form-control mb-2" type="text" name="security_question" value="${userObj.securityQuestion}" readonly>
								<a class="btn btn-dark col-md-12" href="user_updateSecurityQuestion.jsp">Update</a>
							</div>
							<div class="mb-3">
								<label>Address</label>
								<input class="form-control mb-2" type="text" name="address" value="${userObj.address}" readonly>
								<a class="btn btn-dark col-md-12" href="user_updateAddAddress.jsp">Update</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>