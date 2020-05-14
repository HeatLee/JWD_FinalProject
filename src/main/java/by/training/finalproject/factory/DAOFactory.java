package by.training.finalproject.factory;

import by.training.finalproject.dao.*;
import by.training.finalproject.dao.impl.AddressDAOImpl;
import by.training.finalproject.dao.impl.RequestDAOImpl;
import by.training.finalproject.dao.impl.RoomDAOImpl;
import by.training.finalproject.dao.impl.UserDAOImpl;
import by.training.finalproject.entity.Address;
import by.training.finalproject.entity.Request;
import by.training.finalproject.entity.Room;
import by.training.finalproject.entity.User;

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
}
