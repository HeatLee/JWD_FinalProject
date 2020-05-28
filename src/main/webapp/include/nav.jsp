<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="controller?command=INDEX_PAGE">Bookin</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav"
                aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>
        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active"><a href="index.jsp" class="nav-link">Home</a></li>
                <c:if test="${user == null}">
                    <li class="nav-item cta"><a href="controller?command=SIGN_UP_PAGE"
                                                class="nav-link"><span>Sign Up</span></a></li>
                    <li class="nav-item cta"><a href="controller?command=SIGN_IN_PAGE"
                                                class="nav-link"><span>Sign In</span></a></li>
                </c:if>
                <c:if test="${user != null and user.userRole == 'USER'}">
                    <li class="nav-link"><a href="controller?command=PROFILE_PAGE"><span>${user.login}</span></a></li>
                </c:if>
                <c:if test="${user != null and user.userRole == 'ADMIN'}">
                    <li class="nav-link"><a href="controller?command=ADMIN_PAGE"><span>${user.login}</span></a></li>
                </c:if>
                <c:if test="${user != null}">
                    <li class="nav-item cta"><a href="controller?command=SIGN_OUT"
                                                class="nav-link"><span>Sign Out</span></a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
