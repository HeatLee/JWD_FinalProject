package by.training.finalproject.factory;

import by.training.finalproject.dao.impl.RoomDAOImpl;
import by.training.finalproject.dao.impl.UserDAOImpl;

public enum DAOFactory {
    INSTANCE;

    public UserDAOImpl getUserDAO() {
        return UserDAOImpl.getInstance();
    }

    public RoomDAOImpl getRoomDAO() {
        return RoomDAOImpl.getInstance();
    }
}
