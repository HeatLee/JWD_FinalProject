package by.training.finalproject.entity;

import java.util.Date;
import java.util.Objects;

public class Request {
    private int id;
    private int capacity;
    private Date checkIn;
    private Date departure;
    private int stars;
    private Address address;
    private User reservationUser;

    public Request(int id, int capacity, Date checkIn, Date departure, int stars, Address address, User reservationUser) {
        this.id = id;
        this.capacity = capacity;
        this.checkIn = checkIn;
        this.departure = departure;
        this.stars = stars;
        this.address = address;
        this.reservationUser = reservationUser;
    }

    public Request(Request request) {
        this.id = request.id;
        this.capacity = request.capacity;
        this.checkIn = (Date) request.checkIn.clone();
        this.departure = (Date) request.departure.clone();
        this.stars = request.stars;
        this.address = new Address(request.address);
        this.reservationUser = new User(request.reservationUser);
    }

    public User getReservationUser() {
        return reservationUser;
    }

    public void setReservationUser(User reservationUser) {
        this.reservationUser = reservationUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id &&
                capacity == request.capacity &&
                stars == request.stars &&
                Objects.equals(reservationUser, request.reservationUser) &&
                Objects.equals(checkIn, request.checkIn) &&
                Objects.equals(departure, request.departure) &&
                Objects.equals(address, request.address);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + capacity;
        result = 31 * result + stars;
        result = 31 * result + (reservationUser != null ? reservationUser.hashCode() : 0);
        result = 31 * result + (checkIn != null ? checkIn.hashCode() : 0);
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", checkIn=" + checkIn +
                ", departure=" + departure +
                ", stars=" + stars +
                ", address=" + address +
                ", reservationUserId=" + reservationUser +
                '}';
    }
}
