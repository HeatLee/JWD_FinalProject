package by.training.finalproject.validator.impl;


import by.training.finalproject.entity.Request;
import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.validator.Validator;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class RequestValidator implements Validator<Request> {
    @Override
    public void validate(Request request) throws ValidatorException {
        isValidCapacity(request.getCapacity());
        isValidDates(request.getCheckIn(), request.getDeparture());
        isValidStars(request.getStars());
    }

    private void isValidCapacity(int capacity) throws ValidatorException {
        if (capacity <= 0) {
            throw new ValidatorException("Invalid capacity value");
        }
    }

    private void isValidDates(LocalDate checkIn, LocalDate departure) throws ValidatorException {
        if (checkIn.isBefore(LocalDate.now())) {
            throw new ValidatorException("Invalid check in date");
        }
        if (departure.isBefore(checkIn)) {
            throw new ValidatorException("Invalid departure date");
        }
    }

    private void isValidStars(int stars) throws ValidatorException{
        if (stars <= 0) {
            throw new ValidatorException("Invalid amount of stars");
        }
    }
}
