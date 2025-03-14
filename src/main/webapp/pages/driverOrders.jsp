<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/common/header.jsp"%>
<%@ include file="/common/messageContainer.jsp"%>
<!--  -->

<div id="wrapper">
	<!--  sidemenu -->
	<%@ include file="/common/driverSideMenu.jsp"%>

	<!-- Page Content -->
	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div>
				<a href="#" class="btn" id="menu-toggle"><i class="bi bi-list"></i></a>
			</div>
			<div class="container mx-auto ">
				<h1 class="my-4 text-center text-primary">Completed Orders</h1>
				<div class="card">
					<div class="card-body">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th scope="col">Booking Date Time</th>
									<th scope="col">Pickup Location</th>
									<th scope="col">Drop Location</th>
									<th scope="col">Estimated Travel Time</th>
									<th scope="col">Distance</th>
									<th scope="col">Booking Status</th>
									<th scope="col-2">Action
									<th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="booking" items="${bookings}">
									<tr>
										<th scope="row">${booking.bookingDatetime}</th>
										<td>${booking.pickupLocation}</td>
										<td>${booking.destination}</td>
										<td>${booking.estimatedTime}mins.</td>
										<td>${booking.distance}km</td>
										<c:choose>
											<c:when test="${booking.status eq 'booked'}">
												<td><span class="badge bg-primary">Booked</span></td>
											</c:when>
											<c:when test="${booking.status eq 'accepted'}">
												<td><span class="badge bg-info">Accepted</span></td>
											</c:when>
											<c:when test="${booking.status eq 'onride'}">
												<td><span class="badge bg-warning">On Ride</span></td>
											</c:when>
											<c:when test="${booking.status eq 'cancelled'}">
												<td><span class="badge bg-danger">Cancelled</span></td>
											</c:when>
											<c:when test="${booking.status eq 'completed'}">
												<td><span class="badge bg-success">Completed</span></td>
											</c:when>
											<c:otherwise>
												<td><span class="badge bg-secondary">Unknown</span></td>
											</c:otherwise>
										</c:choose>
										<td><c:if test="${booking.status eq 'booked'}">
												<button type="button"
													class="btn btn-outline-danger btn-delete"
													booking-id="${booking.bookingId}">
													<i class="bi bi-trash3"></i>
												</button>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
<!--  -->
<%@ include file="/common/footer.jsp"%>
