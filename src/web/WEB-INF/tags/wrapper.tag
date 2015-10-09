<%-- 
    Document   : wrapper
    Created on : 08-10-2015, 09:08:44
    Author     : AlexanderSteen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@attribute name="message"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stuff</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="script/PersonScipt.js"></script>
    </head>
    <body>
        <div class="container">
            <ul class="nav nav-tabs">
                <li role="presentation"><a href="index.jsp">Home</a></li>
                <li role="presentation"><a href="person.jsp">Person</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Documentaton<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="documentation.jsp">API Help</a></li>
                        <li><a href="github.jsp">GITHub links</a></li>
                    </ul>
                </li>
            </ul>
            <jsp:doBody />
        </div>
    </body>
</html>