<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../component/allcss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot Password - JapaShop</title>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@ include  file="../component/navbar.jsp"%>
	
	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 text-center">Forgot Password</p>

						<c:if test="${not empty incorrectInfDataMsg}">
							<p class="text-center text-danger fs-3">${incorrectInfDataMsg}</p>
							<c:remove var="incorrectInfDataMsg" scope="session" />
						</c:if>

						<form action="userForgotPasswordServlet" method="post">
							<div class="mb-3">
								<label class="form-label">Email address</label>
								<input class="form-control" type="email" name="email" placeholder="Enter Email" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Mobile Number</label>
								<input class="form-control" type="number" name="mobileNumber" placeholder="Enter Mobile Number" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Security Question</label>
								<select class="form-select" name="securityQuestion" aria-label="Default select example">
									<option selected>Select one of the questions</option>
									<option value="What is the name of your first pet?">What is the name of your first pet?</option>
									<option value="What was your first car?">What was your first car?</option>
									<option value="What is your favorite colour?">What is your favorite color?</option>
									<option value="What was your first nickname?">What was your first nickname?</option>
								</select>
							</div>
							<div class="mb-3">
								<label class="form-label">Answer</label>
								<input class="form-control" type="text" name="answer" placeholder="Enter Answer" required>
							</div>
							<div class="mb-3">
								<label class="form-label">New Password</label>
								<input class="form-control" type="password" name="newPassword" placeholder="Enter New Password" required>
							</div>
							<button type="submit" class="btn bg-dark text-white col-md-12">Recall</button>
						</form>
						To go back to login page, <a href="login_index.jsp">click here</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>