package by.training.finalproject.builder;

import by.training.finalproject.entity.Address;
import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.User;

import java.util.Date;

public class RequestBuilder extends AbstractBuilder<Request> {
    public RequestBuilder() {
        businessEntity = new Request();
    }

    public void buildRequestId(int id) {
        businessEntity.setId(id);
    }

    public void buildCapacity(int capacity) {
        businessEntity.setCapacity(capacity);
    }

    public void buildCheckInDate(Date date) {
        businessEntity.setCheckIn(date);
    }

    public void buildDepartureDate(Date date) {
        businessEntity.setDeparture(date);
    }

    public void buildStars(int stars) {
        businessEntity.setStars(stars);
    }

    public void buildAddress(Address address) {
        businessEntity.setAddress(address);
    }

    public void buildReservationUser(User user) {
        businessEntity.setReservationUser(user);
    }
}
