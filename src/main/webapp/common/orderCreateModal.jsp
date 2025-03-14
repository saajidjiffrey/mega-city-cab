<!-- Order Creation Modal -->
<div id="orderModal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Create Order</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form id="orderForm">
                    <input type="hidden" id="orderBookingId">
                    <input type="hidden" id="orderDistance">
                    <input type="hidden" id="orderCustomerId">
                    <input type="hidden" id="orderVehicleId">

                    <div class="mb-3">
                        <label for="startTime" class="form-label">Start Time</label>
                        <input type="datetime-local" id="startTime" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="endTime" class="form-label">End Time</label>
                        <input type="datetime-local" id="endTime" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Create Order</button>
                </form>
            </div>
        </div>
    </div>
</div>


<script>
document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".btn-completeBooking").forEach(button => {
        button.addEventListener("click", function () {
        	
            let bookingId = this.getAttribute("booking-id");
            let distance = this.getAttribute("booking-distance");
            let customerId = this.getAttribute("booking-customerId");
            let vehicleId = this.getAttribute("booking-vehicleId");
            
            if (!bookingId || !distance || !customerId || !vehicleId) {
                console.error("Missing booking details!");
                return;
            }

            // Set values in modal
            document.getElementById("orderBookingId").value = bookingId;
            document.getElementById("orderDistance").value = distance;
            document.getElementById("orderCustomerId").value = customerId;
            document.getElementById("orderVehicleId").value = vehicleId;

            // Show modal
            let orderModal = new bootstrap.Modal(document.getElementById("orderModal"));
            orderModal.show();
        });
    });

    document.getElementById("orderForm").addEventListener("submit", function (event) {
        event.preventDefault();

        let bookingId = document.getElementById("orderBookingId").value;
        let distance = document.getElementById("orderDistance").value;
        let customerId = document.getElementById("orderCustomerId").value;
        let startTime = document.getElementById("startTime").value;
        let endTime = document.getElementById("endTime").value;
        let vehicleId = document.getElementById("orderVehicleId").value;

        let urlEncodedData = new URLSearchParams();
        urlEncodedData.append("bookingId", bookingId);
        urlEncodedData.append("distance", distance);
        urlEncodedData.append("customerId", customerId);
        urlEncodedData.append("startTime", startTime);
        urlEncodedData.append("endTime", endTime);
        urlEncodedData.append("vehicleId", vehicleId);

        fetch("order?action=createOrder", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: urlEncodedData.toString()
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                showToast("Order Created Successfully!", "bg-success");
                let orderModal = bootstrap.Modal.getInstance(document.getElementById("orderModal"));
                orderModal.hide();
            } else {
                showToast("Failed to Create Order", "bg-danger");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            showToast("An error occurred. Please try again.", "bg-danger");
        });
    });
});

</script>
