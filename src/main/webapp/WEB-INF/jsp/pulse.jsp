<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sales Team Funnel</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="shortcut icon" href="https://indianexpress.com/favicon.ico"
	type="image/x-icon" />

<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<script type="text/javascript">


</script>


</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a href="#" class="navbar-brand"> <img
				src="https://indianexpress.com/wp-content/themes/indianexpress/images/indian-express-logo-n.svg"
				height="20" alt="CoolBrand">
			</a>
			<button type="button" class="navbar-toggler"
				data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarCollapse">
				<div class="navbar-nav">
					<a href="home" class="nav-item nav-link active">Home </a>
					
				</div>
				<div class="navbar-nav">
					<a href="uploadExpense" class="nav-item nav-link active">UploadExpense</a>
					
				</div>
				
				<div class="navbar-nav">
					<a href="uploadHeadCount" class="nav-item nav-link active">UploadHeadCount</a>
					
				</div>
				
				<div class="navbar-nav">
					<a href="uploadPageViewData" class="nav-item nav-link active">UploadPageView</a>
					
				</div>
				
			
					<div class="navbar-nav">
					<a href="uploadactual" class="nav-item nav-link active">UploadActual</a>
					
				</div>
				
				
				<div class="navbar-nav">
					<a href="displayheadCountReport" class="nav-item nav-link active">HeadCountReport</a>
					
				</div>
				
				
				<div class="navbar-nav ms-auto">
					  <a href="#" class="nav-item nav-link text-primary">${userName}</a>
				</div>
			</div>
		</div>
	</nav>






	





</body>
</html>