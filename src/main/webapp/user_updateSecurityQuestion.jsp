<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../component/allcss.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Security Question - JapaShop</title>

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
					<p class="text-center fs-3">Update Security Question</p>
					
					<c:if test="${not empty secQuesErrorMsg}">
						<p class="text-center text-danger fs-5">${secQuesErrorMsg}</p>
						<c:remove var="secQuesErrorMsg" scope="session" />
					</c:if>
					<c:if test="${not empty incorrectOldSecQuesMsg}">
						<p class="text-center text-danger fs-5">${incorrectOldSecQuesMsg}</p>
						<c:remove var="incorrectOldSecQuesMsg" scope="session" />
					</c:if>
					
					<div class="card-body">
						<form action="./updateSecurityQuestionAndAnswerServlet" method="post">
							<div class="mb-3">
								<label>Select Old Security Question</label>
								<select class="form-select" name="oldSecurityQuestion" aria-label="Default select example" required>
									<option selected>Select one of the questions</option>
									<option value="What is the name of your first pet?">What is the name of your first pet?</option>
									<option value="What was your first car?">What was your first car?</option>
									<option value="What is your favorite color?">What is your favorite color?</option>
									<option value="What was your first nickname?">What was your first nickname?</option>
								</select>
							</div>
							<div class="mb-3">
								<label>Enter Old Answer</label>
								<input class="form-control" type="text" name="oldAnswer" required>
							</div>
							<div class="mb-3">
								<label>Select New Security Question</label>
								<select class="form-select" name="newSecurityQuestion" aria-label="Default select example" required>
									<option selected>Select one of the questions</option>
									<option value="What is the name of your first pet?">What is the name of your first pet?</option>
									<option value="What was your first car?">What was your first car?</option>
									<option value="What is your favorite color?">What is your favorite color?</option>
									<option value="What was your first nickname?">What was your first nickname?</option>
								</select>
							</div>
							<div class="mb-3">
								<label>Enter New Answer</label>
								<input class="form-control" type="text" name="newAnswer" required>
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