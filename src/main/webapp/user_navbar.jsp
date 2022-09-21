<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-secondary">

	<div class="container-fluid">
	
	<c:if test="${not empty userObj}">
		<a class="navbar-brand fs-5" href="product_index.jsp"> <i class="fa-sharp fa-solid fa-store"></i>  JAPA Shop  <i class="fa-sharp fa-solid fa-store"></i></a>
	</c:if>
	<c:if test="${empty userObj}">
		<a class="navbar-brand" href="index.jsp"> <i class="fa-sharp fa-solid fa-store"></i>  JAPA Shop  <i class="fa-sharp fa-solid fa-store"></i></a>
	</c:if>
	
	<c:if test="${not empty userObj}">
		<a class="navbar-brand fs-5">Welcome ${userObj.name} !!</a>
	</c:if>
	
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">

				<c:if test="${empty userObj}">
					<li class="nav-item"><a class="nav-link" aria-current="page" href="login_index.jsp">Login page</a></li>
				</c:if>
				
				<li class="nav-item"><a class="nav-link fs-5" href="product_viewProduct.jsp"><i class="fa-solid fa-box"></i> Product List</a></li>
				<li class="nav-item"><a class="nav-link fs-5" href="product_cart.jsp"><i class="fa-solid fa-cart-shopping"></i> Cart</a></li>
				<li class="nav-item"><a class="nav-link fs-5" href="user_orders.jsp"><i class="fa-solid fa-boxes-stacked"></i> My Orders</a></li>
				<li class="nav-item"><a class="nav-link fs-5" href="support_index.jsp"><i class="fas fa-comment-dots"></i> Support</a></li>
				
				<c:if test="${not empty userObj}">
					<div class="dropdown">
						<button class="btn btn-dark dropdown-toggle fs-5" type="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="fa-solid fa-circle-user"></i> ${userObj.name}</button>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="user_userDetails.jsp">User Details</a></li>
							<li><a class="dropdown-item" href="user_changePassword.jsp">Change Password</a></li>
							<li><a class="dropdown-item" href="userLogoutServlet">Logout</a></li>
						</ul>
					</div>
				</c:if>
				
			</ul>
		</div>
	</div>
</nav>