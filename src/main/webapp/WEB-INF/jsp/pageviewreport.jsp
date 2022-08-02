<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Pulse Revenue System</title>

<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet" href="/plugins/fontawesome-free/css/all.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="/css/adminlte.min.css">
<link rel="shortcut icon" href="https://indianexpress.com/favicon.ico"
	type="image/x-icon" />
<style>
.stay-open {
	display: block !important;
}
</style>

</head>
<%
int level=(Integer)request.getAttribute("level");
%>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">

		<!-- Navbar -->

		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="index3.html" class="brand-link"> <img src="/img/ie.png"
				style="opacity: .8; max-width: 247px;"> <span
				class="brand-text font-weight-light"></span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar user panel (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
					<div class="info">
						<a href="#" class="d-block">${userName}</a>
					</div>
				</div>



				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column"
						data-widget="treeview" role="menu" data-accordion="false">
						<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-tachometer-alt"></i>
								<p>
									Dashboard <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							 <ul class="nav nav-treeview">
								<li class="nav-item"><a href="home"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Dashboard</p>
								</a></li>
								<%if(level==3){ %>			
								<li class="nav-item"><a href="user"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>User Management</p>
								</a></li>
						<%} %>	
								
							</ul></li>
						 <%if(level>1){ %>
						<li class="nav-item"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-copy"></i>
								<p>
									Upload <i class="fas fa-angle-left right"></i> <span
										class="badge badge-info right"></span>
								</p>
						</a>
							<ul class="nav nav-treeview stay-open">
								<li class="nav-item"><a href="uploadExpense"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>UploadExpense</p>
								</a></li>
								<li class="nav-item"><a href="uploadHeadCount"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>UploadHeadCount</p>
								</a></li>
								<li class="nav-item"><a href="uploadPageViewData"
									class="nav-link active"> <i class="far fa-circle nav-icon"></i>
										<p>UploadPageView</p>
								</a></li>
								<li class="nav-item"><a href="uploadactual"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>UploadActual</p>
								</a></li>
								<li class="nav-item"><a href="uploadrevenue"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>UploadRevenue</p>
								</a></li>

							</ul></li>
						<%} %>	

						<li class="nav-item stay-open"><a href="#" class="nav-link"> <i
								class="nav-icon fas fa-tree"></i>
								<p>
									Reports <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview stay-open">
								<li class="nav-item"><a href="revenueView" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>Revenue</p>
								</a></li>
								<li class="nav-item"><a href="displayheadCountReport"
									class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>Expense</p>
								</a></li>
								<li class="nav-item"><a href="pnl" class="nav-link"> <i
										class="far fa-circle nav-icon"></i>
										<p>Pnl</p>
								</a></li>

							</ul></li>
					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6"></div>

					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">

					<div class="row">
						<div class="col-md-1">&nbsp;</div>

						<div class="col-md-9">
							<div class="card card-primary">
								<div class="card-header bg-dark">
									<h3 class="card-title">Please upload PageView Data sheet</h3>
								</div>
								<div class="card-body">
									<div class="form-group">
										<!-- general form elements -->
										<form:form action="/processPageViewData" method="post"
											enctype="multipart/form-data" modelAttribute="fp" onsubmit="return checkform()">

											<label for="fileimage"> <img
												src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR11x7lA3T1MDrUTFqXDl9RbibPI9lz-mQdMp-ZBJYO_nkJSl5mHtNHzHMA03rDDO3BtFs&usqp=CAU"
												style="margin-left: 200px; width: 400px; margin-top: 50px" />
											</label>

											<input type="file" name="file" class="form-control-file"
												id="fileimage" style="display: none;"
												onchange="getImage(this.value)" />
											<div id="displayimage"></div>
											<label class="text-primary">${message}</label>
											<div class="col-md-4">
												<button type="submit" class="btn btn-dark">Upload</button>
											</div>


										</form:form>
									</div>
								</div>
							</div>

							<!-- /.card -->

							<!-- general form elements -->
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->

				</div>

				<!-- /.container-fluid -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<footer class="main-footer">
			<div class="float-right d-none d-sm-block">
				<b>Version</b> 1.0.0
			</div>
			<strong>Copyright &copy; 2022 <a
				href="https://indianexpress.com/">Indian Express</a>.
			</strong> All rights reserved.
		</footer>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Add Content Here -->
		</aside>
		<!-- /.control-sidebar -->
	</div>
	<!-- ./wrapper -->

	<!-- jQuery -->
	<script src="/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- ChartJS -->
	<script src="/plugins/chart.js/Chart.min.js"></script>
	<!-- AdminLTE App -->
	<script src="/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="/js/demo.js"></script>
	<!-- Page specific script -->
	<script>
	function getImage(imageName)
	{
		var newImg=imageName.replace(/^.*\\/,"")
		$("#displayimage").html(newImg);
	}
	function checkform()
	{
		if(!$('input[type="file"]').val()) {
			   alert('No file is uploaded please upload file');
			   return false;
			}
	}
	
	</script>
</body>
</html>



