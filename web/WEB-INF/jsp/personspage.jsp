<%-- 
    Document   : newjsf
    Created on : May 10, 2017, 1:29:33 PM
    Author     : slowen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Persons</title>
        <link rel="stylesheet" href="<c:url value="/main/resources/css/style.css" /> ">
    </head>
    
    <body>
        <table>
            <c:url var="indexUrl" value="/main/index" />
            <c:url var="addUrl" value="/main/persons/add" />
            <tr>
                <td><a href="${indexUrl}">To Main Page</a></td>
                <td><a href="${addUrl}">Add Person</a></td>
            </tr>
        </table>
        <h1>Persons List</h1>

        <table>
            <thead>
                <tr><th>Person</th></tr>
            </thead>
        </table>

        <c:if test="${!empty persons}">
            <table>
                <tbody>
                    <c:forEach items="${persons}" var="person">
                        <c:url var="infoUrl" value="/main/persons/personinfo?id=${person.getID()}" />
                        <tr>
                            <td>
                                <a href="${infoUrl}">
                                    <c:out value="${person.getLastName()} ${person.getFirstName()} ${person.getMiddleName()}" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
 
        <c:if test="${empty persons}">
            <table>
                <tbody>
                    <tr><td>There are currently no persons in the list.</td></tr>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
