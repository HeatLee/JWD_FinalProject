package by.training.finalproject.service;

import by.training.finalproject.entity.User;
import by.training.finalproject.exception.ServiceException;

public interface UserService {
    User signIn(User user) throws ServiceException;

    User signUp(User user) throws ServiceException;
}
