<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Yahoo Finance System</title>

    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../css/sb-admin.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


    <!-- AngularJS library -->
    <script src="<c:url value='/js/angular.min.js'/>"></script>
    <script>
    angular.module("balanceModule",[])
    .controller("creditcardController",['$scope', '$http', '$templateCache', 
                                        function($scope, $http, $templateCache){
    	$scope.getAllCreditcards = function(){
    		$http({
    			method: 'POST',
    			url: 'untrans.htm',
        		header: {
        			'Content-Type': 'application/x-www-form-urlencoded'
        		},
        		data: {},
        		cache: $templateCache
    		}).success(function(data, status){
    			$scope.allcards = data;
    		}).error(function(data, status){
    			alert(data);
    		});
    	};
    }]);
    </script>
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/app/dashboard.htm">Yahoo Finance System</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>${shownName}<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="<c:url value='/j_spring_security_logout'/>"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/app/dashboard.htm"><i class="fa fa-fw fa-dashboard"></i> MarketData</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/app/portfolio.htm"><i class="fa fa-fw fa-table"></i> Portfolio</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/app/history.htm"><i class="fa fa-fw fa-table"></i> History</a>
                    </li>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/app/balance.htm"><i class="fa fa-fw fa-file"></i> Balance</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Add Balance
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="dashboard.htm">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-file"></i> Balance
                            </li>
                        </ol>
                    </div>
                    <div class="col-lg-8">
                    	<h2>Add Balance</h2>
                    	<form class="form-horizontal" role="form">
                    		<div id="creditcard-info" class="form-group" style="background:#f77">
                    			<h3>   Credit Card Information</h3>
                    			<div class="form-group">
                    				<div class="col-sm-8 col-sm-offset-1">
                    					<input class="form-control" id="creditCardNo" placeholder="Card Number"/>
                    				</div>
                    				<div class="col-sm-2"><input class="form-control" id="code" placeholder="CVV"/></div>
                    			</div>
                    			<div class="form-group">                    			
                    			<label for="ex-year" class="col-sm-2 control-label">Expire Time:</label>
                    				<div class="col-sm-2"><input type="text" class="form-control" id="ex-year" placeholder="yy"/></div>
                    				<div class="col-sm-2"><input type="text" class="form-control" id="ex-month" placeholder="mm"/></div>
                    			</div>                    			
                    			<div class="form-group">
                    				<label for="cc-name" class="col-sm-2 control-label">Name:</label>
                    				<div class="col-sm-8"><input type="text" class="form-control" id="cc-name" placeholder="Holder's name"/></div>
                    			</div>
                    		</div>
                    		<div id="balance-info" class="form-group">
                    			<label for="balance-input" class="col-sm-2 control-label">Balance:</label>
                    			<div class="col-sm-6">
                    				<input type="number" class="form-control" id="balance-input" placeholder="Balance"/>
                    			</div>
                    			<button class="btn btn-primary">Submit</button>
                    		</div>
                    	</form>
                    </div>
                    <!-- /.pay-content -->
                    <div class="col-lg-4">
                    	<h2>Saved Credit Cards</h2>
                    	<table></table>
                    </div>
                    <!-- /.creditcard-content -->
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>

</body>

</html>
