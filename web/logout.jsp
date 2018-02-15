<%--
  Created by IntelliJ IDEA.
  User: barazoulay
  Date: 02/12/2017
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>logout Page</title>
</head>
<body>
<%
    String user = null;
    ServletContext app = session.getServletContext();
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
    session.removeAttribute("username");
    session.removeAttribute("password");
    session.invalidate();
    app.removeAttribute("username");
    app.removeAttribute("password");


%>

<h1>You have been successfully logged out</h1>
To login again <a href="/servlets/login.jsp">click here</a>.
</body>
</html>