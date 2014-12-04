<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</head>
<body>
<!-- Not Finished -->
<form name="f" action="register.htm" method="POST" id="login-form">
	<p>Username:</p><input type="text" name="r_username" id="r_username"/><br/>
	<p>Password:</p><input type="password" name="r_password" id="r_password"/><br/>
	<p>password:</p><input type="password" name="r_r_password" id="r_r_password"/><br/>
	<p>first name:</p><input type="text" name="r_firstname" id="r_firstname"/><br/>
	<p>last name:</p><input type="text" name="r_lastname" id="r_lastname"/><br/>
	<p>phone number:</p><input type="text" name="r_phone" id="r_phone"/><br/>
	<p>email:</p><input type="email" name="r_email" id="r_email"/><br/>
	<p>address:</p><input type="text" name="r_address" id="r_address"/><br/>
	<p>city:</p><input type="text" name="r_city" id="r_city"/><br/>
	<p>State:</p><input type="text" name="r_state" id="r_state"/><br/>
	<p>zipcode:</p><input type="text" name="r_zipcode" id="r_zipcode"/><br/>
	<button type="submit">Register</button>
</form>
</body>
</html>