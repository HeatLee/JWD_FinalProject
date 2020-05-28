<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <jsp:include page="include/header.jsp"/>
    <title>Bookin</title>
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
</head>
<body>
<jsp:include page="include/nav.jsp"/>
<c:if test="${err != null}">
    <div class="row my-2 alert alert-warning alert-dismissible fade show" role="alert">
        <strong>${err}</strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>
<c:if test="${user.userRole.id == 3}">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <form class="needs-validation" action="controller" method="post" novalidate>
                    <div class="row" style="background-color: hsl(210, 10%, 23%); color: hsl(0, 0%, 100%);">
                        <div class="form-group col">
                            <label for="checkIn"><fmt:message key="add_request_form.check_in.label"/></label>
                            <div class="form-field">
                                <input id="checkIn" type="date" class="form-control" name="checkIn" required>
                            </div>
                        </div>
                        <div class="form-group col">
                            <label for="departure"><fmt:message key="add_request_form.departure.label"/></label>
                            <div class="form-field">
                                <input id="departure" type="date" class="form-control" name="departure" required>
                            </div>
                        </div>
                        <div class="form-group col">
                            <div class="form-field">
                                <label for="country"><fmt:message key="add_request_form.country.label"/></label>
                                <input id="country" type="text" class="form-control" name="country" required>
                            </div>
                        </div>
                        <div class="form-group col">
                            <label for="town"><fmt:message key="add_request_form.town.label"/></label>
                            <div class="form-field">
                                <input id="town" type="text" class="form-control" name="town" required>
                            </div>
                        </div>
                        <div class="form-group col">
                            <label for="capacity"><fmt:message key="add_request_form.capacity.label"/></label>
                            <div class="form-field">
                                <input id="capacity" type="number" class="form-control" name="capacity" required>
                            </div>
                        </div>
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
                        <div class="col-lg align-self-end">
                            <div class="form-group">
                                <div class="form-field">
                                    <button type="submit" class="btn btn-primary" name="command"
                                            value="SEND_REQUEST">
                                        <fmt:message key="add_request_form.submit_button"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</c:if>
<script>
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
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>