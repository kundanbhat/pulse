<%@page import="com.ie.Pulse.entity.Brand"%>
<%@page import="java.util.List"%>
<%@page import="com.ie.Pulse.entity.HeadCountdto"%>
<%@page import="java.util.Map"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Pulse Revenue System</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/css/adminlte.min.css">
  <link rel="shortcut icon" href="https://indianexpress.com/favicon.ico"
	type="image/x-icon" />
	
	<style>

<link href="https://cdn.anychart.com/releases/v8/css/anychart-ui.min.css" type="text/css" rel="stylesheet">
  <link href="https://cdn.anychart.com/releases/v8/fonts/css/anychart-font.min.css" type="text/css" rel="stylesheet">

.stay-open {
	display: block !important;
}
 html,
    body,
    #barChart {
      width: 100%;
      height: 400px;
      margin: 0;
      padding: 0;
    }
    #revenuelineChart,#profitlineChart,#expenselineChart,#waterfallchart{
    width: 100%;
      height: 300px;
      margin: 0;
      padding: 0;
    }
    
  
</style>


<script>





function getReport()
{
	
		//document.getElementById("loader").style.display='block';
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();
		console.log(startDate);
		var brandId=$("#brand").val();
		var combrandId=$("#combrand").val();
		generateGraph(startDate,endDate,brandId,combrandId);
		
		
		
}

