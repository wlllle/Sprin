<%-- 
    Document   : subdivisionspage
    Created on : May 2, 2017, 2:40:58 PM
    Author     : slowen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subdivisions</title>
        <link rel="stylesheet" href="<c:url value="/main/resources/css/style.css" /> ">
    </head>
    
    <body>
        <table>
            <c:url var="indexUrl" value="/main/index" />
            <c:url var="addUrl" value="/main/subdivisions/add" />
            <tr>
                <td><a href="${indexUrl}">To Main Page</a></td>
                <td><a href="${addUrl}">Add Subdivision</a></td>
            </tr>
        </table>
        <h1>Subdivisions List</h1>

        <table>
            <thead>
                <tr><th>Subdivision</th></tr>
            </thead>
        </table>
        
        <c:if test="${!empty subdivisions}">
            <table>
                <tbody>
                    <c:forEach items="${subdivisions}" var="subdivision">
                        <c:url var="infoUrl" value="/main/subdivisions/subinfo?id=${subdivision.getID()}" />
                        <tr>
                            <td>
                                <a href="${infoUrl}">
                                    <c:out value="${subdivision.getName()}" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
 
        <c:if test="${empty subdivisions}">
            <table>
                <tbody>
                    <tr><td>There are currently no subdivisions in the list.</td></tr>
                </tbody>
            </table>
        </c:if>
 
    </body>
</html>
