<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Yahoo Finance Administrator Page</title>

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value='/css/sb-admin.css'/>" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="<c:url value='/css/plugins/morris.css'/>" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value='/font-awesome-4.1.0/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <!-- AngularJS library -->
    <script src="<c:url value='/js/angular.min.js'/>"></script>
    <script>
    angular.module('adminApp', [])
    .controller('navController', ['$scope', function($scope){
    	$scope.changePage = function(targetPage){
    		$scope.mainContent = targetPage;
    	};
    }])
    .controller('transController', ['$scope', '$http', '$templateCache',
                                    function($scope, $http, $templateCache){
    	$scope.getUncomTrans = function(){
    		$http({
    			method: 'POST',
    			url: 'untrans.htm',
        		header: {
        			'Content-Type': 'application/x-www-form-urlencoded'
        		},
        		data: {},
        		cache: $templateCache
    		}).success(function(data, status){
    			$scope.uTrans = data;
    		}).error(function(data, status){
    			alert(data);
    		});
    	};
    	$scope.getComTrans = function(){
    		$http({
    			method: 'POST',
    			url: 'cotrans.htm',
        		header: {
        			'Content-Type': 'application/x-www-form-urlencoded'
        		},
        		data: {},
        		cache: $templateCache
    		}).success(function(data, status){
    			$scope.cTrans = data;
    		}).error(function(data, status){
    			alert(data);
    		});
    	};
    	$scope.getTrans = function(){
    		$scope.getUncomTrans();
    		$scope.getComTrans();
    	}
    	$scope.makeTrans = function(){
    		$http({
    			method: 'POST',
    			url: 'maketrans.htm',
        		header: {
        			'Content-Type': 'application/x-www-form-urlencoded'
        		},
        		data: {},
        		cache: $templateCache
    		}).success(function(data, status){
        		$scope.uTrans = [];
        		$scope.cTrans = data;
    		}).error(function(data, status){
    			alert(data);
    		});
    	}
    }])
    .controller('stocksController', ['$scope', '$http', '$templateCache',
                                     function($scope, $http, $templateCache){
    	$scope.getStocksInfo = function() {
    		$http({
    			method: 'POST',
    			url: 'stocks.htm',
    			header: {
    				'Content-Type': 'application/x-www-form-urlencoded'
    			},
    			data: {},
    			cache: $templateCache
    		})
    		.success(function(data, status){
    			$scope.pageInfo = '1';
    			$scope.stocks = data;
    		})
    		.error(function(data, status){
    			alert(data);
    		});
    	};
    	$scope.addStocks = function(ssid) {
    		$scope.ssid = ssid;
    		$http({
    			method: 'POST',
    			url: 'addstocks.htm',
    			header: {
    				'Content-Type': 'application/x-www-form-urlencoded'
    			},
    			data: { 'sid': $scope.ssid },
    			cache: $templateCache
    		})
    		.success(function(data, status){
    			$scope.pageInfo = '1';
    			$scope.stocks = data;
    		})
    		.error(function(data, status){
    			alert(data);
    		});
    		
    	};
    	$scope.removeStocks = function(ssid){
    		$http({
    			method: 'POST',
    			url: 'rmstocks.htm',
    			header: {
    				'Content-Type': 'application/x-www-form-urlencoded'
    			},
    			data: { 'sid': ssid },
    			cache: $templateCache
    		})
    		.success(function(data, status){
    			$scope.pageInfo = '1';
    			$scope.stocks = data;
    		})
    		.error(function(data, status){
    			alert(data);
    		});
    	};
    }]);
    </script>
