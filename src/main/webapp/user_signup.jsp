<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../component/allcss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup Page</title>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@ include file="../component/navbar.jsp"%>

	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 text-center">User Register</p>

						<c:if test="${not empty userRegErrorMsg}">
							<p class="text-center text-danger fs-3">${userRegErrorMsg}</p>
							<c:remove var="userRegErrorMsg" scope="session"/>
						</c:if>
						<c:if test="${not empty emailNotValidMsg}">
							<p class="text-center text-danger fs-3">${emailNotValidMsg}</p>
							<c:remove var="emailNotValidMsg" scope="session"/>
						</c:if>
						<c:if test="${not empty passwordNotValidMsg}">
							<p class="text-center text-danger fs-3">${passwordNotValidMsg}</p>
							<c:remove var="passwordNotValidMsg" scope="session"/>
						</c:if>
						<c:if test="${not empty passwordsDontMatch}">
							<p class="text-center text-danger fs-3">${passwordsDontMatch}</p>
							<c:remove var="passwordsDontMatch" scope="session"/>
						</c:if>

						<form action="userSignupServlet" method="post">
							<div class="mb-3">
								<label class="form-label">Full Name</label>
								<input class="form-control" type="text" name="name" placeholder="Enter Name" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Email address</label>
								<input class="form-control" type="text" name="email" placeholder="Enter Email" required>
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
									<option value="What is your favorite color?">What is your favorite color?</option>
									<option value="What was your first nickname?">What was your first nickname?</option>
								</select>
							</div>
							<div class="mb-3">
								<label class="form-label">Answer</label>
								<input class="form-control" type="text" name="answer" placeholder="Enter Answer" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Password</label>
								<input class="form-control" type="password" name="password1" placeholder="Enter Password" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Confirm Password</label>
								<input class="form-control" type="password" name="password2" placeholder="Re-Enter Password" required>
							</div>
							<button type="submit" class="btn bg-dark text-white col-md-12">Register</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>