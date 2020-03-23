package by.training.finalproject.entity;

import java.util.Objects;

public class Address {
    private String country;
    private String town;

    public Address(String country, String town) {
        this.country = country;
        this.town = town;
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
        return Objects.equals(country, address.country) &&
                Objects.equals(town, address.town);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, town);
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", town='" + town + '\'' +
                '}';
    }
}
