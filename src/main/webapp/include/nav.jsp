<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="controller?command=INDEX_PAGE">Bookin</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav"
                aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>
        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="nav_bar.language"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <form action="controller" method="get">
                            <button type="submit" class="dropdown-item" name="language"
                                    value="ru">
                                Ру
                            </button>
                        </form>
                        <form action="controller" method="get">
                            <button type="submit" class="dropdown-item" name="language"
                                    value="en">
                                En
                            </button>
                        </form>
                    </div>
                </li>
                <li class="nav-item active"><a href="controller?command=INDEX_PAGE" class="nav-link"><fmt:message key="nav_bar.home"/> </a></li>
                <c:if test="${user == null}">
                    <li class="nav-item cta"><a href="controller?command=SIGN_UP_PAGE"
                                                class="nav-link"><span><fmt:message key="nav_bar.sign_up"/></span></a></li>
                    <li class="nav-item cta"><a href="controller?command=SIGN_IN_PAGE"
                                                class="nav-link"><span><fmt:message key="nav_bar.sign_in"/></span></a></li>
                </c:if>
                <c:if test="${user != null and user.userRole == 'USER'}">
                    <li class="nav-link"><a href="controller?command=PROFILE_PAGE"><span>${user.login}</span></a></li>
                </c:if>
                <c:if test="${user != null and user.userRole == 'ADMIN'}">
                    <li class="nav-link"><a href="controller?command=ADMIN_PAGE"><span>${user.login}</span></a></li>
                </c:if>
                <c:if test="${user != null}">
                    <li class="nav-item cta"><a href="controller?command=SIGN_OUT"
                                                class="nav-link"><span><fmt:message key="nav_bar.sign_out"/></span></a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
