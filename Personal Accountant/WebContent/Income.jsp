<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Personal Accountant - Income</title>
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
				<li><a href="IncomeController" class="active"><i class="fa fa-rupee fa-fw"></i>Income</a></li>
				<li><a href="CashBookController"><i class="fa fa-book fa-fw"></i>Cash
						Book</a></li>
				<li><a href="BankBookController"><i class="fa fa-university fa-fw"></i>Bank
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
			${requestScope.IncomeMessage}
			<div class="templatemo-content-container">
				<div class="templatemo-content-widget white-bg">
					<h2 class="margin-bottom-10">Income</h2>
					<br> <br>
					<form action="IncomeController" onsubmit="return submitForm();"
						class="templatemo-login-form" method="post">
						<div class="row form-group">
							<div class="col-lg-12 col-md-6 form-group">
								<label for="inputIncomeName">Name</label> <input type="text"
									class="form-control" id="inputIncomeName"
									name="inputIncomeName" placeholder="Income Name" required>
							</div>
						</div>

						<div class="row form-group">
							<div class="col-lg-6 col-md-6 form-group">
								<label class="control-label templatemo-block"
									id="inputCategoryLabel">Category</label> <select
									name="inputIncomeCategory" id="inputIncomeCategory"
									class="form-control">
									${requestScope.incomeCategories}
								</select>
							</div>
							<div class="col-lg-6 col-md-6 form-group">
								<label class="control-label templatemo-block">Receive By</label> <select
									name="inputReceiveBy" class="form-control">
									<option value="Cash" selected>Cash</option>
									<option value="UPI">UPI</option>
									<option value="Cheque">Cheque</option>
									<option value="Debit Card">Debit Card</option>
									<option value="Credit Card">Credit Card</option>
									<option value="Direct Account Transfer">Direct Account
										Transfer</option>
								</select>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-lg-6 col-md-6 form-group">
								<label for="inputAmount" id="inputAmountLabel">Amount</label> <input
									type="number" step="0.01" class="form-control" id="inputAmount"
									name="inputAmount" placeholder="0.00" required>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-lg-12 col-md-6 form-group">
								<label for="inputRemark">Remark</label> <input type="text"
									class="form-control" id="inputRemark" name="inputRemark"
									required>
							</div>
						</div>

						<div class="row form-group">
							<div class="col-lg-12 col-md-6 form-group">
								<label for="inputDate">Date</label> <input type="date"
									class="form-control" id="inputDate" name="inputDate" required>
							</div>
						</div>
						<div class="form-group text-right">
							<button type="submit" class="templatemo-blue-button">Add</button>
							<button type="reset" class="templatemo-white-button">Reset</button>
						</div>
					</form>
				</div>
				<footer class="text-right">
				<p>Copyright &copy; 2018 UT | Designed by Utkarsh Mishra</p>
				</footer>
			</div>
		</div>
	</div>

	<!-- JS -->
	<script>
		window.onload = submitForm();
		function submitForm() {
			//window.alert("in submit");
			if (document.getElementById("inputIncomeCategory").value == "") {
				document.getElementById("inputCategoryLabel").innerHTML = "Category (* no category created)<a href=\"IncomeCategoryController\"> Create Category !</a>";
				return false;
			}

			//window.alert(document.getElementById("inputDate").value);
			return true;
		}
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
		document.getElementById("inputDate").setAttribute("max", today);
		
	</script>
	<script src="js/jquery-1.11.2.min.js"></script>
	<!-- jQuery -->
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<!--  jQuery Migrate Plugin -->
	<script type="text/javascript" src="js/templatemo-script.js"></script>
	<!-- Templatemo Script -->
</body>
</html>