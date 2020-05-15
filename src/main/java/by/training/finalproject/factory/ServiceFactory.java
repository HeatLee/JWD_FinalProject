package by.training.finalproject.factory;

import by.training.finalproject.service.*;
import by.training.finalproject.service.impl.*;

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
    public RoomService getRoomService() {
        return RoomServiceImpl.getInstance();
    }
}
