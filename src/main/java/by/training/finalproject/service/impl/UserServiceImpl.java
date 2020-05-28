package by.training.finalproject.service.impl;

import by.training.finalproject.dao.UserDAO;
import by.training.finalproject.entity.User;
import by.training.finalproject.exception.DAOException;
import by.training.finalproject.exception.ServiceException;
import by.training.finalproject.exception.ValidatorException;
import by.training.finalproject.factory.DAOFactory;
import by.training.finalproject.service.UserService;
import by.training.finalproject.validator.Validator;
import by.training.finalproject.validator.impl.UserValidator;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private static final Validator<User> USER_VALIDATOR = new UserValidator();
    private static final UserDAO<User> USER_DAO = DAOFactory.INSTANCE.getUserDAO();
    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    private UserServiceImpl() {

    }

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void signUp(User user) throws ServiceException {
        try {
            USER_VALIDATOR.validate(user);
            if (USER_DAO.readByLogin(user.getLogin()) != null) {
                throw new ServiceException("Login is already taken.");
            }
            if (USER_DAO.readByEmail(user.getEmail()) != null) {
                throw new ServiceException("Email is already taken");
            }
            DAOFactory.INSTANCE.getUserDAO().add(user);
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
        try {
            User dbUser;
            if ((dbUser = USER_DAO.readByLogin(user.getLogin())) == null) {
                throw new ServiceException("User with such login does not exist");
            }
            if (!StringUtils.equals(dbUser.getPassword(), user.getPassword())) {
                throw new ServiceException("Wrong password");
            }
            user.setPassword(StringUtils.EMPTY);
            return dbUser;
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error. Sorry for that :(");
        }
    }

    @Override
    public void editUser(User user) throws ServiceException {
        try {
            USER_VALIDATOR.validate(user);
            if (USER_DAO.readByLogin(user.getLogin()) != null) {
                throw new ServiceException("Login is already taken.");
            }
            if (USER_DAO.readByEmail(user.getEmail()) != null) {
                throw new ServiceException("Email is already taken");
            }
            USER_DAO.update(user);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error. Sorry for that :(");
        } catch (ValidatorException e) {
            LOGGER.warn(e);
            throw new ServiceException("New user data is invalid");
        }
    }

    @Override
    public String getPasswordByLogin(String login) throws ServiceException {
        try {
            String password = USER_DAO.readPasswordByLogin(login);
            if (password == null) {
                throw new ServiceException("Invalid login");
            }
            return password;
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new ServiceException("Server error. Sorry for that :(");
        }
    }
}
