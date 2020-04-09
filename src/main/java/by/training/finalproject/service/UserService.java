package by.training.finalproject.service;

import by.training.finalproject.entity.User;
import by.training.finalproject.exception.ServiceException;

public interface UserService {

    User getUserByLogin(String login) throws ServiceException;

    User getUserByEmail(String email) throws ServiceException;
}
