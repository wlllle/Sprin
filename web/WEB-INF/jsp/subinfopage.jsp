<%-- 
    Document   : subinfo
    Created on : May 4, 2017, 7:07:10 PM
    Author     : slowen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:out value="${subdivision.getName()}" /></title>
        <link rel="stylesheet" href="<c:url value="/main/resources/css/style.css" /> ">
    </head>
    
    <body>
        <table>
            <c:url var="indexUrl" value="/main/index" />
            <c:url var="backUrl" value="/main/subdivisions" />
            <tr>
                <td><a href="${indexUrl}">To Main Page</a></td>
                <td><a href="${backUrl}">To Subdivisions List</a></td>
            </tr>
        </table>
        <c:if test="${headDivision == null}">
            <h1>Head Division Info</h1>
        </c:if>
        <c:if test="${headDivision != null}">
            <h1>Subdivision Info</h1>
        </c:if>
        <table>
            <c:url var="editUrl" value="/main/subdivisions/edit?id=${subdivision.getID()}" />
            <tr>
                <th><a href="${editUrl}">Edit Subdivision</a></th>
            </tr>
        </table>
        <table>
            <tr>
                <th>Name</th>
                <td><c:out value="${subdivision.getName()}" /></td>
            </tr>
            <tr>
                <th>Head Person</th>
                <td>
                    <c:url var="headPersonInfoUrl" value="/main/persons/personinfo?id=${subdivision.getHeadPerson().getID()}" />
                    <a href="${headPersonInfoUrl}">
                        <c:out value="${subdivision.getHeadPerson().getLastName()}" />
                        <c:out value="${subdivision.getHeadPerson().getFirstName()}" />
                        <c:out value="${subdivision.getHeadPerson().getMiddleName()}" />
                    </a>
                </td>
            </tr>
            <c:if test="${headDivision != null}">
                <tr>
                    <th>Head Division</th>
                    <td>
                        <c:url var="infoUrl" value="/main/subdivisions/subinfo?id=${headDivision.getID()}" />
                        <a href="${infoUrl}">
                            <c:out value="${headDivision.getName()}" />
                        </a>
                    </td>
                </tr>
            </c:if>
        </table>

        <br>

        <table><thead><tr><th>Subject Subdivisions</th></tr></thead></table>
        <c:if test="${!empty subdivisions}">
            <table>
                <tbody>
                    <c:forEach items="${subdivisions}" var="sub">
                        <c:url var="infoUrl" value="/main/subdivisions/subinfo?id=${sub.getID()}" />
                        <tr>
                            <td>
                                <a href="${infoUrl}">
                                    <c:out value="${sub.getName()}" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty subdivisions}">
            <table><tbody><tr><td>There is no subject subdivisions.</td></tr></tbody></table>
        </c:if>

        <br>

        <table><thead><tr><th>Current Staff</th></tr></thead></table>
        <c:if test="${!empty staff}">
            <table>
                <thead>
                    <tr>
                        <th>Person</th>
                        <th>Position</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${staff}" var="vacancy">
                        <tr>
                            <td>
                                <c:url var="personInfoUrl" value="/main/persons/personinfo?id=${vacancy.getPerson().getID()}" />
                                <a href="${personInfoUrl}">
                                    <c:out value="${vacancy.getPerson().getLastName()}" />
                                    <c:out value="${vacancy.getPerson().getFirstName()}" />
                                    <c:out value="${vacancy.getPerson().getMiddleName()}" />
                                </a>
                            </td>
                            <td>
                                <c:url var="editPHUrl" value="/main/subdivisions/editposhis?subid=${subdivision.getID()}&phid=${vacancy.getID()}" />
                                <a href="${editPHUrl}">
                                    <c:out value="${vacancy.getSubdivisionsPositions().getPosition().getName()}" />
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty staff}">
            <table><tbody><tr><td>There is no staff.</td></tr></tbody></table>
        </c:if>

        <br>
              
        <table><thead><tr><th>Free Vacancies</th></tr></thead></table>
        <table>
            <tbody>
                <tr>
                    <th>
                        <c:url var="addVacancyUrl" value="/main/subdivisions/choosepos?id=${subdivision.getID()}" />
                        <a href="${addVacancyUrl}">
                            <c:out value="Add Vacancy" />
                        </a>
                    </th>
                </tr>
            </tbody>
        </table>
        <c:if test="${!empty freeVacancies}">
            <table>
                <thead>
                    <tr>
                        <th>Position</th>
                        <th>Number Of Positions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${freeVacancies}" var="vacancy">
                        <tr>
                            <td>
                                <c:url var="hireUrl" value="/main/subdivisions/addposhis?spid=${vacancy.getID()}" />
                                <a href="${hireUrl}">
                                    <c:out value="${vacancy.getPosition().getName()}" />
                                </a>
                            </td>
                            <td>
                                <c:out value="${vacancy.getNumberOfPositions()}" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>