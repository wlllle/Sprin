<%-- 
    Document   : addpospage
    Created on : May 25, 2017, 6:07:49 PM
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
        <title>Create Position</title>
    </head>
    <body>
        <h1>Create New Position</h1>
        <c:url var="processUrl" value="/main/subdivisions/addpos?id=${id}" />
        <form:form modelAttribute="positionAttribute" method="POST" action="${processUrl}">
            <table>
                <tr>
                    <td><form:label path="Name">Name:</form:label></td>
                    <td><form:input path="Name"/></td>
                    <td><form:errors path="Name" /></td>
                </tr>
                <tr>
                    <td><form:label path="Duties">Duties:</form:label></td>
                    <td><form:textarea path="Duties" cols="34" rows="5"/></td>
                    <td><form:errors path="Duties" /></td>
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