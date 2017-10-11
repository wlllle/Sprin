<%-- 
    Document   : editposhispage
    Created on : May 27, 2017, 9:11:36 PM
    Author     : slowen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dismiss from Position</title>
    </head>
    <body>
        <h1>Dismiss from Position</h1>
        <c:url var="processUrl" value="/main/subdivisions/editposhis?subid=${subid}&phid=${phid}" />
        <form:form modelAttribute="positionsHistoryAttribute" method="POST" action="${processUrl}">
            <table>
                <tr>
                    <td><form:label path="OusterDate">Ouster Date:</form:label></td>
                    <td><form:input path="OusterDate"/></td>
                    <td><form:errors path="OusterDate" /></td>
                </tr>
            </table>
            <input type="submit" name="action" value="Save" />
            <c:if test="${discard == false}">
                <input type="submit" name="action" value="Discard" />
            </c:if>
            <c:if test="${discard == true}">
                <br> <br>
                <c:out value="Are you sure? All changes will be lost." />
                <br> <br>
                <input type="submit" name="action" value="Yes, Discard" />
                <input type="submit" name="action" value="No, Cancel" />
            </c:if>
        </form:form>
    </body>
</html>