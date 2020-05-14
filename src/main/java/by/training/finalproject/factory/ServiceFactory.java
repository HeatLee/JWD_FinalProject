package by.training.finalproject.factory;

import by.training.finalproject.service.AddressService;
import by.training.finalproject.service.RequestService;
import by.training.finalproject.service.UserService;
import by.training.finalproject.service.impl.AddressServiceImpl;
import by.training.finalproject.service.impl.RequestServiceImpl;
import by.training.finalproject.service.impl.UserServiceImpl;

public enum ServiceFactory {
    INSTANCE;

    public UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public RequestService getRequestService() {
        return RequestServiceImpl.getInstance();
    }

    public AddressService getAddressService() {
        return AddressServiceImpl.getInstance();
    }
}
