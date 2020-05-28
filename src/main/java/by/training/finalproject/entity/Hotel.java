package by.training.finalproject.entity;

import java.util.Objects;

public class Hotel {
    private int id;
    private String name;
    private int stars;
    private Address address;

    public Hotel(int id, String name, int stars, Address address) {
        this.id = id;
        this.name = name;
        this.stars = stars;
        this.address = address;
    }

    public Hotel(Hotel hotel) {
        this.id = hotel.id;
        this.name = hotel.name;
        this.stars = hotel.stars;
        this.address = new Address(hotel.address);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Hotel hotel = (Hotel) o;
        return id == hotel.id &&
                stars == hotel.stars &&
                Objects.equals(name, hotel.name) &&
                Objects.equals(address, hotel.address);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + stars;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                ", address=" + address +
                '}';
    }
}
