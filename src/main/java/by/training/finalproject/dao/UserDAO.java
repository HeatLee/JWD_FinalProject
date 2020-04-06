package by.training.finalproject.dao;

import by.training.finalproject.entity.User;
import by.training.finalproject.exception.DAOException;

public interface UserDAO extends CommonDAO<User> {

    User readByLogin(String login) throws DAOException;

    User readByEmail(String email) throws DAOException;
}
