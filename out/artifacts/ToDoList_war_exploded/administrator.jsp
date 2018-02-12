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
    <link rel ="stylesheet" href="/bootstrap/css/bootstrap.min.css">
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



    <c:forEach items = "${sessions}" var="SessionListener">

        <tr>
            <td>${SessionListener} </td>
        <%--  <td>
                <a  class="btn btn-info create" href="/servlets/edit.jsp?id=${Item.ID}&title=${Item.title}&desc=${Item.description}&imp=${Item.levelOfImp}&date=${Item.date} ">Edit</a>
                <a  class="btn btn-info create" href="/servlets/delete.jsp?id=${Item.ID}">Delete</a>
            </td>--%>
        </tr>
    </c:forEach>



<%--    <tr>
        <td>${SESSIONID}</td>
        <td>${INTERVAL}</td>
        <td>${ACTIVE}</td>
    </tr>--%>

    </tbody>
</table>


<%--
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>--%>
    </div>
</div>
</body>
</html>
