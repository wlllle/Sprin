<%-- 
    Document   : mainpage
    Created on : May 6, 2017, 3:17:52 PM
    Author     : slowen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Main Page</title>
        <link rel="stylesheet" href="<c:url value="/main/resources/css/style.css" /> ">
    </head>
    
    <body>
        <table>
            <c:url var="subdivisionsUrl" value="/main/subdivisions" />
            <c:url var="personsUrl" value="/main/persons" />
            <tr>
                <td><a href="${subdivisionsUrl}">Subdivisions List</a></td>
                <td><a href="${personsUrl}">Persons List</a></td>
            </tr>
        </table>
        <h1>Welcome to the Firm Database</h1>
        <p align="center">Use menu above this text for access to the database.</p>
    </body>
</html>
