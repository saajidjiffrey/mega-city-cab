
<style>

/* Sidebar */
#sidebar-wrapper {
	z-index: 1;
	position: absolute;
	width: 0;
	height: 100%;
	overflow-y: hidden;
	background: #661010;
	opacity: 0.9;
	transition: all .5s;
	display: flex;
	align-items: center;
}

/* Main Content */
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
/* Change the width of the sidebar to display it*/
#wrapper.menuDisplayed #sidebar-wrapper {
	width: 250px;
}

#wrapper.menuDisplayed #page-content-wrapper {
	padding-left: 250px;
}

/* Sidebar styling */
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
	background: #2a0404;
}
</style>

<!-- Sidebar -->
<div id="sidebar-wrapper">
	<ul class="sidebar-nav">
		<li><a href="driver?action=showDriverDashboard">Dashboard</a></li>
		<li><a href="driver?action=showDriverAvailableRides">Available Rides</a></li>
		<li><a href="driver?action=showDriverMyRides">My Rides</a></li>
		<li><a href="driver?action=showDriverOrders">Completed Orders</a></li>
		<li><a href="#">Logout</a></li>
	</ul>
</div>
