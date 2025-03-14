<%@ include file="common/header.jsp"%>
<!--  -->
<div class="container">
	<header
		class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 border-bottom">
		<div class="col-md-3 mb-2 mb-md-0">
			<a href="index.jsp" 
				class="d-inline-flex link-body-emphasis text-decoration-none align-items-center"> 
				<img alt="logo" src="common/images/logo.png" class="me-2" style="width: 64px;">
				<h3 class="text-primary">Mega City Cab</h3>
			</a>
		</div>

		<div class="col text-end">
<!-- 			<a type="button" class="btn btn-outline-primary me-2" href="driver?action=showDriverDashboard">Driver Dashboard</a> -->
<!-- 			<a type="button" class="btn btn-outline-primary me-2" href="customer?action=showCustomerDashboard">Customer Dashboard</a> -->
			<a type="button" class="btn btn-outline-secondary me-2" href="user?action=login">Login</a>
			<a type="button" class="btn btn-primary" href="user?action=signup">Sign-up</a>
		</div>
	</header>
</div>
<div id="myCarousel" class="carousel slide mb-6" data-bs-ride="carousel">
	<div class="carousel-indicators">
		<button type="button" data-bs-target="#myCarousel"
			data-bs-slide-to="0" class="" aria-label="Slide 1"></button>
		<button type="button" data-bs-target="#myCarousel"
			data-bs-slide-to="1" aria-label="Slide 2" class="active"
			aria-current="true"></button>
		<button type="button" data-bs-target="#myCarousel"
			data-bs-slide-to="2" aria-label="Slide 3" class=""></button>
	</div>
	<div class="carousel-inner">
		<div class="carousel-item active carousel-item-start">
			<img class="img-fluid" alt="logo" src="common/images/slider1.jpg">
			<div class="container">
				<div class="carousel-caption text-start">
					<h1>Ride with Comfort & Safety</h1>
					<p class="opacity-75">Experience a hassle-free journey with Mega City Cab. Reliable, affordable, and always on time.</p>
					<p>
						<a class="btn btn-lg btn-primary" href="user?action=signup">Sign
							up Today</a>
					</p>
				</div>
			</div>
		</div>
		<div class="carousel-item carousel-item-next carousel-item-start">
			<img class="img-fluid" alt="logo" src="common/images/slider2.jpg">
			<div class="container">
				<div class="carousel-caption">
					<h1>Book a Ride Anytime, Anywhere</h1>
					<p>With just a few taps, you can book a ride to your destination. Choose from a range of vehicles suited to your needs.</p>
				</div>
			</div>
		</div>
		<div class="carousel-item">
			<img class="img-fluid" alt="logo" src="common/images/slider3.jpg">
			<div class="container">
				<div class="carousel-caption text-end">
					<h1>Trusted by Thousands of Riders</h1>
					<p>Join a growing community of satisfied customers who enjoy safe, comfortable, and affordable rides every day.</p>
					<p>
						<a class="btn btn-lg btn-primary" href="user?action=signup">Sign
							up Today</a>
					</p>
				</div>
			</div>
		</div>
	</div>
	<button class="carousel-control-prev" type="button"
		data-bs-target="#myCarousel" data-bs-slide="prev">
		<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="visually-hidden">Previous</span>
	</button>
	<button class="carousel-control-next" type="button"
		data-bs-target="#myCarousel" data-bs-slide="next">
		<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="visually-hidden">Next</span>
	</button>
</div>

<div class="container marketing">

	<div class="row">
    <div class="col-lg-4">
		<img  alt="logo" src="common/images/home1.jpg" style="width:180px; height: 180px; border-radius: 120px;">
        <h2 class="fw-normal">Affordable Rides</h2>
        <p>Enjoy budget-friendly fares without compromising on comfort and safety. Get the best value for your trips across the city.</p>
    </div>

    <div class="col-lg-4">
        <img alt="logo" src="common/images/home2.jpg" style="width:180px; height: 180px; border-radius: 120px;">
        <h2 class="fw-normal">Fast & Reliable</h2>
        <p>Book a cab instantly and reach your destination on time. Our drivers ensure a smooth and efficient ride every time.</p>
    </div>

    <div class="col-lg-4">
        <img alt="logo" src="common/images/home3.jpg" style="width:180px; height: 180px; border-radius: 120px;">
        <h2 class="fw-normal">Safety First</h2>
        <p>Your safety is our priority. Our cabs are sanitized regularly, and our drivers are well-trained and verified.</p>
    </div>
</div>


	<hr class="featurette-divider">

	<div class="row featurette">
    <div class="col-md-7 order-md-1">
        <h2 class="featurette-heading fw-normal lh-1">
            Fast & Reliable. <span class="text-body-secondary">On Time, Every Time.</span>
        </h2>
        <p class="lead">
            Need a ride in a hurry? Our advanced booking system ensures you get a cab within minutes. With real-time tracking and efficient routes, we guarantee a hassle-free and timely journey to your destination.
        </p>
    </div>
    <div class="col-md-5 order-md-1">
	<img alt="logo" src="common/images/home4.jpg" class="w-100 object-fit-cover img-fluid rounded"  style="height: 500px;">
    </div>
</div>


	<hr class="featurette-divider">

	<div class="row featurette">
    <div class="col-md-7 order-md-2">
        <h2 class="featurette-heading fw-normal lh-1">
            Ride in Comfort. <span class="text-body-secondary">Luxury at Every Mile.</span>
        </h2>
        <p class="lead">
            Experience a smooth and relaxing journey with our modern, well-maintained fleet. Whether it's a short ride or a long trip, Mega City Cab ensures you travel in style and comfort.
        </p>
    </div>
    <div class="col-md-5 order-md-1">
        <img alt="logo" src="common/images/home5.jpg" class="w-100 object-fit-cover img-fluid rounded" style="height: 500px;">
    </div>
</div>

<hr class="featurette-divider">

<div class="row featurette">
    <div class="col-md-7">
        <h2 class="featurette-heading fw-normal lh-1">
            Safety First. <span class="text-body-secondary">Your Security Matters.</span>
        </h2>
        <p class="lead">
            Our professional drivers are background-checked and trained to provide the safest rides. All vehicles are equipped with GPS tracking, and we offer 24/7 customer support for your peace of mind.
        </p>
    </div>
    <div class="col-md-5">
        <img alt="logo" src="common/images/home6.jpg" class="w-100 object-fit-cover img-fluid rounded" style="height: 500px;">
    </div>
</div>


	<hr class="featurette-divider">


</div>
<!--  -->
<%@ include file="common/footer.jsp"%>