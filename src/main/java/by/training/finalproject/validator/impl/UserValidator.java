package by.training.finalproject.validator.impl;

import by.training.finalproject.entity.User;
import by.training.finalproject.entity.UserRole;
import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.validator.Validator;

public class UserValidator implements Validator<User> {

    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_]{5,16}$";
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9]{5,32}$";
    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_]+@(.+)$";

    @Override
    public void validate(User user) throws ValidatorException{
        isValidEmail(user.getEmail());
        isValidLogin(user.getLogin());
        isValidPassword(user.getPassword());
        isValidUserId(user.getUserId());
        isValidUserRole(user.getUserRole());
    }

    public void isValidLogin(String login) throws ValidatorException {
        if (!login.matches(LOGIN_REGEX) || login.isEmpty()) {
            throw new ValidatorException("Invalid login format");
        }
    }

    public void isValidPassword(String password) throws ValidatorException {
        if (!password.matches(PASSWORD_REGEX) || password.isEmpty()) {
            throw new ValidatorException("Invalid password format");
        }
    }

    public void isValidEmail(String email) throws ValidatorException {
        if (!email.matches(EMAIL_REGEX) || email.isEmpty()) {
            throw new ValidatorException("Invalid email format");
        }
    }

    public void  isValidUserId(int id) throws ValidatorException {
        if (!(id >= 0)) {
            throw new ValidatorException("Invalid id value");
        }
    }

    public void isValidUserRole(UserRole userRole) throws ValidatorException {
        if (UserRole.UNSUPPORTED_INDEX.equals(userRole)) {
            throw new ValidatorException("Invalid user role");
        }
    }
}
