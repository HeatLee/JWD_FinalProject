package by.training.finalproject.dao;

import by.training.finalproject.entity.User;
import by.training.finalproject.exception.DAOException;

public interface UserDAO<T> extends CommonDAO<T> {
    User readByLogin(String login) throws DAOException;

    User readByEmail(String email) throws DAOException;

    String readPasswordByLogin(String login) throws DAOException;
}
