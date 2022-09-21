<%@ page import="db.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../component/allcss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Support - JapaShop</title>

<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@ include  file="./user_navbar.jsp"%>

	<c:if test="${empty userObj}">
		<c:redirect url="./login_index.jsp"></c:redirect>
	</c:if>

	<div class="container p-4">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<p class="text-center fs-3">Support Page</p>
					
					<c:if test="${not empty supportSuccessMsg}">
						<p class="text-center text-success fs-3">${supportSuccessMsg}</p>
						<c:remove var="supportSuccessMsg" scope="session" />
					</c:if>
					<c:if test="${not empty supportErrorMsg}">
						<p class="text-center text-danger fs-5">${supportErrorMsg}</p>
						<c:remove var="supportErrorMsg" scope="session" />
					</c:if>
					
					<div class="card-body">
						<form action="./supportServlet" method="post">
							<div class="mb-3">
								<label>Subject</label>
								<select class="form-select" name="subject" aria-label="Default select example" required>
									<option selected>Select the subject</option>
									<option value="Delivery Issues">Delivery Issues</option>
									<option value="Product Return">Product Return</option>
									<option value="Suggestions">Suggestions</option>
									<option value="Complains">Complains</option>
									<option value="Other">Other</option>
								</select>
							</div>
							<div class="form-floating mb-4">
								<textarea class="form-control" name="body" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 200px"></textarea>
								<label for="floatingTextarea2">Leave your comments here</label>
							</div>
							<input type="hidden" value="${userObj.email}" name="email">
							<button class="btn btn-dark col-md-12" type="submit">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>