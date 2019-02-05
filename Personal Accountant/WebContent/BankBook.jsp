<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Personal Accountant - BankBook</title>
<meta name="description" content="">
<meta name="author" content="templatemo">

<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700'
	rel='stylesheet' type='text/css'>
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/templatemo-style.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

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
				<li><a href="BankBookController" class="active"><i class="fa fa-university fa-fw"></i>Bank
						Book</a></li>
				<li><a href="DayBookController"><i class="fa fa-sun-o fa-fw"></i>Day
						Book</a></li>
				<li><a href="BalanceSheetController"><i class="fa fa-database fa-fw"></i>Balance
						Sheet</a></li>
				<li><a href="EditProfileController"><i class="fa fa-pencil fa-fw"></i>Edit
						Profile</a></li>
				<li><a href="SignOutController"><i class="fa fa-eject fa-fw"></i>Sign
						Out</a></li>
			</ul>
			</nav>
		</div>

		<div class="templatemo-content col-1 light-gray-bg">
			<div class="templatemo-content-container">
				<div class="templatemo-content-widget no-padding">
					<div class="panel panel-default table-responsive">
						<table
							class="table table-striped table-bordered templatemo-user-table">
							<thead>
								<tr>
								<form action="BankBookController">
									<td><h2 class="text-center">Bank Book</h2></td>
									<td><label for="dateFrom">Date From</label><br>
									<input type="date" name="dateFrom" id="dateFrom" required></td>
									<td><label for="dateTo">To</label><br>
									<input type="date" name="dateTo" id="dateTo" required></td>
									<td><input type="submit" class="templatemo-edit-btn" value="show"></td>
								</form>
								</tr>
							</thead>
							${requestScope.bankBookTableData}
						</table>
					</div>
				</div>

				<footer class="text-right">
				<p>
					Copyright &copy; 2084 UT | Designed by Utkarsh Mishra
				</p>
				</footer>
			</div>
		</div>
	</div>
	
	<!-- JS -->
	<script>
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1;
	var yyyy = today.getFullYear();
	 if(dd<10){
	        dd='0'+dd
	    } 
	    if(mm<10){
	        mm='0'+mm
	    } 

	today = yyyy+'-'+mm+'-'+dd;
	document.getElementById("dateTo").setAttribute("max", today);
	
	</script>
	<script src="js/jquery-1.11.2.min.js"></script>
	<!-- jQuery -->
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<!--  jQuery Migrate Plugin -->
	<script type="text/javascript" src="js/templatemo-script.js"></script>
	<!-- Templatemo Script -->
</body>
</html>