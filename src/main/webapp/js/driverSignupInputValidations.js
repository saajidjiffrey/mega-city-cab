/**
 * 
 */

//Customer Form validations
	document.getElementById("dName").addEventListener("input", function () {
	    let errorDiv = this.nextElementSibling;
	    let value = this.value;

	    if (!value) {
	        this.setCustomValidity("Full Name is required.");
	    } else {
	        this.setCustomValidity("");
	    }

	    errorDiv.textContent = this.validationMessage;
	});
	document.getElementById("dAddress").addEventListener("input", function () {
	    let errorDiv = this.nextElementSibling;
	    let value = this.value;

	    if (!value) {
	        this.setCustomValidity("Address is required.");
	    } else if (value.length > 50 ) {
	        this.setCustomValidity("Address cannot be more than 10 digits.");
	    } else {
	        this.setCustomValidity("");
	    }

	    errorDiv.textContent = this.validationMessage;
	});
	document.getElementById("dNIC").addEventListener("input", function () {
	    let errorDiv = this.nextElementSibling;
	    let value = this.value;
		
	    const nicRegex = /^[0-9]{9}[Vv]$|^[0-9]{12}$/;
	    
	    if (!value) {
	        this.setCustomValidity("NIC is required.");
	    } else if (!nicRegex.test(value)) {
	        this.setCustomValidity("Invalid NIC format. Use 9 digits + 'V' or 12 digits.");
	    } else {
	        this.setCustomValidity("");
	    }

	    errorDiv.textContent = this.validationMessage;
	});
	
	document.getElementById("dLicenseNo").addEventListener("input", function () {
		    let errorDiv = this.nextElementSibling;
		    let value = this.value;
					    
		    if (!value) {
		        this.setCustomValidity("Vehicle License number  is required.");
		    }  else {
		        this.setCustomValidity("");
		    }

		    errorDiv.textContent = this.validationMessage;
		});
	
	document.getElementById("dPhone").addEventListener("input", function () {
	    let errorDiv = this.nextElementSibling; 
	    let value = this.value;

	    const phoneRegex = /^\d+$/
	    
	    if (!value) {
	        this.setCustomValidity("Phone Number is required.");
	    } else if (!phoneRegex.test(value)) {
	        this.setCustomValidity("Phone Number must contain only numbers.");
	    } else if (value.length > 10) {
	        this.setCustomValidity("Phone Number cannot be more than 10 digits.");
	    } else {
	        this.setCustomValidity("");
	    }

	    errorDiv.textContent = this.validationMessage; 
	});
	
	document.getElementById("dEmail").addEventListener("input", function () {
	    let errorDiv = this.nextElementSibling;
	    let value = this.value;

	    const emailRegex = /^[A-Za-z0-9+_.-]+@(.+)$/
	    
	    if (!value) {
	        this.setCustomValidity("Email is required.");
	    } else if (!emailRegex.test(value)) {
	        this.setCustomValidity("Invalid email format.");
	    } else {
	        this.setCustomValidity("");
	    }

	    errorDiv.textContent = this.validationMessage;
	});
	
	document.getElementById("dUserName").addEventListener("input", function () {
	    let errorDiv = this.nextElementSibling;
	    let value = this.value;

	    if (!value) {
	        this.setCustomValidity("User name is required.");
	    } else {
	        this.setCustomValidity("");
	    }

	    errorDiv.textContent = this.validationMessage;
	});
	
	document.getElementById("dPassword").addEventListener("input", function () {
	    let errorDiv = this.nextElementSibling;
	    let value = this.value;

	    if (!value) {
	        this.setCustomValidity("Password is required.");
	    } else if (value.length < 8 || !/[a-zA-Z]/.test(value) || !/[0-9]/.test(value)) {
	        this.setCustomValidity("Password must be at least 8 characters long and include both letters and numbers.");
	    } else {
	        this.setCustomValidity("");
	    }

	    errorDiv.textContent = this.validationMessage;
	    
	    validatePasswordMatch();

	});
	
	document.getElementById("dConfirmPassword").addEventListener("input", function () {
	    let errorDiv = this.nextElementSibling;
	    let value = this.value;

	    if (!value) {
	        this.setCustomValidity("Confirm Password is required.");
	    } else {
	        this.setCustomValidity("");
	    }

	    errorDiv.textContent = this.validationMessage;
	    
	    validatePasswordMatch();
	});
	
	// Function to check if passwords match
	function validatePasswordMatch() {
	    let password = document.getElementById("dPassword").value;
	    let confirmPassword = document.getElementById("dConfirmPassword");
	    let errorDiv = confirmPassword.nextElementSibling;

	    if (confirmPassword.value && password !== confirmPassword.value) {
	        confirmPassword.setCustomValidity("Passwords do not match.");
	    } else {
	        confirmPassword.setCustomValidity("");
	    }

	    errorDiv.textContent = confirmPassword.validationMessage;
	}