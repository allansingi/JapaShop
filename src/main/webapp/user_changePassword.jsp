<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../component/allcss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password - JapaShop</title>

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
					<p class="text-center fs-3">Change Password</p>
					
					<c:if test="${not empty changePassSuccessMsg}">
						<p class="text-center text-success fs-3">${changePassSuccessMsg}</p>
						<c:remove var="changePassSuccessMsg" scope="session" />
					</c:if>
					<c:if test="${not empty changePassErrorMsg}">
						<p class="text-center text-danger fs-5">${changePassErrorMsg}</p>
						<c:remove var="changePassErrorMsg" scope="session" />
					</c:if>
					<c:if test="${not empty incorrectOldPassMsg}">
						<p class="text-center text-danger fs-5">${incorrectOldPassMsg}</p>
						<c:remove var="incorrectOldPassMsg" scope="session" />
					</c:if>
					
					<div class="card-body">
						<form action="./userChangePasswordServlet" method="post">
							<div class="mb-3">
								<label>Enter Old Password</label> <input type="password" name="oldPassword" class="form-control" required>
							</div>
							<div class="mb-3">
								<label>Enter New Password</label> <input type="password" name="newPassword" class="form-control" required>
							</div>
							<input type="hidden" value="${userObj.email}" name="email">
							<button class="btn btn-dark col-md-12">Change Password</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>