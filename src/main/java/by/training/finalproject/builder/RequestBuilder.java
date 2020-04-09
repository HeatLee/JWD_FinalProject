package by.training.finalproject.builder;

import by.training.finalproject.entity.Address;
import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.User;

import java.util.Date;

public class RequestBuilder {

    private int id;
    private int capacity;
    private Date checkIn;
    private Date departure;
    private int stars;
    private Address address;
    private User reservationUser;

    public RequestBuilder buildRequestId(int id) {
        this.id = id;
        return this;
    }

    public RequestBuilder buildCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public RequestBuilder buildCheckInDate(Date date) {
        this.checkIn = (Date) date.clone();
        return this;
    }

    public RequestBuilder buildDepartureDate(Date date) {
        this.departure = (Date)date.clone();
        return this;
    }

    public RequestBuilder buildStars(int stars) {
        this.stars = stars;
        return this;
    }

    public RequestBuilder buildAddress(Address address) {
        this.address = new Address(address);
        return this;
    }

    public RequestBuilder buildReservationUser(User user) {
        this.reservationUser = new User(user);
        return this;
    }

    public Request build() {
        return new Request(id, capacity, checkIn, departure, stars, address, reservationUser);
    }

}
