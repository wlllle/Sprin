<%-- 
    Document   : editsubpage
    Created on : May 9, 2017, 4:08:25 PM
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
        <title>Edit Subdivision</title>
    </head>
    <body>
        <h1>Edit Subdivision</h1>
        <c:url var="processUrl" value="/main/subdivisions/edit?id=${subdivisionAttribute.getID()}" />
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
                                <c:if test="${sub.getID() != subdivisionAttribute.getID()}">
                                    <form:option value="${sub.getID()}" label= "${sub.getName()}" />
                                </c:if>
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
            <c:if test="${delete == false}">
                <input type="submit" name="action" value="Delete" />
            </c:if>
            <c:if test="${discard == true}">
                <br> <br>
                <c:out value="Are you sure? All changes will be lost." />
                <br> <br>
                <input type="submit" name="action" value="Yes, Discard" />
                <input type="submit" name="action" value="No, Cancel" />
            </c:if>
            <c:if test="${delete == true}">
                <br> <br>
                <c:out value="Are you sure? It will delete all related information." />
                <br> <br>
                <input type="submit" name="action" value="Yes, Delete" />
                <input type="submit" name="action" value="No, Cancel" />
            </c:if>
        </form:form>
    </body>
</html>