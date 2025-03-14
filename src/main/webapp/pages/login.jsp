<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/messageContainer.jsp"%>
<!--  -->

<div class='container'>
	<h1 class="my-3 fw-normal text-center">Login</h1>

	<div class="card">
		<div class="card-body">

			<div class='mw-50'>
				<form action="user?action=userLogin" method="post"
					class="requires-validation" id="loginForm" novalidate>

					<div class="mb-3">
						<label for="userName" class="form-label">User Name</label> <input
							type="text" class="form-control" id="userName"
							placeholder="User Name" name="userName" required>
						<div class="invalid-feedback"></div>
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">Password</label> <input
							type="password" class="form-control" id="password"
							placeholder="Password" name="password" required>
						<div class="invalid-feedback"></div>
					</div>
					<div class="mb-3">
						<button type="submit" class="btn btn-primary mb-3">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</div>

<!--  -->
<%@ include file="/common/footer.jsp"%>
<script src="js/form-validation.js"></script>

<script>
	document.getElementById("userName").addEventListener("input", function () {
	    let errorDiv = this.nextElementSibling;
	    let value = this.value;
	
	    if (!value) {
	        this.setCustomValidity("User name is required.");
	    } else {
	        this.setCustomValidity("");
	    }
	
	    errorDiv.textContent = this.validationMessage;
	});
	
	document.getElementById("password").addEventListener("input", function () {
	    let errorDiv = this.nextElementSibling;
	    let value = this.value;
	
	    if (!value) {
	        this.setCustomValidity("Password is required.");
	    } else {
	        this.setCustomValidity("");
	    }
	
	    errorDiv.textContent = this.validationMessage;	
	});
	
	
	//form
    var userRole = "<%=session.getAttribute("userType") != null ? session.getAttribute("userType") : ""%>";
    console.log("User role from session:", userRole); 
document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault();

    let formData = new FormData(this);
    let urlEncodedData = new URLSearchParams(formData).toString();

    for (let [key, value] of formData.entries()) {
        console.log(key + " = " + value);
    }
 	console.log(urlEncodedData );
 	
    fetch('user?action=userLogin', {
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
    		
    		// Check the role from the session variable
            if (userRole === "CUSTOMER") {
                window.location.href = "customer?action=showCustomerDashboard"; 
            } else if (userRole === "DRIVER") {
                window.location.href = "customer?action=showDriverDashboard";
            } else {
                console.error("Unknown user role");
                alert("An error occurred. Please try again.");
            }
    		
    	} else {
    		showToast(data.message, "bg-danger");
    	}
    })
    .catch(error => {
        console.error("Error:", error);
        showToast"An error occurred. Please try again.", "bg-danger");
    });
});

</script>