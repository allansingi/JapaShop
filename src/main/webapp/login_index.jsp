<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="component/allcss.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login Page - Japa Shop</title>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@ include  file="component/navbar.jsp"%>
	
	<div class="container p-5">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 text-center">User Login</p>
						
						<c:if test="${not empty userRegSuccessMsg}">
							<p class="text-center text-success fs-3">${userRegSuccessMsg}</p>
							<c:remove var="userRegSuccessMsg" scope="session"/>
						</c:if>
						<c:if test="${not empty updatePassSuccessMsg}">
							<p class="text-center text-success fs-3">${updatePassSuccessMsg}</p>
							<c:remove var="updatePassSuccessMsg" scope="session"/>
						</c:if>
						<c:if test="${not empty loginErrorMsg}">
							<p class="fs-3 text-center text-danger">${loginErrorMsg}</p>
							<c:remove var="loginErrorMsg" scope="session" />
						</c:if>
						<c:if test="${not empty admLogoutSuccessMsg}">
							<p class="fs-3 text-center text-success">${admLogoutSuccessMsg}</p>
							<c:remove var="admLogoutSuccessMsg" scope="session" />
						</c:if>
						<c:if test="${not empty userLogoutSuccessMsg}">
							<p class="fs-3 text-center text-success">${userLogoutSuccessMsg}</p>
							<c:remove var="userLogoutSuccessMsg" scope="session" />
						</c:if>

						<form action="userLoginServlet" method="post">
							<div class="mb-3">
								<label class="form-label">Email address</label>
								<input class="form-control" name="email" type="email" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Password</label>
								<input class="form-control" name="password" type="password" required>
							</div>
							<button class="btn bg-dark text-white col-md-12" type="submit">Login</button>
						</form>
						Don't have an account? <a class="text-decoration-none" href="user_signup.jsp">click here</a> and create one<br>
						Forgot Password? <a class="text-decoration-none" href="user_forgotPassword.jsp">click here</a> to retrieve it
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>