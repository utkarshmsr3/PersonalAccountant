<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Personal Accountant - Expense Category</title>
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
				<li><a href="ExpenseCategoryController" class="active"><i
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
				<li><a href="EditProfileController"><i class="fa fa-pencil fa-fw"></i>Edit
						Profile</a></li>
				<li><a href="SignOutController"><i class="fa fa-eject fa-fw"></i>Sign
						Out</a></li>
			</ul>
			</nav>
		</div>
		<!-- Main content -->


		<div class="templatemo-content col-1 light-gray-bg">
			${requestScope.expenseCategoryNotification}
			<br> <br> <br>
			<script>
				function createAction(){
					//window.alert("in create");
					document.getElementById("expenseCategoryOperationCreate").value = "create";
					//window.alert("returning true:create");
					return true;
				}
			</script>
			<form action="ExpenseCategoryController" onsubmit="return createAction();" class="templatemo-login-form" method="post">
				<input type="hidden" id="expenseCategoryOperationCreate" name="expenseCategoryOperationCreate">
				<div class="row form-group">
					<div class="col-lg-1 has-success form-group"></div>
					<div class="col-lg-10 has-success form-group">
						<label class="control-label" for="inputCategoryName">Category
							Name</label> <input type="text" class="form-control" id="inputCategoryName" name="inputCategoryName" required>
					</div>
					<div class="col-lg-1 has-success form-group"></div>
				</div>
				<div class="row form-group">
					<div class="col-lg-1 has-success form-group"></div>
					<div class="col-lg-10 form-group">
						<label class="control-label" for="inputCategoryDetails">Category
							Details</label>
						<textarea class="form-control" id="inputCategoryDetails" name="inputCategoryDetails" rows="3" required></textarea>
					</div>
					<div class="col-lg-1 has-success form-group"></div>
				</div>

				<div class="form-group text-center">
					<button type="submit" class="templatemo-blue-button">Create</button>
					<button type="reset" class="templatemo-white-button">Cancel</button>
				</div>
			</form>
			<div class="templatemo-content-widget no-padding">
				<div class="panel panel-default table-responsive">
				<script>
					var previousData = {};
					function editAction(catid){
						var catNametd = document.getElementById("catName" + catid);
						var catDetailstd = document.getElementById("catDetails" + catid);
						previousData[catid] = [catNametd.innerHTML, catDetailstd.innerHTML];
						catNametd.innerHTML = "<input type=\"text\" id=\"inputCatName"+catid+"\" value =\" "+ catNametd.innerHTML +"\">";
						catDetailstd.innerHTML = "<input type=\"text\" id=\"inputCatDetails"+catid+"\" value=\""+ catDetailstd.innerHTML +"\">";
						var editCanceltd = document.getElementById("editCancel" + catid);
						editCanceltd.innerHTML = "<a onclick=\"cancelAction(" + catid + ")\" class=\"templatemo-edit-btn\">Cancel</a>";
						var deleteSavetd = document.getElementById("deleteSave" + catid);
						deleteSavetd.innerHTML = "<a onclick=\"saveAction(" + catid + ")\" class=\"templatemo-edit-btn\">Save</a>";						
					}
					
					function cancelAction(catid){
						var catNametd = document.getElementById("catName" + catid);
						var catDetailstd = document.getElementById("catDetails" + catid);
						catNametd.innerHTML = previousData[catid][0];
						catDetailstd.innerHTML = previousData[catid][1];
						var editCanceltd = document.getElementById("editCancel" + catid);
						editCanceltd.innerHTML = "<a onclick=\"editAction(" + catid + ")\" class=\"templatemo-edit-btn\">Edit</a>";
						var deleteSavetd = document.getElementById("deleteSave" + catid);
						deleteSavetd.innerHTML = "<a onclick=\"deleteAction(" + catid + ")\" class=\"templatemo-edit-btn\">Delete</a>";						
						delete previousData[catid];
					}
					
					function deleteAction(catid){
						document.getElementById("expenseCategoryOperation").value = "delete";
						document.getElementById("changedId").value = catid;
						var form = document.getElementById("tableForm");
						form.submit();
					}
					
					function saveAction(catid){
						//window.alert("in save");
						document.getElementById("expenseCategoryOperation").value = "save";
						document.getElementById("changedCatName").value = document.getElementById("inputCatName"+catid).value;
						document.getElementById("changedCatDetails").value = document.getElementById("inputCatDetails"+catid).value;
						document.getElementById("changedId").value = catid;
						var form = document.getElementById("tableForm");
						form.submit();
						//window.alert("form submitted")
					}
				</script>
					<form action="ExpenseCategoryController" id="tableForm" method="post">
						<input type="hidden" id="expenseCategoryOperation" name="expenseCategoryOperation">
						<input type="hidden" id="changedId" name="changedId">
						<input type="hidden" id="changedCatName" name="changedCatName">
						<input type="hidden" id="changedCatDetails" name="changedCatDetails">
						${requestScope.expenseCategoryData}
					</form>
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