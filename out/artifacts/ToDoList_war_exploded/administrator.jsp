<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: barazoulay
  Date: 10/02/2018
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css"/>
    <script src="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <title>Admin</title>
    <h1>Admin panel</h1>
</head>
<body>
<div data-role="page">
    <div data-role="content">
        <table class="table">
            <thead>
            <tr>
                <th scope="col" style="text-align: center">Sessions Monitoring <br>active sessions: ${active}</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${sessions}" var="SessionListener">

                <tr>
                    <td>${SessionListener} </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
