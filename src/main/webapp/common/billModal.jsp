<!-- Bill Details Modal -->
<div class="modal fade" id="billModal" tabindex="-1"
	aria-labelledby="billModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="billModalLabel">Bill Details</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<table class="table">
					<tbody>
						<tr>
							<th>Booking Date</th>
							<td id="billBookingDatetime"></td>
						</tr>
						<tr>
							<th>Pickup Location</th>
							<td id="billPickupLocation"></td>
						</tr>
						<tr>
							<th>Destination</th>
							<td id="billDestination"></td>
						</tr>
						<tr>
							<th>Estimated Time</th>
							<td id="billEstimatedTime"></td>
						</tr>
						<tr>
							<th>Distance</th>
							<td id="billDistance"></td>
						</tr>
						<tr>
							<th>Fare Amount</th>
							<td id="billFareAmount"></td>
						</tr>
						<tr>
							<th>Total Amount</th>
							<td id="billTotalAmount"></td>
						</tr>
						<tr>
							<th>Tax</th>
							<td id="billTax"></td>
						</tr>
						<tr>
							<th>Discount</th>
							<td id="billDiscount"></td>
						</tr>
						<tr>
							<th>Final Amount</th>
							<td id="billFinalAmount"></td>
						</tr>
						<tr>
							<th>Payment Status</th>
							<td id="billPaymentStatus"></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<script>
document.querySelectorAll(".btn-viewBill").forEach(button => {
    button.addEventListener("click", function () {
    	console.log("hi");
        // Get data from button attributes
        document.getElementById("billBookingDatetime").textContent = this.getAttribute("data-booking-datetime");
        document.getElementById("billPickupLocation").textContent = this.getAttribute("data-pickup-location");
        document.getElementById("billDestination").textContent = this.getAttribute("data-destination");
        document.getElementById("billEstimatedTime").textContent = this.getAttribute("data-estimated-time") + " mins";
        document.getElementById("billDistance").textContent = this.getAttribute("data-distance") + " km";
        document.getElementById("billFareAmount").textContent = "LKR " + this.getAttribute("data-fare-amount");
        document.getElementById("billTotalAmount").textContent = "LKR " + this.getAttribute("data-total-amount");
        document.getElementById("billTax").textContent = "LKR " + this.getAttribute("data-tax");
        document.getElementById("billDiscount").textContent = "LKR " + this.getAttribute("data-discount");
        document.getElementById("billFinalAmount").textContent = "LKR " + this.getAttribute("data-final-amount");
        document.getElementById("billPaymentStatus").textContent = this.getAttribute("data-payment-status");
    });
});
</script>
