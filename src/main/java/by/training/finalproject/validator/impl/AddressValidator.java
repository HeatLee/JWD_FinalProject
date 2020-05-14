package by.training.finalproject.validator.impl;

import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.validator.Validator;

public class AddressValidator implements Validator<AddressValidator> {
    @Override
    public void validate(AddressValidator address) throws ValidatorException {

    }

    private void isValidCountry(String country) throws ValidatorException {
        if (country.length() <= 0) {
            throw new ValidatorException("Invalid country name");
        }
    }

    private void isValidTown(String town) throws ValidatorException {
        if (town.length() <= 0) {
            throw new ValidatorException("Invalid town name");
        }
    }
}
