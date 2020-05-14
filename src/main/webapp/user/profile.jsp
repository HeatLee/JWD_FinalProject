<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <title>Profile</title>
    <jsp:include page="../include/header.jsp"/>
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
                    <a href="#profile" data-target="#profile" data-toggle="tab" class="nav-link active">Profile</a>
                </li>
                <li class="nav-item">
                    <a href="#requests" data-target="#requests" data-toggle="tab"
                       class="nav-link">Requests</a>
                </li>
                <li class="nav-item">
                    <a href="#edit" data-target="#edit" data-toggle="tab" class="nav-link">Edit user info</a>
                </li>
            </ul>
            <div class="tab-content py-4">
                <div class="tab-pane active" id="profile">
                    <h5 class="mb-3">User Profile</h5>
                    <div class="row">
                        <div class="col">
                            <h2>Login <h6>${user.login}</h6></h2>
                            <h2>Email <h6>${user.email}</h6></h2>
                        </div>
                    </div>
                </div>
                <div class="tab-pane" id="requests">
                    <table class="table table-hover table-striped">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Check In</th>
                            <th scope="col">Departure</th>
                            <th scope="col">Capacity</th>
                            <th scope="col">Stars</th>
                            <th scope="col">Country</th>
                            <th scope="col">Town</th>
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
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane" id="edit">
                    <div class="container py-2">
                        <div class="row my-2" style="padding-left: 50px;">
                            <div class="col-lg-8 order-lg-1 personal-info">
                                <form role="form" action="controller" method="post">
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">Login</label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="text" value="${user.login}" name="login"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">Email</label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="email" value="${user.email}"
                                                   name="email"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">Old Password</label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="password" name="oldPassword"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">Password</label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="password" name="password"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-3 col-form-label form-control-label">Confirm
                                            password</label>
                                        <div class="col-lg-9">
                                            <input class="form-control" type="password" name="passwordConf"/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-lg-9 ml-auto text-right">
                                            <button type="submit" class="btn btn-primary" name="command"
                                                    value="CHANGE_USER">
                                                Save Changes
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