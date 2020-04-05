package by.training.finalproject.dao;

import by.training.finalproject.entity.User;

public interface UserDAO extends CommonDAO<User> {

    User readByLogin(String login);

    User readByEmail(String email);
}
