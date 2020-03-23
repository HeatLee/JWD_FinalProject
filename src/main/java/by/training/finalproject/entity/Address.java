package by.training.finalproject.entity;

import java.util.Objects;

public class Address {
    private int id;
    private String country;
    private String town;

    public Address(int id, String country, String town) {
        this.id = id;
        this.country = country;
        this.town = town;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getTown() {
        return town;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id &&
                Objects.equals(country, address.country) &&
                Objects.equals(town, address.town);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (town != null ? town.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                '}';
    }
}
