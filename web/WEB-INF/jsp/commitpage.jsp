<%-- 
    Document   : corridorpage
    Created on : May 9, 2017, 4:43:49 PM
    Author     : slowen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Commit</title>
    </head>
    <body>

        <h1>Commit</h1>
        <p>Transaction is done.</p>

        <%--Redirect to subdivisions pages--%>
        <c:set var="str" scope = "session" value = "subdivisions"/>
        <c:if test="${redirect == str}">
            <c:url var="redirectUrl" value="/main/subdivisions" />
            <p><a href="${redirectUrl}">OK.</a></p>
        </c:if>
        <c:set var="str" scope = "session" value = "subinfo"/>
        <c:if test="${redirect == str}">
            <c:url var="redirectUrl" value="/main/subdivisions/subinfo?id=${id}" />
            <p><a href="${redirectUrl}">OK.</a></p>
        </c:if>
        <c:set var="str" scope = "session" value = "choosepos"/>
        <c:if test="${redirect == str}">
            <c:url var="redirectUrl" value="/main/subdivisions/choosepos?id=${id}" />
            <p><a href="${redirectUrl}">OK.</a></p>
        </c:if>
        <c:set var="str" scope = "session" value = "choosenum"/>
        <c:if test="${redirect == str}">
            <c:url var="redirectUrl" value="/main/subdivisions/choosenum?subid=${id}&posid=${posid}" />
            <p><a href="${redirectUrl}">OK.</a></p>
        </c:if>

        <%--Redirect to persons pages--%>
        <c:set var="str" scope = "session" value = "persons"/>
        <c:if test="${redirect == str}">
            <c:url var="redirectUrl" value="/main/persons" />
            <p><a href="${redirectUrl}">OK.</a></p>
        </c:if>
        <c:set var="str" scope = "session" value = "personinfo"/>
        <c:if test="${redirect == str}">
            <c:url var="redirectUrl" value="/main/persons/personinfo?id=${id}" />
            <p><a href="${redirectUrl}">OK.</a></p>
        </c:if>
    </body>
</html>