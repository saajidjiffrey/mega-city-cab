<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/common/header.jsp"%>
<style>
.card {
	border-radius: 0.5rem;
}

.table-hover tbody tr:hover {
	background-color: #f1f1f1;
}

.badge {
	font-size: 0.9rem;
	padding: 0.5rem;
}

.btn-delete {
	font-size: 1rem;
	padding: 0.5rem 1rem;
}
</style>
<!--  -->

<div id="wrapper">
	<!-- Side menu -->
	<%@ include file="/common/customerSideMenu.jsp"%>
	<%@ include file="/common/messageContainer.jsp"%>

	<!-- Page Content -->
	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div>
				<a href="#" class="btn" id="menu-toggle"><i class="bi bi-list"></i></a>
			</div>

			<div class="container mx-auto mt-4">
				<h1 class="my-4 text-center text-primary">My Bookings</h1>

				<div class="card shadow-sm border-light">
					<div class="card-header bg-primary text-white">
						<h5 class="mb-0">Booking Details</h5>
					</div>

					<div class="card-body">
						<table class="table table-striped table-hover">
							<thead class="thead-dark">
								<tr>
									<th scope="col">Booking Date Time</th>
									<th scope="col">Pickup Location</th>
									<th scope="col">Drop Location</th>
									<th scope="col">Estimated Travel Time</th>
									<th scope="col">Distance</th>
									<th scope="col">Booking Status</th>
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="booking" items="${bookings}">
									<tr>
										<th scope="row">${booking.bookingDatetime}</th>
										<td>${booking.pickupLocation}</td>
										<td>${booking.destination}</td>
										<td>${booking.estimatedTime}mins</td>
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
													<i class="bi bi-trash3"></i> Delete
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

<script src="js/map.js"></script>
<script>
document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".btn-delete").forEach(button => {
        button.addEventListener("click", function () {
            let bookingId = this.getAttribute("booking-id");
            
            if (!bookingId) {
                console.error("Booking ID not found!");
                return;
            }

            console.log("Deleting booking with ID:", bookingId);

            let urlEncodedData = new URLSearchParams();
            urlEncodedData.append("bookingId", bookingId);

            fetch("booking?action=deleteBooking", {
                method: "POST", 
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: urlEncodedData.toString()
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {                    
                    this.closest("tr").remove();
                    
                    showToast("Booking Deleted Successfully!", "bg-success");
                } else {
                	showToast("Failed to Delete Booking", "bg-danger");
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


