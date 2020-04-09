package by.training.finalproject.builder;

import by.training.finalproject.entity.Address;

public class AddressBuilder {

    private int id;
    private String country;
    private String town;

    public AddressBuilder buildAddressId(int id) {
        this.id = id;
        return this;
    }

    public AddressBuilder buildCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressBuilder buildTown(String town) {
        this.town = town;
        return this;
    }

    public Address build() {
        return new Address(id, country, town);
    }
}
