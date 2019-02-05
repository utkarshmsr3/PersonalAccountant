<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Personal Accountant - Edit Profile</title>
<meta name="description" content="">
<meta name="author" content="templatemo">

<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700'
	rel='stylesheet' type='text/css'>
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/templatemo-style.css" rel="stylesheet">

</head>
<body>
	<!-- Left column -->
	<div class="templatemo-flex-row">
		<div class="templatemo-sidebar">
			<div class="mobile-menu-icon">
				<i class="fa fa-bars"></i>
			</div>
			<nav class="templatemo-left-nav">
			<ul>
				<li><a href="DashboardController"><i class="fa fa-home fa-fw"></i>Dashboard</a></li>
				<li><a href="ExpenseCategoryController"><i
						class="fa fa-bar-chart fa-fw"></i>Expense Category</a></li>
				<li><a href="IncomeCategoryController"><i
						class="fa fa-money fa-fw"></i>Income Category</a></li>
				<li><a href="ExpenseController"><i class="fa fa-dollar fa-fw"></i>Expense</a></li>
				<li><a href="IncomeController"><i class="fa fa-rupee fa-fw"></i>Income</a></li>
				<li><a href="CashBookController"><i class="fa fa-book fa-fw"></i>Cash
						Book</a></li>
				<li><a href="BankBookController"><i class="fa fa-university fa-fw"></i>Bank
						Book</a></li>
				<li><a href="DayBookController"><i class="fa fa-sun-o fa-fw"></i>Day
						Book</a></li>
				<li><a href="BalanceSheetController"><i class="fa fa-database fa-fw"></i>Balance
						Sheet</a></li>
				<li><a href="EditProfileController" class="active"><i class="fa fa-pencil fa-fw"></i>Edit
						Profile</a></li>
				<li><a href="SignOutController"><i class="fa fa-eject fa-fw"></i>Sign
						Out</a></li>
			</ul>
			</nav>
		</div>

		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-content-container">
				<div class="templatemo-content-widget white-bg">
					<h2 class="margin-bottom-10">Edit Profile</h2>
					<br>
					<script>
						function submitEditProfile() {
							//window.alert("in submit");
							if (document.getElementById("inputNewPassword").value != document
									.getElementById("inputConfirmNewPassword").value) {
								document
										.getElementById("inputConfirmNewPasswordLabel").innerHTML = "Confirm New Password (*Password did not match)";
								return false;
							} else if (document
									.getElementById("inputCurrentPassword").value == '${requestScope.password}') {
							//	window.alert("true");
								document.getElementById("isFormSubmitted").value = "true";
							//	console.log("true completed");
								return true;
							}

							else {
							//	window.alert("false");
								document
										.getElementById("inputCurrentPasswordLabel").innerHTML = "Current Password (*Incorrect password)";
							//	window.alert("false completed");
							}
							//window.alert("error");
							return false;
						}
						function eraseError() {
							document
									.getElementById("inputCurrentPasswordLabel").innerHTML = "Current Password";
							document.getElementById("inputUsernameLabel").innerHTML = "Username";
							document
									.getElementById("inputConfirmNewPasswordLabel").innerHTML = "Confirm New Password";
						}
					</script>
					<form action="EditProfileController" class="templatemo-login-form"
						onsubmit="return submitEditProfile()" method="post"
						accept-charset="utf-8">
						<div class="row form-group">
							<div class="col-lg-6 col-md-6 form-group">
								<label for="inputFirstName">First Name</label> <input
									type="text" class="form-control" id="inputFirstName"
									name="inputFirstName" required>
							</div>
							<div class="col-lg-6 col-md-6 form-group">
								<label for="inputLastName">Last Name</label> <input type="text"
									class="form-control" id="inputLastName" name="inputLastName">
							</div>
						</div>
						<div class="row form-group">
							<div class="col-lg-6 col-md-6 form-group">
								<label for="inputUsername" id="inputUsernameLabel">Username ${requestScope.usernameError}</label>
								<input type="text" class="form-control" id="inputUsername"
									name="inputUsername" onfocus="eraseError();" required>
							</div>
							<div class="col-lg-6 col-md-6 form-group">
								<label for="inputEmail">Email</label> <input type="email"
									class="form-control" id="inputEmail" name="inputEmail" required>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-lg-12 col-md-6 form-group">
								<label for="inputMobileNo">Mobile No</label> <input type="text"
									class="form-control" pattern="[0-9]{10}" id="inputMobileNo"
									name="inputMobileNo" required>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-lg-6 col-md-6 form-group">
								<label for="inputCurrentPassword" id="inputCurrentPasswordLabel">Current
									Password</label> <input type="password" class="form-control highlight"
									id="inputCurrentPassword" name="inputCurrentPassword"
									placeholder="*********************" required
									onfocus="eraseError();">
							</div>
						</div>
						<div class="row form-group">
							<div class="col-lg-6 col-md-6 form-group">
								<label for="inputNewPassword">New Password</label> <input
									type="password" class="form-control" id="inputNewPassword"
									name="inputNewPassword">
							</div>
							<div class="col-lg-6 col-md-6 form-group">
								<label for="inputConfirmNewPassword"
									id="inputConfirmNewPasswordLabel">Confirm New Password</label>
								<input type="password" class="form-control"
									id="inputConfirmNewPassword" name="inputConfirmNewPassword"
									onfocus="eraseError();">
							</div>
						</div>
						<div class="row form-group">
							<div class="col-lg-12 form-group">
								<label class="control-label" for="inputAddress">Address</label>
								<textarea class="form-control" id="inputAddress"
									name="inputAddress" rows="3" required></textarea>
							</div>
						</div>

						<div class="form-group text-right">
							<input type="hidden" id="isFormSubmitted" name="isFormSubmitted">
							<button type="submit" class="templatemo-blue-button">Update</button>
							<button type="reset" class="templatemo-white-button">Reset</button>
						</div>
					</form>
				</div>
				<footer class="text-right">
				<p>
					Copyright &copy; 2018 UT | Designed by <a
						href="http://www.templatemo.com" target="_parent">Utkarsh
						Mishra</a>
				</p>
				</footer>
			</div>
		</div>
	</div>


	<!-- JS -->
	<script>
		document.getElementById("inputFirstName").value = '${requestScope.firstName}';
		document.getElementById("inputLastName").value = '${requestScope.lastName}';
		document.getElementById("inputUsername").value = '${requestScope.username}'
		document.getElementById("inputEmail").value = '${requestScope.email}';
		document.getElementById("inputMobileNo").value = '${requestScope.mobileno}';
		document.getElementById("inputNewPassword").value = '${requestScope.password}';
		document.getElementById("inputConfirmNewPassword").value = '${requestScope.password}';
		document.getElementById("inputAddress").innerHTML = '${requestScope.address}';
	</script>
	<script src="js/jquery-1.11.2.min.js"></script>
	<!-- jQuery -->
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<!--  jQuery Migrate Plugin -->
	<script type="text/javascript" src="js/templatemo-script.js"></script>
	<!-- Templatemo Script -->
</body>
</html>