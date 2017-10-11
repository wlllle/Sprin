<%-- 
    Document   : addvacancypage
    Created on : May 25, 2017, 5:55:53 PM
    Author     : slowen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="Add Vacancy" /></title>
        <link rel="stylesheet" href="<c:url value="/main/resources/css/style.css" /> ">
    </head>
    
    <body>
        <table>
            <c:url var="indexUrl" value="/main/index" />
            <c:url var="backUrl" value="/main/subdivisions/subinfo?id=${id}" />
            <c:url var="addUrl" value="/main/subdivisions/addpos?id=${id}" />
            <tr>
                <td><a href="${backUrl}">To Subdivision</a></td>
                <td><a href="${addUrl}">Add Position</a></td>
            </tr>
        </table>
        <h1>Add Vacancy</h1>
        <c:if test="${!empty positions}">
            <table>
                <tbody>
                    <c:forEach items="${positions}" var="position">
                        <c:url var="choiceUrl" value="/main/subdivisions/choosenum?subid=${id}&posid=${position.getID()}" />
                        <tr>
                            <td>
                                <a href="${choiceUrl}">
                                    <c:out value="${position.getName()}" />
                                </a>
                            </td>
                            <td>
                                <c:out value="${position.getDuties()}" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
 
        <c:if test="${empty positions}">
            <table>
                <tbody>
                    <tr><td>There are currently no positions in the list. <a href="${addUrl}">Add Position</a></td></tr>
                </tbody>
            </table>
        </c:if>
    </body>
</html>