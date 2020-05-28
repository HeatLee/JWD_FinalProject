package by.training.finalproject.factory;

import by.training.finalproject.dao.*;
import by.training.finalproject.dao.impl.*;
import by.training.finalproject.entity.*;

public enum DAOFactory {
    INSTANCE;

    public UserDAO<User> getUserDAO() {
        return UserDAOImpl.getInstance();
    }

    public RoomDAO<Room> getRoomDAO() {
        return RoomDAOImpl.getInstance();
    }

    public RequestDAO<Request> getRequestDAO() {
        return RequestDAOImpl.getInstance();
    }

    public AddressDAO<Address> getAddressDAO() {
        return AddressDAOImpl.getInstance();
    }

    public ResponseDAO<Response> getResponseDAO() {
        return ResponseDAOImpl.getInstance();
    }

    public HotelDAO<Hotel> getHotelDAO() {
        return HotelDAOImpl.getInstance();
    }
}
