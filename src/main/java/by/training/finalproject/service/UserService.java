package by.training.finalproject.service;

import by.training.finalproject.entity.User;

public interface UserService {

    User getUserByLogin(String login);

    User getUserByEmail(String email);
}
