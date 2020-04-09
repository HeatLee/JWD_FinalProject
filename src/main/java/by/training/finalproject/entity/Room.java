package by.training.finalproject.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Room {
    private int id;
    private int capacity;
    private Hotel hotel;
    private BigDecimal price;
    private RoomStatus status;

    public Room(int id, int capacity, Hotel hotel, BigDecimal price, RoomStatus status) {
        this.id = id;
        this.capacity = capacity;
        this.hotel = hotel;
        this.price = price;
        this.status = status;
    }

    public Room(Room room) {
        this.id = room.id;
        this.capacity = room.capacity;
        this.hotel = new Hotel(room.hotel);
        this.price = BigDecimal.valueOf(room.price.doubleValue());
        this.status = RoomStatus.getStatusById(room.status.getStatusId());
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        if (id != room.id) return false;
        if (capacity != room.capacity) return false;
        if (!Objects.equals(hotel, room.hotel)) return false;
        if (!Objects.equals(price, room.price)) return false;
        return status == room.status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + capacity;
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
