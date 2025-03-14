<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/common/header.jsp"%>
<!--  -->

<div id="wrapper">
	<!-- 	sidemenu -->
	<%@ include file="/common/customerSideMenu.jsp"%>
	<%@ include file="/common/messageContainer.jsp"%>
	<%@ include file="/common/billModal.jsp"%>

	<!-- Page Content -->
	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div>
				<a href="#" class="btn" id="menu-toggle"><i class="bi bi-list"></i></a>
			</div>
			<div class="container mx-auto ">
				<h1 class="my-3">Completed Rides</h1>
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
									<th scope="col-4">Action</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="booking" items="${completedBookings}">
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
										<td>
											<button type="button"
												class="btn btn-outline-secondary btn-viewBill"
												data-bs-toggle="modal" data-bs-target="#billModal"
												data-booking-id="${booking.bookingId}"
												data-booking-datetime="${booking.bookingDatetime}"
												data-pickup-location="${booking.pickupLocation}"
												data-destination="${booking.destination}"
												data-estimated-time="${booking.estimatedTime}"
												data-distance="${booking.distance}"
												data-fare-amount="${booking.order.fareAmount}"
												data-bill-id="${booking.bill.billId}"
												data-total-amount="${booking.bill.totalAmount}"
												data-tax="${booking.bill.tax}"
												data-discount="${booking.bill.discount}"
												data-final-amount="${booking.bill.finalAmount}"
												data-payment-status="${booking.bill.paymentStatus}">
												<i class="bi bi-receipt"></i> View Bill
											</button>
										</td>
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

<script src="js/map.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function () {
    // Select all delete buttons
    document.querySelectorAll(".btn-delete").forEach(button => {
        button.addEventListener("click", function () {
            let bookingId = this.getAttribute("booking-id");
            
            if (!bookingId) {
                console.error("Booking ID not found!");
                return;
            }

            console.log("Deleting booking with ID:", bookingId);

            // Prepare data for request
            let urlEncodedData = new URLSearchParams();
            urlEncodedData.append("bookingId", bookingId);

            // Send DELETE request using Fetch API
            fetch("customer?action=deleteBooking", {
                method: "POST", 
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: urlEncodedData.toString()
            })
            .then(response => response.json())
            .then(data => {
                let messageDiv = document.getElementById("messageContainer");

                if (data.success) {
                    messageDiv.innerHTML =  '<div class="alert alert-success">' + data.message + '</div>';
                    
                    //  remove the deleted booking row from the table
                    this.closest("tr").remove();
                } else {
                    messageDiv.innerHTML =  '<div class="alert alert-danger">' + data.message + '</div>';
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("An error occurred. Please try again.");
            });
        });
    });
    
    
});

</script>


