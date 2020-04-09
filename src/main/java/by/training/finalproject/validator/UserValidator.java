package by.training.finalproject.validator;

import by.training.finalproject.entity.User;
import by.training.finalproject.entity.UserRole;
import by.training.finalproject.exception.ValidatorException;

public class UserValidator {

    private static final String LOGIN_REGEX = "^[a-zA-Z0-9_]{5,16}$";
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9]{8,32}$";
    private static final String EMAIL_REGEX =
            "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";

    public void validateUser(User user) throws ValidatorException{
        if (!(isValidLogin(user.getLogin())
                && isValidPassword(user.getPassword())
                && isValidEmail(user.getEmail())
                && isValidUserId(user.getUserId())
                && isValidUserRole(user.getUserRole()))) {
            throw new ValidatorException("Invalid user data");
        }
    }

    private boolean isValidLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    private boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    private boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    private boolean isValidUserId(int id) {
        return id >= 0;
    }

    private boolean isValidUserRole(UserRole userRole) {
        return !UserRole.UNSUPPORTED_INDEX.equals(userRole);
    }
}
