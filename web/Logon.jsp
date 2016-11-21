
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://use.fontawesome.com/ea6070bed7.js"></script>
        <title>Club Logon</title>
    </head>
    <body class="container bg-primary">
        <br>
        <br>
        <h2 class="text-center">Welcome to the Club - Please Logon</h2>
        <h1 class="text-center"><i class="fa fa-user-circle-o fa-3x"></i></h1>
        <form action="ClubLogon" method="post" class="form-horizontal col-sm-6 col-sm-offset-3">
            <br>
            <br>
            <div>
                ${msg}
            </div>
            <br>
            <div class="form-group">
                <label for="userid" class="col-sm-2 control-label">User ID</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="userid" name="userid"
                           placeholder="User ID" value="${empty m.memid ? cookie.userid.value : m.memid}" >
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="Password" >
                </div>
            </div>
            <br>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">&nbsp; Logon &nbsp;</button>
                </div>
            </div>
        </form>
    </body>
</html>
