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
			<div class="container mx-auto row">
				<!-- Statistics Cards -->
				<div class="col-md-4 mb-4">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">Available Rides</h5>
							<p class="card-text">10</p>
							<!-- Replace with dynamic value -->
						</div>
					</div>
				</div>
				<div class="col-md-4 mb-4">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">Bookings Accepted</h5>
							<p class="card-text">5</p>
							<!-- Replace with dynamic value -->
						</div>
					</div>
				</div>
				<div class="col-md-4 mb-4">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">Bookings Completed</h5>
							<p class="card-text">3</p>
							<!-- Replace with dynamic value -->
						</div>
					</div>
				</div>
				<div class="col-12 mb-4">
					<div class="card">
						<div class="row g-0">
							<div class="col-md-4">
								<img src="path/to/cab-image.jpg" class="img-fluid rounded-start"
									alt="Cab Image">
								<!-- Replace with actual image path -->
							</div>
							<div class="col-md-8">
								<div class="card-body">
									<h5 class="card-title">Ongoing Ride</h5>
									<p>
										<strong>Pickup Location:</strong> Location 1
									</p>
									<!-- Replace with dynamic value -->
									<p>
										<strong>Destination:</strong> Location 2
									</p>
									<!-- Replace with dynamic value -->
									<p>
										<strong>Booked Date and Time:</strong> 2025-03-14 10:00 AM
									</p>
									<!-- Replace with dynamic value -->
									<p>
										<strong>Price:</strong> $15.00
									</p>
									<!-- Replace with dynamic value -->
									<p>
										<strong>Estimated Time:</strong> 30 minutes
									</p>
									<!-- Replace with dynamic value -->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Ongoing Ride -->
			<div class="row">
				
			</div>

		</div>
	</div>
</div>
<!--  -->
<%@ include file="/common/footer.jsp"%>
