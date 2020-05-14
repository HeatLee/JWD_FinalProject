package by.training.finalproject.service;

import by.training.finalproject.entity.User;
import by.training.finalproject.exception.ServiceException;

public interface UserService {
    User signIn(User user) throws ServiceException;

    void signUp(User user) throws ServiceException;

    void editUser(User user) throws ServiceException;

    String getPasswordByLogin(String login) throws ServiceException;
}
