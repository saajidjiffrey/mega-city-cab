<%@ include file="/common/header.jsp"%>
<!--  -->
<div class='form-signin m-auto 100vh'>
	<form class='m-6 mt-5'>
		<h1 class="h3 mb-3 fw-normal">Sign Up</h1>

		<div class="form-floating">
			<input type="email" class="form-control" id="floatingInput"
				placeholder="name@example.com"> <label for="floatingInput">Email
				address</label>
		</div>
		<div class="form-floating">
			<input type="password" class="form-control" id="floatingPassword"
				placeholder="Password"> <label for="floatingPassword">Password</label>
		</div>

		<div class="form-check text-start my-3">
			<input class="form-check-input" type="checkbox" value="remember-me"
				id="flexCheckDefault"> <label class="form-check-label"
				for="flexCheckDefault"> Remember me </label>
		</div>
		<button class="btn btn-primary w-100 py-2" type="submit">Sign
			in</button>
		<p class="mt-5 mb-3 text-body-secondary">© 2017–2024</p>
	</form>
</div>

<div class='container'>
	<form>
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

				<h1 class="h3 mb-3 fw-normal">Sign Up as a Customer</h1>
				<div class='mw-50'>
					<form>
						<div class="mb-3">
							<label for="name" class="form-label">Full Name</label> <input
								type="text" class="form-control" id="name"
								placeholder="Full name">
						</div>
						<div class="mb-3">
							<label for="address" class="form-label">Address</label>
							<textarea class="form-control" id="address" rows="4"></textarea>
						</div>
						<div class="mb-3">
							<label for="NIC" class="form-label">NIC</label> <input
								type="text" class="form-control" id="NIC"
								placeholder="NIC number">
						</div>
						<div class="mb-3">
							<label for="phone" class="form-label">Phone Number</label> <input
								type="tel" class="form-control" id="phone"
								placeholder="Phone Number">
						</div>
						<div class="mb-3">
							<label for="email" class="form-label">Email</label> <input
								type="email" class="form-control" id="email"
								placeholder="Emailr">
						</div>
						<div class="mb-3">
							<label for="userName" class="form-label">User Name</label> <input
								type="text" class="form-control" id="userName"
								placeholder="User Name">
						</div>
						<div class="mb-3">
							<label for="password" class="form-label">Password</label> <input
								type="password" class="form-control" id="password"
								placeholder="Password"
								aria-describedby="passwordHelpBlockCustomer">
							<div id="passwordHelpBlockCustomer" class="form-text">Your
								password must be 8-20 characters long, contain letters and
								numbers, and must not contain spaces, special characters, or
								emoji.</div>
						</div>
					</form>

				</div>


			</div>
			<div class="tab-pane fade" id="driver-tab-pane" role="tabpanel"
				aria-labelledby="driver-tab" tabindex="0">..driver-tab-pane".</div>

		</div>

	</form>
</div>

<!--  -->
<%@ include file="/common/footer.jsp"%>