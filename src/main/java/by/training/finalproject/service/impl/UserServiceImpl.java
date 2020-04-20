package by.training.finalproject.service.impl;

import by.training.finalproject.dao.impl.UserDAOImpl;
import by.training.finalproject.entity.User;
import by.training.finalproject.exception.DAOException;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.factory.DAOFactory;
import by.training.finalproject.service.UserService;
import by.training.finalproject.validator.Validator;
import by.training.finalproject.validator.impl.UserValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService{
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private static final Validator<User> VALIDATOR = new UserValidator();
    private static final UserDAOImpl FACTORY = DAOFactory.INSTANCE.getUserDAO();

    private static final UserServiceImpl SERVICE;
    static {
        SERVICE = new UserServiceImpl();
    }

    private UserServiceImpl() {

    }

    public static UserServiceImpl getInstance() {
        return SERVICE;
    }

    @Override
    public User signUp(User user) throws ServiceException {
        try {
            VALIDATOR.validate(user);
            if (FACTORY.readByLogin(user.getLogin()) != null) {
                throw new ServiceException("Login is already taken.");
            }
            if (FACTORY.readByEmail(user.getEmail()) != null) {
                throw new ServiceException("Email is already taken");
            }
            DAOFactory.INSTANCE.getUserDAO().add(user);
            return user;
        } catch (ValidatorException e) {
            LOGGER.info(e);
            throw new ServiceException(e.getMessage());
        } catch (DAOException ex) {
            LOGGER.warn(ex);
            throw new ServiceException("Server error. Sorry for that :(");
        }
    }

    @Override
    public User signIn(User user) throws ServiceException {
       try{
           if ((user = FACTORY.readByLogin(user.getLogin())) == null) {
               throw new ServiceException("User with such login does not exist");
           }
           return user;
       } catch (DAOException e) {
           LOGGER.warn(e);
           throw new ServiceException("Server error. Sorry for that :(");
       }
    }
}
