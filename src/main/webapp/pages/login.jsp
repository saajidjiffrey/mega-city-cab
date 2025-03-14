<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/messageContainer.jsp"%>
<!--  -->

<div class="container my-5">
    <h1 class="fw-bold text-center mb-4">Login</h1>

    <div class="card shadow-lg border-light">
        <div class="card-body p-5">
            <form action="user?action=userLogin" method="post" class="requires-validation" id="loginForm" novalidate>
                <div class="mb-4">
                    <label for="userName" class="form-label fw-semibold">User Name</label>
                    <input type="text" class="form-control form-control-lg" id="userName" placeholder="Enter your username" name="userName" required>
                    <div class="invalid-feedback">Please enter your username.</div>
                </div>

                <div class="mb-4">
                    <label for="password" class="form-label fw-semibold">Password</label>
                    <input type="password" class="form-control form-control-lg" id="password" placeholder="Enter your password" name="password" required>
                    <div class="invalid-feedback">Please enter your password.</div>
                </div>

                <div class="mb-4 d-flex justify-content-between">
                    <div>
<!--                         <a href="#" class="text-muted">Forgot Password?</a> -->
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary btn-lg px-4">Login</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="text-center mt-3">
        <p class="text-muted">Don't have an account? <a href="user?action=signup" class="fw-semibold">Sign Up</a></p>
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
        // Validate the fields before submitting
        let userName = document.getElementById("userName").value;
        let password = document.getElementById("password").value;

        if (!userName || !password) {
            showToast("Please fill in both fields.", "bg-warning");
            event.preventDefault(); 
            return;
        }

        event.preventDefault();  

        let formData = new FormData(this);
        let urlEncodedData = new URLSearchParams(formData).toString();

        fetch('user?action=userLogin', {
            method: 'POST',
            body: urlEncodedData,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then(data => {   
            if (data.success) {
                showToast(data.message, "bg-success");

                if (data.role === "CUSTOMER") {
                    window.location.href = "customer?action=showCustomerDashboard"; 
                } else if (data.role === "DRIVER") {
                    window.location.href = "driver?action=showDriverDashboard";
                } else if (data.role === "ADMIN") {
                    window.location.href = "admin?action=showAdminDashboard";
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
            showToast("An error occurred. Please try again.", "bg-danger");
        });
    });


</script>