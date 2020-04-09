package by.training.finalproject.service.impl;

import by.training.finalproject.entity.User;
import by.training.finalproject.exception.DAOException;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.factory.DAOFactory;
import by.training.finalproject.service.CommonService;
import by.training.finalproject.service.UserService;
import by.training.finalproject.validator.Validator;
import by.training.finalproject.validator.impl.UserValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService, CommonService<User> {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private static final Validator<User> VALIDATOR = new UserValidator();

    @Override

    public User add(User user) throws ServiceException {
        try {
            VALIDATOR.validate(user);
            DAOFactory.INSTANCE.getUserDAO().add(user);
            return user;
        } catch (ValidatorException | DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User update(User user) throws ServiceException {
        try {
            VALIDATOR.validate(user);
            DAOFactory.INSTANCE.getUserDAO().update(user);
            return user;
        } catch (ValidatorException | DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User delete(User user) throws ServiceException {
        try {
            VALIDATOR.validate(user);
            DAOFactory.INSTANCE.getUserDAO().delete(user);
            return user;
        } catch (ValidatorException | DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User getById(int id) throws ServiceException {
        try {
            User user = DAOFactory.INSTANCE.getUserDAO().readById(id);
            if (user == null) { //todo fix to Optional
                throw new ServiceException("Such index does not exist");
            }
            return user;
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        try {
            return DAOFactory.INSTANCE.getUserDAO().readAll();
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) throws ServiceException {
        try {
            User user = DAOFactory.INSTANCE.getUserDAO().readByLogin(login);
            if (user == null) { // todo fix to Optional
                throw new ServiceException("No such login");
            }
            return user;
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        try {
            User user = DAOFactory.INSTANCE.getUserDAO().readByEmail(email);
            if (user == null) { // todo fix to Optional
                throw new ServiceException("No such email");
            }
            return user;
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException(e);
        }
    }
}
