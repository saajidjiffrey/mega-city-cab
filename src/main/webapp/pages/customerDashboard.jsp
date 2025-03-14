<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/common/header.jsp"%>
<style>
.btn-outline-primary {
    border-radius: 0.5rem;
    font-weight: 600;
}

.card-header {
    font-size: 1.25rem;
    font-weight: 500;
}

.card-body {
    padding: 2rem;
}

#map {
    border-radius: 0.75rem;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}
	
</style>
<div id="wrapper">
    <%@ include file="/common/customerSideMenu.jsp"%>
    <%@ include file="/common/messageContainer.jsp"%>

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div>
                <a href="#" class="btn" id="menu-toggle"><i class="bi bi-list"></i></a>
            </div>

            <div class="container mx-auto row">
                <h1 class="mt-3 mb-4 text-center text-primary">Book Your Ride</h1>

                <div class="col-lg-6">
                    <div id="map" class="rounded shadow-sm" style="height: 600px;"></div>
                </div>

                <div class="col-lg-6">
                    <div class="card shadow-lg border-light">
                        <div class="card-header text-white bg-primary">
                            <h5>Book a Ride</h5>
                        </div>

                        <div class="card-body">
                            <form id="bookRideForm" class="row g-4" action="customer?action=createBooking" method="post" novalidate>
                                <div class="col-12">
                                    <h6 class="mb-2">Pickup Location</h6>
                                    <h3 id="pickupLocationLabel" class="text-muted">-</h3>
                                </div>
                                <div class="col-12">
                                    <h6 class="mb-2">Drop Location</h6>
                                    <h3 id="destinationLabel" class="text-muted">-</h3>
                                </div>
                                <div class="col-6">
                                    <h6 class="mb-2">Distance</h6>
                                    <h3 id="distanceLabel" class="text-muted">-</h3>
                                </div>
                                <div class="col-6">
                                    <h6 class="mb-2">Estimated Time</h6>
                                    <h3 id="estimatedTimeLabel" class="text-muted">-</h3>
                                </div>

                                <!-- Vehicle Type Selection -->
                                <div class="col-12">
                                    <h6 class="mb-2">Select Vehicle Type</h6>
                                    <div class="btn-group d-flex flex-wrap" role="group">
                                        <c:forEach var="vehicle" items="${vehicles}">
                                            <input type="radio" class="btn-check" name="vehicleType" value="${vehicle.vehicleId}" id="${vehicle.vehicleType}" required>
                                            <label class="btn btn-lg btn-outline-primary m-1" style="padding: 15px; font-size: 18px;" for="${vehicle.vehicleType}">
                                                ${vehicle.vehicleType}
                                            </label>
                                        </c:forEach>
                                    </div>
                                    <div class="invalid-feedback">Please select a vehicle type.</div>
                                </div>

                                <div class="col-12">
                                    <label for="bookingDatetime" class="form-label">Booking Datetime</label> 
                                    <input type="datetime-local" class="form-control" id="bookingDatetime" name="bookingDatetime" required>
                                    <div class="invalid-feedback">Please select a booking date and time.</div>
                                </div>

                                <!-- Hidden Fields -->
                                <input type="hidden" id="pickupLocation" name="pickupLocation">
                                <input type="hidden" id="destination" name="destination">
                                <input type="hidden" id="pickupLat" name="pickupLat">
                                <input type="hidden" id="pickupLng" name="pickupLng">
                                <input type="hidden" id="destinationLat" name="destinationLat">
                                <input type="hidden" id="destinationLng" name="destinationLng">
                                <input type="hidden" id="estimatedTime" name="estimatedTime">
                                <input type="hidden" id="distance" name="distance">

                                <div class="mb-3 d-flex justify-content-between">
                                    <button type="button" onclick="resetLocations()" class="btn btn-danger mb-3">
                                        <i class="bi bi-x"></i> Reset Locations
                                    </button>
                                    <button type="button" onclick="calculateDistance()" class="btn btn-secondary mb-3">
                                        Confirm Location
                                    </button>
                                    <button type="submit" class="btn btn-primary mb-3">
                                        Book Now
                                    </button>
                                </div>
                            </form>

                            <!-- Validation Messages -->
                            <div id="errorMessages" class="alert alert-danger d-none"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>  
</div>

<%@ include file="/common/footer.jsp"%>

<script src="js/map.js"></script>
<script>
    document.getElementById("bookRideForm").addEventListener("submit", function(event) {
        event.preventDefault(); 

        let form = this;
        let isValid = true;
        let errorMessages = [];

        if (!document.getElementById("pickupLocation").value.trim()) {
            errorMessages.push("Please select a pickup location.");
            isValid = false;
        }
        if (!document.getElementById("destination").value.trim()) {
            errorMessages.push("Please select a drop location.");
            isValid = false;
        }
        if (!document.getElementById("distance").value.trim()) {
            errorMessages.push("Please confirm the distance.");
            isValid = false;
        }
        if (!document.getElementById("estimatedTime").value.trim()) {
            errorMessages.push("Please confirm the estimated time.");
            isValid = false;
        }
        if (!document.querySelector('input[name="vehicleType"]:checked')) {
            errorMessages.push("Please select a vehicle type.");
            isValid = false;
        }
        if (!document.getElementById("bookingDatetime").value.trim()) {
            errorMessages.push("Please select a booking date and time.");
            isValid = false;
        }

        let errorDiv = document.getElementById("errorMessages");
        if (!isValid) {
            errorDiv.innerHTML = errorMessages.join("<br>");
            errorDiv.classList.remove("d-none");
            return;
        } else {
            errorDiv.classList.add("d-none");
        }

        let formData = new FormData(form);
        let urlEncodedData = new URLSearchParams(formData).toString();

        fetch('customer?action=createBooking', {
            method: 'POST',
            body: urlEncodedData,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
        .then(response => {
            console.log("Response Status:", response.status);
            return response.json();
        })
        .then(data => {
            console.log("Received JSON Response:", data);
                        
            if (data.success) {
            	showToast(data.message, "bg-success");
            } else {
                showToast(data.message, "bg-danger");
            }
        })
        .catch(error => {
            console.error("Fetch Error:", error);
            showToast("An error occurred. Please try again.", "bg-danger");
        });
    });
</script>
	