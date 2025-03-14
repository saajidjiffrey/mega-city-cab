<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/common/header.jsp"%>
<%@ include file="/common/messageContainer.jsp"%>
<!--  -->

<div id="wrapper">
	<!--  sidemenu -->
	<%@ include file="/common/adminSideMenu.jsp"%>

	<!-- Page Content -->
	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div>
				<a href="#" class="btn" id="menu-toggle"><i class="bi bi-list"></i></a>
			</div>
			<div class="container mx-auto ">
				<h1 class="my-3">All Bookings</h1>

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
												<td><span class="badge bg-primary status">Booked</span></td>
											</c:when>
											<c:when test="${booking.status eq 'accepted'}">
												<td><span class="badge bg-info status">Accepted</span></td>
											</c:when>
											<c:when test="${booking.status eq 'onride'}">
												<td><span class="badge bg-warning status">On
														Ride</span></td>
											</c:when>
											<c:when test="${booking.status eq 'cancelled'}">
												<td><span class="badge bg-danger status">Cancelled</span></td>
											</c:when>
											<c:when test="${booking.status eq 'completed'}">
												<td><span class="badge bg-success status">Completed</span></td>
											</c:when>
											<c:otherwise>
												<td><span class="badge bg-secondary status">Unknown</span></td>
											</c:otherwise>
										</c:choose>
										<td><c:if test="${booking.status eq 'booked'}">
												<!-- Dropdown for driver selection -->
												<select class="driver-select">
													<c:forEach var="driver" items="${drivers}">
														<option value="${driver.driverId}">${driver.name}</option>
													</c:forEach>
												</select>
												<button type="button"
													class="btn btn-primary btn-assignBooking"
													booking-id="${booking.bookingId}">Assign to Driver
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

<!-- Modal for confirmation -->
<div class="modal fade" id="assignBookingModal" tabindex="-1"
	aria-labelledby="assignBookingModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="assignBookingModalLabel">Confirm
					Driver Assignment</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">Are you sure you want to assign this
				booking to the selected driver?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary"
					id="confirmAssignBooking">Yes, Assign</button>
			</div>
		</div>
	</div>
</div>
<!--  -->
<%@ include file="/common/footer.jsp"%>

<script>
document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".btn-assignBooking").forEach(button => {
        button.addEventListener("click", function () {
        	console.log('hi');
            let bookingId = this.getAttribute("booking-id");

            if (!bookingId) {
                console.error("Booking ID not found!");
                return;
            }

            console.log("Accepting booking with ID:", bookingId);

            // Collect the driverId from the dropdown (you should have a dropdown with driver options)
            let driverId = this.closest("tr").querySelector("select.driver-select").value;
            console.log("Accepting booking with driverID:", driverId);
            if (!driverId) {
                showToast("Please select a driver!", "bg-danger");
                return;
            }

            let urlEncodedData = new URLSearchParams();
            urlEncodedData.append("bookingId", bookingId);
            urlEncodedData.append("driverId", driverId);

            fetch("admin?action=assignBookingToDriver", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: urlEncodedData.toString()
            })
            .then(response => response.json())  // Make sure to parse the response as JSON
            .then(data => {
                console.log("Raw Response:", data);

                if (data.success) {
                    this.closest("tr").querySelector(".status").innerText = "Assigned to Driver";
                    showToast("Booking Assigned Successfully!", "bg-success");
                } else {
                    showToast("Failed to Assign Booking", "bg-danger");
                }
            })
            .catch(error => {
                console.error("Fetch Error:", error);
                showToast("An error occurred. Please try again.", "bg-danger");
            });
        });
    });
});

</script>

