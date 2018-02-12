<%@ page import="java.util.List" %>
<%@ page import="com.hit.model.Item" %>
<%@ page import="com.hit.model.SessionsListener" %>

<link rel ="stylesheet" href="/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/bootstrap/css/custom.css">
<script src="/bootstrap/js/bootstrap.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.css" />
<script src="/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"></script>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url=exception.jsp?type=timeout" />
    <c:choose>
        <c:when test="${param.type == 'timeout'}">
            <p>Your session has been timed out.</p>
        </c:when>
        <c:otherwise>
            <!-- Handle default case here. -->
        </c:otherwise>
    </c:choose>
    <style>

        body{
            background-image: url("http://marckeil.de/wp-content/uploads/2015/08/To-Do-List-Resized.jpg");
            background-size: cover;
        }
        h1{ color: #7c4736;
            font-family: 'Trocchi', serif;
            font-size: 70px;
            font-weight: normal;
            line-height: 48px;
            margin: 20px;
            text-align: center;
        }

        table,th,td{
            padding: 34rem;
            width: 80%;
            margin:auto;
            border: 1px solid white;
            border-collapse: collapse;
            text-align: center;
            font-size: 30px;
            background: black;
            opacity: 0.7;
            color: white;
            margin-top: 100px;

        }
        .create
        {
            text-decoration:none;
            border-radius: 20%;
            /*width: 80%;*/
            margin:auto;
            border: 1px solid white;
            border-collapse: collapse;
            text-align: center;
            font-size: 24px;
            table-layout: fixed;
           /* background: red;
            opacity: 0.7;
            color: white;*/
           /* margin-top: 100px;*/
        }
        .createC{
            text-decoration:none;
            width: 80%;
            margin:auto;
            border: 1px solid white;
            border-collapse: collapse;
            text-align: center;
            font-size: 24px;
            table-layout: fixed;
            background: red;
            opacity: 0.7;
            color: white;
            margin-top: 100px;
        }
        .Chead{

            font-family: "Times New Roman", Times, serif;
            font-style: italic;
            border-style: solid;
            background-color: #4bf5ff;
        }
        .id
        {
            display: none;
        }
        input[type=text] {
            width: 130px;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: white;
           /* background-image: url('searchicon.png');*/
            background-position: 10px 10px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            -webkit-transition: width 0.4s ease-in-out;
            transition: width 0.4s ease-in-out;
        }


         #myBtn {
             display: none;
             position: fixed;
             bottom: 20px;
             right: 30px;
             z-index: 99;
             border: none;
             outline: none;
             background-color: red;
             color: white;
             cursor: pointer;
             padding: 15px;
             border-radius: 10px;
         }

        #myBtn:hover {
            background-color: #555;
        }
        dropbtn {
            color: white;
            padding: 16px;
            font-size: 16px;
            border: none;
            cursor: pointer;
            background-color: #4bf5ff;
        }

        .dropbtn:hover, .dropbtn:focus {
            background-color: #4bf5ff;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            opacity: 0.7;
            display: none;
            position: absolute;
            background-color: #4bf5ff;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 11px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown a:hover {background-color: #f1f1f1}

        .show {display:block;
            left: 81px;
            top:0px;}


    </style>

    <title>To Do List</title>
</head>
<body>
<div data-role="page">

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
<div data-role="header">
<h1>To Do List</h1>
</div>
<div data-role="content">
<div class="container">
    <button onclick="topFunction()" id="myBtn" title="Go to top">Top</button>

    <div>
<form>
    <input type="text" name="search" placeholder="Search..">
</form>
</div>

    <form action="/servlets/logout.jsp" method="get">
        <button type="submit">Logout</button>
    </form>

    <div class="dropdown">
        <button onclick="myFunction()" class="dropbtn btn btn-secondary dropdown-toggle">Tasks</button>
        <div id="myDropdown" class="dropdown-content">
            <a href="todolist.jsp?param1=pending">Pending tasks</a>
            <a href="todolist.jsp?param1=completed">Finished tasks</a>
            <a href="todolist.jsp">All tasks</a>
        </div>
    </div>

    <script>
        /* When the user clicks on the button,
        toggle between hiding and showing the dropdown content */
        function myFunction() {
            document.getElementById("myDropdown").classList.toggle("show");
        }

        // Close the dropdown if the user clicks outside of it
        window.onclick = function(event) {
            if (!event.target.matches('.dropbtn')) {

                var dropdowns = document.getElementsByClassName("dropdown-content");
                var i;
                for (i = 0; i < dropdowns.length; i++) {
                    var openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                        openDropdown.classList.remove('show');
                    }
                }
            }
        }
    </script>

    <div style="width: 100%; overflow-x: auto">
    <table data-role="table" class="table table-dark">

        <tr class="Chead">
            <td class="id">Id</td>
            <td>Title</td>
            <th>Description</th>
            <th>Importance</th>
            <th>Date</th>
            <th>Status</th>
            <th><a class ="btn btn-warning btn-lg" href = "/create.jsp">Create new item</a></th>

        </tr>
        <c:forEach items= "${items}" var="Item">

            <tr>
                <td class="id" >${Item.ID} </td>
                <td>${Item.title} </td>
                <td>${Item.description}</td>
                <td>${Item.levelOfImp}</td>
                <td>${Item.date}</td>
                <td>${Item.status}</td>

                <td>
                    <a  class="btn btn-info create" href="/servlets/edit.jsp?id=${Item.ID}&title=${Item.title}&desc=${Item.description}&imp=${Item.levelOfImp}&date=${Item.date} ">Edit</a>
                    <a  class="btn btn-info create" href="/servlets/delete.jsp?id=${Item.ID}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </div>
    </div>


<script>
    // When the user scrolls down 20px from the top of the document, show the button
    window.onscroll = function() {scrollFunction()};

    function scrollFunction() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            document.getElementById("myBtn").style.display = "block";
        } else {
            document.getElementById("myBtn").style.display = "none";
        }
    }

    // When the user clicks on the button, scroll to the top of the document
    function topFunction() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
    }


</script>
</div>
</div>
</body>
</html>
