<%--
  Created by IntelliJ IDEA.
  User: barazoulay
  Date: 05/12/2017
  Time: 1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/bootstrap/css/custom.css">
    <link rel="stylesheet" href="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css"/>
    <script src="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <title>Delete</title>
    <h1>Item have been deleted successfully</h1>
    <style>
        body {
            background-image: url("http://marckeil.de/wp-content/uploads/2015/08/To-Do-List-Resized.jpg");
            background-size: cover;
        }

    </style>

</head>
<body>
<div data-role="page">
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

<form action="/servlets/todolist.jsp" method="post">
    <button class="badge badge-secondary" type="submit">Back to Home-Page</button>
</form>
</div>
</body>
</html>
