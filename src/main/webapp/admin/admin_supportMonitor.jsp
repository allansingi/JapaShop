<%@ page import="dao.SupportDao"%>
<%@ page import="java.util.List"%>
<%@ page import="db.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../component/allcss.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Support Monitor - Japa Shop</title>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
	<%@ include file="admin_navbar.jsp"%>
	
	<c:if test="${empty adminObj}">
		<c:redirect url="../login_index.jsp"></c:redirect>
	</c:if>
	
	<div class="container-fluid p-3">
		<div class="row">
			<div class="col-md-12">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-3 text-center">Support Monitor</p>
						<table class="table table-striped table-hover">
							<thead class="table-dark">
								<tr>
									<th scope="col" class="text-center">Id</th>
									<th scope="col" class="text-center">Email</th>
									<th scope="col" class="text-center">Subject</th>
									<th scope="col" class="text-center">Body</th>
								</tr>
							</thead>
							<%
								SupportDao dao = new SupportDao(DBConnection.getConn());
								List<List<String>> list = dao.getAllMessages();
								for(List<String> message: list) {
							%>
							<tbody>
								<tr>
									<td class="text-center"><%=message.get(0)%></td>
									<td class="text-center"><%=message.get(1)%></td>
									<td class="text-center"><%=message.get(2)%></td>
									<td class="text-center"><%=message.get(3)%></td>
							</tbody>
							<% } %>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>