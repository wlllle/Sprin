<%-- 
    Document   : rollbackpage
    Created on : May 9, 2017, 6:47:56 PM
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

        <h1>Rollback</h1>
        <p>Transaction is cancelled.</p>

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
        <c:set var="str" scope = "session" value = "subinforeject"/>
        <c:if test="${redirect == str}">
            <p>This subdivision has child subdivision!</p>
            <c:url var="redirectUrl" value="/main/subdivisions/subinfo?id=${id}" />
            <p><a href="${redirectUrl}">OK.</a></p>
        </c:if>
        <c:set var="str" scope = "session" value = "choosepos"/>
        <c:if test="${redirect == str}">
            <c:url var="redirectUrl" value="/main/subdivisions/choosepos?id=${id}" />
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
        <c:set var="str" scope = "session" value = "personinforeject"/>
        <c:if test="${redirect == str}">
            <p>This person is head of subdivision!</p>
            <c:url var="redirectUrl" value="/main/persons/personinfo?id=${id}" />
            <p><a href="${redirectUrl}">OK.</a></p>
        </c:if>
    </body>
</html>
