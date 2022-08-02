

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
<link rel="stylesheet"
	href="/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
<script>
	
	function getReport() {

		//document.getElementById("loader").style.display = 'block';
	/*	$("#pulseData").html("");
		$.get("headcountreportbasedbrand", {
			startDate : $("#startDate").val(),
			endDate : $("#endDate").val(),
			reportType : $("#reportType").val()
		}, function(data, status) {
			$("#pulseData").html(data);
			
			$("#example1").DataTable({

				"responsive" : false,

				"bPaginate" : false,
				"bSort" : false,
				"buttons" : [ "copy", "csv", "excel", "colvis" ]
			}).buttons().container().appendTo(
					'#example1_wrapper .col-md-6:eq(0)');
		});*/
		//document.getElementById("loader").style.display = 'none';
		
		
		
		$("#pulseData").html("");
		$.get("headcountreportbasedbrand",{ startDate:$("#startDate").val(), endDate:$("#endDate").val(), reportType:$("#reportType").val()}, function(data, status){
			$("#pulseData").html(data);
			$("#example1").DataTable({
		    	
		    	 "responsive": false,
		    	
		    	"bPaginate": false,
		    	"bSort": false,
		      "buttons": ["copy", "csv", "excel",  "colvis"]
		    }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
			//document.getElementById("loader").style.display='none';
		  });

	}

	function opentrs(className) {

		$("." + className).slideToggle('slow');
	}
</script>

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
									class="nav-link "> <i class="far fa-circle nav-icon"></i>
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

						<li class="nav-item stay-open"><a href="#" class="nav-link">
								<i class="nav-icon fas fa-tree"></i>
								<p>
									Reports <i class="fas fa-angle-left right"></i>
								</p>
						</a>
							<ul class="nav nav-treeview stay-open">
								<li class="nav-item "><a href="revenueView"
									class="nav-link "> <i class="far fa-circle nav-icon"></i>
										<p>Revenue</p>
								</a></li>
								<li class="nav-item"><a href="displayheadCountReport"
									class="nav-link active"> <i class="far fa-circle nav-icon"></i>
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
						<div class="col-sm-3">
							Start Date <input class="form-control" type="month"
								id="startDate" />
						</div>
						<div class="col-sm-3">


							End Date <input class="form-control" type="month" id="endDate" />
						</div>
						<div class="col-sm-3">


							Type<select name="reportType" id="reportType"
								class="form-control">
								<option value="headcount">HeadCount</option>
								<%if(level==3){ %>
								<option value="salarywise">SalaryWise</option>
								<option value="l1l2vendorExpense">CategoryWiseExpense</option>
								<%} %>
								<option value="monthsalarywise">MonthWiseSalary</option>
								
								<option value="brandwiseexpense">BrandWiseExpense</option>
							</select>

						</div>
						<div>
							&nbsp; <a onclick="getReport()"
								class="btn btn-primary form-control">Search</a>

						</div>

					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<div class="card">

						<!-- /.card-header -->
						<div class="card-body" id="pulseData"></div>
						

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
	<script src="/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<script src="/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
	<script src="/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
	<script src="/plugins/jszip/jszip.min.js"></script>
	<script src="/plugins/pdfmake/pdfmake.min.js"></script>
	<script src="/plugins/pdfmake/vfs_fonts.js"></script>
	<script src="/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
	<script src="/plugins/datatables-buttons/js/buttons.print.min.js"></script>
	<script src="/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
	<script>
		$(function() {

		});
	</script>

</body>
</html>



