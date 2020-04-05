package by.training.finalproject.builder;

import by.training.finalproject.entity.Hotel;
import by.training.finalproject.entity.Room;
import by.training.finalproject.entity.RoomStatus;

import java.math.BigDecimal;

public class RoomBuilder extends AbstractBuilder<Room> {
    public RoomBuilder() {
        businessEntity = new Room();
    }

    public void buildRoomId(int id) {
        businessEntity.setId(id);
    }

    public void buildCapacity(int capacity) {
        businessEntity.setCapacity(capacity);
    }

    public void buildHotel(Hotel hotel) {
        businessEntity.setHotel(hotel);
    }

    public void buildPrice(BigDecimal price) {
        businessEntity.setPrice(price);
    }

    public void buildStatus(RoomStatus status) {
        businessEntity.setStatus(status);
    }
}
