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
				<h1 class="my-4 text-center text-primary">Available Rides</h1>
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
												<td><span class="badge bg-warning status">On Ride</span></td>
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
												<button type="button"
													class="btn btn-primary btn-acceptBooking"
													booking-id="${booking.bookingId}">
													Accept Booking
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

<script>
document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".btn-acceptBooking").forEach(button => {
        button.addEventListener("click", function () {
            let bookingId = this.getAttribute("booking-id");

            if (!bookingId) {
                console.error("Booking ID not found!");
                return;
            }

            console.log("Accepting booking with ID:", bookingId);

            let urlEncodedData = new URLSearchParams();
            urlEncodedData.append("bookingId", bookingId);

            fetch("driver?action=acceptBooking", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: urlEncodedData.toString()
            })
            .then(response => response.text()) 
            .then(data => {
                console.log("Raw Response:", data); 

                try {
                    let jsonData = JSON.parse(data);
                    if (jsonData.success) {
                        this.closest("tr").querySelector(".status").innerText = "Accepted";
                        showToast("Booking Accepted Successfully!", "bg-success");
                    } else {
                        showToast("Failed to Accept Booking", "bg-danger");
                    }
                } catch (error) {
                    console.error("JSON Parsing Error:", error);
                    showToast("Invalid response from server", "bg-danger");
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

