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

    <title>Trader History</title>

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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${shownName}<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/app/profile.htm"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/app/settings.htm"><i class="fa fa-fw fa-gear"></i> Settings</a>
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
                    <li>
                        <a href="${pageContext.request.contextPath}/app/balance.htm"><i class="fa fa-fw fa-file"></i> Balance</a>
                    </li>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/app/transaction.htm"><i class="fa fa-fw fa-file"></i> Transaction</a>
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
                            Profile
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="${pageContext.request.contextPath}/app/dashboard.htm">MarketData</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-table"></i> Profile
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
                <div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i> View Profile</h3>
                            </div>
                            <div class="panel-body">
                            	<h3>First Name:${firstName}</h3>
                            	<h3>Last  Name:${lastName}</h3>
                            	<h3>Phone:     ${phone}</h3>
                            	<h3>Email:     ${email}</h3>
                            	<h3>Address:   ${address}</h3>
                            	<h3>State:     ${state}</h3>
                            	<h3>City:      ${city}</h3>
                            	<h3>Zipcode:   ${zipcode}</h3>
                            	<h3>Balance:   ${balance}</h3>
                            </div>
                        </div>
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