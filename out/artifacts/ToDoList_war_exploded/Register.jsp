<%@ page import="com.hit.model.HibernateToDoListDAO" %>
<%@ page import="com.hit.model.User" %><%--
  Created by IntelliJ IDEA.
  User: barazoulay
  Date: 30/11/2017
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel ="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/bootstrap/css/custom.css">
    <link rel="stylesheet" href="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url=exception.jsp?type=timeout" />

    <title>Register</title>
</head>
<body>
<div data-role="page">
    <div data-role="header">
<h1>${messageR}</h1>
<h3 class="error">${message}</h3>
</div>
    <div data-role="content">
<div class="container" style="margin-top: 100px";>
    <div class="row">
        <div class="col-md-4 col-md-offset-4" style="margin: auto">
            <div class="panel panel-default">
                <h4>Register</h4><br>
                <br>
                <div class="panel-body">

                    <form action="/servlets/login.jsp" method="post" >
                        <div class="form-group">
                            <input type="text" placeholder="Enter First Name" name="firstname" required>
                        </div><!-- Form Group-->
                        <div class="form-group">
                            <input type="text" placeholder="Enter Last Name" name="lastname" required>
                        </div><!-- Form Group-->
                        <div class="form-group">
                            <input type="email" placeholder="Enter Email" name="email" required>
                        </div><!-- Form Group-->
                        <div class="form-group">
                            <input type="text" placeholder="Enter Phone number" name="phone" required>
                        </div><!-- Form Group-->
                        <div class="form-group">
                            <input type="text" placeholder="Enter Username" name="username" required>
                        </div><!-- Form Group-->
                        <div class="form-group">
                            <input type="password" placeholder="Enter Password..." name="password" required>
                        </div><!-- Form Group-->
                        <br>
                        <input type="submit" name="" class="btn btn-success btn-lg btn-block" value="SignUp">
                    </form>

                    <form action="/servlets/login.jsp" method="get">
                        <input type="submit" name="" class="btn btn-success btn-lg btn-block" value="Back to login page">
                    </form>
                </div>
                <div class="lock"><i class="fa fa-lock fa-3x"></i></div>
                <div class="label">Sign Up Form</div>
                <div class="label2"></div>

            </div>
        </div><!--col-md-4 col-md-offset-4-->
    </div><!-- row class-->
</div><!-- container class-->
    </div>
</div>
</body>
</html>


