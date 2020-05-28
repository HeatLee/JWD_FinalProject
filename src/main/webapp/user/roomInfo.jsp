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
    <meta charset="utf-8">
    <title><fmt:message key="response_info.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body, html {
            height: 100%;
            background: #343A40;
        }

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
</head>
<body>
<jsp:include page="/include/nav.jsp"/>
<c:if test="${err != null}">
    <div class="row my-2 alert alert-warning alert-dismissible fade show" role="alert">
        <strong>${err}</strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>
<div class="container h-100">
    <div class="d-flex align-items-center flex-column justify-content-center h-100">
        <div class="card card-block w-50 mx-auto">
            <div class="row">
                <div class="col-md-6">
                    <label for="checkInDate">
                        <fmt:message key="response_info.info.check_in"/>
                    </label>
                    <input class="form-control" id="checkInDate" type="date"
                           value="${userResponse.request.checkIn}"
                           readonly/>
                </div>
                <div class="col-md-6">
                    <label for="departureDate">
                        <fmt:message key="response_info.info.departure"/>
                    </label>
                    <input class="form-control" id="departureDate" type="date"
                           value="${userResponse.request.departure}"
                           readonly/>
                </div>
            </div>
            <div class="row" style="align-content: center;">
                <div class="col-md-3">
                    <label for="hotelName">
                        <fmt:message key="response_info.info.hotel_name"/>
                    </label>
                    <input class="form-control" type="text" id="hotelName"
                           value="${userResponse.room.hotel.name}"
                           readonly>
                </div>
                <div class="col-md-4 align-self-end">
                    <label for="hotelStars">
                        <fmt:message key="response_info.info.stars"/>
                    </label>
                    <div class="rating-box" id="hotelStars">
                        <div class="ratings">
                            <span class="fa fa-star-o"></span>
                            <span class="fa fa-star-o"></span>
                            <span class="fa fa-star-o"></span>
                            <span class="fa fa-star-o"></span>
                            <span class="fa fa-star-o"></span>
                        </div>
                        <input type="hidden" id="rating-value" name="stars"
                               value="${userResponse.room.hotel.stars}">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-8 offset-md-4">
                    <label for="address">
                        <fmt:message key="response_info.info.address"/>
                    </label>
                    <input id="address" class="form-control" type="text"
                           value="">
                </div>
            </div>
            <div class="col-md-3 align-self-end">
                <label for="roomPrice">
                    <fmt:message key="response_info.info.room_price"/>
                </label>
                <div class="input-group mb-3">
                    <input class="form-control" type="text" id="roomPrice" value="${userResponse.room.price}"
                           readonly>
                    <div class="input-group-append">
                        <span class="input-group-text">$</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    document.addEventListener('readystatechange', event => {
        if (event.target.readyState === 'interactive') {
            let stars = document.querySelector(".ratings").children;
            let ratingValue = document.querySelector("#rating-value");
            for (let i = 0; i < ratingValue.getAttribute("value"); i++) {
                stars[i].classList.remove("fa-star-o");
                stars[i].classList.add("fa-star");
            }
            createFullAddress();
        }
    });

    function createFullAddress() {
        document.querySelector("#address").setAttribute("value", "${userResponse.room.hotel.address.country} \\ ${userResponse.room.hotel.address.town}")
    }
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
