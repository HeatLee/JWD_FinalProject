<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="include/header.jsp"/>
    <title>Bookin</title>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/starRating.css">
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
            <form action="controller" method="post">
                <div class="row" style="background-color: hsl(210, 10%, 23%); color: hsl(0, 0%, 100%);">
                    <div class="form-group col">
                        <label for="checkIn">Check In</label>
                        <div class="form-field">
                            <input id="checkIn" type="date" class="form-control" name="checkIn">
                        </div>
                    </div>
                    <div class="form-group col">
                        <label for="departure">Departure</label>
                        <div class="form-field">
                            <input id="departure" type="date" class="form-control" name="departure">
                        </div>
                    </div>
                    <div class="form-group col">
                        <div class="form-field">
                            <label for="country">Country</label>
                            <input id="country" type="text" class="form-control" name="country">
                        </div>
                    </div>
                    <div class="form-group col">
                        <label for="town">Town</label>
                        <div class="form-field">
                            <input id="town" type="text" class="form-control" name="town">
                        </div>
                    </div>
                    <div class="form-group col">
                        <label for="capacity">Capacity</label>
                        <div class="form-field">
                            <input id="capacity" type="number" class="form-control" name="capacity">
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
                                    Send Request
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
<script src="js/starRating.js"></script>
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