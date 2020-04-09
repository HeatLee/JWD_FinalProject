package by.training.finalproject.validator;

import by.training.finalproject.exception.ValidatorException;

public interface Validator<T> {
    void validate(T t) throws ValidatorException;
}