function generateGraph(startDate,endDate,brandId,combrandId)
{
	$("#waterfallchart").html('');
	$("#barChart").html('');
	$("#revenuelineChart").html('');
	$("#expenselineChart").html('');
	$("#profitlineChart").html('');
	$.get("dashboardreport",{ startDate:startDate, endDate:endDate,brandId:brandId,combrandId:combrandId}, function(data, status){
		
	 	var sdata=data.split("@@@@");
	 	//console.log(sdata);
	 	//console.log(sdata[0]);
	 	//console.log(sdata[1]);
	    var splitData=sdata[0].split("#");
	    var brand=sdata[2].split("vs");
	   
	    
	    var time=splitData[0].split(",");
	    var revenue=splitData[1].split(",");
	    var expense=splitData[2].split(",");
	    var profitPercent=splitData[3].split(",");
	    
	   
	    
	    var k=0;
	    var xdataset = new Array(time.length-1);
	    for(k=0;k<time.length-1;k++)
	    {
	    	xdataset[k]=new Array(4);
	    	xdataset[k][0]=time[k].trim();
	    	xdataset[k][1]=revenue[k].trim();
	    	xdataset[k][2]=expense[k].trim();
	    	xdataset[k][3]=profitPercent[k].trim();
	    }
	 
	
	   
	
	      // create data set on our data
	      var dataSet = anychart.data.set(xdataset);

	      // map data for the first series, take x from the zero column and value from the first column of data set
	      var firstSeriesData = dataSet.mapAs({ x: 0, value: 1 });

	      // map data for the second series, take x from the zero column and value from the second column of data set
	      var secondSeriesData = dataSet.mapAs({ x: 0, value: 2 });

	      // map data for the third series, take x from the zero column and value from the third column of data set
	      var thirdSeriesData = dataSet.mapAs({ x: 0, value: 3 });

	      // create column chart
	      var chart = anychart.column();
	     

	      // turn on chart animation
	      chart.animation(true);

	      // set chart title text settings
	      //chart.title('Revenue vs Expense vs P/L');

	      // create scale for line series and extraYAxis
	      // it force line series to not stuck values with over series
	      var scale = anychart.scales.linear();
	      chart.title(brand[0]);

	      // create extra axis on the right side of chart
	      chart
	        .yAxis(3)
	        .title('Profit %')
	        .orientation('right')
	        .scale(scale);

	      // create second series with mapped data
	      var columnSeries = chart.column(firstSeriesData);
	      columnSeries.name('Revenue');
	      columnSeries.fill('green');

	      // create third series with mapped data
	      var splineSeries = chart.column(secondSeriesData);
	      splineSeries.name('Expense').fill('red');
	      

	      // create line series and set scale for it
	      var lineSeries = chart.spline(thirdSeriesData);
	      lineSeries.name('P/L').yScale(scale).stroke('2.5 #ef6c00');

	      chart.tooltip().displayMode('union');

	      chart.interactivity().hoverMode('by-x');

	      // set container id for the chart
	      chart.container('barChart');

	      // initiate chart drawing
	      chart.draw();
	     
	      splitData=sdata[1].split("#");
	      
	      if(combrandId!="-1")
	      {
	    	  var comrevenue=splitData[0].split(",");
			  var comexpense=splitData[1].split(",");
			  var comprofitPercent=splitData[2].split(",");
			  k=0;
			  var  xcomrevenuedataset = new Array(time.length-1);
			  var  xcomexpensedataset = new Array(time.length-1);
			  var  xcompnl = new Array(time.length-1);
			  for(k=0;k<time.length-1;k++)
			    {
				    xcomrevenuedataset[k]=new Array(3);
				    xcomrevenuedataset[k][0]=time[k].trim();
				    xcomrevenuedataset[k][2]=comrevenue[k].trim();
				    xcomrevenuedataset[k][1]=revenue[k].trim();
				    
				    xcomexpensedataset[k]=new Array(3);
				    xcomexpensedataset[k][0]=time[k].trim();
				    xcomexpensedataset[k][2]=comexpense[k].trim();
				    xcomexpensedataset[k][1]=expense[k].trim();
				    
				    
				    xcompnl[k]=new Array(3);
				    xcompnl[k][0]=time[k].trim();
				    xcompnl[k][2]=comprofitPercent[k].trim();
				    xcompnl[k][1]=profitPercent[k].trim();
				    
			    	
			    }
			 
			  var dataSet = anychart.data.set(xcomrevenuedataset);
			    
	          // map data for the first series, take x from the zero column and value from the first column of data set
	          var firstSeriesData = dataSet.mapAs({ x: 0, value: 1 });
	    
	          // map data for the second series, take x from the zero column and value from the second column of data set
	          var secondSeriesData = dataSet.mapAs({ x: 0, value: 2 });
	    
	          // map data for the third series, take x from the zero column and value from the third column of data set
	         
	    
	          // create line chart
	          var chart = anychart.line();
	    
	          // turn on chart animation
	          chart.animation(true);
	    
	          // set chart padding
	          chart.padding([10, 20, 5, 20]);
	    
	          // turn on the crosshair
	          chart.crosshair().enabled(true).yLabel(false).yStroke("black");
	    
	          // set tooltip mode to point
	          chart.tooltip().positionMode('point');
	          chart.tooltip().width(200);
	    
	          // set chart title text settings
	         
	    
	          // set yAxis title
	          chart.yAxis().title('Rs');
	          chart.xAxis().labels().padding(5);
	    	  var brand=sdata[2].split("vs");
	          // create first series with mapped data
	          var firstSeries = chart.line(firstSeriesData);
	          firstSeries.name(brand[0]);
	          firstSeries.hovered().markers().enabled(true).type('circle').size(4);
	          firstSeries
	            .tooltip()
	            .position('right')
	            .anchor('left-center')
	            .offsetX(5)
	            .offsetY(5);
	    
	          // create second series with mapped data
	          var secondSeries = chart.line(secondSeriesData);
	          secondSeries.name(brand[1]);
	          secondSeries.hovered().markers().enabled(true).type('circle').size(4);
	          secondSeries
	            .tooltip()
	            .position('right')
	            .anchor('left-center')
	            .offsetX(5)
	            .offsetY(5);
	    
	          // create third series with mapped data
	         
	    
	          // turn the legend on
	          chart.legend().enabled(true).fontSize(13).padding([0, 0, 10, 0]);
	    
	          // set container id for the chart
	          chart.container('revenuelineChart');
	          // initiate chart drawing
	          chart.draw();
	          
	          
	          
	          //expense chart*************************
	          
	          var dataSet = anychart.data.set(xcomexpensedataset);
			    
	          // map data for the first series, take x from the zero column and value from the first column of data set
	          var firstSeriesData = dataSet.mapAs({ x: 0, value: 1 });
	    
	          // map data for the second series, take x from the zero column and value from the second column of data set
	          var secondSeriesData = dataSet.mapAs({ x: 0, value: 2 });
	    
	          // map data for the third series, take x from the zero column and value from the third column of data set
	         
	    
	          // create line chart
	          var chart = anychart.line();
	    
	          // turn on chart animation
	          chart.animation(true);
	    
	          // set chart padding
	          chart.padding([10, 20, 5, 20]);
	    
	          // turn on the crosshair
	          chart.crosshair().enabled(true).yLabel(false).yStroke("black");
	    
	          // set tooltip mode to point
	          chart.tooltip().positionMode('point');
	          chart.tooltip().width(200);
	    
	          // set chart title text settings
	         
	          // set yAxis title
	          chart.yAxis().title('Rs');
	          chart.xAxis().labels().padding(5);
	    	  var brand=sdata[2].split("vs");
	          // create first series with mapped data
	          var firstSeries = chart.line(firstSeriesData);
	          firstSeries.name(brand[0]);
	          firstSeries.hovered().markers().enabled(true).type('circle').size(4);
	          firstSeries
	            .tooltip()
	            .position('right')
	            .anchor('left-center')
	            .offsetX(5)
	            .offsetY(5);
	    
	          // create second series with mapped data
	          var secondSeries = chart.line(secondSeriesData);
	          secondSeries.name(brand[1]);
	          secondSeries.hovered().markers().enabled(true).type('circle').size(4);
	          secondSeries
	            .tooltip()
	            .position('right')
	            .anchor('left-center')
	            .offsetX(5)
	            .offsetY(5);
	    
	          // create third series with mapped data
	         
	    
	          // turn the legend on
	          chart.legend().enabled(true).fontSize(13).padding([0, 0, 10, 0]);
	    
	          // set container id for the chart
	          chart.container('expenselineChart');
	          // initiate chart drawing
	          chart.draw();
		
	          
	        //profit line chart ************************* 
	          
	          var dataSet = anychart.data.set(xcompnl);
			    
	          // map data for the first series, take x from the zero column and value from the first column of data set
	          var firstSeriesData = dataSet.mapAs({ x: 0, value: 1 });
	    
	          // map data for the second series, take x from the zero column and value from the second column of data set
	          var secondSeriesData = dataSet.mapAs({ x: 0, value: 2 });
	    
	          // map data for the third series, take x from the zero column and value from the third column of data set
	         
	    
	          // create line chart
	          var chart = anychart.line();
	    
	          // turn on chart animation
	          chart.animation(true);
	    
	          // set chart padding
	          chart.padding([10, 20, 5, 20]);
	    
	          // turn on the crosshair
	          chart.crosshair().enabled(true).yLabel(false).yStroke("black");
	    
	          // set tooltip mode to point
	          chart.tooltip().positionMode('point');
	          chart.tooltip().width(200);
	    
	          // set chart title text settings
	         
	    
	          // set yAxis title
	          chart.yAxis().title('Rs');
	          chart.xAxis().labels().padding(5);
	    	  var brand=sdata[2].split("vs");
	          // create first series with mapped data
	          var firstSeries = chart.line(firstSeriesData);
	          firstSeries.name(brand[0]);
	          firstSeries.hovered().markers().enabled(true).type('circle').size(4);
	          firstSeries
	            .tooltip()
	            .position('right')
	            .anchor('left-center')
	            .offsetX(5)
	            .offsetY(5);
	    
	          // create second series with mapped data
	          var secondSeries = chart.line(secondSeriesData);
	          secondSeries.name(brand[1]);
	          secondSeries.hovered().markers().enabled(true).type('circle').size(4);
	          secondSeries
	            .tooltip()
	            .position('right')
	            .anchor('left-center')
	            .offsetX(5)
	            .offsetY(5);
	    
	          // create third series with mapped data
	         
	    
	          // turn the legend on
	          chart.legend().enabled(true).fontSize(13).padding([0, 0, 10, 0]);
	    
	          // set container id for the chart
	          chart.container('profitlineChart');
	          // initiate chart drawing
	          chart.draw();
	    	  
	      }
	      const chartObject = {
	    		  x: 'testFirstName',
	    		  value: 'testLastName'
	    		};
	      
	      
	      const endchartObject = {
	    		  x: 'p/l',
	    		  isTotal: 'true'
	    		};
	   	  
	      
	      
	      var waterfallArr=sdata[3].split("#");
	     
	      
	      var reveneueWfArr=waterfallArr[0].split(",");
	      var wfdata=[];
	      var arrlength=0;
	      
	      k=0;
	      console.log(reveneueWfArr);
	      for(k=0;k<reveneueWfArr.length-1;k++)
	      {
	    	  const wf=new Object();
	    	  wf.x=reveneueWfArr[k].split(":")[0];
	    	  wf.value=reveneueWfArr[k].split(":")[1];
	    	  wfdata[arrlength]=wf;
	    	  arrlength=arrlength+1;
	    	  
	      }
	      
	      var expenseWfArr=waterfallArr[1].split(",");
	      k=0;
	     
	      for(k=0;k<expenseWfArr.length-1;k++)
	      {
	    	  const wf=new Object();
	    	  wf.x=expenseWfArr[k].split(":")[0];
	    	  wf.value=-1*expenseWfArr[k].split(":")[1];
	    	  wfdata[arrlength]=wf;
	    	  arrlength=arrlength+1;
	    	  
	      }
	      
	    
    	  wfdata[arrlength]=endchartObject;	
    	  
    	  
    	  
    	  
    	  chart = anychart.waterfall();

    	// create a series and set the data
    	var series = chart.waterfall(wfdata);

    	// set the container id
    	chart.container("waterfallchart");

    	// initiate drawing the chart
    	chart.draw();
	      
   
	
  });
	
}


