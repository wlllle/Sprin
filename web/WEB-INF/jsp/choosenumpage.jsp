<%-- 
    Document   : choosenumpage
    Created on : May 25, 2017, 6:35:12 PM
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
        <title>Choose Number of Positions</title>
    </head>
    <body>
        <c:url var="processUrl" value="/main/subdivisions/choosenum?subid=${subid}&posid=${posid}" />
        <form:form modelAttribute="subposAttribute" method="POST" action="${processUrl}">
            <table>
                <tr>
                    <td><form:label path="NumberOfPositions">Number of Positions:</form:label></td>
                    <td><form:input path="NumberOfPositions"/></td>
                    <td><form:errors path="NumberOfPositions" /></td>
                </tr>
            </table>
            <input type="submit" name="action" value="Save" />
            <c:if test="${discard == false}">
                <input type="submit" name="action" value="Discard" />
            </c:if>
            <c:if test="${discard == true}">
                <c:out value="Sure?" />
                <input type="submit" name="action" value="Yes, Discard" />
                <input type="submit" name="action" value="No, Cancel" />
            </c:if>
        </form:form>
    </body>
</html>