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
    <title><fmt:message key="admin_page.title"/></title>
    <jsp:include page="../include/header.jsp"/>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <style type="text/css">
        .rating-box {
            margin: auto;
            display: flex;
            flex-direction: row;
            align-content: center;
        }

        .rating-box .ratings .fa {
            color: #ddda11;
            float: left;
            cursor: pointer;
        }
    </style>
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
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
            <ul class="nav nav-tabs" role="tablist" id="myTab">
                <li role="presentation" class="nav-item">
                    <a href="#request" role="tab"
                       data-target="#request" data-toggle="tab"
                       class="nav-link active"><fmt:message key="admin_page.request_list.tab_title"/></a>
                </li>
                <li role="presentation" class="nav-item">
                    <a href="#hotels" role="tab"
                       data-target="#hotels" data-toggle="tab"
                       class="nav-link"><fmt:message key="admin_page.hotel_list.tab_title"/></a>
                </li>
                <li role="presentation" class="nav-item">
                    <a href="#rooms" role="tab"
                       data-target="#rooms" data-toggle="tab"
                       class="nav-link"><fmt:message key="admin_page.room_list.tab_title"/></a>
                </li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="request">
                    <h5><fmt:message key="admin_page.request_list.table.title"/></h5>
                    <table class="table table-hover table-striped">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col"><fmt:message
                                    key="admin_page.request_list.table.check_in.column.title"/></th>
                            <th scope="col"><fmt:message
                                    key="admin_page.request_list.table.departure.column.title"/></th>
                            <th scope="col"><fmt:message
                                    key="admin_page.request_list.table.capacity.column.title"/></th>
                            <th scope="col"><fmt:message key="admin_page.request_list.table.stars.column.title"/></th>
                            <th scope="col"><fmt:message key="admin_page.request_list.table.country.column.title"/></th>
                            <th scope="col"><fmt:message key="admin_page.request_list.table.town.column.title"/></th>
                            <th scope="col"><fmt:message key="admin_page.request_list.table.user.column.title"/></th>
                            <th scope="col"><fmt:message key="admin_page.request_list.table.status.column.title"/></th>
                            <th scope="col"><fmt:message key="admin_page.request_list.table.action.column.title"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestList}" var="item">
                            <tr>
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
                                        <form action="controller" method="get">
                                            <input type="hidden" name="requestId" value="${item.id}"/>
                                            <button type="submit" class="btn btn-success" name="command"
                                                    value="CREATE_RESPONSE_PAGE">
                                                <fmt:message
                                                        key="admin_page.request_list.table.action.column.response"/>
                                            </button>
                                        </form>
                                    </td>
                                </c:if>
                                <c:if test="${item.status.id == 2}">
                                    <td>
                                        <form action="controller" method="post">
                                            <input type="hidden" name="requestId" value="${item.id}"/>
                                            <button type="submit" class="btn btn-danger" name="command"
                                                    value="DELETE_RESPONSE">
                                                <fmt:message key="admin_page.request_list.table.action.column.delete"/>
                                            </button>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div role="tabpanel" class="tab-pane" id="hotels">
                    <table class="table table-hover table-striped">
                        <thead class="thead-dark">
                        <tr>
                            <th><fmt:message key="admin_page.hotel_list.table.name.column.title"/></th>
                            <th><fmt:message key="admin_page.hotel_list.table.stars.column.title"/></th>
                            <th><fmt:message key="admin_page.hotel_list.table.country.column.title"/></th>
                            <th><fmt:message key="admin_page.hotel_list_list.table.town.column.title"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${hotelList}" var="item">
                            <tr>
                                <td>${item.name}</td>
                                <td>${item.stars}</td>
                                <td>${item.address.country}</td>
                                <td>${item.address.town}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addHotel">
                        <fmt:message key="admin_page.hotel_list.add_button"/>
                    </button>
                </div>
                <div role="tabpanel" class="tab-pane" id="rooms">
                    <form class="form-row" action="controller" method="get">
                        <div class="form-group col-md-9">
                            <div class="input-group" id="hotelSelect">
                                <select class="custom-select" name="hotelId">
                                    <c:forEach items="${hotelList}" var="item">
                                        <option value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </select>
                                <div class="input-group-append">
                                    <button class="btn btn-outline-secondary" type="submit" name="command"
                                            value="SHOW_HOTEL_ROOMS">
                                        <fmt:message key="admin_page.room_list.show_button"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-3">
                            <button type="button" class="btn btn-primary"
                                    data-toggle="modal" data-target="#addRoom">
                                <fmt:message key="admin_page.room_list.add_button"/>
                            </button>
                        </div>
                    </form>
                    <div class="row row-cols-1 row-cols-md-3">
                        <c:forEach items="${rooms}" var="room">
                            <div class="col mb-4">
                                <div class="card">
                                    <img src="https://i.ibb.co/6ZsKz8N/room-Blank.png" class="card-img-top" alt="Room">
                                    <div class="card-body">
                                        <h5 class="card-title">
                                            <fmt:message
                                                key="admin_page.room_list.room_info.pre_hotel_name"/> ${room.hotel.name}
                                        </h5>
                                        <p>
                                            <fmt:message key="admin_page.room_list.room_info.room_info.stars_amount"/>${room.hotel.stars}
                                        </p>
                                    </div>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">
                                            <fmt:message key="admin_page.room_list.room_info.room_info.capacity"/> ${room.capacity}
                                        </li>
                                        <li class="list-group-item">
                                            <fmt:message key="admin_page.room_list.room_info.room_info.address"/> ${room.hotel.address.country}.${room.hotel.address.town}</li>
                                        <li class="list-group-item">
                                            <fmt:message key="admin_page.room_list.room_info.room_info.price"/> ${room.price}
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Add new room modal -->
<div class="modal fade" id="addRoom" tabindex="-1" role="dialog" aria-labelledby="addRoomTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addRoomTitle"><fmt:message key="add_room.modal.title"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form class="needs-validation" action="controller" method="post" novalidate>
                <div class="modal-body">
                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <div class="input-group mb-1">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">$</span>
                                </div>
                                <input type="text" class="form-control" name="roomPrice" required/>
                            </div>
                        </div>
                        <div class="form-group col-md-8">
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" name="capacity" required>
                                <div class="input-group-append">
                                    <span style="align-self: center" class="input-group-text fas">&#xf406;</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <select class="custom-select" name="hotelId">
                        <c:forEach items="${hotelList}" var="item">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <fmt:message key="add_room.modal.close"/>
                    </button>
                    <button type="submit" class="btn btn-primary"
                            name="command" value="ADD_ROOM">
                        <fmt:message key="add_room.modal.apply"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Add new hotel modal -->
