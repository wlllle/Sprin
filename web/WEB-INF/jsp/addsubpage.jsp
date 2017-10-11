<%-- 
    Document   : addsubpage
    Created on : May 6, 2017, 8:14:47 PM
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
        <title>Create Subdivision</title>
    </head>
    <body>
        <h1>Create New Subdivision</h1>
        <c:url var="processUrl" value="/main/subdivisions/add" />
        <form:form modelAttribute="subdivisionAttribute" method="POST" action="${processUrl}">
            <table>
                <tr>
                    <td><form:label path="Name">Name:</form:label></td>
                    <td><form:input path="Name"/></td>
                    <td><form:errors path="Name" /></td>
                </tr>

                <tr>
                    <td><form:label path="HeadPerson">Head Person:</form:label></td>
                    <td><form:select path="HeadPerson">
                            <c:forEach items="${persons}" var="person">
                                <form:option value="${Integer.toString(person.getID())}" label=
                                "${person.getLastName()} ${person.getFirstName()} ${person.getMiddleName()}" />
                            </c:forEach>
                       </form:select>
                    </td>
                    <td><form:errors path="HeadPerson" /></td>
                </tr>
                
                <tr>
                    <td><form:label path="HeadDivisionID">Head Division:</form:label></td>
                    <td><form:select path="HeadDivisionID">
                            <form:option value="${null}" label="Null" />
                            <c:forEach items="${subdivisions}" var="sub">
                                <form:option value="${sub.getID()}" label= "${sub.getName()}" />
                            </c:forEach>
                       </form:select>
                    </td>
                    <td><form:errors path="HeadDivisionID" /></td>
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