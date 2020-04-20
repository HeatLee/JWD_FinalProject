<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign In</title>
    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/reg/style.css">
</head>
<body>

<div class="main">
    <!-- Sing in  Form -->
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <div class="row">
                        <a href="controller?command=INDEX_PAGE"><img
                                src="https://i.ibb.co/HCFjQgt/logo.jpg"
                                alt="Home"/></a>
                    </div>
                    <div class="row">
                        <a href="controller?command=SIGN_UP_PAGE"><img
                                src="https://i.ibb.co/YQf0qkK/sing-Up-Logo.jpg"
                                alt="Sing Up"/></a>
                    </div>
                </div>
                <div class="signin-form">
                    <h2 class="form-title">Sign in</h2>
                    <form method="POST" class="register-form" id="login-form" action="controller">
                        <div class="form-group">
                            <label for="login"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="login" id="login" placeholder="Your Login"/>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="password" id="password" placeholder="Password"/>
                        </div>
                        <%--                        <div class="form-group">--%>
                        <%--                            <input type="checkbox" name="remember-me" id="remember-me" class="agree-term"/>--%>
                        <%--                            <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember--%>
                        <%--                                me</label>--%>
                        <%--                        </div>--%>
                        <div class="form-group form-button">
                            <input type="submit" name="command" id="command" class="form-submit" value="SIGN_IN"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

</div>

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>