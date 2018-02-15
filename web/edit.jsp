<%--
  Created by IntelliJ IDEA.
  User: barazoulay
  Date: 04/12/2017
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="/bootstrap/css/create.css">
    <link rel="stylesheet" href="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <script src="/bootstrap/js/moment.js"></script>
    <script src="/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap-datetimepicker-standalone.css">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap-datetimepicker.min.css">
    <title>Edit</title>
</head>
<body>
<div data-role="page">
    <div data-role="header">
        <%

            String user = null;
            if (session.getAttribute("username") == null) {
                response.sendRedirect("/servlets/login.jsp");
            } else user = (String) session.getAttribute("username");
            String userName = null;
            String sessionID = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username")) userName = cookie.getValue();
                    if (cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
                }
            }

        %>
    </div>
    <div data-role="content">
        <div id="fullscreen_bg" class="fullscreen_bg">
            <div class="container">
                <div class="row">
                    ${message}
                    <div class="panel panel-default" style="margin: auto">
                        <div class="panel-body">
                            <form action="/servlets/edit.jsp" method="post" class="form-signin">

                                <H3 class="text-center">Edit Item</H3>


                                <div class="form-group">
                                    <div class="input-group">
        <span class="input-group-addon"><span class="glyphicon-pencil"></span>
        </span>
                                        <input type="text" class="form-control" name="title" placeholder="${title}"
                                               value="${title}" required>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="input-group">
        <span class="input-group-addon"><span class="glyphicon-pencil"></span>
        </span>
                                        <input type="text" class="form-control" name="description" placeholder="${desc}"
                                               value="${desc}" required>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="input-group">
        <span class="input-group-addon"><span class="glyphicon-pencil"></span>
        </span>
                                        <select name="levelofimportance" class="form-control" required>
                                            <option value="0">Less Important</option>
                                            <option value="1">Important</option>
                                            <option value="2">Very Important</option>
                                        </select>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class='input-group date' id='datetimepicker11'>
                                        <input type='text' class="form-control" name="date" placeholder="${date}"
                                               value="${date}" required/>
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


                                <div class="form-group">
                                    <div class="input-group">
        <span class="input-group-addon"><span class="glyphicon-pencil"></span>
        </span>
                                        <select name="status" class="form-control">
                                            <option value="pending">Not finish yet</option>
                                            <option value="completed">Finish</option>
                                        </select>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label for="element-4">&nbsp;</label>
                                    <input type="submit" id="element-4" value="Update item"
                                           class="btn btn-sm btn-primary btn-block">
                                </div>


                            </form>
                            <form action="/servlets/todolist.jsp" method="post">
                                <button class="badge badge-secondary" type="submit">Back to Home-Page</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
