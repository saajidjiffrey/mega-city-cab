<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/messageContainer.jsp"%>
<!--  -->

<div class='container'>
	<h1 class="my-3 fw-normal text-center">Sign Up as</h1>

	<div class="card">
		<div class="card-body">

			<ul class="nav nav-tabs d-flex justify-content-center" id="myTab"
				role="tablist">
				<li class="nav-item" role="presentation">
					<button class="nav-link active" id="customer" data-bs-toggle="tab"
						data-bs-target="#customer-tab-pane" type="button" role="tab"
						aria-controls="customer-tab-pane" aria-selected="true">Customer</button>
				</li>
				<li class="nav-item" role="presentation">
					<button class="nav-link" id="driver-tab" data-bs-toggle="tab"
						data-bs-target="#driver-tab-pane" type="button" role="tab"
						aria-controls="driver-tab-pane" aria-selected="false">Driver</button>
				</li>
			</ul>

			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="customer-tab-pane"
					role="tabpanel" aria-labelledby="customer-tab" tabindex="0">
					<!--  Customer -->
					<div class='mw-50'>
						<form action="user?action=customerSignup" method="post"
							class="requires-validation" novalidate id="customerSignUpForm">
							<div class="mb-3 mt-3">
								<label for="cName" class="form-label">Full Name</label> <input
									type="text" class="form-control" id="cName"
									placeholder="Full name" name="cName" required>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="cAddress" class="form-label">Address</label>
								<textarea class="form-control" id="cAddress" rows="4"
									name="cAddress" required></textarea>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="cNIC" class="form-label">NIC</label> <input
									type="text" class="form-control" id="cNIC"
									placeholder="NIC number" name="cNIC" required>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="cPhone" class="form-label">Phone Number</label> <input
									type="tel" class="form-control" id="cPhone"
									placeholder="Phone Number" name="cPhone" required
									pattern="\d{1,10}">
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="cEmail" class="form-label">Email</label> <input
									type="email" class="form-control" id="cEmail"
									placeholder="Email" name="cEmail" required>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="cUserName" class="form-label">User Name</label> <input
									type="text" class="form-control" id="cUserName"
									placeholder="User Name" name="cUserName" required>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="cPassword" class="form-label">Password</label> <input
									type="password" class="form-control" id="cPassword"
									placeholder="Password" name="cPassword"
									aria-describedby="passwordHelpBlockCustomer" required>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="cConfirmPassword" class="form-label">Confirm
									Password</label> <input type="password" class="form-control"
									id="cConfirmPassword" placeholder="Confirm Password"
									name="cConfirmPassword" required>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<button type="submit" class="btn btn-primary mb-3">Sign
									Up</button>
							</div>
						</form>
					</div>


				</div>
				<div class="tab-pane fade" id="driver-tab-pane" role="tabpanel"
					aria-labelledby="driver-tab" tabindex="0">
					<div class='mw-50'>
						<form action="user?action=driverSignup" method="post"
							class="requires-validation" novalidate id="driverSignUpForm">
							<div class="mb-3 mt-3">
								<label for="dName" class="form-label">Full Name</label> <input
									type="text" class="form-control" id="dName"
									placeholder="Full name" name="dName" required>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="dAddress" class="form-label">Address</label>
								<textarea class="form-control" id="dAddress" rows="4"
									name="dAddress" required></textarea>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="dNIC" class="form-label">NIC</label> <input
									type="text" class="form-control" id="dNIC"
									placeholder="NIC number" name="dNIC" required>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="dLicenseNo" class="form-label">License
									Number</label> <input type="number" class="form-control"
									id="dLicenseNo" name="dLicenseNo" placeholder="License Number"
									required>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="dPhone" class="form-label">Phone Number</label> <input
									type="tel" class="form-control" id="dPhone"
									placeholder="Phone Number" name="dPhone" required
									pattern="\d{1,10}">
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="dEmail" class="form-label">Email</label> <input
									type="email" class="form-control" id="dEmail"
									placeholder="Email" name="dEmail" required>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="dUserName" class="form-label">User Name</label> <input
									type="text" class="form-control" id="dUserName"
									placeholder="User Name" name="dUserName" required>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="dPassword" class="form-label">Password</label> <input
									type="password" class="form-control" id="dPassword"
									placeholder="Password" name="dPassword" required>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<label for="dConfirmPassword" class="form-label">Confirm
									Password</label> <input type="password" class="form-control"
									id="dConfirmPassword" placeholder="Confirm Password"
									name="dConfirmPassword" required>
								<div class="invalid-feedback"></div>
							</div>
							<div class="mb-3">
								<button type="submit" class="btn btn-primary mb-3">Sign
									Up</button>
							</div>
						</form>
					</div>

				</div>

			</div>
		</div>
	</div>

</div>


<!--  -->
<%@ include file="/common/footer.jsp"%>
<script src="js/form-validation.js"></script>
<script src="js/customerSignupInputValidations.js"></script>
<script src="js/driverSignupInputValidations.js"></script>
<script>
document.getElementById("customerSignUpForm").addEventListener("submit", function(event) {
    event.preventDefault();

    let formData = new FormData(this);
    let urlEncodedData = new URLSearchParams(formData).toString();

    for (let [key, value] of formData.entries()) {
        console.log(key + " = " + value);
    }
 	console.log(urlEncodedData );
 	
    fetch('user?action=customerSignup', {
        method: 'POST',
        body: urlEncodedData,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
    .then(response => response.json())
    .then(data => {    	
    	if (data.success) {
    		showToast(data.message, "bg-success");
    		window.location.href =  "driver?action=showCustomerDashboard";
    	} else {
    		showToast(data.message, "bg-danger");
    	}
    })
    .catch(error => {
        console.error("Error:", error);
        showToast("An error occurred. Please try again.", "bg-danger");
    });
});

document.getElementById("driverSignUpForm").addEventListener("submit", function(event) {
    event.preventDefault();

    let formData = new FormData(this);
    let urlEncodedData = new URLSearchParams(formData).toString();

    for (let [key, value] of formData.entries()) {
        console.log(key + " = " + value);
    }
 	console.log(urlEncodedData );
 	
    fetch('user?action=driverSignup', {
        method: 'POST',
        body: urlEncodedData,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
    .then(response => response.json())
    .then(data => {    	
    	if (data.success) {
    		showToast(data.message, "bg-success");
    		window.location.href =  "driver?action=showDriverDashboard";
    	} else {
    		showToast(data.message, "bg-danger");
    	}
    })
    .catch(error => {
        console.error("Error:", error);
        showToast("An error occurred. Please try again.", "bg-danger");
    });
});


</script>

