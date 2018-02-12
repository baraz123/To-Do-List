<%--
  Created by IntelliJ IDEA.
  User: barazoulay
  Date: 02/12/2017
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel ="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="/bootstrap/css/create.css">
    <link rel="stylesheet" href="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="/bootstrap/js/moment.js"></script>
    <script src="/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap-datetimepicker-standalone.css">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap-datetimepicker.min.css">

    <title>Create new Item</title>

</head>
<body>
<div data-role="page">
    <div data-role="header">
<%

    String user = null;
    if(session.getAttribute("username") == null){
        response.sendRedirect("/servlets/login.jsp");
    }else user = (String) session.getAttribute("username");
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("username")) userName = cookie.getValue();
            if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
    }

%>
    </div>
    <div data-role="content">
<div id="fullscreen_bg" class="fullscreen_bg">
    <div class="container">
        <div class="row">
<form action="/servlets/create.jsp" method="post" class="form-signin">

        <div class="panel panel-default">
            <div class="panel-body">
                <H3 class="text-center">Adding New Item</H3>
${message}
<form action="/servlets/create.jsp" method="post" class="form form-signup" role="form">

    <div class="form-group">
        <div class="input-group">
        <span class="input-group-addon"><span class="glyphicon-pencil"></span>
        </span>
        <input type="text" class="form-control" name="title" placeholder="Title">
    </div>
    </div>


    <div class="form-group">
        <div class="input-group">
        <span class="input-group-addon"><span class="glyphicon-pencil"></span>
        </span>
            <input type="text" class="form-control" name="description" placeholder="description">
        </div>
    </div>




    <div class="form-group">
        <div class="input-group">
        <span class="input-group-addon"><span class="glyphicon-pencil"></span>
        </span>
            <select name="levelofimportance" class="form-control">
                <option  value="0">Less Important</option>
                <option  value="1">Important</option>
                <option  value="2">Very Important</option>
            </select>
        </div>
    </div>

   <%-- <div class="form-group">
        <div class="input-group">
        <span class="input-group-addon"><span class="glyphicon-pencil"></span>
        </span>
            <input type="text" class="form-control" name="date" placeholder="Date">
        </div>
    </div>--%>

<%--
    <script src="<%=request.getContextPath()%>/bootstraps"></script>
--%>
            <div class="form-group">
                <div class='input-group date' id='datetimepicker11'>
                    <input type='text' class="form-control" name="date"/>
                    <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar">
                    </span>
                </span>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker11').datetimepicker({
                    daysOfWeekDisabled: [0, 6]
                });
            });
        </script>


<%--
    <script src="<%=request.getContextPath()%>bootstrap/js/bootstrap-datetimepicker.js"></script>
--%>



    <div class="form-group">
        <label for="element-4">&nbsp;</label>
        <input type="submit" id="element-4" value="addItem" class="btn btn-sm btn-primary btn-block">
    </div>

</form>
                <form action="/servlets/logout.jsp" method="get">

                    <button type="submit">Logout</button>
                </form>

                <form action="/servlets/todolist.jsp" method="post">
                    <button class="badge badge-secondary" type="submit">Back to Home-Page</button>
                </form>
            </div><!--Panel body-->
        </div><!--Default-->
</form>
        </div><!--row-->
    </div>
</div>
    </div>
</div>
</body>
</html>
