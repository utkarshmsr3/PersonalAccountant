<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Personal Accountant Dashboard - Home</title>
<meta name="description" content="">
<meta name="author" content="templatemo">

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
				<li><a href="DashboardController" class="active"><i
						class="fa fa-home fa-fw"></i>Dashboard</a></li>
				<li><a href="ExpenseCategoryController"><i
						class="fa fa-bar-chart fa-fw"></i>Expense Category</a></li>
				<li><a href="IncomeCategoryController"><i
						class="fa fa-money fa-fw"></i>Income Category</a></li>
				<li><a href="ExpenseController"><i
						class="fa fa-dollar fa-fw"></i>Expense</a></li>
				<li><a href="IncomeController"><i class="fa fa-rupee fa-fw"></i>Income</a></li>
				<li><a href="CashBookController"><i
						class="fa fa-book fa-fw"></i>Cash Book</a></li>
				<li><a href="BankBookController"><i
						class="fa fa-university fa-fw"></i>Bank Book</a></li>
				<li><a href="DayBookController"><i
						class="fa fa-sun-o fa-fw"></i>Day Book</a></li>
				<li><a href="BalanceSheetController"><i
						class="fa fa-database fa-fw"></i>Balance Sheet</a></li>
				<li><a href="EditProfileController"><i
						class="fa fa-pencil fa-fw"></i>Edit Profile</a></li>
				<li><a href="SignOutController"><i
						class="fa fa-eject fa-fw"></i>Sign Out</a></li>
			</ul>
			</nav>
		</div>
		<!-- Main content -->
		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-content-container">
				<div class="templatemo-flex-row flex-content-row">
					<div class="templatemo-content-widget white-bg col-2">
						<i class="fa fa-times"></i>
						<div class="square"></div>
						<h2 class="templatemo-inline-block">PERSONAL ACCOUNTANT</h2>
						<hr>
						<p>
							Welcome !<br> Building, preserving and managing your
							personal wealth requires specialized attention. You will get
							one-on-one guidance and a comprehensive financial management
							tools that work with your goals.
						</p>
						<p>We provide you the tools to manage and keep status of your
							expense and income source. We assure you that the data you fill
							in is solely your privacy, and nobody other than the authorized
							user can make changes to it.</p>
					</div>
					<div class="templatemo-content-widget white-bg col-2 text-center">
						<i class="fa fa-times"></i>
						<h2 class="text-uppercase">Manage</h2>
						<h3 class="text-uppercase margin-bottom-10">Your Own Way</h3>
						<img src="images/income-expense.jpg" alt="Bicycle"
							class="img-circle img-thumbnail">
					</div>
					
				</div>
				<div class="templatemo-flex-row flex-content-row">
					<div class="col-1">
						<div class="templatemo-content-widget orange-bg">
							<i class="fa fa-times"></i>
							<div class="media">
								<div class="media-left">
									<a href="#"> <img class="media-object img-circle"
										src="images/management.jpg" alt="Sunset">
									</a>
								</div>
								<div class="media-body">
									<h2 class="media-heading text-uppercase">Thought of the
										hour</h2>
									<p>${requestScope.quote}</p>
								</div>
							</div>
						</div>
					</div>
					<div class="col-1">
						<div class="templatemo-content-widget white-bg">
							<i class="fa fa-times"></i>
							<div class="media">
								<div class="media-left">
									<a href="#"> <img class="media-object img-circle"
										src="images/lastlogin.jpg" alt="Sunset">
									</a>
								</div>
								<div class="media-body">
									<h2 class="media-heading text-uppercase">Last Logged In</h2><br>
									<p>${requestScope.lastLogin}</p>
								</div>
							</div>
						</div>
					</div>
					
				</div>
				<footer class="text-right">
				<p>
					Copyright &copy; 2018 UT | Designed by Utkarsh Mishra
				</p>
				</footer>
			</div>
		</div>
	</div>

	<!-- JS -->
	<script src="js/jquery-1.11.2.min.js"></script>
	<!-- jQuery -->
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<!--  jQuery Migrate Plugin -->
	<script type="text/javascript" src="js/templatemo-script.js"></script>
	<!-- Templatemo Script -->

</body>
</html>