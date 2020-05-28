package by.training.finalproject.validator.impl;

import by.training.finalproject.entity.Room;
import by.training.finalproject.entity.RoomStatus;
import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.validator.Validator;

import java.math.BigDecimal;

public class RoomValidator implements Validator<Room> {

    @Override
    public void validate(Room room) throws ValidatorException {
        isValidCapacity(room.getCapacity());
        isValidPrice(room.getPrice());
        isValidRoomStatus(room.getStatus());
    }

    private void isValidCapacity(int capacity) throws ValidatorException {
        if (capacity <= 0) {
            throw new ValidatorException("Invalid capacity value");
        }
    }

    private void isValidPrice(BigDecimal price) throws ValidatorException {
        if (price.doubleValue() <= 0) {
            throw new ValidatorException("Invalid price value");
        }
    }

    private void isValidRoomStatus(RoomStatus status) throws ValidatorException{
        if (status.equals(RoomStatus.UNSUPPORTED_INDEX)) {
            throw new ValidatorException("Invalid status value");
        }
    }
}
