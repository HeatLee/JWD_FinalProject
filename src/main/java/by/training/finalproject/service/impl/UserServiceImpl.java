package by.training.finalproject.service.impl;

import by.training.finalproject.dao.impl.UserDAOImpl;
import by.training.finalproject.entity.User;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.service.CommonService;
import by.training.finalproject.service.UserService;
import by.training.finalproject.validator.UserValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService, CommonService<User> {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Override
    public User add(User user) throws ServiceException {
        return user;
    }

    @Override
    public User update(User user) {
        return user;
    }

    @Override
    public User delete(User user) {
        return user;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