<div class="modal fade" id="addHotel" tabindex="-1" role="dialog" aria-labelledby="addHotelTitle" aria-hidden="true">
    <link rel="stylesheet" href="../css/starRating.css">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addHotelTitle">
                    <fmt:message key="add_hotel.modal.title"/>
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form class="needs-validation" action="controller" method="post" novalidate>
                <div class="modal-body">
                    <div class="form-row">
                        <div class="form-group col-md-9">
                            <label for="hotelName">
                                <fmt:message key="add_hotel.modal.input.name"/>
                            </label>
                            <input type="text" class="form-control" id="hotelName" name="hotelName" required>
                        </div>
                        <div class="form-group col-md-3">
                            <label>
                                <fmt:message key="add_hotel.modal.input.stars"/>
                            </label>
                            <div class="rating-box">
                                <div class="ratings">
                                    <span class="fa fa-star-o"></span>
                                    <span class="fa fa-star-o"></span>
                                    <span class="fa fa-star-o"></span>
                                    <span class="fa fa-star-o"></span>
                                    <span class="fa fa-star-o"></span>
                                </div>
                                <input type="hidden" id="rating-value" name="stars">
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="hotelCountry">
                                <fmt:message key="add_hotel.modal.input.country"/>
                            </label>
                            <input type="text" class="form-control" id="hotelCountry" name="country" required>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="hotelTown">
                                <fmt:message key="add_hotel.modal.input.town"/>
                            </label>
                            <input type="text" class="form-control" id="hotelTown" name="town" required>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <fmt:message key="add_hotel.modal.close"/>
                    </button>
                    <button type="submit" class="btn btn-primary" name="command" value="ADD_NEW_HOTEL">
                        <fmt:message key="add_hotel.modal.apply"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    document.readyState;
    const stars = document.querySelector(".ratings").children;
    const ratingValue = document.querySelector("#rating-value");
    let index;

    for (let i = 0; i < stars.length; i++) {
        stars[i].addEventListener("mouseover", function () {
            for (let j = 0; j < stars.length; j++) {
                stars[j].classList.remove("fa-star");
                stars[j].classList.add("fa-star-o");
            }
            for (let j = 0; j <= i; j++) {
                stars[j].classList.remove("fa-star-o");
                stars[j].classList.add("fa-star");
            }
        });
        stars[i].addEventListener("click", function () {
            ratingValue.value = i + 1;
            index = i;
        });
        stars[i].addEventListener("mouseout", function () {

            for (let j = 0; j < stars.length; j++) {
                stars[j].classList.remove("fa-star");
                stars[j].classList.add("fa-star-o");
            }
            for (let j = 0; j <= index; j++) {
                stars[j].classList.remove("fa-star-o");
                stars[j].classList.add("fa-star");
            }
        })
    }

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
        crossorigin="anonymous" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous" type="text/javascript"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>