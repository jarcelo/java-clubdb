<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
 <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Welcome</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://use.fontawesome.com/ea6070bed7.js"></script>
        <style>
            table.member-details{
                border-collapse: collapse;
            }
            table.member-details td, table.member-details th{
                padding: 6px;
                border: 1px solid #999;
            }
        </style>
    </head>
    <c:if test="${!m.authenticated}">
        <script type="text/javascript">
            window.location = "/ClubDB_Arcelo";
        </script>
    </c:if>
    <c:if test="${m.authenticated}">
        <body class="container">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="text-center">&nbsp;Club Member Data</h1>
                </div>
                <br>
                <br>
                <div class="row">
                    <div class="col-sm-6">
                        <form id="memupdate" action="MemberUpdate" method="post" class="form-horizontal">
                            <div class="form-group">
                                <label for="memberId" class="col-sm-4 control-label">Member ID</label>
                                <div class="col-sm-8">
                                  <input class="form-control" type="text" id="memberId" name="memberId"
                                           value="${m.memberId}" readonly="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="col-sm-4 control-label">Last Name</label>
                                <div class="col-sm-8">
                                  <input class="form-control" type="text" id="lastName" name="lastName"
                                           value="${m.lastName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="firstName" class="col-sm-4 control-label">First Name</label>
                                <div class="col-sm-8">
                                  <input class="form-control" type="text" id="firstName" name="firstName"
                                           value="${m.firstName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="middleName" class="col-sm-4 control-label">Middle Name</label>
                                <div class="col-sm-8">
                                  <input class="form-control" type="text" id="middleName" name="middleName"
                                           value="${m.middleName}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="status" class="col-sm-4 control-label">Status</label>
                                <div class="col-sm-8">
                                  <input class="form-control" type="text" id="status" name="status"
                                           value="${m.status}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="membershipDate" class="col-sm-4 control-label">Member Date</label>
                                <div class="col-sm-8">
                                  <input class="form-control" type="text" id="membershipDate" name="membershipDate"
                                         value="${m.membershipDate}" placeholder="YYYY-MM-DD">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-4 control-label">Password</label>
                                <div class="col-sm-8">
                                  <input class="form-control" type="password" id="psswd" name="password"
                                           value="${m.password}">
                                </div>
                            </div>
                                <br>
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-6">
                                  <button type="submit" class="btn btn-primary">Update Member Data</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-sm-6">
                        <br>
                        <br>
                        <h1 class="text-primary text-center"><i class="fa fa-id-card-o fa-5x"></i></h1>
                    </div
                </div>
                           
                <div class="row">
                    <div class="col-sm-12 col-sm-offset-1">
                        <br>
                        <h4>View Transaction History From</h4>
                        <form action="ShowPurchases" method="post" class="form-inline">
                            <div class="form-group">
                                <label for="month">Month</label>
                                <input type="text" class="form-control" id="month" name="month" placeholder="">
                            </div>
                            <div class="form-group">
                                <label for="day">Day</label>
                                <input type="text" class="form-control" id="day" name="day" placeholder="">
                            </div>
                            <div class="form-group">
                                <label for="year">Year</label>
                                <input type="text" class="form-control" id="year" name="year" placeholder="">
                            </div>
                            <button type="submit" class="btn btn-success">View Transactions</button>
                        </form>  
                    </div>
                </div>
                <br>
                </div>
                <div>
                    <br>
                    <h5>
                        <a href="/ClubDB_Arcelo">&nbsp;&nbsp;&nbsp;<i class="fa fa-chevron-circle-left"></i>&nbsp;Back to the Login Screen</a>
                    </h5>
                </div>              
            </div>
            <div class="alert alert-info" role="alert">
                ${msg}
            </div>
        </body>
    </c:if>
</html>