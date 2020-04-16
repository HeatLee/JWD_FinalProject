package by.training.finalproject.factory;

import by.training.finalproject.service.UserService;
import by.training.finalproject.service.impl.UserServiceImpl;

public enum ServiceFactory {
    INSTANCE;

    public UserServiceImpl geUserService() {
        return UserServiceImpl.getInstance();
    }
}
