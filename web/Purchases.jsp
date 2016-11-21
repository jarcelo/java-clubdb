<%-- 
    Document   : Purchases
    Created on : Nov 20, 2016, 10:43:27 AM
    Author     : josepharcelo
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchases</title>
    </head>
    <body>
        <h1>Member Purchases</h1>
        <h2>${m.memid}</h2>
        <h2>${m.firstnm} ${m.lastnm}</h2>
        
        <table>
            <tr>
                <th>Purchase Dt</th>
                <th>Purchase Type</th>
                <th>Trans. Cd</th>
                <th>Trans. Desc</th>
                <th>Amount</th>
            </tr>
            <c:forEach var="p" items="${pur}">
                <tr>
                    <td>${p.purchdt}</td>
                    <td>${p.purchtype}</td>
                    <td>${p.transcd}</td>
                    <td>${p.transdesc}</td>
                    <td>${p.amt}</td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <p>${msg}</p>
        </div>
        <a href="MemberScreen.jsp">Back to Member Screen</a>
    </body>
</html>
