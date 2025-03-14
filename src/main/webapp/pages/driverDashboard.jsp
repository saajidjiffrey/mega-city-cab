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
			<div class="container mx-auto row mt-4">
				<!-- Statistics Cards -->
				<div class="col-md-4 mb-4">
					<div class="card shadow-sm rounded">
						<div class="card-body text-center">
							<h5 class="card-title text-uppercase font-weight-bold">Available
								Rides</h5>
							<p class="card-text display-4">${bookings.size()}</p>
						</div>
					</div>
				</div>
				<div class="col-md-4 mb-4">
					<div class="card shadow-sm rounded">
						<div class="card-body text-center">
							<h5 class="card-title text-uppercase font-weight-bold">Bookings
								Accepted</h5>
							<p class="card-text display-4">${acceptedBookingList.size()}</p>
						</div>
					</div>
				</div>
				<div class="col-md-4 mb-4">
					<div class="card shadow-sm rounded">
						<div class="card-body text-center">
							<h5 class="card-title text-uppercase font-weight-bold">Bookings
								Completed</h5>
							<p class="card-text display-4">${completedBookingList.size() }</p>
						</div>
					</div>
				</div>

				<div class="col-12 mb-4">
					<div class="card shadow-sm rounded">
						<div class="row g-0">
							<div class="col-md-4">
								<img src="common/images/slider1.jpg"
									class="img-fluid rounded-start" alt="Cab Image">
							</div>
							<div class="col-md-8">
								<div class="card-body">
									<h5 class="card-title text-uppercase">Ongoing Ride</h5>
									<p>
										<strong>Pickup Location:</strong> Location 1
									</p>
									<p>
										<strong>Destination:</strong> Location 2
									</p>
									<p>
										<strong>Booked Date and Time:</strong> 2025-03-14 10:00 AM
									</p>
									<p>
										<strong>Price:</strong> $15.00
									</p>
									<p>
										<strong>Estimated Time:</strong> 30 minutes
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>

<!--  -->
<%@ include file="/common/footer.jsp"%>
