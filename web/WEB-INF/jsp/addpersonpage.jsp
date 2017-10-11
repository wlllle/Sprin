<%-- 
    Document   : addpersonpage
    Created on : May 10, 2017, 1:47:56 PM
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
        <title>Create Person</title>
    </head>
    <body>
        <h1>Create New Person</h1>
        <c:url var="processUrl" value="/main/persons/add" />
        <form:form modelAttribute="personAttribute" method="POST" action="${processUrl}">
            <table>
                <tr>
                    <td><form:label path="FirstName">First Name:</form:label></td>
                    <td><form:input path="FirstName"/></td>
                    <td><form:errors path="FirstName" /></td>
                </tr>

                <tr>
                    <td><form:label path="MiddleName">Middle Name:</form:label></td>
                    <td><form:input path="MiddleName"/></td>
                    <td><form:errors path="MiddleName" /></td>
                </tr>

                <tr>
                    <td><form:label path="LastName">Last Name:</form:label></td>
                    <td><form:input path="LastName"/></td>
                    <td><form:errors path="LastName" /></td>
                </tr>

                <tr>
                    <td><form:label path="HomeAdress">Home Address:</form:label></td>
                    <td><form:input path="HomeAdress"/></td>
                    <td><form:errors path="HomeAdress" /></td>
                </tr>

                <tr>
                    <td><form:label path="Education">Education:</form:label></td>
                    <td><form:input path="Education"/></td>
                    <td><form:errors path="Education" /></td>
                </tr>

                <tr>
                    <td><form:label path="RecruitmentDate">Recruitment Date:</form:label></td>
                    <td><form:input path="RecruitmentDate"/></td>
                    <td><form:errors path="RecruitmentDate" /></td>
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