</script>
</head>

<%
int level=(Integer)request.getAttribute("level");
%>


<body class="hold-transition sidebar-mini">
<div class="wrapper">
 <div class="preloader flex-column justify-content-center align-items-center">
    <img class="animation__shake" src="https://indianexpress.com/wp-content/themes/indianexpress/images/indian-express-logo-n.svg" alt="The Indian Express" height="300" width="300">
  </div>
  <!-- Navbar -->

  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
     <a href="index3.html" class="brand-link">
      <img src="/img/ie.png"   style="opacity: .8;max-width: 247px;">
      <span class="brand-text font-weight-light"></span>
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
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Dashboard
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
           <ul class="nav nav-treeview stay-open">
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
								
							</ul>
          </li>
         <%if(level>1){ %>
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-copy"></i>
              <p>
                Upload
                <i class="fas fa-angle-left right"></i>
                <span class="badge badge-info right"></span>
              </p>
            </a>
            <ul class="nav nav-treeview stay-open">
              <li class="nav-item">
                <a href="uploadExpense" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>UploadExpense</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="uploadHeadCount" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>UploadHeadCount</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="uploadPageViewData" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>UploadPageView</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="uploadactual" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>UploadActual</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="uploadrevenue" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>UploadRevenue</p>
                </a>
              </li>
              
                </ul>
          </li>
          <%} %>
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-tree"></i>
              <p>
                Reports
                <i class="fas fa-angle-left right"></i>
              </p>
            </a>
            <ul class="nav nav-treeview stay-open">
              <li class="nav-item">
                <a href="revenueView" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Revenue</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="displayheadCountReport" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Expense</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="pnl" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Pnl</p>
                </a>
              </li>
              
            </ul>
          </li>
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
						
						
						<div class="col-sm-2">
						Brand<select name="brand" id="brand" class="form-control">
									<c:forEach items="${brand}" var="brand">
											<option value="${brand.id}">${brand.name}</option>
									</c:forEach>
							</select>
					</div>	
					<div class="col-sm-2">
						Comparision<select name="combrand" id="combrand" class="form-control">
											<option value="-1"></option>
									<c:forEach items="${brand}" var="brand">
											<option value="${brand.id}">${brand.name}</option>
									</c:forEach>
							</select>
					</div>	
					<div>
							&nbsp; <a onclick="getReport()"
								class="btn btn-primary form-control">Search</a>

						</div>	
						

					</div>
				</div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
      
      
      		<div class="row">
          
          <!-- /.col (LEFT) -->
          <div class="col-md-12">
            <!-- LINE CHART -->
            
            <!-- /.card -->

            <!-- BAR CHART -->
            <div class="card card-success">
              <div class="card-header">
                <h3 class="card-title">WaterFall Chart</h3>

                
              </div>
              <div class="card-body">
                <div class="chart">
                	<div id="waterfallchart"></div>
                </div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->

            <!-- STACKED BAR CHART -->
           
            <!-- /.card -->

          </div>
          <!-- /.col (RIGHT) -->
        </div>

        <div class="row">
          
          <!-- /.col (LEFT) -->
          <div class="col-md-12">
            <!-- LINE CHART -->
            
            <!-- /.card -->

            <!-- BAR CHART -->
            <div class="card card-success">
              <div class="card-header">
                <h3 class="card-title">Revenue vs Expense vs P/L</h3>

                
              </div>
              <div class="card-body">
                <div class="chart">
                	<div id="barChart"></div>
                </div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->

            <!-- STACKED BAR CHART -->
           
            <!-- /.card -->

          </div>
          <!-- /.col (RIGHT) -->
        </div>
        <!-- /.row -->
        <div class="row">
          
          <!-- /.col (LEFT) -->
          <div class="col-md-12">
            <!-- LINE CHART -->
            
            <!-- /.card -->

            <!-- BAR CHART -->
            <div class="card card-success">
              <div class="card-header">
                <h3 class="card-title">Revenue Comparision Charts</h3>

                
              </div>
              <div class="card-body">
                <div class="chart">
                	<div id="revenuelineChart"></div>
                </div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->

            <!-- STACKED BAR CHART -->
           
            <!-- /.card -->

          </div>
          <!-- /.col (RIGHT) -->
        </div>
        
        <div class="row">
          
          <!-- /.col (LEFT) -->
          <div class="col-md-12">
            <!-- LINE CHART -->
            
            <!-- /.card -->

            <!-- BAR CHART -->
            <div class="card card-success">
              <div class="card-header">
                <h3 class="card-title">Expense Comparision Charts</h3>

                
              </div>
              <div class="card-body">
                <div class="chart">
                	<div id="expenselineChart"></div>
                </div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->

            <!-- STACKED BAR CHART -->
           
            <!-- /.card -->

          </div>
          <!-- /.col (RIGHT) -->
        </div>
        
        
        
        
        
        <div class="row">
          
          <!-- /.col (LEFT) -->
          <div class="col-md-12">
            <!-- LINE CHART -->
            
            <!-- /.card -->

            <!-- BAR CHART -->
            <div class="card card-success">
              <div class="card-header">
                <h3 class="card-title">Profit Comparision Charts</h3>

                
              </div>
              <div class="card-body">
                <div class="chart">
                	<div id="profitlineChart"></div>
                </div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->

            <!-- STACKED BAR CHART -->
           
            <!-- /.card -->

          </div>
          <!-- /.col (RIGHT) -->
        </div>

		

		

        
        
        
        
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="float-right d-none d-sm-block">
      <b>Version</b> 1.0.0
    </div>
    <strong>Copyright &copy; 2022 <a href="https://indianexpress.com/">Indian Express</a>.</strong> All rights reserved.
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

<script src="https://cdn.anychart.com/releases/v8/js/anychart-base.min.js"></script>
<script src="https://cdn.anychart.com/releases/v8/js/anychart-ui.min.js"></script>
<script src="https://cdn.anychart.com/releases/v8/js/anychart-exports.min.js"></script>
 <script src="https://cdn.anychart.com/releases/8.11.0/js/anychart-waterfall.min.js"></script>
  

<!-- Page specific script -->
<script>
$( document ).ready(function() {
	var today = new Date();
	var dd = String(today.getDate()).padStart(2, '0');
	var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
	var yyyy = today.getFullYear();

	var  startDate =  (yyyy-1)+"-"+mm;
	$("#startDate").val(startDate);
	
	
	var endDate=yyyy+"-"+mm;
	$("#endDate").val(endDate);
	$("#brand").val(4);
	$("#combrand").val(5);
	
	generateGraph(startDate,endDate,4,5);
});
</script>


</body>
</html>
