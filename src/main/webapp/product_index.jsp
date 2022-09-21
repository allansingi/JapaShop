<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../component/allcss.jsp" %>
<%@ page import="db.DBConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Japa Shop Home</title>
<style type="text/css">
	.paint-card{box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);}
</style>
</head>
<body>

	<c:if test="${empty userObj}">
		<%@ include  file="../component/navbar.jsp"%>
		<c:redirect url="../index.jsp"></c:redirect>
	</c:if>
	<c:if test="${not empty userObj}">
		<%@ include  file="../user_navbar.jsp"%>
	</c:if>
	
	<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="img/homepage/JapaShop-Carrossel-1.jpg" class="d-block w-100" alt="..." height="500px">
			</div>
			<div class="carousel-item">
				<img src="img/homepage/JapaShop-Carrossel-2.jpg" class="d-block w-100" alt="..." height="500px">
			</div>
			<div class="carousel-item">
				<img src="img/homepage/JapaShop-Carrossel-3.png" class="d-block w-100" alt="..." height="500px">
			</div>
		</div>
		<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span class="visually-hidden">Next</span>
		</button>
	</div>
	<div class="container p-3">
		<p class="text-center fs-2">The Best Online Shopping of The Moment!</p>
			<div class="row">
				<div class="col-md-8 p-5">
					<div class="row">
						<div class="col-md-6">
							<div class="card paint-card">
								<div class="card-body">
									<p class="text-center fs-5">More Control</p>
									<p>Many times, when we opt for conventional shopping, we tend to spend a lot more than planned and end up buying items that aren't exactly what we wanted (but we can't find anything better in the store). Online, you don't have to let the store's inventory dictate what you buy, and you can get exactly what you want and need.</p>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="card paint-card">
								<div class="card-body">
									<p class="text-center fs-5">No Crowds</p>
									<p>If you are like me, you hate crowds when you're shopping. Especially during holidays, festivals, or on weekends, they can be such a huge headache. Also, being crushed in the crowds of shoppers sometimes makes us feel rushed or hurried. You don't have to battle for a parking place. All of these problems can be avoided when you shop online.</p>
								</div>
							</div>
						</div>
						<div class="col-md-6 mt-2">
							<div class="card paint-card">
								<div class="card-body">
									<p class="text-center fs-5">More Variety</p>
									<p>The choices online are amazing. You can find almost any brand or item you're looking for. You can get in on the latest international trends without spending money on airfare. You can shop from retailers in other parts of the state, country, or even world instead of being limited to your own geography. A far greater selection of colors and sizes than you will find locally are at your disposal.</p>
								</div>
							</div>
						</div>
						<div class="col-md-6 mt-2">
							<div class="card paint-card">
								<div class="card-body">
									<p class="text-center fs-5">Easy Price Comparisons</p>
									<p>Comparing and researching products and their prices is so much easier online. If you're shopping for appliances, for example, you can find consumer reviews and product comparisons for all the options on the market, with links to the best prices. We can research firsthand experience, ratings, and reviews for most products and retailers from all over the world to have a reliable feedback.</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<img alt="" src="img/homepage/JapaShop-TopPic.jpg" height="700px" width="500px">
				</div>
			</div>
	</div>
	<hr>
	<div class="container p-2">
		<p class="text-center fs-2 ">Our Team</p>
		<div class="row">
			<div class="col-md-3">
				<div class="card paint-card">
					<div class="card-body text-center">
						<img src="img/team/CEO.jpg" width="250px" height="300px">
						<p class="fw-bold fs-5">Goku</p>
						<p class="fs-7">(CEO)</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card paint-card">
					<div class="card-body text-center">
						<img src="img/team/CFO.jpg" width="250px" height="300px">
						<p class="fw-bold fs-5">Vegeta</p>
						<p class="fs-7">(CFO)</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card paint-card">
					<div class="card-body text-center">
						<img src="img/team/CTO.jpg" width="250px" height="300px">
						<p class="fw-bold fs-5">Allan Borges</p>
						<p class="fs-7">(CTO)</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card paint-card">
					<div class="card-body text-center">
						<img src="img/team/CSO.png" width="250px" height="300px">
						<p class="fw-bold fs-5">Piccolo</p>
						<p class="fs-7">(CSO)</p>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="../component/footer.jsp" %>
</body>
</html>