package by.training.finalproject.validator.impl;

import by.training.finalproject.entity.UserRole;
import by.training.finalproject.exception.ValidatorException;
import org.junit.Test;

public class UserValidatorTest {

    @Test(expected = ValidatorException.class)
    public void isValidLoginNegativeTest() throws ValidatorException {
        UserValidator validator = new UserValidator();
        validator.isValidLogin("qwe");
    }

    @Test
    public void isValidLoginPositiveTest() throws  ValidatorException {
        UserValidator validator = new UserValidator();
        validator.isValidLogin("qwerty1");
    }

    @Test(expected = ValidatorException.class)
    public void isValidPasswordNegativeTest() throws ValidatorException {
        UserValidator validator = new UserValidator();
        validator.isValidPassword("123");
    }

    @Test
    public void isValidPasswordPositiveTest() throws ValidatorException {
        UserValidator validator = new UserValidator();
        validator.isValidPassword("123456g");
    }

    @Test(expected = ValidatorException.class)
    public void isValidEmailNegativeTest() throws ValidatorException {
        UserValidator validator = new UserValidator();
        validator.isValidEmail("@d.");
    }

    @Test(expected = ValidatorException.class)
    public void isValidUserIdNegativeTest() throws ValidatorException {
        UserValidator validator = new UserValidator();
        validator.isValidUserId(-1);
    }

    @Test(expected = ValidatorException.class)
    public void isValidUserRoleNegativeTest() throws ValidatorException {
        UserValidator validator = new UserValidator();
        validator.isValidUserRole(UserRole.UNSUPPORTED_INDEX);
    }
}