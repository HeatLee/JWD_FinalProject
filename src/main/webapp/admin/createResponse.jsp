<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <title>Response creating</title>
    <jsp:include page="../include/header.jsp"/>
    <style>
        body, html {
            height: 100%;
        }
    </style>
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
<div class="container-fluid">
    <div class="d-flex align-items-center flex-column justify-content-center h-100">
        <label for="userRequest">User request</label>
        <div class="row" id="userRequest">
            <table class="table table-hover table-striped">
                <tbody>
                <tr>
                    <td>${request.checkIn}</td>
                    <td>${request.departure}</td>
                    <td>${request.capacity}</td>
                    <td>${request.address.country}</td>
                    <td>${request.address.town}</td>
                    <td>${request.stars}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="row row-cols-1 row-cols-md-3">
    <c:forEach items="${rooms}" var="room">
        <div class="col mb-4">
            <div class="card">
                <img src="https://i.ibb.co/6ZsKz8N/room-Blank.png" class="card-img-top" alt="Room">
                <div class="card-body">
                    <h5 class="card-title">Room in ${room.hotel.name}</h5>
                    <p>Hotels stars amount - ${room.hotel.stars}</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Capacity: ${room.capacity}</li>
                    <li class="list-group-item">Placed in ${room.hotel.address.country}.${room.hotel.address.town}</li>
                    <li class="list-group-item">Price: ${room.price}</li>
                </ul>
                <div class="card-body">
                    <form action="controller" method="post">
                        <input type="hidden" name="roomId" value="${room.id}">
                        <input type="hidden" name="requestId" value="${request.id}">
                        <button type="submit" class="btn btn-primary" name="command" value="APPLY_RESPONSE">
                            Apply response
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>
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
