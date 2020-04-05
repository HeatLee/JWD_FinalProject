package by.training.finalproject.builder;

import by.training.finalproject.entity.Address;

public class AddressBuilder extends AbstractBuilder<Address>{

    public AddressBuilder() {
        businessEntity = new Address();
    }

    public void buildAddressId(int id) {
        businessEntity.setId(id);
    }

    public void buildCountry(String country) {
        businessEntity.setCountry(country);
    }

    public void buildTown(String town) {
        businessEntity.setTown(town);
    }
}
