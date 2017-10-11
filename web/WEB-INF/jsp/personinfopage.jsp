<%-- 
    Document   : personinfo
    Created on : May 10, 2017, 1:35:19 PM
    Author     : slowen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${person.getLastName()} ${person.getFirstName()}" /></title>
        <link rel="stylesheet" href="<c:url value="/main/resources/css/style.css" /> ">
    </head>
    
    <body>
        <table>
            <c:url var="indexUrl" value="/main/index" />
            <c:url var="backUrl" value="/main/persons" />
            <tr>
                <td><a href="${indexUrl}">To Main Page</a></td>
                <td><a href="${backUrl}">To Persons List</a></td>
            </tr>
        </table>
        <h1>Person Info</h1>
        <table>
            <c:url var="editUrl" value="/main/persons/edit?id=${person.getID()}" />
            <tr>
                <th><a href="${editUrl}">Edit Person</a></th>
            </tr>
        </table>
        <table>
            <tr>
                <th>Full Name</th>
                <td><c:out value="${person.getLastName()} ${person.getFirstName()} ${person.getMiddleName()}" /></td>
            </tr>
            <tr>
                <th>Home Address</th>
                <td>
                    <c:out value="${person.getHomeAdress()}" />
                </td>
            </tr>
            <tr>
                <th>Education</th>
                <td>
                    <c:out value="${person.getEducation()}" />
                </td>
            </tr>
            <tr>
                <th>Recruitment Date</th>
                <td>
                    <c:out value="${person.getRecruitmentDate()}" />
                </td>
            </tr>
        </table>
        <c:if test="${!empty managedSubs}">
            <table><thead><tr><th>Head of subdivisions</th></tr></thead></table>
            <table>
                <tbody>
                    <c:forEach items="${managedSubs}" var="sub">
                        <c:url var="managedSubInfoUrl" value="/main/subdivisions/subinfo?id=${sub.getID()}" />
                        <tr>
                            <td>
                                <a href="${managedSubInfoUrl}">
                                    <c:out value="${sub.getName()}" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${!empty curPos}">
            <table><thead><tr><th>Current Positions</th></tr></thead></table>
            <table>
                <thead>
                    <tr>
                        <th>Subdivision</th>
                        <th>Position</th>
                        <th>Approintment Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${curPos}" var="vacancy">
                        <tr>
                            <td>
                                <c:url var="curSubInfoUrl"
                                       value="/main/subdivisions/subinfo?id=${vacancy.getSubdivisionsPositions().getSubdivision().getID()}" />
                                <a href="${curSubInfoUrl}">
                                    <c:out value="${vacancy.getSubdivisionsPositions().getSubdivision().getName()}" />
                                </a>
                            </td>
                            <td>
                                <c:out value="${vacancy.getSubdivisionsPositions().getPosition().getName()}" />
                            </td>
                            <td>
                                <c:out value="${vacancy.getApprointmentDate()}" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${!empty prevPos}">
            <table><thead><tr><th>Positions History</th></tr></thead></table>
            <table>
                <thead>
                    <tr>
                        <th>Subdivision</th>
                        <th>Position</th>
                        <th>Appointment Date</th>
                        <th>Ouster Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${prevPos}" var="vacancy">
                        <tr>
                            <td>
                                <c:url var="prevSubInfoUrl"
                                       value="/main/subdivisions/subinfo?id=${vacancy.getSubdivisionsPositions().getSubdivision().getID()}" />
                                <a href="${prevSubInfoUrl}">
                                    <c:out value="${vacancy.getSubdivisionsPositions().getSubdivision().getName()}" />
                                </a>
                            </td>
                            <td>
                                <c:out value="${vacancy.getSubdivisionsPositions().getPosition().getName()}" />
                            </td>
                            <td>
                                <c:out value="${vacancy.getApprointmentDate()}" />
                            </td>
                            <td>
                                <c:out value="${vacancy.getOusterDate()}" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>