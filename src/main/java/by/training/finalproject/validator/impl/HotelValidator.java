package by.training.finalproject.validator.impl;

import by.training.finalproject.entity.Hotel;
import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.validator.Validator;

public class HotelValidator implements Validator<Hotel> {
    @Override
    public void validate(Hotel hotel) throws ValidatorException {
        isValidStarsAmount(hotel.getStars());
    }

    private void isValidStarsAmount(int stars) throws ValidatorException {
        if (stars > 5 || stars <= 0) {
            throw new ValidatorException("Invalid amount of stars");
        }
    }
}
