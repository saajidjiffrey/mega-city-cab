
<style>

#sidebar-wrapper {
	z-index: 1;
	position: absolute;
	width: 0;
	height: 100%;
	overflow-y: hidden;
	background: var(--bs-primary);
	opacity: 0.9;
	transition: all .5s;
	display: flex;
	align-items: center;
}

#page-content-wrapper {
	width: 100%;
	position: absolute;
	padding: 15px;
	transition: all .5s;
}

#menu-toggle {
	transition: all .3s;
	font-size: 2em;
}
#wrapper.menuDisplayed #sidebar-wrapper {
	width: 250px;
}

#wrapper.menuDisplayed #page-content-wrapper {
	padding-left: 250px;
}

.sidebar-nav {
	padding: 0;
	list-style: none;
	transition: all .5s;
	width: 100%;
	text-align: center;
}

.sidebar-nav li {
	line-height: 40px;
	width: 100%;
	transition: all .3s;
	padding: 10px;
}

.sidebar-nav li a {
	display: block;
	text-decoration: none;
	color: #ddd;
}

.sidebar-nav li:hover {
	background: #012b69;
}
</style>

<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<li><a href="customer?action=showCustomerDashboard">Book a Ride</a></li>
		<li><a href="customer?action=showCustomerBookings">My Bookings</a></li>
		<li><a href="customer?action=showCustomerOrders">Completed Orders</a></li>
		<li><a href="#" onclick="logout() ">Logout</a></li>
	</ul>
</div>

<script>
function logout() {
    fetch('user?action=userLogout', {
        method: 'POST',
        headers: { 'Cache-Control': 'no-cache' } 
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        return response.json(); 
    })
    .then(data => {
        console.log("Logout Response:", data);
        if (data.success) {
            window.location.href = "user?action=login"; 
        } else {
            showToast(data.message, "bg-danger");
        }
    })
    .catch(error => {
        console.error("Logout Error:", error);
        showToast("An error occurred while logging out.", "bg-danger");
    });
}



</script>
