package by.training.finalproject.builder;

import by.training.finalproject.entity.Address;
import by.training.finalproject.entity.Hotel;

public class HotelBuilder {

    private int id;
    private String name;
    private int stars;
    private Address address;

    public HotelBuilder buildHotelId(int id) {
        this.id = id;
        return this;
    }

    public HotelBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public HotelBuilder buildStars(int stars) {
        this.stars = stars;
        return this;
    }

    public HotelBuilder buildAddress(Address address) {
        this.address = new Address(address);
        return this;
    }

    public Hotel build() {
        return new Hotel(id, name, stars, address);
    }
}
