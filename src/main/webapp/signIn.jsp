<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <title>Sign In</title>
    <jsp:include page="include/header.jsp"/>
    <style>
        body, html {
            height: 100%;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="controller?command=INDEX_PAGE">Bookin</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav"
                aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span>
        </button>
        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a href="index.jsp" class="nav-link"><fmt:message key="nav_bar.home"/></a></li>
                <li class="nav-item cta"><a href="controller?command=SIGN_UP_PAGE"
                                            class="nav-link"><span><fmt:message key="nav_bar.sign_up"/></span></a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="bg-dark">
    <div class="container-fluid">
        <div class="d-flex align-items-center flex-column justify-content-center h-100  text-white">
            <form class="needs-validation" action="controller" method="POST" novalidate>
                <div>
                    <label for="login"><fmt:message key="sign_in.form.login"/> </label>
                    <input type="text" class="form-control form-control-lg" id="login" name="login" required>
                </div>
                <div>
                    <label for="password"><fmt:message key="sign_in.form.password"/> </label>
                    <input type="password" class="form-control form-control-lg" id="password" name="password" required>
                </div>
                <p> ${err} </p>
                <button class="btn btn-primary" type="submit" name="command" id="command" value="SIGN_IN">
                    <fmt:message key="sign_in.form.submit_button"/>
                </button>
            </form>
        </div>
    </div>
</div>

<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>