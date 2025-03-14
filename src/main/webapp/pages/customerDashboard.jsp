<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/common/header.jsp"%>
<!--  -->

<div id="wrapper">
	<!-- 	sidemenu -->
	<%@ include file="/common/customerSideMenu.jsp"%>
	<%@ include file="/common/messageContainer.jsp"%>

	<!-- Page Content -->
	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div>
				<a href="#" class="btn" id="menu-toggle"><i class="bi bi-list"></i></a>
			</div>
			<div class="container mx-auto row">


				<div class="col-lg-9">
					<div id="map" style="height: 600px;"></div>
					
				</div>


				<div class="col-lg-3">
					<div class="card">
						<div class="card-header">Book a Ride</div>

						<div class="card-body">
							<form id="bookRideForm" class="row g-3 requires-validation" action="customer?action=createBooking" method="post" novalidate>
								<div class="col-12">
									<h6>Pickup Location</h6>
									<h3 id="pickupLocationLabel">-</h3>
								</div>
								<div class="col-12">
									<h6>Drop Location</h6>
									<h3 id="destinationLabel">-</h3>
								</div>
								<div class="col-6">
									<h6>Distance</h6>
									<h3 id="distanceLabel">-</h3>
								</div>
								<div class="col-6">
									<h6>Estimated Time</h6>
									<h3 id="estimatedTimeLabel">-</h3>
								</div>
								<div class="col-12">
									<c:forEach var="vehicle" items="${vehicles}">
										<input type="radio" class="btn-check" name="vehicleType" value="${vehicle.vehicleId}" id="${vehicle.vehicleType}" autocomplete="off" >
										<label class="btn btn-secondary" for="${vehicle.vehicleType}">${vehicle.vehicleType}</label>
							        </c:forEach>
								</div>
								<div class="col-12">
									<label for="bookingDatetime" class="form-label">Booking Datetime</label> <input
										type="datetime-local" class="form-control" id="bookingDatetime"
										placeholder="Booking Datetime" name="bookingDatetime" required>
									<div class="invalid-feedback"></div>
								</div>
								<input type="hidden" class="form-control" id="pickupLocation" placeholder="pickupLocation" name="pickupLocation" >
								<input type="hidden" class="form-control" id="destination" placeholder="destniation" name="destination" >
								<input type="hidden" class="form-control" id="pickupLat" placeholder="pickupLat" name="pickupLat" >
								<input type="hidden" class="form-control" id="pickupLng" placeholder="pickupLng" name="pickupLng" >
								<input type="hidden" class="form-control" id="destinationLat" placeholder="destinationLat" name="destinationLat" >
								<input type="hidden" class="form-control" id="destinationLng" placeholder="destinationLng" name="destinationLng" >
								<input type="hidden" class="form-control" id="estimatedTime" placeholder="estimatedTime" name="estimatedTime" >
								<input type="hidden" class="form-control" id="distance" placeholder="distance" name="distance" >
							
								<div class="mb-3">
									<button type="button"  onclick="resetLocations()" class="btn btn-danger mb-3"><i class="bi bi-x"></i></button>
									<button type="button"  onclick="calculateDistance()" class="btn btn-secondary mb-3">Confirm Location</button>
									<button type="submit" class="btn btn-primary mb-3">Book Now</button>
									
									<button type="button" onclick="logFormValues()">Check Form Data</button>

									<script>
									function logFormValues() {
									    let formData = new FormData(document.querySelector("form"));
									    for (let [key, value] of formData.entries()) {
									        console.log(key + " = " + value);
									    }
									}
									</script>
								</div>
							</form>
						</div>
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
    // Function to handle form submission using Ajax
    document.getElementById("bookRideForm").addEventListener("submit", function(event) {
        event.preventDefault();  // Prevent the form from submitting normally

        // Collect form data
        let formData = new FormData(this);
        let urlEncodedData = new URLSearchParams(formData).toString();
     // Log form data to ensure values are being captured correctly
        for (let [key, value] of formData.entries()) {
            console.log(key + " = " + value);
        }
     
     	console.log(urlEncodedData );
        // Make an Ajax request to submit the form data
        fetch('customer?action=createBooking', {
            method: 'POST',
            body: urlEncodedData,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
        .then(response => response.json())  // Expecting a JSON response
        .then(data => {
        	let messageDiv = document.getElementById("messageContainer");
        	
        	if (data.success) {
        	    messageDiv.innerHTML = '<div class="alert alert-success">' + data.message + '</div>';
        	    // You can update the UI or show a success message
        	} else {
        	    messageDiv.innerHTML = '<div class="alert alert-danger">' + data.message + '</div>';
        	    // You can show an error message if needed
        	}
        })
        .catch(error => {
            console.error("Error:", error);
            alert("An error occurred. Please try again.");
        });
    });
</script>


