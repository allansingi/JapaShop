<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-dark">

	<div class="container-fluid">
	
		<a class="navbar-brand fs-5" href="admin_index.jsp"><i class="fa-sharp fa-solid fa-store"></i>  JAPA Shop  <i class="fa-sharp fa-solid fa-store"></i></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button> 
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link fs-5" href="admin_index.jsp">ADM HOME</a></li>
				<li class="nav-item"><a class="nav-link fs-5" href="admin_addProduct.jsp"><i class="fa-regular fa-square-plus"></i> Add Product</a></li>
				<li class="nav-item"><a class="nav-link fs-5" href="admin_viewProducts.jsp"><i class="fa-regular fa-rectangle-list"></i> View / Edit Products</a></li>
				<li class="nav-item"><a class="nav-link fs-5" href="admin_supportMonitor.jsp"><i class="fa-regular fa-envelope"></i> Support Monitor</a></li>
				<li class="nav-item"><a class="nav-link fs-5" href="admin_orders.jsp"><i class="fa-brands fa-jedi-order"></i> Orders</a></li>
				<li class="nav-item"><a class="nav-link fs-5" href="admin_canceledOrders.jsp"><i class="fa-regular fa-circle-xmark"></i> Canceled Orders</a></li>
				<li class="nav-item"><a class="nav-link fs-5" href="admin_deliveredOrders.jsp"><i class="fa-solid fa-truck-ramp-box"></i> Delivered Orders</a></li>
			</ul>
			<form class="d-flex">
				<div class="dropdown">
					<button class="btn btn-light dropdown-toggle fs-5" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">Admin</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
						<li><a class="dropdown-item" href="../adminLogoutServlet">Logout</a></li>
					</ul>
				</div>
			</form>
		</div>
	</div>
</nav>