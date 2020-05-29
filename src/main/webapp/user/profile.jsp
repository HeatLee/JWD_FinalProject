<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<!doctype html>
<html lang="en">
<head>
    <title><fmt:message key="profile_page.title"/></title>
    <jsp:include page="../include/header.jsp"/>
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
</head>
<body>
<jsp:include page="../include/nav.jsp"/>

<div class="container">
    <c:if test="${err != null}">
        <div class="row my-2 alert alert-warning alert-dismissible fade show" role="alert">
            <strong>${err}</strong>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>
    <div class="row my-2">
        <div class="col-lg-8 order-lg-2">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a href="#profile" data-target="#profile" data-toggle="tab" class="nav-link active">
                        <fmt:message key="profile_page.profile.tab.title"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#requests" data-target="#requests" data-toggle="tab"
                       class="nav-link"><fmt:message key="profile_page.request.tab.title"/></a>
                </li>
                <li class="nav-item">
                    <a href="#edit" data-target="#edit" data-toggle="tab" class="nav-link">
                        <fmt:message key="profile_page.edit.tab.title"/>
                    </a>
                </li>
            </ul>
            <div class="tab-content py-4">
                <div class="tab-pane active" id="profile">
                    <h5 class="mb-3">
                        <fmt:message key="profile_page.profile.tab.info.title"/>
                    </h5>
                    <div class="row">
                        <div class="col">
                            <h2><fmt:message key="profile_page.profile.tab.login_info.title"/> <h6>${user.login}</h6></h2>
                            <h2><fmt:message key="profile_page.profile.tab.email_info.title"/> <h6>${user.email}</h6></h2>
                        </div>
                    </div>
                </div>
                <div class="tab-pane" id="requests">
                    <table class="table table-hover table-striped">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><fmt:message key="profile_page.request.tab.table.head_title.check_in"/></th>
                            <th scope="col"><fmt:message key="profile_page.request.tab.table.head_title.departure"/></th>
                            <th scope="col"><fmt:message key="profile_page.request.tab.table.head_title.capacity"/></th>
                            <th scope="col"><fmt:message key="profile_page.request.tab.table.head_title.stars"/></th>
                            <th scope="col"><fmt:message key="profile_page.request.tab.table.head_title.country"/></th>
                            <th scope="col"><fmt:message key="profile_page.request.tab.table.head_title.town"/></th>
                            <th scope="col"><fmt:message key="profile_page.request.tab.table.head_title.action"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestList}" var="item">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.checkIn}</td>
                                <td>${item.departure}</td>
                                <td>${item.capacity}</td>
                                <td>${item.stars}</td>
                                <td>${item.address.country}</td>
                                <td>${item.address.town}</td>
                                <c:if test="${item.status.id == 3}">
                                    <td>
                                        <form action="controller" method="post">
                                            <input type="hidden" name="requestId" value="${item.id}"/>
                                            <button style="width: 53px" class="btn btn-danger" type="submit"
                                                    name="command" value="DELETE_USER_REQUEST">
                                                <i style='font-size:24px' class='fas'>&#xf2ed;</i>
                                            </button>
                                        </form>
                                    </td>
                                </c:if>
                                <c:if test="${item.status.id == 2}">
                                    <td>
                                        <form action="controller" method="get">
                                            <input type="hidden" name="requestId" value="${item.id}"/>
                                            <button class="btn btn-success" type="submit"
                                                    name="command" value="GET_USER_RESPONSE">
                                                <i style='font-size:24px' class='fas'>&#xf594;</i>
                                            </button>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane" id="edit">
                    <div class="container py-2">
                        <div class="row my-2" style="padding-left: 50px;">
                            <div class="col-lg-8 order-lg-1 personal-info">
                                <form class="needs-validation" role="form" action="controller" method="post" novalidate>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">
                                            <fmt:message key="profile_page.edit.tab.form.login.label"/>
                                        </label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="text" value="${user.login}" name="login" required/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">
                                            <fmt:message key="profile_page.edit.tab.form.email.label"/>
                                        </label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="email" value="${user.email}" name="email" required/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">
                                            <fmt:message key="profile_page.edit.tab.form.old_password.label"/>
                                        </label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="password" name="oldPassword"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">
                                            <fmt:message key="profile_page.edit.tab.form.password.label"/>
                                        </label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="password" name="password"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">
                                            <fmt:message key="profile_page.edit.tab.form.confirm_password.label"/>
                                        </label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="password" name="passwordConf"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-lg-9 ml-auto text-right">
                                            <button type="submit" class="btn btn-success" name="command"
                                                    value="CHANGE_USER">
                                                <i style='font-size:24px' class='far'>&#xf0c7;</i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4 order-lg-1 text-center">
            <img src="https://i.ibb.co/DgF4Rp4/default-user-icon-4.jpg" class="mx-auto img-fluid img-circle d-block"
                 alt="avatar">
        </div>
    </div>
</div>
<script type="text/javascript">
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

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>