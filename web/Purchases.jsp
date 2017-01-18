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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <title>Purchases</title>
    </head>
    <body class="container">
        <div class="panel panel-primary">
            <div class="panel-heading"><h3 class="text-center"><span class="glyphicon glyphicon-th text-right"></span>&nbsp;Member Purchases</h3></div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-6">
                        <h4>Member ID: ${m.memberId} </h4>
                        <h4>Member Name: ${m.firstName} ${m.lastName}</h4>
                    </div>
                    <div class="col-sm-6">
                        <h4>${msg}</h4>
                        <h4>Current Balance: ${bal}</h4>
                    </div>
                </div>
               
            </div>
            <table class="table table-striped">
                <tr>
                    <th class="text-left">Purchase Date</th>
                    <th class="text-left">Purchase Type</th>
                    <th class="text-left">Trans Code</th>
                    <th class="text-left">Trans. Description</th>
                    <th class="text-left">Amount</th>
                </tr>
                <c:forEach var="p" items="${pur}">
                    <tr>
                        <td class="text-left">${p.purchaseDate}</td>
                        <td class="text-left">${p.purchaseType}</td>
                        <td class="text-left">${p.transactionCode}</td>
                        <td class="text-left">${p.transactionDescription}</td>
                        <td class="text-left">${p.amount}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div>
            <h5>
                <a href="MemberScreen.jsp"><span class="glyphicon glyphicon-chevron-left"></span>Back to Member Screen</a>
            </h5>
            <br>
            <br>
            <br>
        </div>
    </body>
</html>
