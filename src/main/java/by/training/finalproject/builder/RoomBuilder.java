package by.training.finalproject.builder;

import by.training.finalproject.entity.Hotel;
import by.training.finalproject.entity.Room;
import by.training.finalproject.entity.RoomStatus;

import java.math.BigDecimal;

public class RoomBuilder {
    private int id;
    private int capacity;
    private Hotel hotel;
    private BigDecimal price;
    private RoomStatus status;

    public RoomBuilder buildRoomId(int id) {
        this.id = id;
        return this;
    }

    public RoomBuilder buildCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public RoomBuilder buildHotel(Hotel hotel) {
        this.hotel = new Hotel(hotel);
        return this;
    }

    public RoomBuilder buildPrice(BigDecimal price) {
        this.price = BigDecimal.valueOf(price.doubleValue());
        return this;
    }

    public RoomBuilder buildStatus(RoomStatus status) {
        this.status = RoomStatus.getStatusById(status.getStatusId());
        return this;
    }

    public Room build() {
        return new Room(id, capacity, hotel, price, status);
    }
}
