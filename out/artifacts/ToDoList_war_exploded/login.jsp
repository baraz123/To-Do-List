<%@ page contentType="text/html;charset=UTF-8"  language="java" import="java.util.*" %>
<html>
<head>
    <meta http-equiv="Content-Type" charset=UTF-8">
    <link rel ="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/bootstrap/css/custom.css">
    <link rel="stylesheet" href="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css"/>
    <script src="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>


    <title>Login</title>

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
                <h4>Sign in</h4><hr>
                <div class="panel-body">
                    ${messageW}
                    <br>
                    <form action="/servlets/authentication.jsp" method="post">
                        <div class="form-group">
                               <%-- <label><b>Username</b></label>--%>
                                <input type="text" placeholder="Enter Username..." name="username" required>
                        </div><!-- Form Group-->
                               <%-- <label><b>Password</b></label>--%>
                        <div class="form-group">
                                <input type="password" placeholder="Enter Password..." name="password" required>
                        </div><!-- Form Group-->
                      <%--  <div class="checkbox">
                            <label><input type="checkbox" name="remember"> Remember me</label>
                        </div>--%>
                                <input type="submit" name="" class="btn btn-success btn-lg btn-block" value="Login">
                           </form>

                    <form action="/servlets/Register.jsp" method="get">
                        <input data-role="button" type="submit" name="" class="btn btn-success btn-lg btn-block" value="Register" style="color: red">
                    </form>
                        </div> <%--panel-body--%>
                        <div class="lock"><i class="fa fa-lock fa-3x" style="padding: 20px"></i></div>
                        <div class="label">Login Form</div>
                    <div class="label2"></div>

                  </div><!--panel panel-default-->
                </div><!--col-md-4 col-md-offset-4-->
            </div><!--row-->
</div><!--container-->
    </div><!--content-->
</div><!--page-->
</body>
</html>
