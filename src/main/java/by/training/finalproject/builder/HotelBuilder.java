package by.training.finalproject.builder;

import by.training.finalproject.entity.Address;
import by.training.finalproject.entity.Hotel;

public class HotelBuilder extends AbstractBuilder<Hotel> {

    public HotelBuilder() {
        businessEntity = new Hotel();
    }

    public void buildHotelId(int id) {
        businessEntity.setId(id);
    }

    public void buildName(String name) {
        businessEntity.setName(name);
    }

    public void buildStars(int stars) {
        businessEntity.setStars(stars);
    }

    public void buildAddress(Address address) {
        businessEntity.setAddress(address);
    }
}
