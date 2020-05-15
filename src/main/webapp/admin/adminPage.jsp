<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <title>Admin Page</title>
    <jsp:include page="../include/header.jsp"/>
</head>
<body>
<jsp:include page="../include/nav.jsp"/>
<c:if test="${err != null}">
    <div class="row my-2 alert alert-warning alert-dismissible fade show" role="alert">
        <strong>${err}</strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>
<div class="container">
    <div class="row">
        <div class="col">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a href="#request" data-target="#request" data-toggle="tab" class="nav-link active">Profile</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="request">
                    <h5>User requests</h5>
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
                            <th scope="col">User</th>
                            <th scope="col">Status</th>
                            <th scope="col">Action</th>
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
                                <td>${item.reservationUser.login}</td>
                                <td>${item.status.text}</td>
                                <c:if test="${item.status.id == 3}">
                                    <td>
                                        <form action="controller" method="post">
                                            <input type="hidden" name="requestId" value="${item.id}"/>
                                            <button type="submit" class="btn btn-primary" name="command"
                                                    value="CREATE_RESPONSE_PAGE">
                                                Response
                                            </button>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
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