</head>
<body ng-app="adminApp">
    <div id="wrapper" ng-controller="navController">

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
                <a class="navbar-brand" href="admin.htm">Admin</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${username } <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="config.htm"><i class="fa fa-fw fa-gear"></i> Settings</a>
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
                    <!--  <li>
                        <a ng-click="changePage('manage')"><i class="fa fa-fw fa-dashboard"></i> User Management</a>
                    </li>-->
                    <li>
                        <a ng-click="changePage('trans')"><i class="fa fa-fw fa-edit"></i> Make Transaction</a>
                    </li>
                    <li>
                        <a ng-click="changePage('stocks')"><i class="fa fa-fw fa-bar-chart-o"></i> Add/Remove Stocks</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">

            <div class="container-fluid" ng-switch on="mainContent">

                <!-- Page Heading -->
                <div class="row" ng-switch-default>
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Yahoo Finanace System
                            <small>Administrator Page</small>
                        </h1>
                    </div>
                </div>
                <!-- /.row -->
                
                <!-- page for management -->
                <div class="row" ng-switch-when="manage">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Management
                        </h1>
                    </div>
                </div>
                <!-- /.row page for management-->
                
                <!-- page for transaction -->
                <div class="row" ng-switch-when="trans" ng-controller="transController" ng-init="getTrans()">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                        	Transaction
                        </h1>
                    </div>
                    <div class="col-lg-12">
                    	<h2>Uncommitted</h2>
                    	<button class="btn btn-primary" ng-click="makeTrans()">Commit Transaction</button>
                    	<table class="table table-hover" id="utrans">
                    	<thead><tr>
                    		<th>TID</th>
                    		<th>LID</th>
                    		<th>SID</th>
                    		<th>Time</th>
                    		<th>Price</th>
                    		<th>Quantity</th>
                    		<th>Type</th>
                    		<th>Status</th>
                    	</tr></thead>
                    	<tbody><tr ng-repeat="t in uTrans">
                    		<td>{{t.tid}}</td>
                    		<td>{{t.lid}}</td>
                    		<td>{{t.sid}}</td>
                    		<td>{{t.t_time}}</td>
                    		<td>{{t.price}}</td>
                    		<td>{{t.quantity}}</td>
                    		<td>{{t.t_type}}</td>
                    		<td>{{t.t_status}}</td>
                    	</tr></tbody>
                    	</table>
                    </div>
                    <div class="col-lg-12">
                    	<h2>Comitted</h2>
                    	<table class="table table-hover" id="ctrans">
                    	<thead><tr>
                    		<th>TID</th>
                    		<th>LID</th>
                    		<th>SID</th>
                    		<th>Time</th>
                    		<th>Price</th>
                    		<th>Quantity</th>
                    		<th>Type</th>
                    		<th>Status</th>
                    	</tr></thead>
                    	<tbody><tr ng-repeat="t in cTrans">
                    		<td>{{t.tid}}</td>
                    		<td>{{t.lid}}</td>
                    		<td>{{t.sid}}</td>
                    		<td>{{t.t_time}}</td>
                    		<td>{{t.price}}</td>
                    		<td>{{t.quantity}}</td>
                    		<td>{{t.t_type}}</td>
                    		<td>{{t.t_status}}</td>
                    	</tr></tbody>
                    	</table>
                    </div>
                </div>
                <!-- /.row page for transaction-->
                
                <!-- page for stocks -->
                <div class="row" ng-switch-when="stocks" ng-controller="stocksController" ng-init="getStocksInfo()">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                        	Stocks Info
                        </h1>
                    </div>
                    <form id="addStocks" class="from-group col-lg-12">
                    	<div class="col-lg-8 col-lg-offset-2"><input class="form-control" ng-model="ssid" placeholder="Please enter stock ID."></div>
                    	<div class="col-lg-1"><button class="btn btn-primary" ng-click="addStocks(ssid)">Add</button></div>
                    </form>                    
                    <table class="table table-hover" id="stocks">
                     	<thead><tr><th>SID</th><th class="col-lg-1">Remove</th></tr></thead>
                       	<tbody>
                       		<tr ng-repeat="s in stocks">
                       			<td>{{s.sid | uppercase}}</td>
                       			<td><a ng-click="removeStocks(s.sid)"><span class="s-remove glyphicon glyphicon-remove"></span></a></td>
                       		</tr>
                       	</tbody>
                    </table>
                </div>
                <!-- /.row page for stocks-->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    
    <!-- jQuery -->
    <script src="<c:url value='/js/jquery.js'/>"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value='/js/bootstrap.min.js'/>"></script>

    <!-- Morris Charts JavaScript -->
    <script src="<c:url value='/js/plugins/morris/raphael.min.js'/>"></script>
    <script src="<c:url value='/js/plugins/morris/morris.min.js'/>"></script>
    <script src="<c:url value='/js/plugins/morris/morris-data.js'/>"></script>
    <style>
    .s-remove:hover{
    	color: red;
    }
    </style>

</body>
</html>